package fridge.windows;

import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public abstract class CallableByListener extends fridge.windows.MyWindow{
  protected fridge.window_content.WindowCollection winCollection;
  protected fridge.action_handling.MyListener[] listeners = null;
  protected fridge.window_content.Menu menu = null;
  
  public CallableByListener(JFrame frame, 
                            fridge.action_handling.ClassListSelectionListener CLSL_ptr,
                            int par_myWindowIndex,
                            fridge.window_content.Menu par_menu,
                            fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    winCollection = winColl;
    menu = par_menu;
    menu.setContainingWindow(this);
    addListener(CLSL_ptr);
    givePtrToListeners();
  }
  
  public CallableByListener(JFrame frame, 
                            fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                            int par_myWindowIndex,
                            fridge.window_content.Menu par_menu,
                            fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    int i;
    winCollection = winColl;
    menu = par_menu;
    menu.setContainingWindow(this);
    
    for (i = 0; i < CLSL_list.length; i++){
      addListener(CLSL_list[i]);
    }
    givePtrToListeners();
  }
  
  public CallableByListener(JFrame frame,
                            fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                            fridge.action_handling.ClassActionListener[] CAL_list,
                            int par_myWindowIndex,
                            fridge.window_content.Menu par_menu,
                            fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    int i;
    winCollection = winColl;
    menu = par_menu;
    menu.setContainingWindow(this);
    
    for (i = 0; i < CLSL_list.length; i++){
      addListener(CLSL_list[i]);
    }
    for (i = 0; i < CAL_list.length; i++){
      addListener(CAL_list[i]);
    }
    givePtrToListeners();
  }
  
  public CallableByListener(JFrame frame,
                            fridge.action_handling.ClassActionListener[] CAL_list,
                            int par_myWindowIndex,
                            fridge.window_content.Menu par_menu,
                            fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    int i;
    winCollection = winColl;
    menu = par_menu;
    menu.setContainingWindow(this);
    
    for (i = 0; i < CAL_list.length; i++){
      addListener(CAL_list[i]);
    }
    givePtrToListeners();
    frame.pack();
  }
  
  public CallableByListener(JFrame frame,
                            fridge.action_handling.ClassActionListener[] CAL_list,
                            int par_myWindowIndex,
                            fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    winCollection = winColl;
    
    for (int i = 0; i < CAL_list.length; i++){
      addListener(CAL_list[i]);
    }
    givePtrToListeners();
    frame.pack();
  }
  
  public CallableByListener(JFrame frame,
                            fridge.action_handling.ClassListSelectionListener CLSListener,
                            fridge.action_handling.ClassActionListener[] CAL_list,
                            int par_myWindowIndex,
                            fridge.window_content.WindowCollection winColl){
    super(frame, par_myWindowIndex);
    winCollection = winColl;
    
    for (int i = 0; i < CAL_list.length; i++){
      addListener(CAL_list[i]);
    }
    addListener(CLSListener);
    givePtrToListeners();
    frame.pack();
  }
  
  protected abstract void handleEvent(fridge.action_handling.MyListener ML_ptr);
  protected abstract void addItemsToGroup(String groupName);
  protected abstract void moveItemsToGroup(String groupName);
  public abstract void updateContent(); 
  public abstract void updateViews();
  
  public void listenerEvent(fridge.action_handling.MyListener ML_ptr){
    handleEvent(ML_ptr);
  }
  
  public void updateMenu(){
    menu.update(winCollection);
  }
  
  public JMenuBar getMenuBar(){
    return frame.getJMenuBar();
  }
  
  protected void addListener(fridge.action_handling.MyListener ML_ptr){
    int i;

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
  
  public void addGroup(){
    winCollection.addNew("newGroupWin");
  }
  
  public void groupButtonMenuPress(String buttonName, String containingMenu){
    if ("Add to group" == containingMenu){
      addItemsToGroup(buttonName);
    }
    else if ("Move to group" == containingMenu){
      moveItemsToGroup(buttonName);
    }
  }
  
  protected void givePtrToListeners(){
    int i;
    
    for (i = 0; i < listeners.length; i++){
      listeners[i].setListenedCall(this);
    }
  }
}
