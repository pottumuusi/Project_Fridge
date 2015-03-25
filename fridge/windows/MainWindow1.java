package fridge.windows;

import javax.swing.JFrame;

public class MainWindow1 extends fridge.windows.CallableByListener{
  private int[] selectedFolders;
  private int[] selectedGroups;
  private int myWindowIndex;
  
  public MainWindow1(fridge.window_content.WindowCollection winColl,
                     fridge.window_content.WindowMaker winMaker,
                     fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs){
    super(winMaker.newMainWin1(winColl, CLSL_ptrs), CLSL_ptrs);
    selectedFolders = null;
    selectedGroups = null;
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    int[] selectedIndexes;
    
    switch (ML_ptr.getType()){
    case "ClassListSelectionListener":
      if ("folder" == ML_ptr.getName()){
        selectedFolders = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedFolders = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
      else if ("quickAccess" == ML_ptr.getName()){
        selectedGroups = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedGroups = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
      break;
    }
  }
}