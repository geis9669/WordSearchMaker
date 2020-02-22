package wordseach.controller;

import java.util.List;
import java.util.ArrayList;
import wordseach.model.WordSearchBoard;

public class WordSearchController
{
    private WordSearchBoard board;

    private List<String> wordsToHide;
    
    private int width;
    private int height;
    
    public WordSearchController()
    {
        wordsToHide = new ArrayList<>();
        width = 10;
        height = 10;
        
        addWords(wordsToHide);
    }
    
    private void addWords(List<String> list)
    {
        list.add("gregory");
        list.add("java");
        list.add("arraylist");
        list.add("board");
    }
    
    public void start()
    {
        board.makeBoard(height, width, wordsToHide);
    }
    
    
    


    /**
     * this method makes a string of all the letters of the board and prints them to the console
     * @param board the 2D array that you want printed it should have only one character for each space.
     */
    private void displayBoard(String[][] board)
    {
        String toPrint = "";
        for(int row= 0; row< board.length; row++)
        { 
            for(int col = 0; col< board[0].length; col++)
            {
                if(board[row][col] == null)
                {
                    board[row][col] = "1";
                }
                toPrint += board[row][col]+ " ";
                
            }
            toPrint += "\n";
        }
        System.out.print(toPrint);
    }

}
