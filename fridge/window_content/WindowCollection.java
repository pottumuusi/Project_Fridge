package fridge.window_content;

import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.nio.file.Path;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;


public class WindowCollection extends WindowAdapter{
  private int groupCount;
  private int windowCount;
  private int[] groupWindows;
  private JFrame[] windowList;
  private JFrame[][] listContainer;
  private fridge.group.Group[] groups;
  private fridge.Fridge fridgeInstance;
  private fridge.window_content.Containers cont; // want this to be here?
  private fridge.window_content.WindowMaker winMaker;
  
  private fridge.windows.MyWindow[] myWindows;
  private fridge.window_content.NamedWindow[] namedWindows;
  
  public WindowCollection(fridge.Fridge FI_ptr){
    fridgeInstance = FI_ptr;
    winMaker = new WindowMaker(FI_ptr);
    windowCount = 0;
    groupCount = 0;
    //lastLocation = null;
    
    groupWindows = null;
    windowList = new JFrame[4];
    groups = new fridge.group.Group[10];
    myWindows = new fridge.windows.MyWindow[4];
    namedWindows = new fridge.window_content.NamedWindow[4];
    
    //groups[0] = new fridge.group.Group("testGroup");
    
    addNew("MainWin2");
    if (-1 != getMyWindowsIndex("MainWin2")){
      if (false == ((fridge.windows.MainWindow2)myWindows[getMyWindowsIndex("MainWin2")]).hideSuccessful()){
        System.out.println("[DEBUG] could not hide MainWin2");
      }
    }
    addNew("MainWin1");
    addGroup("testGroup0");
  }
  
  public int getMyWindowsIndex(String windowName){
    int i;
    
    for (i = 0; i < windowCount; i++){
      if (namedWindows[i].getName() == windowName){
        return namedWindows[i].getMyWindowIndex();
      }
    }
    return -1;
  }
  
  public fridge.windows.MyWindow getMyWindow(int index){
    return myWindows[index];
  }
  
  public int getGroupCount(){
    return groupCount;
  }
  
  public fridge.group.Group[] getGroups(){
    return groups;
  }
  
  public int getGroupsLength(){
    return groups.length;
  }
  
  public fridge.group.Group getGroup(String groupName){
    int i;
    
    for (i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        return groups[i];
      }
    }
    System.out.println("[DEBUG] getGroup could not locate group: " + groupName);
    
