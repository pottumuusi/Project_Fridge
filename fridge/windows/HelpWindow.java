package fridge.windows;

import javax.swing.JFrame;

public class HelpWindow extends fridge.windows.CallableByListener{
  private int selectedListIndex;
  //private fridge.action_handling.MyListSelectionListener LSListener;
  private fridge.action_handling.ClassListSelectionListener CLSListener;
  
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
    System.out.println("[DEBUG] print correct text to textArea here");
  }
  
  protected void handleEvent(String listenerType){
    int[] selectedIndexes = new int[CLSListener.getSelectedIndexesLen()];
    selectedIndexes = CLSListener.getSelectedIndexes();
  }
  
  //private printSelected
  
  /*private listListenerCall(int ){
  }*/
}