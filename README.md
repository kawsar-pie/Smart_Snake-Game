# Smart_Snake-Game
## This is a Smart version of Snake game.

In this project we are going to develop a game called “Snake Game Pro” by using java. Normally the "Snake  Game" we used to play back in our childhood worked on the idea that the snake had to be controlled by the  player which tried to eat food & increase its length as long as it was alive and the game was over when it had a  collision with its body. But in our version, We are going to develop a game which is a bit different from the usual  Snake Game. In this game, there is a snake which moves automatically and intelligently to eat the food that is  controlled by the player. If snake bites itself before eating the food then the player wins and if the snake eat the  food then the game is over.

## Functions:
### GamePanel():
  This function initialize the GUI & start the game by calling function startgame().
### Startgame():
  This function start the timer;
### Draw():
  This function draw the components of the game such as snake & apple;
### newApple():
  This function defined an arbitrary position for the apple;
![image](https://user-images.githubusercontent.com/79654190/171030714-a601b2af-6a0c-43fd-8d6a-f5a45e4332be.png)

### Applemove():
  This function moves the apple towards user defined direction;
### Snakemove():
  This function moves the snake smartly & intelligently to catch the apple;
![image](https://user-images.githubusercontent.com/79654190/171030820-14a77905-8dca-4999-92c7-866a9e084f26.png)

### checkApple():
  This function check whether the apple is eaten by snake or not;

### checkCollisions():
  It checks whether the snake collides with it’s any body parts. And also checks
  whether the snake crosses the border in the game window.

![image](https://user-images.githubusercontent.com/79654190/171031287-e991af07-6fa4-4ddb-b8b5-04f09eef58d6.png)

### GameOver():
  It returns whether the game is over or not. The game is over when the snake eats  the apple then the timer is stopped and the game score is printed in the screen by    this GameOver function.

![image](https://user-images.githubusercontent.com/79654190/171031393-96d5e0d5-2e2b-4e08-bf59-7f27a3477e0d.png)



