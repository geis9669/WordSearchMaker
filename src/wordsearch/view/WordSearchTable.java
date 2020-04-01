package wordsearch.view;

import wordsearch.model.WordSearchTableModel;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import sun.swing.SwingUtilities2;

public class WordSearchTable extends JTable {
	
	// Methods in JTable that make the data in the table
	//createDefaultColumnsFromModel
	//get ColumnName
	
	public WordSearchTable()
	{
		super();
	}
	
//	public WordSearchTable(TableModel tableModel)
//	{
//		super(tableModel);
//		
//		
//		//this.addColumn(aColumn);
//	}
	
//	public String getColumnName(int column) {
//        return getModel().getColumnName(convertColumnIndexToModel(column));
//    }
//	
//	public int convertColumnIndexToModel(int viewColumnIndex) {
//        return SwingUtilities2.convertColumnIndexToModel(
//                getColumnModel(), viewColumnIndex);
//    }
	
	
	
	public String getRowName(int row) {
		WordSearchTableModel model = (WordSearchTableModel) getModel();
		return  model.getRowName(row -1);
	}
	
	

//	public boolean getScrollableTracksViewportWidth()
//	{
//		return false;
//	}
}