    return null;
  }
  
  public void addNew(String winType){
    System.out.println("start of addNew: myWindows.length = " + myWindows.length);
    System.out.println("windowCount = " + windowCount);
    if (windowCount + 1 == myWindows.length){
      System.out.println("now in if");
      //windowList = makeLonger(windowList);
      myWindows = makeLonger(myWindows);
      namedWindows = makeLonger(namedWindows);
      System.out.println("after makeLonger method myWindows.length = " + myWindows.length);
    }
    
    //switch (winType){
    //case "MainWin1":
    if ("MainWin1" == winType){
      newNamedWin("MainWin1");
      JList<String> view0, view1;
      
      fridge.action_handling.ClassListSelectionListener[] CLSL_list;
      CLSL_list = new fridge.action_handling.ClassListSelectionListener[2];
      CLSL_list[0] = new fridge.action_handling.ClassListSelectionListener("folder");
      CLSL_list[1] = new fridge.action_handling.ClassListSelectionListener("quickAccess");
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[6];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("folderNameField");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("folderShowGroup");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("quickSave");
      CAL_list[3] = new fridge.action_handling.ClassActionListener("quickLoad");
      CAL_list[4] = new fridge.action_handling.ClassActionListener("qa_showGroup");
      CAL_list[5] = new fridge.action_handling.ClassActionListener("qa_operations");
      
      JTextField folderName = new JTextField("/");
      //JtextField could be initialized to the name of root directory acquired by
      //using Files class. not just "/"
  
      String[] view0Data = {"folder1    group1", "folder2"}; // still testing. will be initialized empty
      view0 = new JList<String>(view0Data);
      String[] view1Data = {"folder1", "folder2"};
      view1 = new JList<String>();
      
      fridge.window_content.Menu mw1_menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
	  
      myWindows[windowCount] = new fridge.windows.MainWindow1(this,
                                                              winMaker,
                                                              CLSL_list,
                                                              CAL_list,
                                                              folderName,
                                                              view0,
                                                              view1,
                                                              mw1_menu,
                                                              windowCount);
      //break;
    }
    else if("MainWin2" == winType){
    //case "MainWin2":
      newNamedWin("MainWin2");
      newGroupWindow(windowCount);
      JList<String> view0, view1;
      //JList<String> mw2_view0, mw2_view1;
      
      fridge.action_handling.ClassListSelectionListener[] CLSL_list;
      CLSL_list = new fridge.action_handling.ClassListSelectionListener[2];
      CLSL_list[0] = new fridge.action_handling.ClassListSelectionListener("group");
      CLSL_list[1] = new fridge.action_handling.ClassListSelectionListener("quickAccess");
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[6];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("groupNameBox");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("groupShowFolder");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("quickSave");
      CAL_list[3] = new fridge.action_handling.ClassActionListener("quickLoad");
      CAL_list[4] = new fridge.action_handling.ClassActionListener("qa_showFolder");
      CAL_list[5] = new fridge.action_handling.ClassActionListener("qa_operations");
      
      JComboBox groupList = new JComboBox();
      
      view0 = new JList<String>();
      view1 = new JList<String>();
      
      fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      
      myWindows[windowCount] = new fridge.windows.MainWindow2(this,
                                                              winMaker,
                                                              CLSL_list,
                                                              CAL_list,
                                                              groupList,
                                                              view0,
                                                              view1,
                                                              menu,
                                                              windowCount);
      //fridge.action_handling.ClassListSelectionListener[] mw2_CLSL_list;
      //mw2_CLSL_list = new fridge.action_handling.ClassListSelectionListener[2];
      //mw2_CLSL_list[0] = new fridge.action_handling.ClassListSelectionListener("group");
      //mw2_CLSL_list[1] = new fridge.action_handling.ClassListSelectionListener("quickAccess");
      
      
      //break;
    }
    else if("HelpWin" == winType){
    //case "HelpWin":
      //newNamedWin(numberedWinName("HelpWin"));
      newNamedWin("HelpWin");
      JTextArea helpTexts = new JTextArea(5, 30);
      fridge.window_content.Menu hw_menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      fridge.action_handling.ClassListSelectionListener CLSListener = new fridge.action_handling.ClassListSelectionListener("helpText");
      //windowList[windowCount] = winMaker.newHelpWin(this);
      //fridge.action_handling.MyListSelectionListener LSListener = new fridge.action_handling.MyListSelectionListener();
      //myWindows[0] = new fridge.windows.HelpWindow(winMaker.newHelpWin(this, CLSListener), CLSListener); //replace 0 with windowCount when ready
      myWindows[windowCount] = new fridge.windows.HelpWindow(this, winMaker, CLSListener,
                                                             helpTexts, hw_menu, windowCount); //replace 0 with windowCount when ready
      //break;
    }
    else if("newGroupWin" == winType){
      newNamedWin("newGroupWin");
      JTextField = new nameField JTextField();
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[3];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("newNameField");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("Create");
      CAL_list[3] = new fridge.action_handling.ClassActionListener("Cancel");
      
      fridge.window_content.Menu hw_menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      
      myWindows[windowCount] = new fridge.windows.NewGroupWindow(this,
                                                                 winMaker,
                                                                 CAL_list,
                                                                 nameField,
                                                                 menu,
                                                                 windowCount);
    }
    else{
    //default:
      System.out.println("[DEBUG] no such winType. windowCount not incremented");
      windowCount--;
    //}
    }
    windowCount++;
    System.out.println("[DEBUG] window count after creation == " + windowCount);
    printNamedWindows(); //this is debug. delete pls
  }
  
  private void newNamedWin(String par_name){
    namedWindows[windowCount] = new fridge.window_content.NamedWindow(par_name, windowCount);
  }
  
  // create new group with given name
  public void addGroup(String newGroupName){
    boolean groupWithSameName = false;
    int i;
    
    if (groupCount + 1 == groups.length){
      groups = makeLonger(groups);
    }
    
    for (i = 0; i < groupCount; i++){
      if (groups[i].getName() == newGroupName){
        groupWithSameName = true;
      }
    }
    
    if (true == groupWithSameName){
      //errorMessage("Could not create group. Group with given name already exists.");
    }
    else{
      groups[groupCount] = new fridge.group.Group(newGroupName);
      System.out.println("addedd group " + groups[groupCount].getName());
      groupCount++;
      
      //groupWindowsNewGroupNotify();
      newGroupNotify();
    }
  }
  
  public void setGroupItems(String groupName, Path[] newGroupItems){
    int i;
    
    for (i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].setItems(newGroupItems);
        break;
      }
    }
    
    groupWindowsItemsChangeNotify();
  }
  
  public void addGroupItems(String groupName, Path[] newGroupItems){
    int i;
    
    for (i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].addItems(newGroupItems);
        break;
      }
    }
    
    groupWindowsItemsChangeNotify();
  }
  
  private void groupWindowsItemsChangeNotify(){
    int i;
    
    for (i = 0; i < groupWindows.length; i++){
      if (groupWindows[i] >= 0){
        ((fridge.windows.CallableByListener)myWindows[groupWindows[i]]).updateViews();
      }
    }
    //all group windows.updateGroupView
  }
  
  private void newGroupNotify(){
    int i;
    
    for (i = 0; i < windowCount; i++){
      ((fridge.windows.CallableByListener)myWindows[i]).updateContent();
    }
  }
  
  private void newGroupWindow(int myWindowIndex){
    int i;
    int[] temp = null;
    
    if (null == groupWindows){
      groupWindows = new int[1];
      groupWindows[0] = myWindowIndex;
    }
    else{
      temp = groupWindows;
      groupWindows = new int[groupWindows.length + 1];
      for(i = 0; i < temp.length; i++){
        groupWindows[i] = temp[i];
      }
      groupWindows[i] = myWindowIndex;
    }
  }
  
  private String numberedWinName(String wantedName){
    int i, k;
    int largestNum;
    String nameWithoutNum;
    String windowName;
    
    for (k = 0; k < namedWindows.length; k++){
      windowName = namedWindows[k].getName();
      for(i = 0; i < wantedName.length(); i++){
        
      }
      
    }
    return null;
  }
  
  private JFrame[] makeShorter(JFrame[] oldList){
    return null;
  }
  
  
  private JFrame[] makeLonger(JFrame[] oldList){
    int i;
    JFrame[] newList = new JFrame[oldList.length + 4];
    //garbage collection should deal with the oldList now?
    System.out.println("[DEBUG] making windowList longer. this might cause problems!!");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  private fridge.windows.MyWindow[] makeLonger(fridge.windows.MyWindow[] oldList){
    int i;
    fridge.windows.MyWindow[] newList = new fridge.windows.MyWindow[oldList.length + 4];
    
    System.out.println("[DEBUG] making myWindows longer. this might cause problems!!");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  private fridge.window_content.NamedWindow[] makeLonger(fridge.window_content.NamedWindow[] oldList){
    int i;
    fridge.window_content.NamedWindow[] newList = new fridge.window_content.NamedWindow[oldList.length + 4];
    
    System.out.println("[DEBUG] making namedWindows longer. this might cause problems!!");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  private fridge.group.Group[] makeLonger(fridge.group.Group[] oldList){
    int i;
    fridge.group.Group[] newList = new fridge.group.Group[oldList.length + 10];
    
    System.out.println("[DEBUG] making groups longer");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  public void windowClosed(WindowEvent e){
    windowCount--;
    System.out.println("[DEBUG] window closed. windowCount == " + windowCount);
    if (windowCount <= 0){
      System.out.println("All windows gone. Exiting");
      fridgeInstance.controlledExit();
    }
    if (true == allWindowsHidden()){
      System.out.println("All remaining windows are hidden. Exiting");
      fridgeInstance.controlledExit();
    }
    //winMaker.updateLocation();
  }
  
  public boolean hideNotificationOk(String windowName, int myWindowIndex){
    //fridge.window_content.SleepingWindow[] temp = null;
    int i;
    
    if (namedWindows[myWindowIndex].getName() == windowName){
      namedWindows[myWindowIndex].setIsHidden(true);
      return true;
    }
    
    System.out.println("[DEBUG] hideNotificationOk got invalid window data. window should not be hidden now");
    return false;
  }
  
  /*public boolean isHidden(String windowName){
    //scan all namedWindows and see if they match given name and are hidden
    
  }*/
  
  
  public void showWindow(String windowName){
    int i;
    
    for (i = 0; i < windowCount; i++){
      //if (namedWindows[i].getName().equals(windowName)){
      if (namedWindows[i].getName() == windowName &&
          true == namedWindows[i].getIsHidden()){
        System.out.println("[DEBUG] showing window: " + windowName);
        myWindows[namedWindows[i].getMyWindowIndex()].show();
        namedWindows[i].setIsHidden(false);
        break;
      }
    }
  }
  
  public boolean namedWindowIsHidden(String searchedWindowName){
    int i;
    
    for (i = 0; i < windowCount; i++){
      if (namedWindows[i].getName() == searchedWindowName && 
          (true == namedWindows[i].getIsHidden())){
        return true;
      }
    }
    return false;
  }
  
  public boolean namedWindowExists(String searchedWindowName){
    int i;
    
    for (i = 0; i < windowCount; i++){
      if (namedWindows[i].getName() == searchedWindowName){
        return true;
      }
    }
    return false;
  }
  
  private boolean allWindowsHidden(){
    int i;
    
    for (i = 0; i < windowCount; i++){
      if (false == namedWindows[i].getIsHidden()){
        System.out.println("[DEBUG] namedWindows[" + i + "] (" + namedWindows[i].getName() + ") is not hidden");
        return false;
      }
    }
    return true;
  }
  
  public void printHiddenWindows(){
    int i;
    
    System.out.println("Hidden windows:");
    for (i = 0; i < windowCount; i++){
      if (true == namedWindows[i].getIsHidden()){
        System.out.println("    " + namedWindows[i].getName() + " " + namedWindows[i].getMyWindowIndex());
      }
    }
  }
  
  public void printNamedWindows(){
    int i;
    
    System.out.println("Named windows:");
    for (i = 0; i < windowCount; i++){
      System.out.println("    " + namedWindows[i].getName() +  " " + namedWindows[i].getMyWindowIndex());
    }
  }
}
