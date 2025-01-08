# Top Down Shooter

Welcome to **Top Down Shooter**, a game project built to apply the knowledge acquired throughout the semester in Developing an Android Mobile Application.

---

## Table of Contents
- [Project structure](#Projectstructure)
- [Application functionality list](#Applicationfunctionalitylist)
- [Data model](#Datamodel)
- [Project implementation](#Projectimplementation)
- [Technologies used](#Technologiesused)
- [Difficulties](#Difficulties)
- [Conclusions](#Conclusions)
- [License](#License)
  
---

## Project structure

The project is organized as follows:
### Entities
- Bullet
- Enemy

### Login
- LoginView
- LoginViewModel
- RegisterView
- RegisterViewModel

### User
#### Models
- User
#### Repositories
- UserRepository

### Views
- GameOverView
- GameScreenView
- GameView
- HighscoreView
- Home
- Joystick

### Others
- Highscore
- MainActivity
- Player
- SaveHighscore
  
---

## Application functionality list

- **Register:** Register an username, email account and set a password to create a user
- **Login:** Use your registered account and password
- **Start:** Press this button to start playing
- **Highscore:** Press this button to see the highscore of the people who play 
- **Movement:** Use the left joystick to move.
- **Aiming:** Use the right joystick to aim your weapon.
- **Shooting:** Works automatically when aiming.
- **Objectives:** Survive enemies to achieve the highest score!

---

## Data model


---

## Project implementation




---

## Technologies used

- **Programming Language(s):** Java and Kotlin
- **Program:** Android Studio
- **Libraries/Frameworks:** Jetpack Compose (including Navigation Component), Firebase (Authentication, Firestore Database) e Google services
- **Other Tools:** 
  
---

## Difficulties

During the development of our project, we encountered several technical challenges that required  patience and persistence.

Firstly, detecting touches on the joysticks presented significant challenges. 

The creation of the joysticks was also not a simple task. 

Another challenge was rotating the player according to the joystick direction. 

Finally, ensuring that the bullet fired in the correct direction the player was facing was particularly difficult. 

We had plans to associate the username with the score obtained. However, we only realized this requirement when the project was nearly complete. To address this, we would have needed to change the way the database stored the data. Given that we had already met the requirements for this part of the project, we decided to leave it as it was.

---

## Conclusions

---

## License 

Copyright 2025 Miguel Silva & Hugo Oliveira

Licensed under [MIT License](LICENSE)
