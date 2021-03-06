package fridge.action_handling;

public class ActionHandlingContainer{
  private fridge.action_handling.MenuActionEventBroker MAE_broker;
  private fridge.action_handling.MenuActionEventHandler MAE_handler;
  
  public ActionHandlingContainer(){
    System.err.println("[DEBUG] ActionHandlingContainer can't be created without providing fridgeInstance");
    System.exit(1);
  }
  
  public ActionHandlingContainer(fridge.Fridge fridgeInstance_ptr){
    MAE_broker = new fridge.action_handling.MenuActionEventBroker();
    MAE_handler = new fridge.action_handling.MenuActionEventHandler(fridgeInstance_ptr);
    
    /*fridgeInstance could be passed to MenuActionEventHandler as parameter and stored to private
     * variable in there. then it would not need to be passed every time in the broker call
     * method. This could be good because fridgeInstance is not needed every time when 
     * brokerCall is executed!!! Action handler then no longer needs to hold fridgeInstance.
     */
  }
  
  public void MAE_brokerCall(String eventSource, String instance, fridge.windows.MyWindow caller){
    MAE_broker.brokerCall(eventSource, instance, MAE_handler, caller);
  }
  
  public void MAE_brokerCall(String eventSource, String instance,
                             fridge.windows.MyWindow caller, String sourceName){
    MAE_broker.brokerCall(eventSource, instance, MAE_handler, caller, sourceName);
  }
  
  public void CA_brokerCall(){}
}
