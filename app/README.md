# Documentation technique SnoozeSpot

## Présentation générale

SnoozeSpot est une appli de partage de lieu de sieste, permettant à des utilisateurs d'échanger dans un feed similaire au fonctionnement de twitter, mais également de poster des lieux de sieste sur une carte que les autres utilisateurs pourront commenter.

L'application est écrite en Kotlin, en utilisant la syntaxe Compose.

## Architecture globale

L'application de base sur une architecture de type MVVM (Modèle, Vue, Vue-Modèle), 

Repositories, viewmodels, view


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
