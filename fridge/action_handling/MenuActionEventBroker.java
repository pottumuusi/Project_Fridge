package fridge.action_handling;

public class MenuActionEventBroker{
  //earlier name of this method was: solveAction
  public void brokerCall(String eventSource,
                         String className,
                         fridge.action_handling.MenuActionEventHandler MAE_handler,
                         fridge.windows.MyWindow caller){
    System.out.println("*MAE_broker invokingAction for*\n"
                      + "Event source: " + eventSource
                      + " (an instance of " + className + ")");
    
    if (eventSource.equals("Quit")){
      invokeQuit(MAE_handler);
    }
    else if (eventSource.equals("View help")){
      invokeViewHelp(MAE_handler);
    }
    else if (eventSource.equals("Open file")){
      ((fridge.windows.FileWindow)caller).openFile();
    }
    else if (eventSource.equals("Close")){
      caller.close();
    }
    else if (eventSource.equals("Settings")){
      //call with selected group name
      //(fridge.windows.CallableByListener)caller.moveItemsToGroup();
      
    }
    else if(eventSource.equals("Exclude")){
      ((fridge.windows.FileWindow)caller).exclude();
    }
    else if(eventSource.equals("Delete")){
      ((fridge.windows.FileWindow)caller).delete();
    }
    else if(eventSource.equals("Paste")){
      ((fridge.windows.FileWindow)caller).paste();
    }
    else if(eventSource.equals("Copy")){
      ((fridge.windows.FileWindow)caller).copy();
    }
    else if(eventSource.equals("Cut")){
      ((fridge.windows.FileWindow)caller).cut();
    }
    else if(eventSource.equals("Group")){
      ((fridge.windows.CallableByListener)caller).addGroup();
    }
    else if(eventSource.equals("Folder")){
      ((fridge.windows.FileWindow)caller).newFolder();
    }
  }
  
  public void brokerCall(String eventSource,
                         String className,
                         fridge.action_handling.MenuActionEventHandler MAE_handler,
                         fridge.windows.MyWindow caller,
                         String eventContainingMenu){
    
    ((fridge.windows.CallableByListener)caller).groupButtonMenuPress(eventSource,
                                                                     eventContainingMenu);
  }
  
  private void invokeQuit(fridge.action_handling.MenuActionEventHandler MAE_handler){
    //String quitInterruptReason;
    
    //quitInterruptReason = MAE_handler.quit();
    MAE_handler.quit();
    //System.out.println("[DEBUG] Program could not exit.\nReason: " + quitInterruptReason);
    /* Hide menus and write data. Use MAE_handler for this? 
     * Or maybe MAE_handler for window hiding and if there is
     * file handler use it to save data. 
     */
    
  }
  
  private void invokeViewHelp(fridge.action_handling.MenuActionEventHandler MAE_handler){
    MAE_handler.viewHelp();
    System.out.println("[DEBUG]viewHelp invoked");
  }
}
