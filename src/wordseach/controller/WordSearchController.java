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
        makeWordArrays(wordsToHide);
    }
    
    
    
    private void makeBoard(int height, int width, List<String> words)
    {
        board = new String[height][width];
        
        List<String[]> wordArrays = makeWordArrays(words);
        
        int randomRow;
        int randomCol;
        int directionRow;
        int directionCol;
        
        for(int index = 0; index<wordArrays.size(); index++)
        {
            randomRow = (int)(Math.random()* height);
            randomCol = (int)(Math.random()* width);
            
            if(board[randomRow][randomCol] == null)
            {
                directionRow = (int)(Math.random()*3)-1;
                directionCol = (int)(Math.random()*3)-1;
                
                
               
            }
            
             
            
        }
    }
    
    /**
     * this method tests if a word will fit in the board 
     * it start on the square after the startRow and startCol
     * @param board
     * @param startRow
     * @param startCol
     * @param directionRow
     * @param directionCol
     * @param word
     * @return
     */
    private boolean testRowDirection(String[][] board, int startRow, int startCol, int directionRow, int directionCol, String[] word)
    {
        int times = 1;
        int nextRow = startRow;
        int nextCol = startCol;
        
        for(int index =1; index<word.length; index++)
        {
            nextRow = startRow + directionRow;
            nextCol = startCol + directionCol;
            
            if(!(board.length>nextRow && board[nextRow].length>nextCol))//this checks if the next space is a valid place in the board
            {
                return false;// is no longer in the board
            }
            
            if(!(board[nextRow][nextCol] == null || board[nextRow][nextCol].equals(word[index])))// checking if the place is either null or the letter I want
            {
                return false; // the board already has a letter there.
            }
            times += 1;
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
        
        for(int index = 0; index< word.length; index++)
        {
            board[row][col] = word[index];
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
