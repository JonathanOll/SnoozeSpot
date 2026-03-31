# Documentation technique SnoozeSpot

## Présentation générale

SnoozeSpot est une appli de partage de lieu de sieste, permettant à des utilisateurs d'échanger dans un feed similaire au fonctionnement de twitter, mais également de poster des lieux de sieste sur une carte que les autres utilisateurs pourront commenter.

L'application est écrite en [Kotlin](https://kotlinlang.org/), en utilisant la syntaxe [Compose](https://developer.android.com/develop/ui/compose/kotlin?hl=fr). [Retrofit](https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet?hl=fr#0) est utilisé pour toute la partie appel à l'API, et une base de données [Room](https://developer.android.com/training/data-storage/room?hl=fr) est présente pour stocker des données en locale (surtout utilisé pour du cache).

Pour la partie appel à l'API, retrofit requiert normalement l'écriture d'un interface kotlin déclarant chaque route avec les types de données en entrée et en sortie. Cette partie étant peu intéressante et très répétitive, elle est automatique sur SnoozeSpot. Une documentation yaml de l'api est générée automatiquement via IntelliJ, et l'app va chercher cette documentation pour générer les data class et les interfaces Retrofit nécessaires.

## Gestion de projet

### Github

SnoozeSpot utilise un repo github pour tout ce qui est de la gestion de version du code. En tout temps, deux branches sont présentes : la branche main (correspondant à la release) et la branche beta. Il est absoluement essentiel que ces deux branches restent saines, dans le sens ou les builds (front et back) doivent fonctionner pour pouvoir être rendu disponibles aux utilisateurs.

Lorsqu'un développeur veut travailler sur une nouvelle fonctionnalité, il crée une nouvelle branche en se basant sur main, et push les changements une fois terminé.

La branche main est protégée et ne peut être modifié que via la création d'une [PR](https://docs.github.com/fr/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests). 

Fonctionnement d'une PR
<img width="850" height="452" alt="GitHub-Pull-request-flow" src="https://github.com/user-attachments/assets/60c53c34-4231-4232-8501-186b5d8da6f2" />

Lors de la création d'une PR, il est demandé au développeur de remplir certaines informations dans le commentaire de cette dernière. Ces informations seront utiles aux autres développeurs pour saisir rapidement les changements apportés et permet également de traquer la source d'apparition d'un bug.

Une pull request ne peut être merge qu'à condition que le build bitrise fonctionne et qu'un autre développeur a approuvé les changements.

Une fois une PR créée, un workflow Github se lancera pour ajouter automatiquement des labels sur cette dernière. En cas de changements sur l'application (respectivement l'api), le tag "frontend" (resp. "backend") sera ajouté sans actions supplémentaires requises de la part du développeur.

### Bitrise

Bitrise est un outil de CI permettant de build l'application à chaque push sur la branche main, ainsi que la mise à disposition d'un lien de téléchargement pour les utilisateurs. On utilise également bitrise pour valider qu'une PR build bien, afin d'éviter d'introduire des changements qui cassent main.

### Render

Render est également un outil de CI, mais il nous permet cette fois de build l'API et de la mettre à disposition sur une URL fixe. À chaque push concernant l'api sur main le service déploiera une nouvelle instance de l'API sur une machine virtuelle créée à l'occasion, et joignable à l'url https://snoozespot.onrender.com/

À noter que le déploiement peut prendre une dizaine de minute, et qu'au bout d'un certain temps d'inactivité, l'api est éteinte, pour être redémarrée dès qu'un nouvel appel est détecté (le démarrage peut prendre une bonne minute, d'où le splashscreen plus long que d'ordinaire).

### Trello

Pour le suivi de développement, on utilise un Trello. Le board contient 7 listes :
- Future : une liste des développements à réaliser dans un futur plus ou moins proche (pas forcément de fonctionnalités claires, juste une idée globale)
- Ready : les développements prêts à être réalisés, les fonctionnalités requises sont plus précises
- Blocked : les développements déjà commencés, mais bloqués car en attente d'une autre fonctionnalité
- In Progress : les développements en cours
- To Review : les développements terminés dont la PR est créée, mais pas encore merge
- Done : les développements terminés, et dont les PRs ont été merge
- Tools : une simple liste des liens pour différents éléments du projet (doc, repo git, ...)

