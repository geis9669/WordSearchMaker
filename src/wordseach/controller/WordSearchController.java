package wordseach.controller;

import java.util.List;
import java.util.ArrayList;

public class WordSearchController
{
    private String[][] board;
    
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
//        makeWordArrays(wordsToHide);
        makeBoard(height, width, wordsToHide);
    }
    
    
    
    private void makeBoard(int height, int width, List<String> words)
    {
        board = new String[height][width];
        
        List<String[]> wordArrays = makeWordArrays(words);
        
        int randomRow;
        int randomCol;
        int directionRow;
        int directionCol;
        
        for(int index = 0; index<wordArrays.size(); index++)// for each word
        {
            randomRow = (int)(Math.random()* height);
            randomCol = (int)(Math.random()* width);
            
            if(board[randomRow][randomCol] == null)
            {
                directionRow = (int)(Math.random()*3)-1;
                directionCol = (int)(Math.random()*3)-1;
                if(testRowDirection(board,randomRow,randomCol,directionRow,directionCol,wordArrays.get(index)))
                {
                    addWord(board,randomRow,randomCol,directionRow,directionCol,wordArrays.get(index));
                    displayBoard(board);
                }
                
               
            }
            
             
            
        }
    }
    
    /**
     * this method tests if a word will fit in the board 
     * it start on the square specified by the startRow and startCol
     * @param board
     * @param startRow the row to start at
     * @param startCol the column to start at
     * @param directionRow
     * @param directionCol
     * @param word
     * @return
     */
    private boolean testRowDirection(String[][] board, int startRow, int startCol, int directionRow, int directionCol, String[] word)
    {
        int nextRow = startRow;
        int nextCol = startCol;
        
        for(int index =0; index<word.length; index++)
        {
            if(!((board.length>nextRow && -1<nextRow) && (board[nextRow].length>nextCol &&-1<nextCol)))//this checks if the next space is a valid place in the board
            {
                return false;// is no longer in the board
            }
            
            if(!(board[nextRow][nextCol] == null || board[nextRow][nextCol].equals(word[index])))// checking if the place is either null or the letter I want
            {
                return false; // the board already has a letter there.
            }
            nextRow += directionRow;
            nextCol += directionCol;
        }
        return true;// the word will fit in the board
    }
    
    /**
     * this will add the letters from the word to the board.
     * @param board
     * @param startRow
     * @param startCol
     * @param directionRow
     * @param directionCol
     * @param word
     */
    private void addWord(String[][] board, int startRow, int startCol, int directionRow, int directionCol, String[] word)
    {
        
        int row = startRow;
        int col = startCol;
        
        for(int index = 0; index<word.length; index++)
        {
            String letter = word[index];
            //System.out.println(row + "  " + col+"  "+ index); a print to debug.
            board[row][col] = letter ;//word[index];
            row += directionRow;
            col += directionCol;
        }
    }
    
    private void findplace(String[] word)
    {
        
    }
    
    /**
     * makes the strings in the list to be arrays where each index has only one letter from the word
     * @param words the words to separate into letters
     * @return the arrays of the words.
     */
    private List<String[]> makeWordArrays(List<String> words)
    {
        List<String[]> results = new ArrayList<>();
        
        for(int index = 0; index<words.size(); index++)
        {
            String word = words.get(index);
            String[] wordArray = new String[word.length()];
            
            for(int wordIndex = 0; wordIndex<word.length(); wordIndex++)
            {
                wordArray[wordIndex] = word.substring(wordIndex, wordIndex+1);
            }
            results.add(wordArray);
        }    
        return results;
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
