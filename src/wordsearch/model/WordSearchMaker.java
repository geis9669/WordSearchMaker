package wordsearch.model;

import java.util.*;

public class WordSearchMaker
{
    public static WordSearchTableModel makeBoard(List<String> wordsToHide, String randomLetters)
    {
    	return makeBoard(wordsToHide, randomLetters, 0, 0);
    }
    /**
     * 
     */
    // make it so I can have more of a square in my program
    public static WordSearchTableModel makeBoard(List<String> wordsToHide, String randomLetters, int width, int height)
    {
    	int smallestWord = Integer.MAX_VALUE/100;
    	int bigestWord = 0;
    	int requiredArea = 0;
    	// this gets the total minimum space needed for all the words, and it finds the biggest and smallest word lengths
    	for(String word : wordsToHide)
    	{
//    		System.out.println(word + "  this");
    		requiredArea += word.length();
    		if(word.length()> bigestWord)
    		{
    			bigestWord = word.length();
    		}
    		if(word.length()< smallestWord)
    		{
    			smallestWord = word.length();
    		}
    	}
    	//System.out.println("small"+smallestWord+", big"+ bigestWord+", together"+ (smallestWord*bigestWord)+"\nTarget"+requiredArea);
    	// gets the height and width that will work for the board
    	int extraSpace = requiredArea/4;  	
    	if(height <= 0 && width <= 0)
    	{
    		IntPair  temp = growSizeToFit(smallestWord, bigestWord, requiredArea+extraSpace, 60);
    		if((Math.random()*100) >= 50)
        	{
        		height = temp.getFirst();
        		width = temp.getSecond();
        	}
        	else
        	{
        		height = temp.getSecond();
        		width = temp.getFirst();
        	}
    	}
    	else 
    	{
    		if(width<bigestWord&&height<bigestWord) {
    			if(width == height)
    			{
    				width = (int)(Math.random()*height*2)-1;
    			}
    			if(width>=height){
    				height = width;
    				width = bigestWord;
    			}else {
    				width = height;
    				height = bigestWord;
    			}
    		}
    		
    		int biased = 50;
    		if(width < height)
    		{
    			biased += 10;
    		}
    		if(width>height)
    		{
    			biased -= 10;
    		}
    		IntPair temp = growSizeToFit(width, height, requiredArea+extraSpace, biased);
    		width = temp.getFirst();
    		height = temp.getSecond();
    	}
    	
        String[][] board = new String[height][width];// make sure the size will actually fit
        List<String> wordsThatDidentFit = new ArrayList<>();
        
        // makes a list of all the possible spots.
        List<IntPair> allPossibleSpots = new ArrayList<>();
        for(int row = 0; row<board.length; row++)
        {
            for(int col = 0; col<board[0].length; col++)
            {
                allPossibleSpots.add(new IntPair(row,col));
            }
        }
        List<IntPair> directions = new ArrayList<>(8);
        int indexd = 0;
        for(int row = -1; row<2; row++)
        {
            for(int col = -1; col<2; col++)
            {
                if(!(row == 0 && col == 0))
                {
                    directions.add(new IntPair(row,col));
                    indexd++;
                }
            }
        }

        List<String[]> wordArrays = makeWordArrays(wordsToHide);
        boolean wordPlaced;// tells me that the word has been put into the array or not.

        // this loop goes through each word to find it a home.
        for(int index = 0; index<wordArrays.size(); index++)
        {
            wordPlaced = false;

            List<IntPair> possibleSpots = new ArrayList<>();
            possibleSpots.addAll(allPossibleSpots);
            // this loop goes through all the spots until it finds one that works
            while(!wordPlaced && possibleSpots.size()>0) {
                int randomPlace = (int) (Math.random()*possibleSpots.size());
                IntPair place = possibleSpots.remove(randomPlace);

                List<IntPair> possibleDirections = new ArrayList<>(8);
                possibleDirections.addAll(directions);
                // this loop goes through all the directions that the word can go.
                while(!wordPlaced && possibleDirections.size() > 0) {
                    IntPair direction = possibleDirections.remove(((int) (Math.random() * possibleDirections.size())));
                    if(testRowDirection(board, place.getFirst(), place.getSecond(), direction.getFirst(), direction.getSecond(), wordArrays.get(index))) {
                        addWord(board, place.getFirst(), place.getSecond(), direction.getFirst(), direction.getSecond(), wordArrays.get(index));
                        wordPlaced = true;
                    }
                }
            }
            if(!wordPlaced)
            {
                String word = "";
                for(String letter: wordArrays.get(index))
                {
                    word += letter;
                }
                wordsThatDidentFit.add(word);
            }
        }
        // sets the nulls to random characters 
        for(int row= 0; row< board.length; row++)
        { 
            for(int col = 0; col< board[0].length; col++)
            {
                if(board[row][col] == null)
                {
                    int random = (int) (Math.random()*randomLetters.length());
                    String character = randomLetters.substring(random, random+1);
                    board[row][col] = character;
                }                
            }     
        }
        return new WordSearchTableModel(board, wordsToHide, wordsThatDidentFit);
    }
    
    /**
     * grows the first and second by one until their area is greater then the required area
     * @param first size to grow
     * @param second size to grow
     * @param requiredArea how much area you want covered
     * @param biased how often one will grow while the other doen't
     * @return a IntPair for both the first and second numbers
     */
    private static IntPair growSizeToFit(int first,int second,int requiredArea, int biased)
    {
    	while(!(first * second >= requiredArea ))// smallestWord*bigestWord -(requiredArea + extraSpace) > -1
    	{
    		//System.out.println("small "+smallestWord+", big "+ bigestWord+", together"+ 
    	       //(smallestWord*bigestWord)+"\nTarget "+requiredArea+", extraspace "+extraSpace);
    		if((Math.random()*100)+1 <= biased)
    		{
    			first += 1;
    		}
    		else
    		{
    			second += 1;
    		}
    	}
    	return new IntPair(first, second);
    }

    /**
     * this method tests if a word will fit in the board
     * it start on the square specified by the startRow and startCol
     * @param board the word search board to add the word to
     * @param startRow the row to start at
     * @param startCol the column to start at
     * @param directionRow to go up with -1 down 1 and nothing 0
     * @param directionCol move left with -1 right with 1 and nothing 0
     * @param word the word as a array to add to the board
     * @return true if the word fits there and false if it doesn't
     */
    private static boolean testRowDirection(String[][] board, int startRow, int startCol, int directionRow, int directionCol, String[] word)
    {
        int nextRow = startRow;
        int nextCol = startCol;

        for(int index =0; index<word.length; index++)
        {
            //this checks if the next space is a valid place in the board
            if(!((board.length>nextRow && -1<nextRow) && (board[nextRow].length>nextCol &&-1<nextCol)))
            {
                return false;// is no longer in the board
            }
            // checking if the place is either null or the letter I want
            if(!(board[nextRow][nextCol] == null || board[nextRow][nextCol].equals(word[index])))
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
     * @param board the word search board to add the word to
     * @param startRow the row to start at
     * @param startCol the column to start at
     * @param directionRow to go up with -1 down 1 and nothing 0
     * @param directionCol move left with -1 right with 1 and nothing 0
     * @param word the word as a array to add to the board
     */
    private static void addWord(String[][] board, int startRow, int startCol, int directionRow, int directionCol, String[] word)
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

    /**
     * makes the strings in the list to be arrays where each index has only one letter from the word
     * @param words the words to separate into letters
     * @return the arrays of the words.
     */
    private static List<String[]> makeWordArrays(List<String> words)
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
