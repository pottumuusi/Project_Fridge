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
  //private fridge.action_handling.MyListSelectionListener LSListener;
  //private fridge.action_handling.ClassListSelectionListener CLSListener;
  
  /*public HelpWindow(JFrame frame){
    super(frame);
    System.out.println("Help window created!");
  }*/
  
  /*public HelpWindow(JFrame frame, fridge.action_handling.ClassListSelectionListener CLSL_ptr){
    super(frame, CLSL_ptr);
  }*/
  
  /*public HelpWindow(JFrame frame, fridge.action_handling.MyListSelectionListener LSL_ptr){
    super(frame);
    LSListener = LSL_ptr;
  }*/
  
  public HelpWindow(fridge.window_content.WindowCollection winColl,
                    fridge.window_content.WindowMaker winMaker,
                    fridge.action_handling.ClassListSelectionListener CLSL_ptr,
                    JTextArea textArea_ptr,
                    fridge.window_content.Menu menu,
                    int par_myWindowIndex){
    super(winMaker.newHelpWin(winColl, CLSL_ptr, textArea_ptr, menu), CLSL_ptr, par_myWindowIndex, menu, winColl);
    
    //menu.setContainingWindow(this);
    
    helpText = textArea_ptr;
    selectedListIndex = 0;
    updateHelpText();
  }
  
  /*protected void addGroup(String groupName){
    
  }
  
  protected void moveToGroup(String groupName, Path[] newItems){
    
  }
  
  protected void addToGroup(String groupName, Path[] newItems){
    
  }*/
  
  //protected void handleEvent(String listenerType, fridge.action_handling.MyListener ML_ptr){
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    int[] selectedIndexes;
    //System.out.println("[DEBUG] CLSListener.getSelectedIndexesLen() ==");
    
    //switch (listenerType){
    switch (ML_ptr.getType()){
    case "ClassListSelectionListener":
      System.out.println("[DEBUG] HelpWindow detected a ClassListSelectionListener event. handling... :O");
      //selectedIndexes = new int[CLSListener.getSelectedIndexesLen()];
      //selectedIndexes = CLSListener.getSelectedIndexes();
      //selectedIndexes = new int[ML_ptr.getSelectedIndexesLen()];
      selectedIndexes = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
      selectedIndexes = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      CLSL_updateIndexes(selectedIndexes);
      break;
    }
    
    //System.out.println("[DEBUG] HelpWindow.handleEvent:\n\ttype of calling listener is: " + listenerType);
    
  }
  
  
  /*protected void addListeners(){
    //listeners  CLSListener = CLSL_ptr;
    addListener(CLSListener);
    //CLSListener = null;
  }*/
  
  private void updateSelectedListIndex(int newListIndex){
    selectedListIndex = newListIndex;
    updateHelpText();
  }
  
  private void updateHelpText(){
    int i;
    InputStream in = null;
    
    switch (selectedListIndex){
    case 0:
      in = getClass().getResourceAsStream("testText1.txt");
      try{
        helpText.read(new InputStreamReader(in), null);
      }
      catch (IOException e){
        e.printStackTrace();
      }
      break;
    case 1:
      in = getClass().getResourceAsStream("testText2.txt");
      try{
        helpText.read(new InputStreamReader(in), null);
      }
      catch (IOException e){
        e.printStackTrace();
      }
      break;
    }
    System.out.println("[DEBUG] print correct text to textArea here");
  }
  
  
  private void CLSL_updateIndexes(int[] selectedIndexes){
    if (1 == selectedIndexes.length){
      selectedListIndex = selectedIndexes[0]; // does not work yet
      updateHelpText();
    }
  }
  
  //private printSelected
  
  /*private listListenerCall(int ){
  }*/
}
