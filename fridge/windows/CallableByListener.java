package fridge.windows;

import java.nio.file.Path;

import javax.swing.JFrame;

public abstract class CallableByListener extends fridge.windows.MyWindow{
  protected fridge.window_content.WindowCollection winCollection;
  protected fridge.action_handling.MyListener[] listeners = null;
  //protected fridge.window_content.Menu menu = null;
  
  /*CallableByListener(JFrame frame){
    super(frame);
    //addListeners();
    givePtrToListeners();
  }*/
  
  CallableByListener(JFrame frame, 
                     fridge.action_handling.ClassListSelectionListener CLSL_ptr,
                     int par_myWindowIndex,
                     fridge.window_content.Menu menu,
                     fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    winCollection = winColl;
    menu.setContainingWindow(this);
    addListener(CLSL_ptr);
    givePtrToListeners();
  }
  
  /*CallableByListener(JFrame frame, fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                     fridge.Fridge fridgeInstance){*/
    //super(frame, fridgeInstance);
  CallableByListener(JFrame frame, 
                     fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                     int par_myWindowIndex,
                     fridge.window_content.Menu menu,
                     fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    int i;
    winCollection = winColl;
    menu.setContainingWindow(this);
    
    for (i = 0; i < CLSL_list.length; i++){
      addListener(CLSL_list[i]);
    }
    givePtrToListeners();
  }
  
  CallableByListener(JFrame frame,
                     fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                     fridge.action_handling.ClassActionListener[] CAL_list,
                     int par_myWindowIndex,
                     fridge.window_content.Menu menu,
                     fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    int i;
    winCollection = winColl;
    menu.setContainingWindow(this);
    
    for (i = 0; i < CLSL_list.length; i++){
      addListener(CLSL_list[i]);
    }
    for (i = 0; i < CAL_list.length; i++){
      addListener(CAL_list[i]);
    }
    givePtrToListeners();
  }
  
  /*public void listenerEvent(fridge.action_handling.MyListener ML_ptr){
    handleEvent(ML_ptr.getType(), ML_ptr);
  }*/
  
  public void listenerEvent(fridge.action_handling.MyListener ML_ptr){
    handleEvent(ML_ptr);
  }
  
  //protected abstract void addListeners();
  protected void addListener(fridge.action_handling.MyListener ML_ptr){
    int i;
    //fridge.action_handling.MyListener[] temp;
    if (null == listeners){
      listeners = new fridge.action_handling.MyListener[1];
      listeners[0] = ML_ptr;
    }
    else{
      fridge.action_handling.MyListener[] temp = new fridge.action_handling.MyListener[listeners.length + 1];
      for (i = 0; i < listeners.length; i++){
        temp[i] = listeners[i];
      }
      listeners = null;
      temp[i] = ML_ptr;
      listeners = temp;
      temp = null;
    }
  }
  
  //protected abstract void handleEvent(String listenerType);
  //protected abstract void handleEvent(String listenerType, fridge.action_handling.MyListener);
  /*protected void addGroup(String groupName){
    
  }
  
  protected void moveItemsToGroup(String groupName, Path[] newItems){
    
  }
  
  protected void addItemsToGroup(String groupName, Path[] newItems){
    
  }*/
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  // Note that at least all abstract methods concerned with groups could be moved here if
  // pointer to windowCollection was stored in this class instead of all the subclasses.
  // propably not. would need files and such.
  // or maybe if they were passed as arguments?? <-- i think this is wrong
  // so confused
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  
  protected abstract void handleEvent(fridge.action_handling.MyListener ML_ptr);
  /*protected abstract void addGroup(String name);
  protected abstract void addToGroup(String groupName, Path[] newItems);
  protected abstract void moveToGroup(String groupName, Path[] newItems);*/
  
  //protected abstract void givePtrToListeners();
  protected void givePtrToListeners(){
    int i;
    
    for (i = 0; i < listeners.length; i++){
      listeners[i].setListenedCall(this);
    }
  }
}
