package fridge.windows;

import javax.swing.JFrame;

public class HelpWindow extends fridge.windows.CallableByListener{
  private int selectedListIndex;
  private int myWindowIndex;
  //private fridge.action_handling.MyListSelectionListener LSListener;
  //private fridge.action_handling.ClassListSelectionListener CLSListener;
  
  /*public HelpWindow(JFrame frame){
    super(frame);
    System.out.println("Help window created!");
  }*/
  
  public HelpWindow(JFrame frame, fridge.action_handling.ClassListSelectionListener CLSL_ptr){
    super(frame, CLSL_ptr);
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
    
    switch (selectedListIndex){
    case 0:
      System.out.println("Should print text for index 0");
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