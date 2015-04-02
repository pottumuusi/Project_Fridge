package fridge.windows;

public class NewGroupWindow extends fridge.windows.CallableByListener{
  private JTextField nameField;
  
  public NewGroupWindow(fridge.window_content.WindowCollection winColl,
                        fridge.window_content.WindowMaker winMaker,
                        fridge.action_handling.ClassActionListener[] CAL_ptrs,
                        JTextField par_nameField,
                        fridge.window_content.Menu menu,
                        int par_myWindowIndex){
    super(winMaker.newNewGroupWin(winColl, CAL_ptrs, nameField, menu),
          CAL_ptrs, par_myWindowIndex, menu);
  }
}