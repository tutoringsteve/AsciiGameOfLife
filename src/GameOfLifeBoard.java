/**
 * Last edit: 08/26/2014
 * @author Steven Sarasin
 */
public class GameOfLifeBoard {
    
    private int[][] gameBoard, gameBoardLastTurn;
    private final int boardWidth, boardHeight;

    /**
     * Initializes game board to a preset game board that is stored as a single
     * line string, and has a specified width and height.
     * 
     * @param initialBoard String representing the map as a single line.
     * @param height The width of the game board in tiles. 
     * @param width The height of the game board in tiles.
     */
    public GameOfLifeBoard(String initialBoard, int height, int width) {
        
        gameBoard = new int[height][width];
        gameBoardLastTurn = new int[height][width];
        
        boardHeight = height;
        boardWidth = width;
        for(int row = 0; row < height; row ++) {
            for(int column = 0; column < width; column++) {
                if(initialBoard.charAt(row*height + column) == '.') {
                    gameBoard[row][column] = 0;
                }
                else {
                    gameBoard[row][column] = 1;
                }
            }
        }        
    }
      
    /**
     * Initializes game board to a preset game board that is stored as a 2D char
     * array and has a specified width and height.
     * 
     * @param initialBoard 2D char array representing the game board. The first 
     * entry in <code>initialBoard</code> represents the top left of the game
     * board and the last entry in <code>initialBoard</code> represents the
     * bottom right of the char array.
     * @param height The width of the game board in tiles.
     * @param width The height of the game board in tiles.
     */
    public GameOfLifeBoard(char[][] initialBoard, int height, int width) {
        boardHeight = width;
        boardWidth = height;
        gameBoard = new int[height][width];
        for(int row = 0; row < height; row ++) {
            for(int column = 0; column < width; column++) {
                //living cell can be any char != '.'
                boolean cellIsDead = (initialBoard[row][column] == '.');
                gameBoard[row][column] = (cellIsDead ? 0 : 1);
            }
        }        
    }
    
    /**
     * Progresses the game one turn using the current state of the game board.
     * <p>
     * The rules for progression are based on John Conway's 'Game of Life' a 
     * rule-set for generating cellular automaton. If a cell is alive and has
     * more than 3 neighbors it dies to over crowding. If a cell is alive and
     * has less than 2 neighbors it dies to starvation. If a cell is dead and 
     * has exactly 3 living neighbors it becomes alive.
     * <p>
     * The new state is saved to the global variable (<code>gameBoard</code>) 
     * while the old state is copied from the original (<code>gameBoard</code>) 
     * to (<code>gameBoardLastTurn</code>)
     */
    public void oneTurn() {
        setGameBoardLastTurn(); //uses System.arraycopy to save gameBoard
        gameBoard = oneTurn(gameBoard);       
    }
    
    /**
     * Copies gameBoard into <code>gameBoardLastTurn</code> using 
     * <code>System.arraycopy</code>
     */
    private void setGameBoardLastTurn() {
        if(gameBoardLastTurn == null ) {
            gameBoardLastTurn = new int[boardHeight][boardWidth];
        }
        
        for(int row = 0; row < boardHeight; row++) {
            System.arraycopy(gameBoard[row], 0,
                    gameBoardLastTurn[row], 0, boardWidth);
        }
    }
    
    private int[][] oneTurn(int[][] gameBoardToEvolve) {
        
        int neighborCount;
        int[][] gameBoardAfterTurn = new int[boardHeight][boardWidth];
        
        for(int row = 0; row < boardHeight; row++) {
            for(int column = 0; column < boardWidth; column++) {
                
                int cell = gameBoardToEvolve[row][column];
                boolean cellIsAlive = (cell == 1);
                neighborCount = numberOfNeighbors(row, column);
                
                if(!cellIsAlive && neighborCount == 3 ) {
                    gameBoardAfterTurn[row][column] = 1;
                }
                else if(cellIsAlive 
                        && (neighborCount == 2 
                        || neighborCount == 3) ){
                    
                    gameBoardAfterTurn[row][column] = 1;
                }
                else {
                    gameBoardAfterTurn[row][column] = 0;
                }
            }
        }
        return gameBoardAfterTurn;
    }
    
