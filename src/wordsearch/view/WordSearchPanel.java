package wordsearch.view;

import wordsearch.controller.WordSearchController;
import wordsearch.model.WordSearchTableModel;

import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WordSearchPanel extends JPanel {
    private WordSearchController controller;

    private SpringLayout layout;
    private JTable wordSearchTable;
    private JScrollPane wordSearchScrollPane;
    
    private JLabel wordLabel;
    private JScrollPane wordScrollPane;
    private JTextArea wordArea;
    
    private JLabel lettersLabel;
    private JTextArea lettersArea;
    
    private JTextField heightField;
    private JLabel heightLabel;
    private JTextField widthField;
    private JLabel widthLabel;
    private JLabel errorLabel;
    
    private JButton enterButton;

    public WordSearchPanel(WordSearchController controller)
    {
        super();
        this.controller = controller;
        this.layout = new SpringLayout();
        
        this.setLayout(null);//layout);
        
        // sets up where the word search will appear
        this.wordSearchTable = new JTable();
        this.wordSearchScrollPane = new JScrollPane(wordSearchTable);
        wordSearchScrollPane.setSize(400,400);
        wordSearchScrollPane.setLocation(10,10);
        this.add(wordSearchScrollPane);
        
        // sets up the place to enter the words to find
        this.wordLabel = new JLabel("<html>Words to find<br>Each line is a word");
        wordLabel.setSize(200, 30);
        wordLabel.setLocation(410+10,10);
        this.add(wordLabel);
        this.wordArea = new JTextArea(); 
        this.wordScrollPane = new JScrollPane(wordArea);
        wordScrollPane.setSize(200,200); // 200,200
        wordScrollPane.setLocation(410 + 10, 10+wordLabel.getHeight()+wordLabel.getY());
        this.add(wordScrollPane);
        
        // sets up the place to enter the random letters.
        this.lettersLabel = new JLabel("Random Letters");
        lettersLabel.setSize(wordLabel.getWidth(), 25);
        lettersLabel.setLocation(10+ wordSearchScrollPane.getWidth()+wordSearchScrollPane.getX(),
        		10+wordScrollPane.getY()+wordScrollPane.getHeight());
        add(lettersLabel);
        this.lettersArea = new JTextArea("abcdefghijklmnopqrstuvwxyz",3,28);
        lettersArea.setSize(wordLabel.getWidth(), 50);
        lettersArea.setLocation(lettersLabel.getX(), 
        		lettersLabel.getHeight()+lettersLabel.getY());
        this.add(lettersArea);
        
      //sets up the place to enter the width and height for the board.
        KeyAdapter numValidator = new KeyAdapter() {
        	public void keyPressed(KeyEvent key)
        	{  		
        		JTextField field = (JTextField) key.getSource();
        		if(key.getKeyChar() >= '0' && key.getKeyChar()<= '9'|| key.getKeyChar() == KeyEvent.VK_BACK_SPACE)
        		{
        			field.setEditable(true);
        			errorLabel.setText("");
        		}else {
        			field.setEditable(false);
        			errorLabel.setText("* Enter only numeric digits(0-9)");
        		}
        	}
        };  
        
        widthLabel = new JLabel("Width");
        widthLabel.setSize(50,20);
        widthLabel.setLocation(lettersArea.getX(),
        		lettersArea.getHeight()+lettersArea.getY());
        add(widthLabel);
        widthField = new JTextField("", 25);
        widthField.addKeyListener(numValidator);
        widthField.setSize(widthLabel.getWidth(), widthLabel.getHeight());
        widthField.setLocation(widthLabel.getX(),
        		widthLabel.getY()+widthLabel.getHeight());
        add(widthField);
        
        heightLabel = new JLabel("Height");
        heightLabel.setSize(widthLabel.getWidth(), widthLabel.getHeight());
        heightLabel.setLocation(widthLabel.getX()+widthLabel.getWidth()+10,
        		widthLabel.getY());
        add(heightLabel);
        heightField = new JTextField(25);        
        add(heightField);
        heightField.addKeyListener(numValidator);
        heightField.setSize(heightLabel.getWidth(),heightLabel.getHeight());
        heightField.setLocation(heightLabel.getX(),
        		heightLabel.getHeight()+heightLabel.getY());

        errorLabel = new JLabel();
        add(errorLabel);
        errorLabel.setSize(180, 25);
        errorLabel.setLocation(widthField.getX(),
        		heightField.getHeight()+heightField.getY());
        
        // sets up the button to make the word search
        enterButton = new JButton("Make WordSearch");
        add(enterButton);
        enterButton.setSize(140,16);
        enterButton.setLocation(10,450);   
        enterButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String words = wordArea.getText();
			    String letters = lettersArea.getText();
			    WordSearchTableModel data = controller.makeTableModel(words, letters, widthField.getText(), heightField.getText());
			    
			    wordSearchTable.setModel(data);
			    int width = 25;
			    for(int col =0; col<wordSearchTable.getColumnCount() ;col++)
			    {
			    	TableColumn column = wordSearchTable.getColumnModel().getColumn(col);
			    	column.setMinWidth(width);
			    	column.setMaxWidth(width);
			    	column.setPreferredWidth(width);
			    }
			}
        });
        
    }
}
