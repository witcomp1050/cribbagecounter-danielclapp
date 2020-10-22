# Wentworth Institute of Technology
## COMP1050
## Project: Cribbage Counter

Last updated: 16-Oct-2020

### Description
Cribbage is a fun card game that contains elements of skill, subetly and of course, luck. Part of the skill of the game is being able to count up the points in your hand accurately and relatively quickly. It takes some practice to count up your points accurately - in fact, if you count incorrectly and your oppoenent "sees" points that you've missed, s/he can take those points away from you. This is called "muggins" and is often invoked when people play for money.

To help the cribbage players of the world, you're going to build a cribbage counting trainer. Your dealer is named Fredo.


### Requirements
* Model the game in an OO manner, e.g., Card, Deck, etc.
* Develop a JavaFX GUI on which to play the game
* Display cards graphically
* You must use the Apache Commons configuration library to manage configuration

### Individual 
This is a solo project. If you want to work in a team, you need to do the Mastermind game.

### History & How to Play
You need not implement and entire game, nor do you have to know how to play cribbage. This is about displaying a hand and
having the player count up the points in it. The computer must be able to verify that the count is correct (or not), and must be able to show how it arrives at its conclusion. Here is some useful background material:

* [Cribbage Overview from Wikipedia](https://en.wikipedia.org/wiki/Cribbage)
* [Counting Cribbage Hands](http://www.rubl.com/rules/cribbage-scoring-chart.html)

Notes:
* In order for a flush to be valid, all of the cards in the hand must be of the same suit, and this scores 4 points.
* If the starter card (the one cut face-up on top of the deck) is also of the suit, score 1 additional point for the flush.

### Game Play
* The dealer will create and shuffle a standard deck of playing cards, deal 4 cards face up for the player and cut the deck to display a 5th card on top of the deck
* The game will then start a clock to time how long it takes the player to calculate the total points in the cribbage hand
* The player will enter his/her calculated score
* The computer verifies whether or not the count is correct
	* If correct, display a random congrats message
	* If incorrect, the application shows the player the correct score with the combination(s) of cards that make up the correct answer.
* During play, the computer keeps track of and displays
	* Total number of hands scored
	* Total number correct vs. incorrect and percent of correct scores. Display as numbers with an associated graphic, e.g, pie chart.
	* Average time in seconds to score a hand. Optionally display a line chart to show trends.
* Player continues to score hands until s/he quits the game. 

#### Options
In order to get some real-world experience using third-party libraries and configuration settings in an application, you will use the Apache Commons configuration library to manage some simple options for the game.

* The background color of the playing surface
* Whether music plays in the background while the user plays the game
* Whether or not to have a maximum time limit for coming up with a count, e.g., 15 seconds


Here is an example of the file's contents:

```
# Cribbage Trainer Properties File (cribc.properties)
background = #FF001A
music = null or _path-to-music-file-that-plays-in-a-loop_
maxTime = 0 or _number of seconds_
``` 

### Creative License
As long as you fulfill the requirements of the project, you are good to go. That said, if you find 
that you're completing the project quickly and want to challenge yourself to learn more, by all means 
experiment and add your own features. Here are some ideas to get you started (all optional):

* Implement a menu that enables the user to start a new game or exit the application
* Embed a video that shows how to count cribbage hands
* Add a dialog box that enables the user to change the application's configuration settings
* Add a dialog or application argument where the application can accept the user's first name to make messaging more personal
* Add an About box that shows application info - author, etc. launched from a menu or button
* Implement an analog clock face vs. a digital display
* Enable saving and loading games and statistics, perhaps on per-user basis, e.g., Joe's stats vs. Mary's stats
* Enable multiple music choices, e.g., classical, jazz, pop, etc.
* Add animation when displaying the correct answers to the player

### Helpful Hint
As you get into the implemenation, you'll realize that counting a small handful of cards can be challenging. A useful realization is that if you can generate all combinations of a given number of cards, you can solve the counting problem in a fairly standard way. 

For example, to recognize pairs of cards, you can generate all 2-card combinations from the 5 you have and check to see which of those combinations are pairs. This is an example of a well-studied problem in combinatorics known as k-subsets. See [Wolfram](https://mathworld.wolfram.com/k-Subset.html) and [Wikipedia](https://en.wikipedia.org/wiki/Combination) for more information.

### Advice
I am here to help with questions including your design, implemenation details, etc. This can be a challenging and
somewhat time-consuming project, so don't wait until the last minute to get started! Make incremental progress every
day. This is the secret to being successful in general! Get started and _do something_.

Also, I suggest you get the core components of the application working, e.g., creating and shuffling a deck, being able to properly count a hand, etc., before you start on the GUI. Trying to do both at the same time can make things confusing. As a general rule when developing software: _get the data structures and algorithms working before adding a GUI._ This helps to ensure that when you add a GUI, you don't need to worry about the application's foundation. 

---
