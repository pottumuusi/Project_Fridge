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
    
    createMethodSelection(cpType, contentPane);
    
    return contentPane;
  }
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassListSelectionListener CLSListener){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    //System.out.println("[DEBUG] Creating content pane with type: " + cpType);
    
    createMethodSelection(cpType, contentPane, CLSListener);
    
    return contentPane;
  }
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassListSelectionListener CLSListener,
                                     JTextArea helpText){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    createMethodSelection(cpType, contentPane, CLSListener, helpText);
    
    return contentPane;
  }
  
  private void createMethodSelection(String cpType, JPanel contentPane){
    switch (cpType){
    case "mainWin1":
      createMainWin1Content(contentPane);
      break;
    }
  }
  
  private void createMethodSelection(String cpType, JPanel contentPane,
                               fridge.action_handling.ClassListSelectionListener CLSListener){
    switch (cpType){
    case "help":
      createHelpContent(contentPane, CLSListener);
      break;
    }
  }
  
  private void createMethodSelection(String cpType,
                                     JPanel contentPane,
                                     fridge.action_handling.ClassListSelectionListener CLSListener,
                                     JTextArea helpText){
    switch (cpType){
    case "help":
      createHelpContent(contentPane, CLSListener, helpText);
      break;
    }
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
  
  /*private void createHelpContent(JPanel contentPane,
                                 fridge.action_handling.MyListSelectionListener LSListener){
    JTextArea output;
    JScrollPane scrollPane, listScrollPane;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    JList list;
    
    String[] listData = {"first", "second", "third"};
    list = new JList(listData);
    
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.setVisibleRowCount(5);
    
    listSelectionModel = list.getSelectionModel();
    listSelectionModel.addListSelectionListener(LSListener);
    
    listScrollPane = new JScrollPane(list);
    contentPane.add(listScrollPane, BorderLayout.WEST);
    contentPane.setOpaque(true);
    output = new JTextArea(5, 30);
    output.setEditable(false);
    scrollPane = new JScrollPane(output);
    
    contentPane.add(scrollPane, BorderLayout.EAST);
  }*/
  
  private void createHelpContent(JPanel contentPane,
                                 fridge.action_handling.ClassListSelectionListener CLSListener,
                                 JTextArea helpTextArea){
    JScrollPane scrollPane, listScrollPane;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    JList list;
    
    String[] listData = {"first", "second", "third"};
    list = new JList(listData);
    
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.setVisibleRowCount(5);
    
    listSelectionModel = list.getSelectionModel();
    listSelectionModel.addListSelectionListener(CLSListener);
    
    listScrollPane = new JScrollPane(list);
    contentPane.add(listScrollPane, BorderLayout.WEST);
    contentPane.setOpaque(true);
    //helpTextArea = new JTextArea(5, 30);
    helpTextArea.setEditable(false);
    helpTextArea.setName("theText");
    scrollPane = new JScrollPane(helpTextArea);
    
    contentPane.add(scrollPane, BorderLayout.EAST);
  }
  
  private void createHelpContent(JPanel contentPane,
                                 fridge.action_handling.ClassListSelectionListener CLSListener){
    JTextArea output;
    JScrollPane scrollPane, listScrollPane;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    JList list;
    
    String[] listData = {"first", "second", "third"};
    list = new JList(listData);
    
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.setVisibleRowCount(5);
    
    listSelectionModel = list.getSelectionModel();
    listSelectionModel.addListSelectionListener(CLSListener);
    
    listScrollPane = new JScrollPane(list);
    contentPane.add(listScrollPane, BorderLayout.WEST);
    contentPane.setOpaque(true);
    output = new JTextArea(5, 30);
    output.setEditable(false);
    output.setName("theText");
    scrollPane = new JScrollPane(output);
    
    contentPane.add(scrollPane, BorderLayout.EAST);
}
  
  private void createHelpContent(JPanel contentPane){
    JTextArea output;
    JScrollPane scrollPane, listScrollPane;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    JList list;
    //JComponent contentPane = new MyList();
    
    /*one way to init
    listModel = new DefaultListModel();
    listModel.addElement("first element");
    listModel.addElement("second element");
    list = new JList(listModel);*/
    
    //another way ot init
    String[] listData = {"first", "second", "third"};
    list = new JList(listData); 
    
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    //list.addListSelectionListener(contListeners.getListListener());
    list.setVisibleRowCount(5);
    
    listSelectionModel = list.getSelectionModel();
    //listSelectionModel.addListSelectionListener(contListeners.getLSListener());
    listSelectionModel.addListSelectionListener(new fridge.action_handling.MyListSelectionListener());
    
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