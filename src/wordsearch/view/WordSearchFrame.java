package wordsearch.view;

import wordsearch.controller.WordSearchController;
import javax.swing.*;

public class WordSearchFrame extends JFrame {
    private WordSearchController controller;
    private JPanel panel;

    public WordSearchFrame(WordSearchController controller)
    {
        super();
        this.controller = controller;
        this.panel = new WordSearchPanel(controller);
        this.add(panel);
        setTitle("Word Search Maker");
        setSize(800,600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
