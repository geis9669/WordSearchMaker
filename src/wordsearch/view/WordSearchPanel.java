package wordsearch.view;

import wordsearch.controller.WordSearchController;
import javax.swing.JPanel;
import javax.swing.*;

public class WordSearchPanel extends JPanel {
    private WordSearchController controller;

    private SpringLayout layout;
    private JTable wordSearchTable;
    private JScrollPane TableScrollPane;

    public WordSearchPanel(WordSearchController controller)
    {
        super();
        this.controller = controller;
        this.layout = new SpringLayout();
        this.wordSearchTable = new JTable(controller.getTableModel());
        this.TableScrollPane = new JScrollPane(wordSearchTable);


        this.setLayout(null);//layout);
        this.add(TableScrollPane);

        //String[][] board = controller.getBoard(); // to get the board to show
        //wordSearchTable.add();


        TableScrollPane.setSize(400,400);
        TableScrollPane.setLocation(10,10 );
    }
}
