package fridge.windows;

import javax.swing.JTextField;

public class NewGroupWindow extends fridge.windows.CallableByListener{
  private JTextField nameField;
  
  public NewGroupWindow(fridge.window_content.WindowCollection winColl,
                        fridge.window_content.WindowMaker winMaker,
                        fridge.action_handling.ClassActionListener[] CAL_ptrs,
                        JTextField par_nameField,
                        fridge.window_content.Menu menu,
                        int par_myWindowIndex){
    super(winMaker.newNewGroupWin(winColl, CAL_ptrs, par_nameField, menu),
          CAL_ptrs, par_myWindowIndex, menu, winColl);
    
    nameField = par_nameField;
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
      System.out.println("[DEBUG] newGroupWindow action event");
      if ("Create" == ML_ptr.getName()){
        System.out.println("should create now. nameField text: " + nameField.getText());
        winCollection.addGroup(nameField.getText());
      }
      else if ("Cancel" == ML_ptr.getName()){
        close();
      }
    }
  }
  //public void 
}
