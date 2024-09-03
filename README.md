# Notes App

The note app uses modern Android technologies, including Jetpack Compose, MVVM architecture, Room, and Hilt. This simple notes app allows users to add new notes, update existing ones, and delete notes.


## Features

1. Uses Room to save notes.
2. Implements dependency injection with Hilt.
3. Allows users to add new notes, update existing ones, and delete notes.
4. Uses Jetpack Compose for navigation and navigation transitions.



## Screenshot

|                                                                                                                         |                                                                                                               |                                                                                                                |
|-------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|
| ![home](https://github.com/user-attachments/assets/a6cba099-9318-4220-9bfe-e1b89532d1a1)                                | ![add note](https://github.com/user-attachments/assets/4456652b-edee-4470-b1e4-4c656258f998)                  | ![update note](https://github.com/user-attachments/assets/b0930211-8647-4e8c-a6e0-1c33dc7285ab)                |





## Package Structure

* [`data`](app/src/main/java/com/techuntried/notes/data): Contains room database, repository, and model classes.
* [`di`](app/src/main/java/com/techuntried/notes/di): Hilt modules.
* [`ui`](app/src/main/java/com/techuntried/notes/ui): UI layer of the app.
    * `nav`: Contains app navigation and destinations.
    * `screens,components`: Contains UI components.
    * `theme`: Material3 theme.


## Build With

[Kotlin](https://kotlinlang.org/):
As the programming language.

[Jetpack Compose](https://developer.android.com/jetpack/compose) :
To build UI.

[Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation) :
For navigation between screens.

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android) :
For injecting dependencies.

[Room](https://developer.android.com/jetpack/androidx/releases/room) :
To store and update notes.


## Installation

Simple clone this app and open in Android Studio.

