package fridge.windows;

import javax.swing.JList;
import javax.swing.JTextField;


public class CollectionSave extends CallableByListener{
  private JTextField saveNameField;
  private JList<String> view0;
  private String saveWinType;
  
  public CollectionSave(fridge.window_content.WindowCollection winColl,
                        fridge.window_content.WindowMaker winMaker,
                        fridge.action_handling.ClassListSelectionListener CLSL_ptr,
                        fridge.action_handling.ClassActionListener[] CAL_ptrs,
                        JTextField par_nameField,
                        JList par_view0,
                        int par_myWindowIndex,
                        String par_saveWinType){
    
    super(winMaker.newCollSaveWin(winColl, CLSL_ptr, CAL_ptrs, par_nameField, par_view0, par_saveWinType),
          CLSL_ptr, CAL_ptrs, par_myWindowIndex, winColl);
    
    saveWinType = par_saveWinType;
    saveNameField = par_nameField;
    view0 = par_view0;
    frame.pack();
  }
  
  public void openFile(){
  }
  
  public void updateViews(){
  }
  
  public void updateContent(){
  }
  
  protected void addItemsToGroup(String groupName){
  }
  
  protected void moveItemsToGroup(String groupName){
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    if ("ClassActionListener" == ML_ptr.getType()){
      if ("Close" == ML_ptr.getName()){
        close();
      }
    }
  }
}