Chaque tâche devra être agrémentée de tags pour la caractériser : des tags "front", "api" si concerné, et un tag ["story point"](https://www.atlassian.com/fr/agile/project-management/estimation), un nombre correspondant à la difficulté/temps estimé pour cette tâche.

## Installation du poste

- Installer Android Studio
- Installer IntelliJ
- Cloner le repo `git clone https://github.com/JonathanOll/SnoozeSpot`
- Ouvrir le dossier /app/ avec Android Studio, et /api/ avec IntelliJ
- Sync gradle
- Dans la barre du haut, cliquer sur Edit Configurations
<img width="326" height="228" alt="image" src="https://github.com/user-attachments/assets/299b09eb-26cc-4f30-aa9b-63fea83e7e40" />

- Créer une nouvelle configuration
<img width="286" height="78" alt="image" src="https://github.com/user-attachments/assets/04b70054-ec6d-44aa-af3f-0889ab9a2932" />

- Tout en bas de la partie droite de la fenêtre, ajouter une task "Run Gradle Task"
<img width="880" height="688" alt="image" src="https://github.com/user-attachments/assets/137fb46e-32b5-4bfa-896c-c72db047e5ca" />

- Sélectionner le gradle project app:app, et saisir preBuild dans "Tasks", puis valider
<img width="367" height="302" alt="image" src="https://github.com/user-attachments/assets/82396dcf-f53a-4451-b555-60475ad07e62" />

- Placer cette tâche au dessus de "Gradle-aware Make", puis appliquer les changements
<img width="628" height="78" alt="image" src="https://github.com/user-attachments/assets/3c577aef-ff56-47de-974d-c900528a35b5" />

Le poste devrait maintenant être opérationnel

/!\ à noter qu'en local, chaque relancement de l'API requerra le relancement de l'app (voir partie Développement en local)

## Conventions de nommage

- Les noms des packages sont en flatcase
- Les noms des fichiers, classes et composants sont en PascalCase
- Les noms des variables et fonctions sont en camelCase
- Les tradcodes sont en snake_case

## Architecture globale

Diagramme de l'archi globale de l'appli
<img width="762" height="352" alt="Diagramme sans nom drawio" src="https://github.com/user-attachments/assets/f5263c6d-73d0-453f-8f52-52ba7d27d6c2" />

### MVVM

L'application de base sur une architecture de type MVVM (Modèle, Vue, Vue-Modèle), une approche visant à séparer les données d'une application (le modèle) de sa présentation (la vue), le vue-modèle agissant comme un pont entre ces 2 éléments.

/!\ à noter que la chaine est bien à respecter, en aucun cas une vue ne doit faire appel directement à un repository ou à la DB locale.

### Repositories

Un repository est une couche d'abstraction entre l'accès aux données et les ViewModels. Leur rôle est de garantir une transparence quant à l'appel à l'api et la DB locale. Ainsi, pour récupérer la liste des spots, un ViewModel fera un appel du type `SpotsRepository.getSpots()`, et le repository se chargera d'essayer de récupérer les données de l'API, en cas d'erreur de récupérer les données de la DB locale.

Le rôle du repository est également d'assurer la partie gestion d'erreur, afin d'éviter d'incessants try catchs dans les ViewModels. Pour ce, toutes les fonctions des repositories retournent non pas le type de la donnée demandée directement, mais une `Response<Type>`, basée sur le [design pattern Result](https://medium.com/@wgyxxbf/result-pattern-a01729f42f8c). Ainsi, dans un ViewModel, on vérifie si un appel a fonctionné en faisant `response.isSuccessful`, dans quel cas on pourra accéder à la donnée avec `response.body()`.

### View & ViewModel

Une vue est donc la partie visuelle, aka la "présentation", elle représente ce avec quoi l'utilisateur interagit pour utiliser l'app. Comme expliqué précédemment, un ViewModel est le "pont" entre les données et la vue.

Pour créer une nouvelle vue, créer le <page>Screen et <page>ViewModel (cf. partie Fichiers), la structure de ces fichiers doit être la suivante : 

#### \<page\>Screen

```Kotlin
... // package & imports

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

  fun fetchData() { // exemple d'appel à un repository
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

Tous les textes de l'application sont traduits en utilisant [le système de traductions d'Android](https://developer.android.com/studio/write/translations-editor?hl=fr). Ainsi, à l'ajout d'un nouveau texte, il devra être saisi dans chaque langue, comme décrit dans la documentation android.

Pour accéder à ses traductions, on utilise la classe R :

```Kotlin
@Composable
fun Composant(...) {
  Text(stringResource(R.string.example_trad_code))
}
```

### Topbar/bottombar

Comme mentionné dans la partie Architecture globale, chaque vue doit prendre en paramètre un ScaffoldController, permettant de gérer la topbar et la bottombar à afficher.

#### BottomBar

L'app utilise une unique bottomBar, le ScaffoldController permet donc de choisir de l'afficher ou non.

```Kotlin
@Composable
fun Composant(..., scaffoldController: ScaffoldController) {
  LaunchedEffect(true) {
    scaffoldController.showBottomBar.value = <true/false>
  }

  ...
}
```

#### TopBar

La topbar quant à elle possède plusieurs variantes. Toutes ces variantes sont disponibles dans app/components/TopBar.kt. Les topbars disponibles sont des topbars assez génériques (une topbar avec simplement le logo de l'app, une topbar avec un bouton retour, ...).

```Kotlin
@Composable
fun Composant(..., scaffoldController: ScaffoldController) {
  LaunchedEffect(true) {
    scaffoldController.topBar.value = { DefaultTopBar() }
  }

  ...
}
```

### Toaster

D'ordinaire, pour faire apparaitre un toast en Kotlin Compose, il faut avoir accès au Contexte, ce qui est le cas des vues, mais pas des ViewModels (sauf si on le passe en paramètre d'une fonction appelée par la vue, mais ce n'est pas une bonne pratique).

Pour pallier à ce problème, nous avons mis en place ce qu'on a décidé d'appeler un "Toaster", un singleton qui peut être appelé depuis n'importe où dans l'appli (notamment dans les ViewModels), possédant une méthode `toast(string)`. L'appel à cette méthode alimente un flux d'évènement (Plus précisemment un [MutableSharedFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-mutable-shared-flow/)), qui est observé à la racine de l'application pour afficher les toasts demandés.

Utilisation : 
```Kotlin
Toaster.instance.toast(R.string.example_toast)
```

### Gestion d'erreur

Cette partie traitera de la gestion d'erreur lors de l'appel à un repository (pas de try catch, on parle bien de lorsque la Response n'est pas successful).

On distingue deux types de failure : 

- #### Le cas où si l'appel échoue, l'écran n'est pas du tout utilisable 
  - (ex: un post ne charge pas sur la page détails)

  - Dans ce cas, il conviendra de faire apparaitre l'erreur en plein écran, avec un bouton en dessous permettant de relancer l'appel.

  - Pour l'affichage de l'erreur, il conviendra d'utiliser la classe ErrorMessage, contenant les types d'erreurs classiques, ainsi que l'id des tradcode correspondants

- #### Le cas où si l'appel échoue, on peut contionuer à utiliser l'écran normalement 
  - (ex: le like d'un post ne fonctionne pas)

  - Dans ce cas, il conviendra de simplement afficher un toast indiquant à l'utilisateur que l'action n'a pas pu être réalisée (voir partie Toaster)

### Routes API écrites à la main

Dans certains cas, le générateur de documentation OpenAPI ne permet pas de générer une documentation correcte pour certaines routes d'API (ex: routes gérant des uploads de fichiers).

Dans ce cas, il conviendra d'écrire les fonctions d'interfaces Retrofit à la main, dans le fichier /api/data/SnoozeSpotApi.kt, au sein de l'interface SnoozeSpotApi.

## Développement en local

/!\ Bien suivre la procédure décrite dans la partie "Installation du poste".

### Build-variants

Les builds variants sont accessibles sous Android Studio via ce menu :

<img width="349" height="544" alt="image" src="https://github.com/user-attachments/assets/e85a975a-1003-4d6d-8360-653066d47ca0" />

Il existe 3 types de builds : 
- development : pour le développement local
- beta : version beta de l'application
- production : version de l'application publiée et exploitable par de vrais utilisateurs

à noter que les 2 derniers types de builds sont configurés pour utiliser l'API déployée sur render, tandis que le type development utilisera l'api déployée en local (ip https://localhost:8080) (voir partie ADB Reverse)

### ADB reverse

Pour le développement en local, il est nécessaire que l'application puisse récupérer les données de l'API hébergée sur la machine du développeur. Pour cela, il convient de mettre en place du port forwarding du téléphone à la machine de développeur. Dans l'idée, cela revient à rediriger un port X du localhost du téléphone à un port Y de la machine du développeur (ex: aller sur localhost:8080 du téléphone peut faire appel au localhost:443 de la machine du développeur). 

- La première étape consiste à [activer l'USB debugging](https://developer.android.com/studio/debug/dev-options?hl=fr)
- La deuxieme étape consiste à installer [ADB](https://developer.android.com/tools/adb) sur la machine du développeur
- La troisième étape est de brancher le téléphone à la machine du développeur via un cable USB permettant le transfert de données
- La quatrièeme et dernière étape consiste à ouvrir un shell et taper `adb reverse tcp:8080 tcp:443`, avant d'accepter la demande qui devrait apparaitre sur le téléphone

### Certificat SSL auto-généré

Dans un soucis de sécurité avec Android, il a été nécessaire de générer un certificat SSL pour pouvoir contacter l'API en HTTPS. Pour cela, on utilise un outil lors du lancement de l'API qui va généré un certificat auto-signé. Le problème est maintenant qu'Android n'accepte pas les certificats auto-signés par défaut, il faut donc lui indiquer que ce certificat peut être utilisé sans risque. Alors, lors du lancement de l'API, on copie le certificat généré dans les fichiers de l'app pour qu'il soit inclus dans son build, et qu'Android accepte son utilisation.

C'est pour cette raison que dès lors qu'on relance l'API, il est nécessaire de recompiler l'app, sans quoi on ne pourra plus la contacter.
