package wordsearch.model;

import javax.swing.table.AbstractTableModel;

public class WordSearchTableModel extends AbstractTableModel
{
    private String[][] wordSearch;

    public WordSearchTableModel(String[][] wordSearchBoard)
    {
        this.wordSearch = wordSearchBoard;
    }

    public String getColumnName(int c)
    {
        return c+1+"";
    }

    @Override
    public int getRowCount() {
        return wordSearch.length;
    }

    @Override
    public int getColumnCount() {
        return wordSearch[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return wordSearch[rowIndex][columnIndex];
    }
    
    public String[][] getWordSearch()
    {
    	return wordSearch;
    }
}
