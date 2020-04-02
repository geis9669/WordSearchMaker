package wordsearch.controller;

import java.util.*;
import wordsearch.model.*;
import wordsearch.view.WordSearchFrame;
import javax.swing.*;

import javax.swing.table.AbstractTableModel;

public class WordSearchController
{
    private WordSearchMaker board;

    private WordSearchFrame frame;
    
    public WordSearchController()
    {
    	board = new WordSearchMaker();
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
    	ArrayList<String> list = new ArrayList<String>();
    	addWords(list);
    	WordSearchBoard board = new WordSearchBoard();
    	WordSearchTableModel mywordSearch = board.makeBoard(list, "1");
    	displayBoard(mywordSearch.getWordSearch());
    	System.out.println("");
    	List<String> wordsNotHid = mywordSearch.getWordsNotHid();
    	for(String word: wordsNotHid)
    	{
    		System.out.println(word);
    	}
    	*/
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
    		System.out.println("word  " +charset + "  " + words.length());
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
    
    public WordSearchTableModel makeTableModel(String words, String letters, String width, String height)
    {
    	if(letters.length()<= 0)
    	{
    		letters = "1";
    	}
    	
        return  board.makeBoard(separateWords(words, "\n"),letters, convertToInt(width), convertToInt(height));
    	int width1 = convertStringToInt(width);
    	int height1 = convertStringToInt(height);
    }

    /**
     * 
     * @param number to make a int
     * @return either the number from the string or zero
     */
	private int convertStringToInt(String number) {
		try
    	{
    		return Integer.parseInt(number);
    	}
    	catch(NumberFormatException error)
    	{
    		return 0;
    	}
	}
    
    private List<String> separateWords(String words, String separator)
    {
    	List<String> wordArray = new ArrayList<>();
    	int start = 0;
    	int end = 0;
    	words = words+separator;
    	while(end< words.length()-1)
    	{
    		end = words.indexOf(separator, start);
    		wordArray.add(words.substring(start,end));
    		start = end +1;
    	}
    	return wordArray; 
    }
    
    public void handleErrors(Exception error)
    {
    	JOptionPane.showMessageDialog(frame, error.getMessage());
    }
    
    /*
     * this method is to test separate words 
     */
    public void testSeparateWords()
    {
    	System.out.println("Test 1");
    	String words = "greg,week,day,month,year";
    	List<String> wordArray = separateWords(words, ",");
    	for(String charset: wordArray)
    	{
    		System.out.println("word  " +charset + "  " + words.length());
    	}
    	
    	System.out.println("Test 2");
    	words = "";
    	wordArray = separateWords(words, ",");
    	for(String charset:wordArray)
    	{
    		System.out.println("word  " +charset + "  " + words.length());
    	}
    }

}
// February 21 2020
// to day I made the method that finds a valid spot work and it prints the board
// I started on making the algorithm to test all the directions that the word could face