package fridge.windows;


import java.nio.file.Path;
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
  //private int currGroup; //should this be of Group class instead of String?
  private String currGroup;
  private String[] quickAccessGroups;
  private fridge.group.Group[] groups;
  //private fridge.window_content.WindowCollection winCollection;
  
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
          CLSL_ptrs, CAL_ptrs, par_myWindowIndex, menu, winColl);
    //menu.setContainingWindow(this);
    
    winCollection = winColl;
    quickAccessGroups = null;
    selectedGroupItems = null;
    selectedQuickAccess = null;
    
    //currGroup = -1;
    currGroup = null;
    groupListBox = groupList;
    view0 = view0_par;
    view1 = view1_par;
    frame.pack();
  }
  
  protected void addGroup(){
    System.out.println("[DEBUG] MainWin2 addGroup not set");
  }
  
  /*public void groupButtonMenuPress(String buttonName){
    //if ()
  }*/
  
  protected void moveItemsToGroup(String groupName){
    System.out.println("[DEBUG] MainWin2 moveItemsToGroup not set");
  }
  
  protected void addItemsToGroup(String groupName){
    System.out.println("[DEBUG] MainWin2 addItemsToGroup not set");
  }
  
  public void updateViews(){
    int i;
    String[] newContent = null;
    Path[] groupItems;
    
    System.out.println("[DEBUG] updating MainWin2 views");
    currGroup = groupListBox.getSelectedItem().toString();
    System.out.println("currGroup == " + currGroup);
    //fridge.group.Group tempGroup;
    //fridge.group.Group[] tempGroups;
    
    //tempGroups = new fridge.group.Group[winCollection.getGroupsLength()];
    //tempGroups = winCollection.getGroups();
    
    //tempGroup = winCollection.getGroups(currGroup);
    if (null != currGroup){
      if (null != winCollection.getGroup(currGroup).getName() &&
          null != winCollection.getGroup(currGroup).getItems()){
        System.out.println("[DEBUG] getting items from group: " + winCollection.getGroup(currGroup).getName());
      
        groupItems = winCollection.getGroup(currGroup).getItems();
        if (groupItems.length > 0){
          newContent = new String[groupItems.length];
        
          for (i = 0; i < groupItems.length; i++){
            newContent[i] = groupItems[i].toString();
          }
        
          view0.setListData(newContent);
        }
      }
      else{
        newContent = new String[1];
        newContent[0] = "";
        view0.setListData(newContent);
      }
    }
    else{
      newContent = new String[1];
      newContent[0] = "";
      view0.setListData(newContent);
    }
  }
  
  public void updateContent(){
    int i;
    int groupCount;
    fridge.group.Group[] tempGroups;
    tempGroups = winCollection.getGroups();
    groupCount = winCollection.getGroupCount();
    
    for (i = 0; i < groupCount; i++){
      System.out.println("[DEBUG] adding group " + i + " " + tempGroups[i].getName());
      groupListBox.addItem(tempGroups[i].getName());
    }
    System.out.println("[DEBUG] updateContent done");
    
    currGroup = groupListBox.getSelectedItem().toString();
    updateMenu();
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    int i;
    
    System.out.println("[DEBUG] MainWin2 handleEvent. type is: " + ML_ptr.getType() + ". name is: " + ML_ptr.getName());
    
    if ("ClassListSelectionListener" == ML_ptr.getType()){
      if ("group" == ML_ptr.getName()){
      }
      else if ("quickAccess" == ML_ptr.getName()){
      }
      
    }
    else if ("ClassActionListener" == ML_ptr.getType()){
      if ("groupShowFolder" == ML_ptr.getName()){
      }
      else if ("quickSave" == ML_ptr.getName()){
        if (null == quickAccessGroups){
          
        }
        else{
          
        }
      }
      else if ("quickLoad" == ML_ptr.getName()){
      }
      else if ("qa_showFolder" == ML_ptr.getName()){
      }
      else if ("qa_operations" == ML_ptr.getName()){
        winCollection.addGroup("fromMainWin2");
        System.out.println("[DEBUG] MainWin2 qa_operations press");
        /*Path[] testPath = new Path[3];
        testPath[0] = ;
        winCollection.addGroupItems("fromMainWin2", testPaths);*/
      }
      else if ("groupNameBox" == ML_ptr.getName()){
        //currGroup = boxSelection
        //updateContent();
        System.out.println("[DEBUG] comboBox selected item: " + groupListBox.getSelectedItem().toString());
        updateViews();
      }
    }
  }
  
  private void updateGroupView(){
    winCollection.getGroups();
  }
  
  /*private void updateContent(){
    int i;
    String[] newContent;
    
    //currGroup = groupListBox. //get name here 
    
    newContent = new String[groups[currGroup].getItemsLength()];
    
    for (i = 0; i < newContent.length; i++){
      newContent[i] = groups[currGroup].getItem(i).toString();
    }
    
    groups[currGroup].getItems();
    view0.setListData(newContent);
  }*/
  
  public boolean hideSuccessful(){
    if (true == winCollection.hideNotificationOk("MainWin2", myWindowIndex)){
      frame.setVisible(false);
      return true;
    }
    return false;
  }
}
