package wordsearch.controller;

import java.util.*;
import wordsearch.model.*;
import wordsearch.view.WordSearchFrame;
import javax.swing.*;

public class WordSearchController
{
    private WordSearchFrame frame;
    
    public WordSearchController()
    {
        frame = new WordSearchFrame(this);
    }
    
    public WordSearchTableModel makeTableModel(String words, String letters, String width, String height)
    {
    	if(letters.length()<= 0)
    		letters = "1";
    	
    	int width1 = convertStringToInt(width);
    	int height1 = convertStringToInt(height);
    	
    	if(words.length()<=0 && width1<=0 && height1<= 0) {
    		return new WordSearchTableModel(new String[0][0], new ArrayList<>(0), new ArrayList<>(0)); }
    	
        return  WordSearchMaker.makeBoard(separateWords(words, "\n"),letters, width1,height1);
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
    
	/**
	 * separate words from a string to a list of strings
	 * @param words the ones to separate
	 * @param separator what is between the words, it isen't included in the Strings
	 * @return an arrayList of words same order as in the string
	 */
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
    
    
    public void saveBoard(WordSearchTableModel data, String path)
    {
    	String newLine = "\n";
    	String textToSave=newLine;
    	String[][] board = data.getWordSearch();
    	for(int row = 0; row<board.length; row++) 
    	{
    		for(int col =0; col<board[0].length; col++)
    		{
    			if(col != 0)
    				textToSave+= " ";
    			textToSave += board[row][col];
    		}
    		textToSave += newLine;
    	}
    	textToSave += newLine;
    	
    	List<String> words = data.getWordsHid();
    	List<String> wordsNotHid = data.getWordsNotHid();
    	int length = 0;
    	if(board.length > 0)
    		length = board[0].length;	
    	final int lineLength = length*2-1;
    	int currentLength = 0;
    	for(String word: words)
    	{
    		if(wordsNotHid.contains(word))
    		{
    			wordsNotHid.remove(word);
    		}
    		else
    		{
    			if(currentLength +1+ word.length() > lineLength)
    			{
    				textToSave+=newLine;
    				currentLength = 0;
    			}
    			if(currentLength != 0)
    			{
    				textToSave += " ";
    				currentLength += 1;
    			}
    			textToSave += word;
    			currentLength += word.length();
    		}	
    	}
    	Output.saveText(this, path, textToSave);
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