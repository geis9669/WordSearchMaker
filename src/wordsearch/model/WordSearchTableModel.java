package wordsearch.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class WordSearchTableModel extends AbstractTableModel
{
	private List<String> wordsHid;
	private List<String> wordsNotHid;
	
    private String[][] wordSearch;

    public WordSearchTableModel(String[][] wordSearchBoard, List<String> wordsHid, List<String> wordsNotHid)
    {
        this.wordSearch = wordSearchBoard;
        this.wordsHid = wordsHid;
        this.wordsNotHid = wordsNotHid;
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
    
    public List<String> getWordsHid() {
		return wordsHid;
	}

	public List<String> getWordsNotHid() {
		return wordsNotHid;
	}


    
    
}
