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
  private JTextField folderName;
  private JList view0;
  private JList view1;
  private int[] selectedFolders;
  private int[] selectedQuickAccess;
  private int myWindowIndex;
  private String currentPath;
  
  public MainWindow1(fridge.window_content.WindowCollection winColl,
                     fridge.window_content.WindowMaker winMaker,
                     fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs,
                     fridge.action_handling.ClassActionListener[] CAL_ptrs,
                     JTextField fn_par,
                     JList view0_par,
                     JList view1_par){
    super(winMaker.newMainWin1(winColl, CLSL_ptrs, CAL_ptrs, fn_par, view0_par, view1_par), CLSL_ptrs, CAL_ptrs);
    selectedFolders = null;
    selectedQuickAccess = null;
    
    folderName = fn_par;
    view0 = view0_par;
    view1 = view1_par;
    currFolder = Paths.get(folderName.getText());
    //folderName.getDocument().addDocumentListener(this);
    //folderName.addActionListener(this);
    /*InputMap im = folderName.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = folderName.getActionMap();
    im.put(KeyStroke.getKeyStroke("ENTER"),*/
    
    System.out.println("[DEBUG] before pack()");
    frame.pack();
    System.out.println("[DEBUG] after pack()");
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    //int[] selectedIndexes;
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
      }
      else if ("quickLoad" == ML_ptr.getName()){
        System.out.println("quickLoad press");
      }
      else if ("qa_showGroup" == ML_ptr.getName()){
        System.out.println("qa_showGroup press");
      }
      else if ("qa_operations" == ML_ptr.getName()){
        System.out.println("qa_operations press");
      }
      else if ("folderName" == ML_ptr.getName()){
        //System.out.println("actionCommand == " + ((fridge.action_handling.ClassActionListener)ML_ptr).getActionCommand());
        currFolder = Paths.get(((fridge.action_handling.ClassActionListener)ML_ptr).getActionCommand());
        updateFolderContent();
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
    
    System.out.println("newContent:");
    for (i = 0; i < newContent.length; i++){
      System.out.println("    " + newContent[i]);
    }
    
    view0.setListData(newContent);
  }
  
  private void getFiles (){
  }
  
  private String[] createFolderViewContent(){
    return null;
  }
  
  private void textFieldString(){
    //System.out.println("fieldStr == " + folderName.getText());
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
