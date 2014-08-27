/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ForcesOfOdin
 */
public class testGameOfLife {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 32;
        GameOfLifeBoard board;
        String boardGiven = 
            ".........." +
            ".........." + 
            "..#......." + 
            "...#......" + 
            ".###......" + 
            ".........." + 
            ".........." + 
            ".........." + 
            ".........." +
            "..........";
        
       String boardGiven2 = 
            "................." +
            "................." +
            "....###...###...." +
            "................." +
            "..#..*.#.#.*..#.." +
            "..#.*..#.#..*.#.." +
            "..#....#.#....#.." +
            "....###...###...." +
            "................." +
            "....###...###...." +
            "..#....#.#....#.." +
            "..#.*..#.#..*.#.." +
            "..#..*.#.#.*..#.." +
            "................." +
            "....###...###...." +
            "................." +
            ".................";
        int X = 17;
        int Y = 17;
        
        char[][] tempArray = new char[X][Y];
        
        for(int i = 0; i < X; i ++) {
            for(int j = 0; j < Y; j++) {
                tempArray[i][j] = boardGiven2.charAt(i*X + j);
            }
        }
        board = new GameOfLifeBoard(boardGiven2,X,Y );
        
        System.out.println(board.toString());
        
        int turns = 1;
        while(turns <= n){
            board.oneTurn();
            turns++;
            
            System.out.println(board.toString());
            if(board.steadyState()) {
                System.out.println("Board reached steady state and will not change");
                break;
            }
        }
        // TODO code application logic here
    }
    
}