    /**
     * Checks to see if (<code>gameBoard</code>) didn't change after a turn. 
     * <p>
     * If a board stays the same after one turn, it will continue to stay the 
     * same for any number of turns. The board is said to be in a steady state.
     * 
     * @return boolean true if the board did NOT change.
     */
    public boolean steadyState() {
        try{
           for(int row = 0; row < boardHeight; row++) {
               for(int col = 0; col < boardWidth; col++) {
                   if(gameBoard[row][col] != gameBoardLastTurn[row][col]) {
                       return false;
                   }
               }
           } 
          return true;
        }
        catch( NullPointerException e) {
            System.out.println(e);
            System.out.println("gameBoardLastTurn was not set before");
            return false;
        }
    }
    
    /**
     * Compute the total number of cells neighboring the input cell which are 
     * alive. 
     * 
     * @param x x coordinate of cell whose neighbors are being counted
     * @param y x coordinate of cell whose neighbors are being counted
     * @return -1 if input was outside the game board, else returns
     * the number of 'living' neighbors of the cell at (x,y). 
     */
    private int numberOfNeighbors(int x, int y) {
        if(    x >= boardWidth || x < 0
            || y >= boardHeight  || y < 0 ) {
            
            numberOfNeighborsDyingNoisily(x,y);
            
            return -1;
        }
        
        //check for boundry, if boundry wrap to otherside
        int xLeft, xRight, yUp, yDown;
        if( x == 0 ) {
            xLeft = boardWidth-1;
            xRight = x+1;
        }
        else if( x == (boardWidth-1)) {
            xLeft = x-1;
            xRight = 0;
        }
        else {
            xLeft = x-1;
            xRight = x+1;
        }
        if( y == 0 ) {
            yDown = boardHeight-1;
            yUp = y + 1;
        }
        
        else if( y == (boardHeight-1)) {
            yDown = y-1;
            yUp = 0;
        }
        else {
            yDown = y - 1;
            yUp = y + 1;            
        }
        //sum will equal number of alive neighbors since 1 for alive 0 for empty
        return (gameBoard[xLeft][yUp]   + gameBoard[x][yUp]   + gameBoard[xRight][yUp] +
                gameBoard[xLeft][y]     +                     + gameBoard[xRight][y]   +
                gameBoard[xLeft][yDown] + gameBoard[x][yDown] + gameBoard[xRight][yDown]);       
   }
    
    /**
     * Helper method for numberOfNeighbors that gives exception text if parameters are bad.
     * 
     * @param x the x coordinate of the cell that numberOfNeighbors is checking around.
     * @param y the y coordinate of the cell that numberOfNeighbors is checking around.
     * @return a string with the details about why the method 'numberOfNeighbors(x,y) failed.
     */
    private String numberOfNeighborsDyingNoisily(int x, int y) {
        String dyingMessage  = "gameBoard width = " + boardWidth + " gameBoard height " + boardHeight + "\n";
               dyingMessage += "numberOfNeighbors(x,y) is dying (noisily) because x and y were ";
               dyingMessage += "\n" +"x = " + x + " y = " + y;
        if( x >= boardWidth || y >= boardHeight ) {
              dyingMessage += "\nOne or more of the innput coordinates (x,y) was too large!";
        } 
        if (x < 0 || y < 0) {
            dyingMessage += "\nOne or more of the input coordinates (x,y) was negative!";
        }
        
        return dyingMessage;
    }
    
    /**
     * Prints the game board as a 2D char array where '.' are dead cells
     * @return String formatted from the gameBoard to printout 2D gameBoard
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < boardWidth; i++) {
            for(int j = 0; j < boardHeight; j++) {
                if(gameBoard[i][j] == 0) {
                    sb.append('.');
                }
                else{
                    sb.append('#');
                }
            }
            sb.append('\n');
        }
        
        return sb.toString();
    }
    
}
