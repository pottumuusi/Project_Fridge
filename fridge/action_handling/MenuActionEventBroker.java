package fridge.action_handling;

public class MenuActionEventBroker{
  
  //earlier name of this method was: solveAction
  public void brokerCall(String eventSource, String className,
                          fridge.action_handling.MenuActionEventHandler MAE_handler){
    System.out.println("*MAE_broker invokingAction for*\n"
                      + "Event source: " + eventSource
                      + " (an instance of " + className + ")");
    
    if (eventSource.equals("Quit")){
      invokeQuit(MAE_handler);
    }
    else if (eventSource.equals("View help")){
      invokeViewHelp(MAE_handler);
    }
  }
  
  private void invokeQuit(fridge.action_handling.MenuActionEventHandler MAE_handler){
    String quitInterruptReason;
    
    quitInterruptReason = MAE_handler.quit();
    System.out.println("[DEBUG] Program could not exit.\nReason: " + quitInterruptReason);
    /* Hide menus and write data. Use MAE_handler for this? 
     * Or maybe MAE_handler for window hiding and if there is
     * file handler use it to save data. 
     */
  }
  
  private void invokeViewHelp(fridge.action_handling.MenuActionEventHandler MAE_handler){
    MAE_handler.viewHelp();
  }
}