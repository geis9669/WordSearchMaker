package wordsearch.model;

import javax.swing.table.AbstractTableModel;

public class WordSearchTableModel extends AbstractTableModel
{
    private String[][] wordSearchBoard;

    public WordSearchTableModel(String[][] wordSearchBoard)
    {
        this.wordSearchBoard = wordSearchBoard;
    }

    public String getColumnName(int c)
    {
        return c+1+"";
    }

    @Override
    public int getRowCount() {
        return wordSearchBoard.length;
    }

    @Override
    public int getColumnCount() {
        return wordSearchBoard[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return wordSearchBoard[rowIndex][columnIndex];
    }
}
