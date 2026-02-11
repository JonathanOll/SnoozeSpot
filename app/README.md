# Documentation technique SnoozeSpot

## Présentation générale

SnoozeSpot est une appli de partage de lieu de sieste, permettant à des utilisateurs d'échanger dans un feed similaire au fonctionnement de twitter, mais également de poster des lieux de sieste sur une carte que les autres utilisateurs pourront commenter.

L'application est écrite en Kotlin, en utilisant la syntaxe Compose. Retrofit est utilisé pour toute la partie appel à l'API, et une base de données Room est présente pour stocker des données en locale (surtout utilisé pour du cache).

## Architecture globale

Diagramme de l'archi globale de l'appli
<img width="762" height="352" alt="Diagramme sans nom drawio" src="https://github.com/user-attachments/assets/f5263c6d-73d0-453f-8f52-52ba7d27d6c2" />

### MVVM

L'application de base sur une architecture de type MVVM (Modèle, Vue, Vue-Modèle), une approche visant à séparer les données d'une application (le modèle) de sa présentation (la vue), le vue-modèle agissant comme un pont entre ces 2 éléments.

/!\ à noter que la chaine est bien à respecter, en aucun cas une vue ne doit faire appel directement à un repository ou à la DB locale.

### Repositories

Un repository est une couche d'abstraction entre l'accès aux données et les ViewModels. Leur rôle est de garantir une transparence quant à l'appel à l'api et la DB locale. Ainsi, pour récupérer la liste des spots, un ViewModel fera un appel du type `SpotsRepository.getSpots()`, et le repository se chargera d'essayer de récupérer les données de l'API, en cas d'erreur de récupérer les données de la DB locale.

Le rôle du repository est également d'assurer la partie gestion d'erreur, afin d'éviter d'incessants try catchs dans les ViewModels. Pour ce, toutes les fonctions des repositories retournent non pas le type de la donnée demandée directement, mais une `Response<Type>`, basée sur le [design pattern Result](https://medium.com/@wgyxxbf/result-pattern-a01729f42f8c). Ainsi, dans un ViewModel, on vérifie si un appel a fonctionné en faisant `response.isSuccessful`, dans quel cas on pourra accéder à la donnée avec `response.body()`.

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

## Développement en local

adb reverse, certificat auto-généré, build-variant

## Appel à l'API
