package fridge.action_handling;

public class MenuActionEventBroker{
  public void invokeAction(String eventSource, String className){
    System.out.println("*MAE_broker invokingAction for*\n"
                      + "Event source: " + eventSource
                      + " (an instance of " + className + ")");
    
    
  }
}