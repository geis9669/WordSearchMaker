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
    
    private JLabel wordLabel;
    private JScrollPane wordScrollPane;
    private JTextArea wordArea;
    
    private JLabel lettersLabel;
    private JTextArea lettersArea;
    
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
        // sets up the place to enter the words to find
        this.wordLabel = new JLabel("<html>Words to find<br>Separte them by new lines");
        wordLabel.setSize(200, 30);
        wordLabel.setLocation(410+10,10);
        this.add(wordLabel);
        this.wordArea = new JTextArea(); // empty is good
        this.wordScrollPane = new JScrollPane(wordArea);
        this.add(wordScrollPane);
        wordScrollPane.setSize(200,200); // 200,200
        wordScrollPane.setLocation(410 + 10, 10+wordLabel.getHeight()+wordLabel.getY());
        
        // sets up the place to enter the random letters.
        this.lettersLabel = new JLabel("Random Letters");
        lettersLabel.setSize(wordLabel.getWidth(), 16);
        lettersLabel.setLocation(410+10, 10+wordScrollPane.getY()+wordScrollPane.getHeight());
        add(lettersLabel);
        this.lettersArea = new JTextArea("abcdefghijklmnopqrstuvwxyz",3,28);
        lettersArea.setSize(wordLabel.getWidth(), 50);
        lettersArea.setLocation(10+ wordSearchScrollPane.getWidth()+wordSearchScrollPane.getX(), 
        		10 +lettersLabel.getHeight()+lettersLabel.getY());
        this.add(lettersArea);
        
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
