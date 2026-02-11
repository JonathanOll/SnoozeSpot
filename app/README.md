# Documentation technique SnoozeSpot

## Présentation générale

SnoozeSpot est une appli de partage de lieu de sieste, permettant à des utilisateurs d'échanger dans un feed similaire au fonctionnement de twitter, mais également de poster des lieux de sieste sur une carte que les autres utilisateurs pourront commenter.

L'application est écrite en [Kotlin](https://kotlinlang.org/), en utilisant la syntaxe [Compose](https://developer.android.com/develop/ui/compose/kotlin?hl=fr). [Retrofit](https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet?hl=fr#0) est utilisé pour toute la partie appel à l'API, et une base de données [Room](https://developer.android.com/training/data-storage/room?hl=fr) est présente pour stocker des données en locale (surtout utilisé pour du cache).

Pour la partie appel à l'API, 

## Conventions de nommage

- Les noms des packages sont en flatcase
- Les noms des fichiers, classes et composants sont en PascalCase
- Les noms des variables et fonctions sont en camelCase

## Architecture globale

Diagramme de l'archi globale de l'appli
<img width="762" height="352" alt="Diagramme sans nom drawio" src="https://github.com/user-attachments/assets/f5263c6d-73d0-453f-8f52-52ba7d27d6c2" />

### MVVM

L'application de base sur une architecture de type MVVM (Modèle, Vue, Vue-Modèle), une approche visant à séparer les données d'une application (le modèle) de sa présentation (la vue), le vue-modèle agissant comme un pont entre ces 2 éléments.

/!\ à noter que la chaine est bien à respecter, en aucun cas une vue ne doit faire appel directement à un repository ou à la DB locale.

### Repositories

Un repository est une couche d'abstraction entre l'accès aux données et les ViewModels. Leur rôle est de garantir une transparence quant à l'appel à l'api et la DB locale. Ainsi, pour récupérer la liste des spots, un ViewModel fera un appel du type `SpotsRepository.getSpots()`, et le repository se chargera d'essayer de récupérer les données de l'API, en cas d'erreur de récupérer les données de la DB locale.

Le rôle du repository est également d'assurer la partie gestion d'erreur, afin d'éviter d'incessants try catchs dans les ViewModels. Pour ce, toutes les fonctions des repositories retournent non pas le type de la donnée demandée directement, mais une `Response<Type>`, basée sur le [design pattern Result](https://medium.com/@wgyxxbf/result-pattern-a01729f42f8c). Ainsi, dans un ViewModel, on vérifie si un appel a fonctionné en faisant `response.isSuccessful`, dans quel cas on pourra accéder à la donnée avec `response.body()`.

### View

Une vue est donc la partie visuelle, aka la "présentation", elle représente ce avec quoi l'utilisateur interagit pour utiliser l'app.

Pour créer une nouvelle vue, créer le <page>Screen et <page>ViewModel (cf. partie Fichiers), la structure de ces fichiers doit être la suivante : 

#### \<page\>Screen

```Kotlin
... # package & imports

@Destination
@Composable
fun <page>Screen(
  navigator: DestinationsNavigator, // Permet la navigation entre les écrans
  scaffoldController: ScaffoldController, // Permet de gérer la topbar/bottombar à afficher sur l'écran
  modifier: Modifier = Modifier,
  vm: <page>ViewModel = viewModel(), // Inclus par android via l'injection de dépendance
) {
  LaunchedEffect(true) {
    scaffoldController.topBar.value = { BackTopBar(navigator) } // Ou autre, voir partie "Quelques éléments"
    scaffoldController.showBottomBar.value = <true/false>
  }

  val <data>: <DataType> by vm.<data>.collectAsState() // Permet de reconstruire les composants qu'il faut lors du changement d'état de <data> dans le ViewModel
  ... // collecte d'autres données du ViewModel

  // SI BESOIN
  LaunchedEffect(true) {
    vm.<fetchData>() // appel automatique au ViewModel, ex: pour lancer un appel api qui charge les données
  }

   ... // Le reste du composant
}
```

#### \<page\>ViewModel

```Kotlin
... # package & imports

class <page>ViewModel: ViewModel() {
  private val _data = MutableStateFlow<Type>(<default_value>) // variable que le ViewModel va mettre à jour 
  val data = _data.asStateFlow() // variable que la vue va récupérer et observer

  fun fetchData() { # exemple d'appel à un repository
    viewModelScope.launch {
      val response = DataRepository.fetchData()
      if (response.isSuccessful)
        _data.update { response.body() }
      else
        Toaster.instance.toast(R.string.could_not_fetch) // voir partie "Quelques éléments"
    }
  }
}
```


Repositories, viewmodels, view, Toaster

## Gestion de projet

Git, PR, Bitrise, render, trello

## Fichiers 

```
app/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── iut/
│           │       └── fauryollivier/
│           │           └── snoozespot/
│           │               ├── api/   # Toute la partie appel à l'API, utilisée en principe uniquement par les repositories
│           │               │   └── data/
│           │               │       ├── AuthInterceptor.kt
│           │               │       ├── NetworkDataSource.kt
│           │               │       └── SnoozeSpotApi.kt
│           │               │   └── generated/
│           │               │       ...   # Tout le code généré par OpenAPI à partir de la doc pour les appels API
│           │               ├── app/
│           │               │   ├── components/
│           │               │   │   └── ...   # Un ensemble de composants utilisés un peu partout dans l'app (ex: ExpandableImage)
│           │               │   ├── pages/
│           │               │   │   └── <page>/
│           │               │   │       ├── <page>Screen.kt   # La view cet écran
│           │               │   │       ├── <page>ViewModel.kt   # le ViewModel de cet écran
│           │               │   │       ├── components/   # Un ensemble de components utilisé uniquement par cet écran (et éventuellement ses sous-écrans)
│           │               │   │       │   └── ...
│           │               │   │       └── <subpage>/
│           │               │   │       │   ├── <subpage>Screen.kt   # La view du sous-écran
│           │               │   │       │   └── <subpage>ViewModel.kt   # Le ViewModel de ce sous écran (absent s'il n'en a pas)
│           │               │   └── ScaffoldController.kt   # Permet de gérer la TopBar/BottomBar à afficher sur chaque écran
│           │               ├── datastore/
│           │               │   └── LocalStorage.kt   # Sert à stocker le token et l'user connecté
│           │               ├── MainActivity.kt   # Initialisation de certains composants, mise en place du toaster...
│           │               ├── repositories/   # Couche d'abstraction permettant l'accès aux données de l'api depuis les ViewModels
│           │               │   └── ...
│           │               ├── room/   # Database locale
│           │               │   ├── AppDatabase.kt
│           │               │   ├── dao/
│           │               │   │   └── ...
│           │               │   ├── overridemodels/   # Modèles écrits à la main (souvent repris de ceux générés par OpenAPI) car le générateur ne permet pas d'obtenir le résultat cherché
│           │               │   │   └── ...
│           │               │   └── RoomJsonConverters.kt
│           │               ├── ui/
│           │               │   └── theme/
│           │               │       ├── Color.kt
│           │               │       ├── Theme.kt
│           │               │       └── Type.kt
│           │               └── utils/   # Elements utiles à plusieurs endroits qui n'ont pas leur place ailleurs
│           │                   ├── ErrorMessage.kt
│           │                   ├── GoogleAuthHelper.kt
│           │                   ├── Toaster.kt
│           │                   ├── UiEvent.kt
│           │                   └── Utils.kt
│           └── res/
│               ├── drawable/
│               │   └── ...   # Les images
│               ├── raw/
│               │   └── ...   # Animations & certificat SSL auto-généré pour le dev
│               └── values/
│                   └── ...   # Infos sur le thème de l'app, traductions
├── ...
└── local.properties   # Pas gitté car contient des clés d'API, demander aux autres devs pour l'avoir
```

## Quelques éléments

### Traductions

TODO

### Topbar/bottombar

TODO

### Toaster

TODO

### Hand-written API routes

TODO

## Développement en local

adb reverse, certificat auto-généré, build-variant

## Appel à l'API
