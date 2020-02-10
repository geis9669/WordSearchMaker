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
        
        for(int index = 0; index<wordArrays.size(); index++)
        {
            randomRow = (int)(Math.random()* height);
            randomCol = (int)(Math.random()* width);
        }
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

}
