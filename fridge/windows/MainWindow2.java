package fridge.windows;

import java.nio.file.Paths;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JComboBox;

public class MainWindow2 extends fridge.windows.CallableByListener{
  private JComboBox groupListBox;
  private JList<String> view0;
  private JList<String> view1;
  private int[] selectedGroupItems;
  private int[] selectedQuickAccess;
  //private Group currGroup;
  private int currGroup; //should this be of Group class instead of String?
  private String[] quickAccessGroups;
  private fridge.group.Group[] groups;
  private fridge.window_content.WindowCollection winCollection;
  
  public MainWindow2(fridge.window_content.WindowCollection winColl,
              fridge.window_content.WindowMaker winMaker,
              fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs,
              fridge.action_handling.ClassActionListener[] CAL_ptrs,
              JComboBox groupList,
              JList view0_par,
              JList view1_par,
              fridge.window_content.Menu menu,
              int par_myWindowIndex){
    super(winMaker.newMainWin2(winColl, CLSL_ptrs, CAL_ptrs, groupList, view0_par, view1_par, menu),
          CLSL_ptrs, CAL_ptrs, par_myWindowIndex);
    menu.setContainingWindow(this);
    
    winCollection = winColl;
    quickAccessGroups = null;
    selectedGroupItems = null;
    selectedQuickAccess = null;
    
    currGroup = -1;
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
      if ("groupShowFolder" == ML_ptr.getType()){
      }
      else if ("quickSave" == ML_ptr.getType()){
      }
      else if ("quickLoad" == ML_ptr.getType()){
      }
      else if ("qa_showFolder" == ML_ptr.getType()){
      }
      else if ("qa_operations" == ML_ptr.getType()){
      }
      else if ("groupNameBox" == ML_ptr.getType()){
        //currGroup = boxSelection
        //updateContent();
      }
    }
  }
  
  private void updateContent(){
    int i;
    String[] newContent;
    
    newContent = new String[groups[currGroup].getItemsLength()];
    
    for (i = 0; i < newContent.length; i++){
      newContent[i] = groups[currGroup].getItem(i).toString();
    }
    
    groups[currGroup].getItems();
    view0.setListData(newContent);
  }
  
  public boolean hideSuccessful(){
    if (true == winCollection.hideNotificationOk("MainWin2", myWindowIndex)){
      frame.setVisible(false);
      return true;
    }
    return false;
  }
}
