package fridge.windows;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;

public class MainWindow1 extends fridge.windows.CallableByListener implements DocumentListener, ActionListener{
  private JTextField folderName;
  private int[] selectedFolders;
  private int[] selectedGroups;
  private int myWindowIndex;
  
  public MainWindow1(fridge.window_content.WindowCollection winColl,
                     fridge.window_content.WindowMaker winMaker,
                     fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs,
                     fridge.action_handling.ClassActionListener[] CAL_ptrs,
                     JTextField fn_par){
    super(winMaker.newMainWin1(winColl, CLSL_ptrs, CAL_ptrs, fn_par), CLSL_ptrs, CAL_ptrs);
    selectedFolders = null;
    selectedGroups = null;
    
    folderName = fn_par;
    folderName.getDocument().addDocumentListener(this);
    //folderName.addActionListener(this);
    /*InputMap im = folderName.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = folderName.getActionMap();
    im.put(KeyStroke.getKeyStroke("ENTER"),*/
    
    System.out.println("[DEBUG] before pack()");
    frame.pack();
    System.out.println("[DEBUG] after pack()");
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    int[] selectedIndexes;
    int i;
    
    //make it impossible to select the first row of folders of groups
    //if selectedFolders[i] == 0 then copy all else to selectedFolders
    //and update it to view?
    
    switch (ML_ptr.getType()){
    case "ClassListSelectionListener":
      if ("folder" == ML_ptr.getName()){
        selectedFolders = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedFolders = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
        printSelectedFolders();
        textFieldString();
        folderName.setText("set text");
      }
      else if ("quickAccess" == ML_ptr.getName()){
        selectedGroups = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedGroups = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
      break;
    case "ClassActionListener":
      System.out.println("actionCommand == " + ((fridge.action_handling.ClassActionListener)ML_ptr).getActionCommand());
      break;
    }
  }
  
  
  
  private void textFieldString(){
    //System.out.println("fieldStr == " + folderName.getText());
  }
  
  private void printSelectedFolders(){
    int i;
    
    System.out.print("Selected folders:\n\t");
    for (i = 0; i < selectedFolders.length; i++){
      System.out.print(selectedFolders[i] + ", ");
    }
  }
  
  public void insertUpdate(DocumentEvent ev) {
    textFieldString();
  }

  public void removeUpdate(DocumentEvent ev) {
    textFieldString();
  }

  public void changedUpdate(DocumentEvent ev) {
  }
  
  public void actionPerformed(ActionEvent e){
    System.out.println("old");
  }
}