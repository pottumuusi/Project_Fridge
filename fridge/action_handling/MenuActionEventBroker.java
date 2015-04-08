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
      ((fridge.windows.CallableByListener)caller).openFile();
    }
    else if (eventSource.equals("Close")){
      caller.close();
    }
    else if (eventSource.equals("Settings")){
      //call with selected group name
      //(fridge.windows.CallableByListener)caller.moveItemsToGroup();
      
    }
    else if(eventSource.equals("Cut")){
      
    }
    else if(eventSource.equals("Group")){
      ((fridge.windows.CallableByListener)caller).addGroup();
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
