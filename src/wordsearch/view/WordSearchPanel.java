package wordsearch.view;

import wordsearch.controller.WordSearchController;
import javax.swing.JPanel;
import javax.swing.*;

public class WordSearchPanel extends JPanel {
    private WordSearchController controller;

    private SpringLayout layout;
    private JTable wordSearchTable;
    private JScrollPane wordBoard;

    public WordSearchPanel(WordSearchController controller)
    {
        super();
        this.controller = controller;
        this.layout = new SpringLayout();
        this.wordSearchTable = new JTable();


        this.setLayout(null);//layout);
        this.add(wordSearchTable);

        String[][] board = controller.getBoard(); // to get the board to show

        wordSearchTable.(board);

        wordSearchTable.setSize(400,400);
        wordSearchTable.setLocation(10,10 );
    }
}
