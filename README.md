# MindGames
### Creator: Eric Carpo

> Welcome to MindGames! This is a collection of games that are designed to test your memory and any additional cognitive abilities. 
> The games are simple, but they can be quite challenging. The goal is to improve your cognitive skills and have fun at the same time.
>
## How to Run

1. Clone the repository to your local machine.
2. Open the project in your favorite IDE.
3. Run the `Main` class in the `src` folder.
4. Follow the instructions in the console to play the games!
5. Enjoy!

## Games
>**Classic Mode**:
- You will have 10 tries to figure out the four-digit number combination I am thinking of with minimal hints! 
- Each guess should consist of any digits from 0-7 (none of my combos use 8 or 9!).
- If you're stumped, we can provide a hint that gives you one number in its correct location
> 
>**Single Mind**:
- You will have 10 tries to figure out the four-digit number combination I am thinking of. 
- Once you've made a guess, I will give you visual hints to help you figure out the correct combination.
>
>**Double Trouble**:
- If you're up for the challenge, try out Double Trouble! It's like Single Mind except with TWO combinations. Double the combo, double the fun! 
- Here you will have to guess two combinations at the same time, but you will have 12 tries to do so.

## Background
Using Java as the primary coding language, I created this project to help people improve their cognitive skills. I believe that games are a great way to exercise your brain and have fun at the same time.  

Additionally, I wanted to implement things in this project that would help me improve and showcase my coding skills. I learned a lot about Java and object-oriented programming while working on this project. I hope you enjoy playing these games as much as I enjoyed creating them.

## Architecture & Implementation
### Overview
The MindGames project is structured into several key components, each responsible for different aspects of the application. The main components are:
- **GameApp**: The entry point of the application, responsible for displaying the main menu and handling user input. 
- **Game Modes**: Different game modes such as Classic Mode, Single Mind Mode, and Double Trouble Mode, each implemented in separate classes.
- **Utilities**: Helper classes and methods used throughout the application, such as random number generation and input validation.
### Components
1. **GameApp**:
   - The GameApp class contains the main method and the main menu logic.
It handles user input and navigates to the appropriate game mode based on the user's choice.
2. **Game Modes**:  
   - **ClassicModeGame**: Implements the logic for the Classic Mode game.
   - **SingleMindGame**: Implements the logic for the Single Mind Mode game.
   - **DoubleCombinationGame**: Implements the logic for the Double Trouble Mode game.
   - Each game mode class contains methods for initializing the game, processing user guesses, and providing hints.
3. **Utilities**:  
   - **RandomNumberGenerator**: A utility class for generating random numbers.
   - **InputValidator**: A utility class for validating user input.
   - **Logger**: Used for logging errors and important events.
### Implementation Details
- **Main Menu**: The main menu is displayed when the application starts. It provides options for the user to select a game mode or view instructions.
- **Game Logic**: Each game mode has its own logic for handling user guesses, providing hints, and determining the game outcome.
- **Error Handling**: The application includes error handling to manage invalid inputs and network failures gracefully.
- **Testing**: The project includes unit tests to ensure the correctness of the game logic and other components.

## Scalability & Future Improvements
### Scalability
- **Modular Design**: The application is designed with a modular architecture, making it easy to add new game modes or features without affecting existing functionality.
- **Performance Optimization**: Optimize the performance of the game logic and user interface to handle a larger number of users and more complex game scenarios.
- **Database Integration**: Integrate a database to store user scores, game progress, and other data, allowing for persistent storage and retrieval of information.
 ### Future Improvements
 - **Additional Game Modes**: Introduce new game modes to provide more variety and challenges for users.
- **User Interface Enhancements**: Improve the user interface with graphical elements and experiences to make the game more engaging.
- **Multiplayer Support**: Add support for multiplayer games, allowing users to compete against each other in real-time.
- **Mobile Version**: Develop a mobile version of the game to reach a wider audience and provide a more convenient gaming experience.
- **AI Opponents**: Implement AI opponents to provide a challenging experience for single players.
- **Localization**: Add support for multiple languages to make the game accessible to a global audience.
- **Analytics**: Integrate analytics to track user behavior and game performance, helping to identify areas for improvement and optimize the user experience.

## Dependencies
- **JUnit Jupiter Engine**: Used for writing and running tests.
  ```xml
  <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.8.1</version>
      <scope>test</scope>
  </dependency>
  ```
## Testing
The tests cover various aspects of the game logic, including:
- Handling user input.
- Generating random numbers.
- Simulating network failures.
- Ensuring correct game flow and logic.
### Example Tests
Here are some examples of how the application was tested: 
```java
// Handling Network Failures: Ensuring the application handles network failures gracefully.
@Test
void randomNumberShouldHandleNetworkFailures() throws NoSuchFieldException, IllegalAccessException {
    Field apiUrlField = GameApp.class.getDeclaredField("apiUrl");
    apiUrlField.setAccessible(true);
    String originalApiUrl = (String) apiUrlField.get(null);

    try {
        apiUrlField.set(null, "https://invalid.url");
        String randomNumber = GameApp.randomNumber();
        assertEquals("", randomNumber);
    } finally {
        apiUrlField.set(null, originalApiUrl);
    }
}
// Validating User Input: Ensuring the application correctly validates user input.  
@Test
void shouldValidateUserInput() {
    assertTrue(GameApp.isValidInput("1234"));
    assertFalse(GameApp.isValidInput("abcd"));
    assertFalse(GameApp.isValidInput("12345"));
}
// Game Logic: Ensuring the game logic works as expected.  
@Test
void shouldCheckGameLogic() {
    GameApp game = new GameApp();
    game.setCombination("1234");
    assertEquals("1A2B", game.checkGuess("1243"));
}
```
