package fridge.windows;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;

public class MainWindow1 extends fridge.windows.CallableByListener implements DocumentListener, ActionListener{
  //private fridge.filesystem.fileOperator // = fridge.filesystem.fileOperator
  //private fridge.filesystem.folderOperator // = new fridge.filesystem.folderOperator
  //private fridge.filesystem.groupOperator // = new fridge.filesystem.groupOperator
  private Path currFolder;
  private JTextField folderNameField;
  private JList<String> view0;
  private JList<String> view1;
  private int[] selectedFolders;
  private int[] selectedQuickAccess;
  private int myWindowIndex;
  private String currentPath;
  private String[] quickAccessFolders;
  
  public MainWindow1(fridge.window_content.WindowCollection winColl,
                     fridge.window_content.WindowMaker winMaker,
                     fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs,
                     fridge.action_handling.ClassActionListener[] CAL_ptrs,
                     JTextField fn_par,
                     JList view0_par,
                     JList view1_par){
    super(winMaker.newMainWin1(winColl, CLSL_ptrs, CAL_ptrs, fn_par, view0_par, view1_par), CLSL_ptrs, CAL_ptrs);
    selectedFolders = null;
    quickAccessFolders = null;
    selectedQuickAccess = null;
    
    folderNameField = fn_par;
    view0 = view0_par;
    view1 = view1_par;
    //if (Files.isDirectory(folderNameField.getText())){
    currFolder = Paths.get(folderNameField.getText());
    //}
    //else{
    //  currFolder = null;
    //} 
    //folderNameField.getDocument().addDocumentListener(this);
    //folderNameField.addActionListener(this);
    /*InputMap im = folderNameField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = folderNameField.getActionMap();
    im.put(KeyStroke.getKeyStroke("ENTER"),*/
    
    System.out.println("[DEBUG] before pack()");
    frame.pack();
    System.out.println("[DEBUG] after pack()");
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    //int[] selectedIndexes;
    String tempStore = null;
    int i;
    
    //make it impossible to select the first row of folders of groups
    //if selectedFolders[i] == 0 then copy all else to selectedFolders
    //and update it to view?
    
    switch (ML_ptr.getType()){
    case "ClassListSelectionListener":
      if ("folder" == ML_ptr.getName()){
        selectedFolders = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedFolders = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
        printSelectedFiles();
      }
      else if ("quickAccess" == ML_ptr.getName()){
        selectedQuickAccess = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedQuickAccess = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
      break;
    case "ClassActionListener":
      if ("folderShowGroup" == ML_ptr.getName()){
        String[] newContent = {"How", "do", "you", "like", "me", "now"};
        System.out.println("folderShowGroup press");
        view0.setListData(newContent);
      }
      else if ("quickSave" == ML_ptr.getName()){
        System.out.println("quickSave press");
        addToQuickAccess();
      }
      else if ("quickLoad" == ML_ptr.getName()){
        System.out.println("quickLoad press");
        loadSelectedQuickAccessFolder();
      }
      else if ("qa_showGroup" == ML_ptr.getName()){
        System.out.println("qa_showGroup press");
      }
      else if ("qa_operations" == ML_ptr.getName()){
        System.out.println("qa_operations press");
      }
      else if ("folderNameField" == ML_ptr.getName()){
        //System.out.println("actionCommand == " + ((fridge.action_handling.ClassActionListener)ML_ptr).getActionCommand());
        tempStore = currFolder.toString();
        currFolder = Paths.get(((fridge.action_handling.ClassActionListener)ML_ptr).getActionCommand());
        if (Files.isDirectory(currFolder)){
          updateFolderContent();
        }
        else{
          currFolder = Paths.get(tempStore);
        }
      }
      break;
    }
  }
  
  private void updateFolderContent(){
    String[] newContent = null;
    String[] temp = null;
    String currFileName = null;
    int folderFileCount = 0;
    int testFileCount = 0;
    int i;
    //String temp;
    //System.out.println("[DEBUG] null new content size = " + newContent.length);
    //getFiles(currentPath);
    //newContent = createFolderViewContent();
    if (Files.isDirectory(currFolder)){
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(currFolder)){
        for (Path file: stream){
          //temp = file.getFileName();
          //if ('.' != file.getFileName().getCharAt(0)){
          currFileName = file.getFileName().toString();
          if ('.' != currFileName.charAt(0) || 
              '.' == currFileName.charAt(1)){
            if (null == newContent){
              newContent = new String[folderFileCount + 1];
              newContent[folderFileCount] = currFileName;
              folderFileCount++;
            }
            else{
              //temp = new String[folderFileCount];
              temp = newContent;
              System.out.println(file.getFileName());
              newContent = new String[folderFileCount + 1];
              for (i = 0; i < folderFileCount; i++){
                newContent[i] = temp[i];
              }
              newContent[folderFileCount] = currFileName;
              folderFileCount++;
            }
          }
        }
      }
      catch (IOException | DirectoryIteratorException x){
        System.err.println(x);
      }
    }
    else{
      //errorMessage("Given path does not match a directory");
    }
    
    System.out.println("newContent:");
    for (i = 0; i < newContent.length; i++){
      System.out.println("    " + newContent[i]);
    }
    
    view0.setListData(newContent);
  }
  
