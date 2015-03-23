package fridge.action_handling;

public class ContainerListeners{
  private fridge.action_handling.MyListSelectionListener lsListener;
  private fridge.action_handling.MyListListener listListener;
  private fridge.Fridge fridgeInstance;
  
  public ContainerListeners(fridge.Fridge FI_ptr){
    lsListener = new fridge.action_handling.MyListSelectionListener();
    listListener = new fridge.action_handling.MyListListener();
    fridgeInstance = FI_ptr;
  }
  
  public fridge.action_handling.MyListListener getListListener(){
    return listListener;
  }
  
  public fridge.action_handling.MyListSelectionListener getLSListener(){
    return lsListener;
  }
}