package fridge.action_handling;

public class MenuActionEventHandler{
  private fridge.Fridge fridgeInstance;
  
  MenuActionEventHandler(fridge.Fridge fridgeInstance_ptr){
    fridgeInstance = fridgeInstance_ptr;
  }
  
  //public void ()
  
  /*public String quit(){
    String[] programStatus;
    int i;
    
    fridgeInstance.controlledExit();
    
    programStatus = fridgeInstance.getProgramStatus();
    
    for (i = 0; i < programStatus.length; i++){
      if (programStatus[i].equals("no_unsaved_data")){
        System.out.println("No unsaved data. Exiting");
        System.exit(0);
      }
    }
    return "Program has unsaved data";
  }*/
  
  public void quit(){
    fridgeInstance.controlledExit();
  }
  
  public void viewHelp(){
    fridge.window_content.WindowCollection winColl;
    System.out.println("[DEBUG] create help window here");
    winColl = fridgeInstance.getWinCollection();
    
    winColl.addNew("HelpWin");
  }
}