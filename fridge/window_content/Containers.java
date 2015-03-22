package fridge.window_content;

import java.awt.*;
//import java.swing.*;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Containers{
  //JTextArea output;
  //JScrollPane scrollPane;
  fridge.action_handling.ContainerListeners contListeners;
  
  public Containers(){
    contListeners = null;
  }
  
  public Containers(fridge.action_handling.ContainerListeners CL_ptr){
    contListeners = CL_ptr;
  }
  
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
    JScrollPane scrollPane, listScrollPane;
    DefaultListModel listModel;
    JList list;
    //JComponent contentPane = new MyList();
    listModel = new DefaultListModel();
    listModel.addElement("first element");
    listModel.addElement("second element");
    list = new JList(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.addListSelectionListener(contListeners.getListListener());
    list.setVisibleRowCount(5);
    listScrollPane = new JScrollPane(list);
    contentPane.add(listScrollPane, BorderLayout.WEST);
    //Create a scrolled text area.
    contentPane.setOpaque(true);
    output = new JTextArea(5, 30);
    output.setEditable(false);
    scrollPane = new JScrollPane(output);
    
    
    
    
    
    
    //Add the text area to the content pane.
    contentPane.add(scrollPane, BorderLayout.EAST);
    
    
  }
  
  /*private class MyList extends JPanel
                       implements ListSelectionListener{
    
    
    public MyList(){
      super(new BorderLayout());
      
      JList list;
      DefaultListModel listModel;
      JScrollPane listScrollPane;
      
      listModel = new DefaultListModel();
      listModel.addElement("First element");
      listModel.addElement("Second element");
      
      list = new JList(listModel);
      list.setSelectionMode(ListSelectionMode.SINGLE_SELECTION);
      list.setSelectedIndex(0);
      list.addListSelectionListener(this);
      list.setVisibleRowCount(10);
      listScrollPane = new JScrollPane(list);
      
      
    }
  }*/
  
  
}