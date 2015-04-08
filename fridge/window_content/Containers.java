package fridge.window_content;

import java.awt.*;

import javax.swing.JList;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
                                     fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                                     fridge.action_handling.ClassActionListener[] CAL_list,
                                     JTextField textField,
                                     JList view0,
                                     JList view1){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    if ("mainWin1" == cpType){
      createMainWin1Content(contentPane, CLSL_list, CAL_list, textField, view0, view1);
    }
    
    return contentPane;
  }
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                                     fridge.action_handling.ClassActionListener[] CAL_list,
                                     JComboBox groupList,
                                     JList view0,
                                     JList view1){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    if ("mainWin2" == cpType){
      createMainWin2Content(contentPane, CLSL_list, CAL_list, groupList, view0, view1);
    }
    
    return contentPane;
  }
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassListSelectionListener CLSListener,
                                     JTextArea textArea){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    switch (cpType){
    case "help":
      createHelpContent(contentPane, CLSListener, textArea);
      break;
    }
    
    return contentPane;
  }
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassActionListener[] CAL_list,
                                     JTextField groupNameField){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    if ("newGroupWin" == cpType){
      createNewGroupWinContent(contentPane, CAL_list, groupNameField);
    }
    
    return contentPane;
  }
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassActionListener[] CAL_list){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    if ("QAOperations" == cpType){
      createQAOperationsContent(contentPane, CAL_list);
    }
    
    return contentPane;
  }
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassListSelectionListener CLSL,
                                     fridge.action_handling.ClassActionListener[] CAL_list,
                                     JTextField saveNameField,
                                     JList view0){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    if ("collSaveWin" == cpType){
      createCollSaveWinContent(contentPane, CLSL, CAL_list, saveNameField, view0);
    }
    
    return contentPane;
  }
  
  public Container createContentPane(String cpType,
                                     fridge.action_handling.ClassActionListener[] CAL_list,
                                     JComboBox collList){
    JPanel contentPane = new JPanel(new BorderLayout());
    
    if ("collLoadWin" == cpType){
      createCollLoadWinContent(contentPane, CAL_list, collList);
    }
    
    return contentPane;
  }
  
  private void createMainWin1Content(JPanel contentPane,
                                     fridge.action_handling.ClassListSelectionListener[] CLSListenerList,
                                     fridge.action_handling.ClassActionListener[] CAL_list,
                                     JTextField folderName,
                                     JList view0,
                                     JList view1){
    //JTextArea output;
    JScrollPane view0ScrollPane, view1ScrollPane;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    //JList view0, view1;
    //JTextField folderName = new JTextField();
    JButton folderShowGroup, quickSave, quickLoad, qa_showGroup, qa_operations, previous;
    JLabel folderLabel = new JLabel();
    JLabel quickAccessLabel = new JLabel();
    //JLabel folderExplanation = new JLabel();
    //JLabel quickAccessExplanation = new JLabel();
    
    folderLabel.setText("Folder");
    quickAccessLabel.setText("Quick Access");
    
    //create upper view
    
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
    
    previous = new JButton("Previous");
    previous.setVerticalTextPosition(AbstractButton.CENTER);
    previous.setHorizontalTextPosition(AbstractButton.LEADING);
    
    //add action listeners
    folderName.addActionListener(CAL_list[0]);
    folderShowGroup.addActionListener(CAL_list[1]);
    quickSave.addActionListener(CAL_list[2]);
    quickLoad.addActionListener(CAL_list[3]);
    qa_showGroup.addActionListener(CAL_list[4]);
    qa_operations.addActionListener(CAL_list[5]);
    
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
    SequentialGroup h6 = layout.createSequentialGroup();
    
    
    h2.addComponent(folderLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    h2.addComponent(folderName);
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
    
    /*h6.addComponent(previous);
    h6.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h6.addComponent(folderName);
    h6.addContainerGap();*/
    
    h1.addContainerGap();
    //h5.addGroup(h6);
    h4.addGroup(h5);
    h3.addGroup(h4);
    h2.addGroup(h3);
    h1.addGroup(h2);
    h1.addContainerGap();
    
    hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
    
    layout.setHorizontalGroup(hGroup);
    
    ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    SequentialGroup v1 = layout.createSequentialGroup();
    SequentialGroup v2 = layout.createSequentialGroup();
    ParallelGroup v3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    ParallelGroup v4 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    //ParallelGroup v5 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    
    v3.addComponent(folderShowGroup);
    v3.addComponent(quickSave);
    v3.addComponent(quickLoad);
    
    v4.addComponent(qa_showGroup);
    v4.addComponent(qa_operations);
    
    /*v5.addComponent(previous);
    v5.addComponent(folderName);*/
    
    v1.addContainerGap();
    /*ParallelGroup v2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
    v2.addComponent(view1ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);*/
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v2.addComponent(quickAccessLabel);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addComponent(view1ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
    //v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addContainerGap();
    
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(folderLabel);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(folderName);
    //v1.addGroup(v5);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
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
  
  
  private void createMainWin2Content(JPanel contentPane,
                                     fridge.action_handling.ClassListSelectionListener[] CLSListenerList,
                                     fridge.action_handling.ClassActionListener[] CAL_list,
                                     JComboBox groupList,
                                     JList view0,
                                     JList view1){
    
    JScrollPane view0ScrollPane, view1ScrollPane;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    
    JButton groupShowFolder, quickSave, quickLoad, qa_showFolder, qa_operations;
    JLabel groupLabel = new JLabel();
    JLabel quickAccessLabel = new JLabel();
    
    groupLabel.setText("Group");
    quickAccessLabel.setText("Quick Access");
    
    view0.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    view0.setSelectedIndex(0);
    view0.setVisibleRowCount(5);
    listSelectionModel = view0.getSelectionModel();
    listSelectionModel.addListSelectionListener(CLSListenerList[0]);
    view0ScrollPane = new JScrollPane(view0);
    
    view1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    view1.setSelectedIndex(0);
    view1.setVisibleRowCount(5);
    listSelectionModel = view1.getSelectionModel();
    listSelectionModel.addListSelectionListener(CLSListenerList[1]);
    view1ScrollPane = new JScrollPane(view1);
    
    //Create buttons
    groupShowFolder = new JButton("Show folder");
    groupShowFolder.setVerticalTextPosition(AbstractButton.CENTER);
    groupShowFolder.setHorizontalTextPosition(AbstractButton.LEADING);
    
    quickSave = new JButton("Quick save");
    quickSave.setVerticalTextPosition(AbstractButton.CENTER);
    quickSave.setHorizontalTextPosition(AbstractButton.LEADING);
    
    quickLoad = new JButton("Quick load");
    quickLoad.setVerticalTextPosition(AbstractButton.CENTER);
    quickLoad.setHorizontalTextPosition(AbstractButton.LEADING);
    
    qa_showFolder = new JButton("Show folder");
    qa_showFolder.setVerticalTextPosition(AbstractButton.CENTER);
    qa_showFolder.setHorizontalTextPosition(AbstractButton.LEADING);
    
    qa_operations = new JButton("Quick access operations");
    qa_operations.setVerticalTextPosition(AbstractButton.CENTER);
    qa_operations.setHorizontalTextPosition(AbstractButton.LEADING);
    
    // Assign listeners to buttons
    groupList.addActionListener(CAL_list[0]);
    groupShowFolder.addActionListener(CAL_list[1]);
    quickSave.addActionListener(CAL_list[2]);
    quickLoad.addActionListener(CAL_list[3]);
    qa_showFolder.addActionListener(CAL_list[4]);
    qa_operations.addActionListener(CAL_list[5]);
    
    contentPane.setOpaque(true);
    GroupLayout layout = new GroupLayout(contentPane);
    contentPane.setLayout(layout);
    
    // Set layout
    //setMainWin2Layout(layout, );
    ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup h1 = layout.createSequentialGroup();
    ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    ParallelGroup h3 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    SequentialGroup h4 = layout.createSequentialGroup();
    SequentialGroup h5 = layout.createSequentialGroup();
    ParallelGroup h6 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    
    
    h2.addComponent(groupLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    h2.addComponent(groupList);
    h2.addComponent(view0ScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    
    h3.addComponent(view1ScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    h3.addComponent(quickAccessLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    
    h4.addComponent(groupShowFolder);
    h4.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h4.addComponent(quickSave);
    h4.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h4.addComponent(quickLoad);
    
    h5.addComponent(qa_showFolder);
    h5.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h5.addComponent(qa_operations);
    h5.addContainerGap();
    
    h6.addGroup(h5);
    
    h1.addContainerGap();
    h4.addGroup(h6);
    h3.addGroup(h4);
    h2.addGroup(h3);
    h1.addGroup(h2);
    h1.addContainerGap();
    
    hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
    
    layout.setHorizontalGroup(hGroup);
    
    ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    SequentialGroup v1 = layout.createSequentialGroup();
    SequentialGroup v2 = layout.createSequentialGroup();
    ParallelGroup v3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    ParallelGroup v4 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    v3.addComponent(groupShowFolder);
    v3.addComponent(quickSave);
    v3.addComponent(quickLoad);
    
    v4.addComponent(qa_showFolder);
    v4.addComponent(qa_operations);
    
    v1.addContainerGap();
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v2.addComponent(quickAccessLabel);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addComponent(view1ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
    v2.addContainerGap();
    
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(groupLabel);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(groupList);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v1.addComponent(view0ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addGroup(v3);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v1.addGroup(v2);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addGroup(v4);
    v1.addContainerGap();
    
    vGroup.addGroup(v1);
    
    layout.setVerticalGroup(vGroup);
  }
  
  /*private void setMainWinLayout(GroupLayout layout, JLabel firstLabel, JLabel secondLabel,
                                 ){
    ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup h1 = layout.createSequentialGroup();
    ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    ParallelGroup h3 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    SequentialGroup h4 = layout.createSequentialGroup();
    SequentialGroup h5 = layout.createSequentialGroup();
    SequentialGroup h6 = layout.createSequentialGroup();
    
    
    h2.addComponent(groupLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    h2.addComponent(groupList);
    h2.addComponent(view0ScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    
    h3.addComponent(view1ScrollPane, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    h3.addComponent(quickAccessLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
    
    h4.addComponent(folderShowGroup);
    h4.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h4.addComponent(quickSave);
    h4.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h4.addComponent(quickLoad);
    
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
    
    ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    SequentialGroup v1 = layout.createSequentialGroup();
    SequentialGroup v2 = layout.createSequentialGroup();
    ParallelGroup v3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    ParallelGroup v4 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    v3.addComponent(folderShowGroup);
    v3.addComponent(quickSave);
    v3.addComponent(quickLoad);
    
    v4.addComponent(qa_showGroup);
    v4.addComponent(qa_operations);
    
    v1.addContainerGap();
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v2.addComponent(quickAccessLabel);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addComponent(view1ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
    v2.addContainerGap();
    
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(folderLabel);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(folderName);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v1.addComponent(view0ScrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addGroup(v3);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v1.addGroup(v2);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addGroup(v4);
    v1.addContainerGap();
    
    vGroup.addGroup(v1);
    
    layout.setVerticalGroup(vGroup);
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
  
  private void createNewGroupWinContent(JPanel contentPane,
                                        fridge.action_handling.ClassActionListener[] CAL_list,
                                        JTextField groupNameField){
    JButton create, cancel;
    JLabel newGroupName = new JLabel();
    
    newGroupName.setText("Name for new group:");
    
    create = new JButton("Create");
    create.setVerticalTextPosition(AbstractButton.CENTER);
    create.setHorizontalTextPosition(AbstractButton.LEADING);
    
    cancel = new JButton("Cancel");
    cancel.setVerticalTextPosition(AbstractButton.CENTER);
    cancel.setHorizontalTextPosition(AbstractButton.LEADING);
    
    groupNameField.addActionListener(CAL_list[0]);
    create.addActionListener(CAL_list[1]);
    cancel.addActionListener(CAL_list[2]);
    
    contentPane.setOpaque(true);
    GroupLayout layout = new GroupLayout(contentPane);
    contentPane.setLayout(layout);
    
    //set layout. oh god
    ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup h1 = layout.createSequentialGroup();
    ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    ParallelGroup h3 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
    ParallelGroup h4 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    h1.addContainerGap();
    //h1.addComponent(newGroupName);
    
    h2.addComponent(newGroupName, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE);
    h2.addComponent(groupNameField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE);
    
    //h3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h3.addComponent(create, GroupLayout.Alignment.LEADING);
    h3.addComponent(cancel, GroupLayout.Alignment.TRAILING);
    
    
    //h1.addGroup(h2);
    //h1.addGroup(h3);
    h4.addGroup(h2);
    h4.addGroup(h3);
    
    h1.addGroup(h4);
    //h1.addComponent(groupNameField);
    
    //h1.addComponent(create);
    //h1.addComponent(cancel);
    
    h1.addContainerGap();
    
    hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
    layout.setHorizontalGroup(hGroup);
    
    
    ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup v1 = layout.createSequentialGroup();
    
    v1.addContainerGap();
    v1.addComponent(newGroupName);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(groupNameField);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addComponent(create);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v1.addComponent(cancel);
    v1.addContainerGap();
    
    vGroup.addGroup(v1);
    
    layout.setVerticalGroup(vGroup);
  }
  
  private void createQAOperationsContent(JPanel contentPane,
                                         fridge.action_handling.ClassActionListener[] CAL_list){
    JButton saveColl, loadColl, deleteColl, cancel;
    
    saveColl = new JButton("Save quick access collection");
    saveColl.setVerticalTextPosition(AbstractButton.CENTER);
    saveColl.setHorizontalTextPosition(AbstractButton.LEADING);
    
    loadColl = new JButton("Load quick access collection");
    loadColl.setVerticalTextPosition(AbstractButton.CENTER);
    loadColl.setHorizontalTextPosition(AbstractButton.LEADING);
    
    deleteColl = new JButton("Delete quick access collection");
    deleteColl.setVerticalTextPosition(AbstractButton.CENTER);
    deleteColl.setHorizontalTextPosition(AbstractButton.LEADING);
    
    cancel = new JButton("Cancel");
    cancel.setVerticalTextPosition(AbstractButton.CENTER);
    cancel.setHorizontalTextPosition(AbstractButton.LEADING);
    
    saveColl.addActionListener(CAL_list[0]);
    loadColl.addActionListener(CAL_list[1]);
    deleteColl.addActionListener(CAL_list[2]);
    cancel.addActionListener(CAL_list[3]);
    
    contentPane.setOpaque(true);
    GroupLayout layout = new GroupLayout(contentPane);
    contentPane.setLayout(layout);
    
    ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup h1 = layout.createSequentialGroup();
    SequentialGroup h2 = layout.createSequentialGroup();
    SequentialGroup h3 = layout.createSequentialGroup();
    ParallelGroup h4 = layout.createParallelGroup(GroupLayout.Alignment.LEADING); 
    
    h2.addComponent(saveColl);
    h2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h2.addComponent(loadColl);
    
    h3.addComponent(deleteColl);
    h3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    h3.addComponent(cancel);
    
    h1.addContainerGap();
    h4.addGroup(h2);
    h4.addGroup(h3);
    h1.addGroup(h4);
    h1.addContainerGap();
    
    hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
    layout.setHorizontalGroup(hGroup);
    
    ParallelGroup  vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup v1 = layout.createSequentialGroup();
    ParallelGroup v2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    ParallelGroup v3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    v2.addComponent(saveColl);
    v2.addComponent(loadColl);
    
    v3.addComponent(deleteColl);
    v3.addComponent(cancel);
    
    v1.addContainerGap();
    v1.addGroup(v2);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v1.addGroup(v3);
    v1.addContainerGap();
    
    vGroup.addGroup(v1);
    layout.setVerticalGroup(vGroup);
  }
  
  private void createCollSaveWinContent(JPanel contentPane,
                                        fridge.action_handling.ClassListSelectionListener CLSListener,
                                        fridge.action_handling.ClassActionListener[] CAL_list,
                                        JTextField saveNameField,
                                        JList view0){
    JButton save, close;
    JLabel collectionsLabel = new JLabel();
    JLabel saveName = new JLabel();
    
    collectionsLabel.setText("Collections");
    saveName.setText("New collection name:");
    
    JScrollPane view0scrollPane;
    DefaultListModel listModel;
    ListSelectionModel listSelectionModel;
    
    view0.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    view0.setSelectedIndex(0);
    view0.setVisibleRowCount(5);
    
    listSelectionModel = view0.getSelectionModel();
    listSelectionModel.addListSelectionListener(CLSListener);
    view0scrollPane = new JScrollPane(view0);
    
    save = new JButton("Save");
    save.setVerticalTextPosition(AbstractButton.CENTER);
    save.setHorizontalTextPosition(AbstractButton.LEADING);
    
    close = new JButton("Close");
    close.setVerticalTextPosition(AbstractButton.CENTER);
    close.setHorizontalTextPosition(AbstractButton.LEADING);
    
    saveNameField.addActionListener(CAL_list[0]);
    save.addActionListener(CAL_list[1]);
    close.addActionListener(CAL_list[2]);
    
    contentPane.setOpaque(true);
    GroupLayout layout = new GroupLayout(contentPane);
    contentPane.setLayout(layout);
    
    ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup h1 = layout.createSequentialGroup();
    ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    SequentialGroup h3 = layout.createSequentialGroup();
    
    h2.addComponent(collectionsLabel);
    h2.addComponent(view0scrollPane);
    h2.addComponent(saveName);
    h2.addComponent(saveNameField);
    
    h3.addComponent(save);
    h3.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    h3.addComponent(close);
    
    h2.addGroup(h3);
    
    h1.addContainerGap();
    h1.addGroup(h2);
    h1.addContainerGap();
    
    hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
    layout.setHorizontalGroup(hGroup);
    
    ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup v1 = layout.createSequentialGroup();
    SequentialGroup v2 = layout.createSequentialGroup();
    ParallelGroup v3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    v2.addComponent(collectionsLabel);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addComponent(view0scrollPane);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    v2.addComponent(saveName);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addComponent(saveNameField);
    
    v3.addComponent(save);
    v3.addComponent(close);
    
    v1.addContainerGap();
    v1.addGroup(v2);
    v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v1.addGroup(v3);
    v1.addContainerGap();
    
    vGroup.addGroup(v1);
    layout.setVerticalGroup(vGroup);
  }
  
  private void createCollLoadWinContent(JPanel contentPane,
                                        fridge.action_handling.ClassActionListener[] CAL_list,
                                        JComboBox collList){
    JButton load, close;
    
    JLabel collectionsLabel = new JLabel();
    collectionsLabel.setText("Collections");
    
    load = new JButton("Load");
    load.setVerticalTextPosition(AbstractButton.CENTER);
    load.setHorizontalTextPosition(AbstractButton.LEADING);
    
    close = new JButton("Close");
    close.setVerticalTextPosition(AbstractButton.CENTER);
    close.setHorizontalTextPosition(AbstractButton.LEADING);
    
    collList.addActionListener(CAL_list[0]);
    load.addActionListener(CAL_list[1]);
    close.addActionListener(CAL_list[2]);
    
    contentPane.setOpaque(true);
    GroupLayout layout = new GroupLayout(contentPane);
    contentPane.setLayout(layout);
    
    ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup h1 = layout.createSequentialGroup();
    SequentialGroup h2 = layout.createSequentialGroup();
    ParallelGroup h3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    h2.addComponent(load);
    h2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED);
    h2.addComponent(close);
    
    h3.addComponent(collectionsLabel);
    h3.addComponent(collList);
    h3.addGroup(h2);
    
    h1.addContainerGap();
    h1.addGroup(h3);
    h1.addContainerGap();
    
    hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
    layout.setHorizontalGroup(hGroup);
    
    ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    SequentialGroup v1 = layout.createSequentialGroup();
    SequentialGroup v2 = layout.createSequentialGroup();
    ParallelGroup v3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    
    v3.addComponent(load);
    v3.addComponent(close);
    
    v2.addComponent(collectionsLabel);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addComponent(collList);
    v2.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
    v2.addGroup(v3);
    
    v1.addContainerGap();
    v1.addGroup(v2);
    v1.addContainerGap();
    
    vGroup.addGroup(v1);
    layout.setVerticalGroup(vGroup);
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
