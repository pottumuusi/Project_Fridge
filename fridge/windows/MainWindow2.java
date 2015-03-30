package fridge.windows;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow2 extends fridge.windows.CallableByListener{
  
  
  MainWindow2(fridge.window_content.WindowCollection winColl,
              fridge.window_content.WindowMaker winMaker,
              fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs,
              fridge.action_handling.ClassActionListener[] CAL_ptrs,
              JComboBox groupList,
              JList view0_par,
              JList view1_par,
              fridge.window_content.Menu menu,
              int par_myWindowIndex){
    super(winMaker.newMainWin2(winColl, CLSL_ptrs, CAL_ptrs, fn_par, view0_par, view1_par, menu),
          CLSL_ptrs, CAL_ptrs, par_myWindowIndex);
    menu.setContainingWindow(this);
    
    winCollection = winColl;
    quickAccessGroups = null;
    selectedGroupItems = null;
    selectedQuickAccess = null;
    
    currGroup = null;
    groupListBox = groupList;
    view0 = view0_par;
    view1 = view1_par;
    frame.pack();
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    int i;
    
    if ("ClassListSelectionListener" == ML_ptr.getType()){
      if ("group" == ML_ptr.getName()){
      }
      else if ("quickAccess" == ML_ptr.getName()){
      }
    }
    else if ("ClassActionListener" == ML_ptr.getType()){
      if ("groupShowFolder"){
      }
      else if ("quickSave" == ML.ptr.getType()){
      }
      else if ("quickLoad" == ML.ptr.getType()){
      }
      else if ("qa_showFolder" == ML.ptr.getType()){
      }
      else if ("qa_operations" == ML.ptr.getType()){
      }
      else if ("groupNameBox" == ML.ptr.getType()){
      }
    }
  }
}
