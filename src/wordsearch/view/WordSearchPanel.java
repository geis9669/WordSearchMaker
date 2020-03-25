package wordsearch.view;

import wordsearch.controller.WordSearchController;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WordSearchPanel extends JPanel {
    private WordSearchController controller;

    private SpringLayout layout;
    private JTable wordSearchTable;
    private JScrollPane TableScrollPane;
    
    private JButton enterButton;

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
        // sets up the button to make the word search
        enterButton = new JButton("Make WordSearch");
        add(enterButton);
        enterButton.setSize(140,16);
        enterButton.setLocation(10,450);
        
        enterButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("enterButton Pressed");
				//controller.getTableModel());
//        enterArea.getText();
			}
        });
        
    }
}
