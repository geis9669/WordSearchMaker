package wordsearch.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;


// this will take the size the user wants and then if any words won't fit in the board because they are too big it will
//increase the size of the board for that
// it will also make a list of unused words that wouldn't fit.

public class WordSearchBoard
{
    private final IntPair[] DIRECTIONS;

    private String[][] board;
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

        DIRECTIONS = new IntPair[8];
        // fill the DIRECTIONS array with the DIRECTIONS
        int index = 0;
        for(int row = -1; row<2; row++)
        {
            for(int col = -1; col<2; col++)
            {
                if(!(row == 0 && col == 0))
                {
                    DIRECTIONS[index] = new IntPair(row,col);
                    index++;
                }
            }
        }
        // done with fill the DIRECTIONS array
    }

    private void fixSize(int height, int width, List<String> words)
    {


    }

    public String[][] getBoard()
    {
        return this.board;
    }

    // may return the board after it finishes or report problems
    public void makeBoard()
    {
        board = new String[height][width];

        List<String[]> wordArrays = makeWordArrays(wordsToHide);

        int randomRow;
        int randomCol;
        int directionRow;
        int directionCol;

        boolean wordPlaced;


        for(int index = 0; index<wordArrays.size(); index++)// for each word
        {
            wordPlaced = false;
            randomRow = (int)(Math.random()* height);
            randomCol = (int)(Math.random()* width);


            // test for the first direction that works
            List<IntPair> possibleDirections = new ArrayList<>(8);
            Collections.addAll(possibleDirections, DIRECTIONS);
            while(!wordPlaced && possibleDirections.size() > 0) {
                IntPair direction = possibleDirections.remove(((int) (Math.random()* possibleDirections.size())));

                System.out.println(randomRow + "  " + randomCol + "  " + direction.getFirst() + "  " + direction.getSecond());
                if(testRowDirection(board, randomRow, randomCol, direction.getFirst(), direction.getSecond(), wordArrays.get(index))) {
                    addWord(board, randomRow, randomCol, direction.getFirst(), direction.getSecond(), wordArrays.get(index));
                    wordPlaced = true;
                }
            }


        }
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
