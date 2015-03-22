package fridge.window_content;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Containers {
  //JTextArea output;
  //JScrollPane scrollPane;
  
  public Container createContentPane(String cpType){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    System.out.println("[DEBUG] Creating content pane with type: " + cpType);
    
    switch (cpType){
    case "mainWin1":
      createMainWin1Content(contentPane);
      break;
    case "help":
      createHelpContent(contentPane);
      break;
    }
    
    return contentPane;
  }
  
  private void createMainWin1Content(JPanel contentPane){
    JTextArea output;
    JScrollPane scrollPane;
    //Create a scrolled text area.
    contentPane.setOpaque(true);
    output = new JTextArea(5, 30);
    output.setEditable(false);
    scrollPane = new JScrollPane(output);
    
    //Add the text area to the content pane.
    contentPane.add(scrollPane, BorderLayout.CENTER);
  }
  
  private void createHelpContent(JPanel contentPane){
    JTextArea output;
    JScrollPane scrollPane;
    //Create a scrolled text area.
    contentPane.setOpaque(true);
    output = new JTextArea(5, 30);
    output.setEditable(true);
    scrollPane = new JScrollPane(output);
    
    //Add the text area to the content pane.
    contentPane.add(scrollPane, BorderLayout.CENTER);
  }
}