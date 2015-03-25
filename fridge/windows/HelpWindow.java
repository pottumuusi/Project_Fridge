package fridge.windows;

import javax.swing.JFrame;

import java.awt.Container;
import java.awt.Component;

import javax.accessibility.AccessibleEditableText;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleText;
import javax.swing.JTextArea;

public class HelpWindow extends fridge.windows.CallableByListener{
  private int selectedListIndex;
  private int myWindowIndex;
  private JTextArea helpTexts;
  //private fridge.action_handling.MyListSelectionListener LSListener;
  //private fridge.action_handling.ClassListSelectionListener CLSListener;
  
  /*public HelpWindow(JFrame frame){
    super(frame);
    System.out.println("Help window created!");
  }*/
  
  public HelpWindow(JFrame frame, fridge.action_handling.ClassListSelectionListener CLSL_ptr){
    super(frame, CLSL_ptr);
  }
  
  public HelpWindow(fridge.window_content.WindowCollection winColl,
                    fridge.window_content.WindowMaker winMaker,
                    fridge.action_handling.ClassListSelectionListener CLSL_ptr,
                    JTextArea textArea_ptr){
    super(winMaker.newHelpWin(winColl, CLSL_ptr, textArea_ptr), CLSL_ptr);
    helpTexts = textArea_ptr;
  }
  
  /*public HelpWindow(JFrame frame, fridge.action_handling.MyListSelectionListener LSL_ptr){
    super(frame);
    LSListener = LSL_ptr;
  }*/
  
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
    int componentCount;
    Container cont;
    Component[] comps;
    AccessibleContext context;
    AccessibleComponent accComp;
    AccessibleText text;
    AccessibleEditableText editableText;
    
    //comps = null;
    cont = frame.getContentPane();
    componentCount = cont.getComponentCount();
    
    switch (selectedListIndex){
    case 0:
      //System.out.println("Should print text for index 0");
      
      comps = new Component[cont.getComponentCount()];
      System.out.println("component count of frames content pane is " + cont.getComponentCount());
      
      if (null == cont.getComponents()){
        System.out.println("getComponents returned null");
      }
      else{
        System.out.println("getComponents did not return null");
      }
      comps = cont.getComponents();
      
      System.out.println("printing component names:");
      for (i = 0; i < componentCount; i++){
        System.out.println("\tcomponent " + i + " == " + comps[i].getName());
        context = comps[i].getAccessibleContext();
        if (null == context){
          System.out.println("\t\tcontext is null");
        }
        
        /*accComp = context.getAccessibleComponent();
        if (null == accComp){
          System.out.println("\t\taccComp is null");
        }
        else{
          System.out.println("\t\twe gots accessibleComponent!!");
        }*/
        
        System.out.println("\t\taccessible description is: " + context.getAccessibleDescription());
        
        System.out.println("\t\taccessibleName is: " + context.getAccessibleName());
        
        editableText = context.getAccessibleEditableText();
        if (null == editableText){
          System.out.println("\t\teditableText is null");
        }
        else{
          System.out.println("\t\tthere is something in the editableText");
        }
        
        text = context.getAccessibleText();
        if (null == text){
          System.out.println("\t\ttext is null");
        }
        else{
          System.out.println("\t\twe got text object!!");
        }
      }
      
      //need frame.getContentPane()
      //remember that HelpWindow has frame. it is in the super class
      break;
    case 1:
      break;
    }
    System.out.println("[DEBUG] print correct text to textArea here");
  }
  
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