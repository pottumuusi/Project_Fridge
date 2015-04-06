package fridge.windows;

public class QAOperations extends fridge.windows.CallableByListener{
  private String operationsType;
  
  public QAOperations(fridge.window_content.WindowCollection winColl,
                           fridge.window_content.WindowMaker winMaker,
                           fridge.action_handling.ClassActionListener[] CAL_ptrs,
                           int par_myWindowIndex,
                           String par_operationsType){
    super(winMaker.newQAOperationsWin(winColl, CAL_ptrs),
          CAL_ptrs, par_myWindowIndex, winColl);
    
    operationsType = par_operationsType;
  }
  
  public void updateViews(){
  }
  
  public void updateContent(){
  }
  
  protected void moveItemsToGroup(String groupName){
  }
  
  protected void addItemsToGroup(String groupName){
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    if ("ClassActionListener" == ML_ptr.getType()){
      if ("Close" == ML_ptr.getName()){
        close();
      }
    }
  }
}