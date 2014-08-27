AsciiGameOfLife
===============

A Java implementation of John Conway's "Game of Life". The game board has to be put in by the user in the GameOfLifeTest class, which is included in this repo. Output is done to the console. I plan to wrap this in a Swing GUI. 

Before I describe the program here are the rules of the game
===============

I did not write the description below (lines 12 through 23)!
From http://en.wikipedia.org/wiki/Conway's_Game_of_Life accessed on 08/27/2014

The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in 
one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

  (1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.
  (2) Any live cell with two or three live neighbours lives on to the next generation.
  (3) Any live cell with more than three live neighbours dies, as if by overcrowding.
  (4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules
simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this
happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules
continue to be applied repeatedly to create further generations.

==============

Users should take a look at the GameOfLifeTest class, where there is a main method that shows how to initializes a GameOfLife object and print the state of the board over the course of a play to the console. The game stops playing when a steady state is reached or the user-specified maximum turns is reached. 

A steady state game is when a board doesn't change after a turn, which implies it will not change for all future turns. Trivial steady state is the board with all cells dead,a random configuration seems likely to go to this state. The example case in the GameOfLifeTest class demos a board that reaches a nontrivial steady state. It is also possible for the board to become periodic, in which case a steady state is never reached. 

=============

Plans for future of this program: 
  (1) Allowing users to save/load game boards from a file.
  (2) Wrapping the game in a Swing GUI. 
  (3) Writing up a class to design and statistically test randomly generated boards with a user-defined board size.
  
