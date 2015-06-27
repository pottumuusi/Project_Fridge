package fridge.windows;

import javax.swing.JFrame;

import java.nio.file.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Container;
import java.awt.Component;

import javax.accessibility.AccessibleEditableText;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleText;
import javax.swing.JTextArea;

public class HelpWindow extends fridge.windows.CallableByListener{
  private int selectedListIndex;
  private JTextArea helpText;
  
  public HelpWindow(fridge.window_content.WindowCollection winColl,
                    fridge.window_content.WindowMaker winMaker,
                    fridge.action_handling.ClassListSelectionListener CLSL_ptr,
                    JTextArea textArea_ptr,
                    fridge.window_content.Menu menu,
                    int par_myWindowIndex){
    super(winMaker.newHelpWin(winColl, CLSL_ptr, textArea_ptr, menu), CLSL_ptr, par_myWindowIndex, menu, winColl);
    
    helpText = textArea_ptr;
    selectedListIndex = 0;
    updateHelpText();
  }
  
  protected void moveItemsToGroup(String groupName){
  }
  
  protected void addItemsToGroup(String groupName){
  }
  
  public void updateViews(){
  }
  
  public void updateContent(){
    updateMenu();
  }
  
  public void openFile(){
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    int[] selectedIndexes;
    
    switch (ML_ptr.getType()){
    case "ClassListSelectionListener":
      selectedIndexes = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
      selectedIndexes = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      CLSL_updateIndexes(selectedIndexes);
      break;
    }
  }
  
  private void updateSelectedListIndex(int newListIndex){
    selectedListIndex = newListIndex;
    updateHelpText();
  }
  
  private void updateHelpText(){
    int i;
    InputStream in = null;
    
    switch (selectedListIndex){
    case 0:
      readTextFile("recommendedRead.txt");
      break;
    case 1:
      readTextFile("quickAccess.txt");
      break;
    case 2:
      readTextFile("quickSave.txt");
      break;
    case 3:
      readTextFile("quickLoad.txt");
      break;
    case 4:
      readTextFile("copyPasta.txt");
      break;
    case 5:
      readTextFile("group.txt");
      break;
    case 6:
      readTextFile("delete.txt");
      break;
    }
  }
  
  private void readTextFile(String fileName){
    InputStream in = null;
    
    in = getClass().getResourceAsStream(fileName);
    try{
      helpText.read(new InputStreamReader(in), null);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
  
  private void CLSL_updateIndexes(int[] selectedIndexes){
    if (1 == selectedIndexes.length){
      selectedListIndex = selectedIndexes[0]; // does not work yet? :D
      updateHelpText();
    }
  }
}
