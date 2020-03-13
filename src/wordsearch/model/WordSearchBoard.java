package wordsearch.model;

import java.util.List;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.concurrent.CopyOnWriteArrayList;


// this will take the size the user wants and then if any words won't fit in the board because they are too big it will
//increase the size of the board for that
// it will also make a list of unused words that wouldn't fit.

public class WordSearchBoard
{
    private final List<IntPair> DIRECTIONS;

    private String[][] board;
    
    private List<String> wordsThatDidentFit;
  
    private List<String> wordsToHide;
    private int height;
    private int width;

    private String randomLetters;

    /**
     *
     * @param height the desired height of the board if its too small it will be increased
     * @param width the desired width of the board if its too small it will be increased
     * @param wordsToHide the list of words to hide make sure its sorted from largest to smallest.
     * @param randomLetters a string of all the possible characters that will fill empty space and if it has duplicate
     *                      then more likely they will be used.
     */
    public WordSearchBoard(int height, int width, List<String> wordsToHide, String randomLetters)
    {
        this.height = height;
        this.width = width;
        this.wordsToHide = wordsToHide;

        this.randomLetters = randomLetters;

        List<IntPair> directions = new ArrayList<>(8);
        // fill the DIRECTIONS array with the DIRECTIONS I want to go
        int index = 0;
        for(int row = -1; row<2; row++)
        {
            for(int col = -1; col<2; col++)
            {
                if(!(row == 0 && col == 0))
                {
                    directions.add(new IntPair(row,col));
                    index++;
                }
            }
        }
        DIRECTIONS = List.copyOf(directions);
        // done with filling the DIRECTIONS immutable list

    }

    private void fixSize(int height, int width, List<String> words)
    {


    }

    public String[][] getBoard()
    {
        return this.board;
    }
    
    /**
     * this will return the words that diden't fit for the most recent make of the board
     * @return a list either empty or with words but not null
     */
    public List<String> getWordsThatdidentFit()
    {
        return this.wordsThatDidentFit;
    }

    // may return the board after it finishes or report problems
    public void makeBoard()
    {
        board = new String[height][width];
        //List<String> wordsThatDidentFit = new ArrayList<>();
        wordsThatDidentFit.clear();
        // makes a list of all the possible spots.
        List<IntPair> allPossibleSpots = new ArrayList<>();
        for(int row = 0; row<board.length; row++)
        {
            for(int col = 0; col<board[0].length; col++)
            {
                allPossibleSpots.add(new IntPair(row,col));
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
                possibleDirections.addAll(DIRECTIONS);
                // this loop goes through all the directions that the word can go.
                while(!wordPlaced && possibleDirections.size() > 0) {
                    IntPair direction = possibleDirections.remove(((int) (Math.random() * possibleDirections.size())));

                    //System.out.println(place.getFirst() + "  " + place.getSecond() + "  " + direction.getFirst() + "  " + direction.getSecond());
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
                //System.out.println(wordsThatDidentFit);
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

        // maybe return what words don't fit in the board
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
    private boolean testRowDirection(String[][] board, int startRow, int startCol, int directionRow, int directionCol, String[] word)
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
