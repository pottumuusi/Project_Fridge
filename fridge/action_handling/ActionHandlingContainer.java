package fridge.action_handling;

public class ActionHandlingContainer{
  private fridge.Fridge fridgeInstance;
  private fridge.action_handling.MenuActionEventBroker MAE_broker;
  private fridge.action_handling.MenuActionEventHandler MAE_handler;
  
  public ActionHandlingContainer(){
    System.out.println("[DEBUG] ActionHandlingContainer can't be created without providing fridgeInstance");
    System.exit(1);
  }
  
  public ActionHandlingContainer(fridge.Fridge fridgeInstance_ptr){
    MAE_broker = new fridge.action_handling.MenuActionEventBroker();
    MAE_handler = new fridge.action_handling.MenuActionEventHandler();
    fridgeInstance = fridgeInstance_ptr;
  }
  
  public void MAE_brokerCall(String eventSource, String instance){
    MAE_broker.brokerCall(eventSource, instance, MAE_handler, fridgeInstance);
  }
}