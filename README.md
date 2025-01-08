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

### Data Structure:
#### Bullet: 
- The Bullet class organizes its data to manage the position, direction, image, and collision detection of a bullet in a game. It has attributes to store the initial position (x, y), speed (speed), normalized direction (directionX, directionY), the bullet's image (bitmap), and a rectangle for collision detection (detectCollisions). The constructor initializes these attributes based on the provided parameters, while the update method updates the bullet's position and the collision rectangle as the bullet moves.

#### Enemy:
- The Enemy class organizes its data to manage the position, speed, direction, image, and collision detection of an enemy in a game. It has attributes for the position (x, y), speed (speed), screen boundaries (maxX, maxY, minX, minY), the enemy's image (bitmap), rotation angle (rotationAngle), direction (direction), and a rectangle for collision detection (detectCollision). The constructor initializes these attributes based on the screen dimensions and randomly sets the initial position and direction. The update method moves the enemy according to its direction and speed, updating the collision rectangle. If the enemy moves out of bounds, the resetPosition method repositions it randomly. The setRotationAngle method adjusts the rotation angle based on the direction, and getRotatedBitmap returns the rotated image.

#### Player:
- The Player class organizes its data to manage the position, rotation, image, speed, and collision detection of a player character in a game. It has attributes for the position (x, y), screen boundaries (maxX, maxY, minX, minY), the player's image (bitmap), rotation angle (rotationAngle), speed (speed), and a rectangle for collision detection (detectCollision). The constructor initializes these attributes based on the screen dimensions and sets the initial position to the center of the screen. The setRotation method calculates the rotation angle based on the direction vector. The update method ensures the player stays within screen boundaries and updates the collision rectangle. The getRotatedBitmap method returns the rotated image, and updateCollisionBounds updates the collision rectangle based on the rotated image.

#### Relationships between Entities
- Player and Bullet: The Player can create and fire Bullet instances. The bullets are directed based on the player's aim, and their movement is updated accordingly.
- Player and Enemy: The Player must avoid collisions with Enemy instances. The enemies move in various directions and can potentially collide with the player, causing damage or other game effects.
- Bullet and Enemy: Bullet instances fired by the player can collide with Enemy instances. When a bullet hits an enemy, it typically results in the enemy being destroyed or taking damage.

#### Data Storage
- In this project, data is stored in attributes within the Player, Enemy, and Bullet classes. Each class has attributes for position, image, speed, and collision detection. The Player class also stores the rotation angle and movement speed. The Enemy class includes direction and speed, while the Bullet class stores normalized direction and speed. These attributes are initialized in the constructors and updated in the update methods to reflect changes during the game.

#### Authentication
- The authentication method used in this project is the Email and password method from Firebase.
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

The development of the Top Down Shooter game project has been a comprehensive and rewarding experience, allowing us to apply and expand our knowledge in Android mobile application development. Throughout the project, we successfully implemented various functionalities, including user registration, login, and gameplay mechanics such as movement and aiming. 
Our data model effectively manages the interactions between the player, bullets, and enemies, ensuring a dynamic and engaging gameplay experience. The use of technologies like Java, Kotlin, Jetpack Compose, and Firebase has enabled us to create a robust and scalable application.
Despite facing several challenges, such as detecting joystick touches, rotating the player, and ensuring accurate bullet direction, we persevered and found solutions to these problems. These difficulties have not only tested our technical skills but also our problem-solving abilities and teamwork.
Although we were unable to fully integrate the username with the high score due to time constraints, we have met the core requirements of the project and delivered a functional and enjoyable game. This project has provided valuable insights and practical experience that will be beneficial for future endeavors in mobile application development.

---

## License 

Copyright 2025 Miguel Silva & Hugo Oliveira

Licensed under [MIT License](LICENSE)