  private void addToQuickAccess(){
    String[] storeArray = null;
    //String[] temp = null;
    int i = 0;
    int maxIndex = -1;
    int[] allIndices = null;
    
    maxIndex = view1.getLastVisibleIndex();
    if (maxIndex > -1){
      storeArray = new String[maxIndex + 2];
      allIndices = new int[maxIndex + 1];
      //temp = new String[maxIndex + 1];
      
      for (i = 0; i <= maxIndex; i++){
        //allIndices[i] = i;
        view1.setSelectedIndex(i);
        storeArray[i] = view1.getSelectedValue();
      }
      
      addQuickAccessFolder(currFolder.toString());
      storeArray[i] = currFolder.getFileName().toString();
      view1.setListData(storeArray);
      
      System.out.println("[DEBUG] storeArray:");
      for (i = 0; i < storeArray.length; i++){
        System.out.println("    " + storeArray[i]);
      }
      
      System.out.println("[DEBUG] quickAccessFolders:");
      for (i = 0; i < quickAccessFolders.length; i++){
        System.out.println("    " + quickAccessFolders[i]);
      }
    }
    else{
      storeArray = new String[1];
      addQuickAccessFolder(currFolder.toString());
      storeArray[0] = currFolder.getFileName().toString();
      System.out.println("[DEBUG] storeArray[0] = " + storeArray[0]);
      view1.setListData(storeArray);
    }
  }
  
  private void addQuickAccessFolder(String newFolder){
    int i;
    String[] temp = quickAccessFolders;
    
    if (null == quickAccessFolders){
      quickAccessFolders = new String[1];
      quickAccessFolders[0] = newFolder;
    }
    else{
      quickAccessFolders = new String[temp.length + 1];
      for (i = 0; i < temp.length; i++){
        quickAccessFolders[i] = temp[i];
      }
      quickAccessFolders[i] = newFolder;
    }
  }
  
  private void addSelectedToQuickAccess(){
    //String[] newContent = null;
    
    //newContent = new String[selectedFolders.length];
    //newContent view0.getSelectedValuesList();
    
    //copy old content to newContent using selectedFolders
    //add new content to newContent
    //view1.setListData(newContent);
  }
  
  private void loadSelectedQuickAccessFolder(){
    int QAF_index;
    if (1 == selectedQuickAccess.length){
      QAF_index = view1.getMinSelectionIndex();
      currFolder = Paths.get(quickAccessFolders[QAF_index]);
      folderNameField.setText(quickAccessFolders[QAF_index]);
      updateFolderContent();
      //currFolder = view1.getListData(selectedQuickAccess[0]);
      //updateFolderContent();
    }
    else{
      //errorMessage("Cannot load multiple Quick Access Folders");
    }
  }
  
  private void openFile(){
  }
  
  private void openSelectedFolder(){
    if (1 == selectedFolders.length){
      //currFolder = view0.getListData(selectedFolders[0]);
      //folderNameField.setText(selectedFolders[0]);
      //updateFolderContent();
    }
    else{
      //errorMessage("Cannot load multiple Quick Access Folders");
    }
  }
  
  private void getFiles (){
  }
  
  private String[] createFolderViewContent(){
    return null;
  }
  
  private void textFieldString(){
    //System.out.println("fieldStr == " + folderNameFieldField.getText());
  }
  
  private void printSelectedFiles(){
    int i;
    
    System.out.print("Selected files:\n\t");
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
