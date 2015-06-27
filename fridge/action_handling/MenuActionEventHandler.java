package fridge.action_handling;

public class MenuActionEventHandler{
  private fridge.Fridge fridgeInstance;
  
  MenuActionEventHandler(fridge.Fridge fridgeInstance_ptr){
    fridgeInstance = fridgeInstance_ptr;
  }
  
  public void quit(){
    fridgeInstance.controlledExit();
  }
  
  public void viewHelp(){
    fridge.window_content.WindowCollection winColl;
    winColl = fridgeInstance.getWinCollection();
    
    winColl.addNew("HelpWin");
  }
}