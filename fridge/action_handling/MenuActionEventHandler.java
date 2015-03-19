package fridge.action_handling;

public class MenuActionEventHandler{
  //public void ()
  
  public String quit(fridge.Fridge fridgeInstance){
    String[] programStatus;
    int i;
    
    programStatus = fridgeInstance.getProgramStatus();
    
    for (i = 0; i < programStatus.length; i++){
      if (programStatus[i].equals("no_unsaved_data")){
        System.exit(0);
      }
    }
    return "Program has unsaved data";
  }
}