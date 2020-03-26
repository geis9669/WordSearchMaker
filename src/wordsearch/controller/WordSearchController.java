package wordsearch.controller;

import java.util.List;
import java.util.ArrayList;
import wordsearch.model.WordSearchBoard;
import wordsearch.model.WordSearchTableModel;
import wordsearch.view.WordSearchFrame;

import javax.swing.table.AbstractTableModel;

public class WordSearchController
{
    private WordSearchBoard board;

    private List<String> wordsToHide;
    
    private int width;
    private int height;

    private WordSearchFrame frame;
    
    public WordSearchController()
    {

        frame = new WordSearchFrame(this);
    }

    /**
     * method used for testing.
     * @param list the list for the words that will be hidden
     */
    private void addWords(List<String> list)
    {
        list.add("gregory");
        list.add("java");
        list.add("arraylist");
        list.add("board");
        list.add("start");
        list.add("today");
        list.add("friday");
    }
    
    public void start()
    {
    	/*
        board = new WordSearchBoard(height, width, wordsToHide, "abcdefghijklmonpqrstuvwxyz1234567890");
        board.makeBoard();
        displayBoard(board.getBoard());
    	*/
    	/*
    	String words = "greg,week,day,month,year";
    	List<String> wordArray = separateWords(words, ",");
    	
    	for(String charset: wordArray)
    	{
    		System.out.println(charset);
    	}
    	*/
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
    
/*    
    public String[][] getBoard()
    {
        board.makeBoard();
        return board.getBoard();
    }

    public AbstractTableModel makeTableModel(String words, String letters)
    {
    	
    	
        board.makeBoard();
        return new WordSearchTableModel(board.getBoard());
    }
  */  
    private List<String> separateWords(String words, String separator)
    {
    	List<String> wordArray = new ArrayList<>();
    	int start = 0;
    	int end = -1;
    	words = words+",";
    	while(end< words.length()-1)
    	{
    		start = end +1;
    		end = words.indexOf(separator, start);
    		wordArray.add(words.substring(start,end));
    	}
    	
    	return wordArray; 
    }

}
// February 21 2020
// to day I made the method that finds a valid spot work and it prints the board
// I started on making the algorithm to test all the directions that the word could face