package fridge.window_content;

import java.awt.*;

import javax.swing.JList;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle;

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
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassListSelectionListener[] CLSListenerList){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    createMethodSelection(cpType, contentPane, CLSListenerList);
    
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
  
  private void createMethodSelection(String cpType, JPanel contentPane,
                                     fridge.action_handling.ClassListSelectionListener[] CLSListenerList){
    switch (cpType){
    case "mainWin1":
      createMainWin1Content(contentPane, CLSListenerList);
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
  
  /*private void createMainWin1Content(JPanel contentPane,
                                     fridge.action_handling.ClassListSelectionListener[] CLSListenerList,
                                     fridge.action_handling.ClassActionListener[] CAListenerList){*/
  private void createMainWin1Content(JPanel contentPane,
                                     fridge.action_handling.ClassListSelectionListener[] CLSListenerList){
    //JTextArea output;
    JScrollPane view0ScrollPane, view1ScrollPane;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    JList view0, view1;
    JButton folderShowGroup, quickSave, quickLoad, qa_showGroup, qa_operations;
    JLabel folderLabel = new JLabel();
    JLabel quickAccessLabel = new JLabel();
    //JLabel folderExplanation = new JLabel();
    //JLabel quickAccessExplanation = new JLabel();
    
    folderLabel.setText("Folder");
    quickAccessLabel.setText("Quick Access");
    
    
    //pack();
    
    //create upper view
    String[] view0Data = {"folder1    group1", "folder2"}; // still testing. will be initialized empty
    view0 = new JList(view0Data);
    
    view0.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    view0.setSelectedIndex(0);
    view0.setVisibleRowCount(5);
    listSelectionModel = view0.getSelectionModel();
    listSelectionModel.addListSelectionListener(CLSListenerList[0]);
    
    view0ScrollPane = new JScrollPane(view0);
    //contentPane.add(view0ScrollPane, BorderLayout.PAGE_START);
    //contentPane.add(view0ScrollPane);
    
    // create buttons
    folderShowGroup = new JButton("Show group");
    folderShowGroup.setVerticalTextPosition(AbstractButton.CENTER);
    folderShowGroup.setHorizontalTextPosition(AbstractButton.LEADING);
    //contentPane.add(folderShowGroup, BorderLayout.NORTH);
    
    quickSave = new JButton("Quick save");
    quickSave.setVerticalTextPosition(AbstractButton.CENTER);
    quickSave.setHorizontalTextPosition(AbstractButton.LEADING);
    
    quickLoad = new JButton("Quick load");
    quickLoad.setVerticalTextPosition(AbstractButton.CENTER);
    quickLoad.setHorizontalTextPosition(AbstractButton.LEADING);
    
    qa_showGroup = new JButton("Show group");
    qa_showGroup.setVerticalTextPosition(AbstractButton.CENTER);
    qa_showGroup.setHorizontalTextPosition(AbstractButton.LEADING);
    
    qa_operations = new JButton("Quick access operations");
    qa_operations.setVerticalTextPosition(AbstractButton.CENTER);
    qa_operations.setHorizontalTextPosition(AbstractButton.LEADING);
    
    
    String[] view1Data = {"folder1_qa", "folder2_qa"}; // still testing
    view1 = new JList(view1Data);
    
    view1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    view1.setSelectedIndex(0);
    view1.setVisibleRowCount(5);
    listSelectionModel = view1.getSelectionModel();
    listSelectionModel.addListSelectionListener(CLSListenerList[1]);
    //output = new JTextArea(5, 30);
    //output.setEditable(false);
    view1ScrollPane = new JScrollPane(view1);
    //contentPane.add(view1ScrollPane, BorderLayout.PAGE_END);
    //contentPane.add(view1ScrollPane);
    
    //Create a scrolled text area.
    contentPane.setOpaque(true);
    System.out.println("[DEBUG] here");
    GroupLayout layout = new GroupLayout(contentPane);
    contentPane.setLayout(layout);
    
    //create group for horizontal axis
    
    ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup h1 = layout.createSequentialGroup();
    //SequentialGroup h2 = layout.createSequentialGroup();
    ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    ParallelGroup h3 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    SequentialGroup h4 = layout.createSequentialGroup();
    SequentialGroup h5 = layout.createSequentialGroup();
    
    
    h2.addComponent(folderLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    h2.addComponent(view0ScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    //h2.addComponent(folderShowGroup, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    //h2.addComponent(quickSave, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);

    //h2.addComponent(view0ScrollPane);
    //h2.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
    //h2.addComponent(folderShowGroup, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    
    
    h3.addComponent(view1ScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    h3.addComponent(quickAccessLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    
    //h4.addContainerGap();
    h4.addComponent(folderShowGroup);
    h4.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h4.addComponent(quickSave);
    h4.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h4.addComponent(quickLoad);
    //h4.addContainerGap();
    
    h5.addComponent(qa_showGroup);
    h5.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h5.addComponent(qa_operations);
    h5.addContainerGap();
    
    h1.addContainerGap();
    h4.addGroup(h5);
    h3.addGroup(h4);
    h2.addGroup(h3);
    h1.addGroup(h2);
    h1.addContainerGap();
    
    hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
    
    layout.setHorizontalGroup(hGroup);
    System.out.println("[DEBUG] here");
    
    ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    SequentialGroup v1 = layout.createSequentialGroup();
    ParallelGroup v3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    ParallelGroup v4 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    v3.addComponent(folderShowGroup);
    v3.addComponent(quickSave);
    v3.addComponent(quickLoad);
    
    v4.addComponent(qa_showGroup);
    v4.addComponent(qa_operations);
    
    
    v1.addContainerGap();
    
    /*ParallelGroup v2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
    v2.addComponent(view1ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);*/
    
    SequentialGroup v2 = layout.createSequentialGroup();
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v2.addComponent(quickAccessLabel);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addComponent(view1ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
    //v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addContainerGap();
    
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(folderLabel);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(view0ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    //v1.addComponent(folderShowGroup);
    //v1.addComponent(quickSave);
    //v1.addComponent(quickLoad);
    v1.addGroup(v3);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v1.addGroup(v2);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addGroup(v4);
    v1.addContainerGap();
    
    vGroup.addGroup(v1);
    
    layout.setVerticalGroup(vGroup);
  }
  
  
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
    helpTextArea.setLineWrap(true);
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