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
  
  public void openFile(){
  }
  
  protected void moveItemsToGroup(String groupName){
  }
  
  protected void addItemsToGroup(String groupName){
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    if ("ClassActionListener" == ML_ptr.getType()){
      if ("Cancel" == ML_ptr.getName()){
        close();
      }
      else if ("SaveCollection" == ML_ptr.getName()){
        winCollection.addNew("collectionSave", myWindowIndex);
      }
      else if ("LoadCollection" == ML_ptr.getName()){
        winCollection.addNew("collectionLoad", myWindowIndex);
      }
      else if ("DeleteCollection" == ML_ptr.getName()){
        winCollection.addNew("collectionDelete", myWindowIndex);
      }
    }
  }
}