package fridge.window_content;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Containers {
  JTextArea output;
  JScrollPane scrollPane;
  
  public Container createContentPane(int cpType){
    JPanel contentPane = new JPanel(new BorderLayout());
    contentPane.setOpaque(true);
    
    System.out.println("[DEBUG] Creating content pane with type: " + cpType);
    
    switch (cpType){
    case 0:
      //Create a scrolled text area.
      output = new JTextArea(5, 30);
      output.setEditable(false);
      scrollPane = new JScrollPane(output);
      
      //Add the text area to the content pane.
      contentPane.add(scrollPane, BorderLayout.CENTER);
      break;
    }
    
    return contentPane;
  }
}