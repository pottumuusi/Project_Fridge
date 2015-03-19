package fridge.action_handling;

public class MenuActionEventBroker{
  
  
  public void solveAction(String eventSource, String className,
                          fridge.action_handling.MenuActionEventHandler MAE_handler,
                          fridge.Fridge fridgeInstance){
    System.out.println("*MAE_broker invokingAction for*\n"
                      + "Event source: " + eventSource
                      + " (an instance of " + className + ")");
    
    if (eventSource.equals("Quit")){
      invokeQuit(MAE_handler, fridgeInstance);
    }
  }
  
  private void invokeQuit(fridge.action_handling.MenuActionEventHandler MAE_handler,
                          fridge.Fridge fridgeInstance){
    String quitInterruptReason;
    
    quitInterruptReason = MAE_handler.quit(fridgeInstance);
    System.out.println("[DEBUG] Program could not exit.\nReason: " + quitInterruptReason);
    /* Hide menus and write data. Use MAE_handler for this? 
     * Or maybe MAE_handler for window hiding and if there is
     * file handler use it to save data. 
     */
  }
}