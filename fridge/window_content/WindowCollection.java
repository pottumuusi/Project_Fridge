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

import fridge.data.FolderCollectionItem;

public class WindowCollection extends WindowAdapter{
  private int caller;
  private int groupCount;
  private int windowCount;
  private String moveType;
  private Path currFolder;
  private Path[] moveSources;
  private String movePerformer;
  private String[] movePerformers;
  private int[] groupWindows;
  private JFrame[] windowList;
  private JFrame[][] listContainer;
  private String[] QA_folderAliases;
  private String[] quickAccessFolders;
  private String loadedFolderCollection;
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
    
    movePerformer = null;
    movePerformers = null;
    moveType = null;
    moveSources = null;
    groupWindows = null;
    windowList = new JFrame[4];
    groups = new fridge.group.Group[10];
    myWindows = new fridge.windows.MyWindow[4];
    namedWindows = new fridge.window_content.NamedWindow[4];
    
    addNew("MainWin2");
    if (-1 != getMyWindowsIndex("MainWin2")){
      if (false == ((fridge.windows.MainWindow2)myWindows[getMyWindowsIndex("MainWin2")]).hideSuccessful()){
        System.err.println("[DEBUG] could not hide MainWin2");
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
    System.err.println("[DEBUG] getGroup could not locate group: " + groupName);
    
    return null;
  }
  
  public Path getCurrFolder(){
    return currFolder;
  }
  
  public String[] getQuickAccessFolders(){
    return quickAccessFolders;
  }
  
  public String[] getQA_folderAliases(){
    return QA_folderAliases;
  }
  
  public String getLoadedFolderCollection(){
    return loadedFolderCollection;
  }
  
  public Path[] getMoveSources(){
    return moveSources;
  }
  
  public String getMoveType(){
    return moveType;
  }
  
  public String getMovePerformer(){
    return movePerformer;
  }
  
  public String[] getMovePerformers(){
    return movePerformers;
  }
  
  public void setMovePerformers(String[] newPerformers){
    movePerformers = newPerformers;
  }
  
  public void setMovePerformer(String performerName){
    movePerformer = performerName;
  }
  
  public void setLoadedFolderCollection(String loadedCollection){
    loadedFolderCollection = loadedCollection;
  }
  
  public void setQuickAccessFolders(String[] par_QAFolders){
    quickAccessFolders = par_QAFolders;
  }
  
  public void setQA_folderAliases(String[] par_aliases){
    QA_folderAliases = par_aliases;
  }
  
  public void setCurrFolder(Path par_currFolder){
    currFolder = par_currFolder;
  }
  
  public void setMoveSources(Path[] newSources){
    moveSources = newSources;
  }
  
  public void setMoveType(String newType){
    moveType = newType;
  }
  
  public void setupMove(Path[] sources, String type){
    setMoveSources(sources);
    setMoveType(type);
  }
  
  public void traceableAdd(String winType, int callerMyWindowIndex){
    caller = callerMyWindowIndex;
    addNew(winType);
  }
  
  public void addNew(String winType, int par_windowIndex){
    int i;
    caller = par_windowIndex;
    addNew(winType);
  }
  
  public void loadFolderQA(FolderCollectionItem collectionItem){
    System.err.println("-folder quick access update-");
    setQA_folderAliases(collectionItem.getAliases());
    setQuickAccessFolders(collectionItem.getPaths());
    
    ((fridge.windows.MainWindow1)myWindows[getMyWindowsIndex("MainWin1")]).loadQuickAccessCollection(collectionItem);
  } 
  
  public void deleteFolderQA(String deletedName){
    if (loadedFolderCollection.equals(deletedName)){
      setLoadedFolderCollection(null);
      setQuickAccessFolders(null);
      setQA_folderAliases(null);
      
      ((fridge.windows.MainWindow1)myWindows[getMyWindowsIndex("MainWin1")]).updateViews();
    }
  }
  
  private void errorWindow(String errorMsg){
    ((fridge.windows.MainWindow1)myWindows[getMyWindowsIndex("MainWin1")]).errorWindow(errorMsg);
  }
  
  public void errorWindow(String windowName, String errorMsg){
    if (windowName.equals("MainWin1")){
      errorWindow(errorMsg);
    }
    else if (windowName.equals("MainWin2")){
      errorWindow2(errorMsg);
    }
  }
  
  private void errorWindow2(String msg){
    ((fridge.windows.MainWindow2)myWindows[getMyWindowsIndex("MainWin2")]).errorWindow(msg);
  }
  
  public void addNew(String winType){
    if (windowCount + 1 == myWindows.length){
      myWindows = makeLonger(myWindows);
      namedWindows = makeLonger(namedWindows);
    }
    
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
      // JtextField could be initialized to the name of root directory acquired by
      // using Files class. not just "/"
  
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
    }
    else if("MainWin2" == winType){
      newNamedWin("MainWin2");
      newGroupWindow(windowCount);
      JList<String> view0, view1;
      
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
    }
    else if("HelpWin" == winType){
      newNamedWin("HelpWin");
      JTextArea helpTexts = new JTextArea(5, 30);
      fridge.window_content.Menu hw_menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      fridge.action_handling.ClassListSelectionListener CLSListener = new fridge.action_handling.ClassListSelectionListener("helpText");
      myWindows[windowCount] = new fridge.windows.HelpWindow(this, winMaker, CLSListener,
                                                             helpTexts, hw_menu, windowCount); //replace 0 with windowCount when ready
    }
    else if("newGroupWin" == winType){
      newNamedWin("newGroupWin");
      JTextField nameField = new JTextField();
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[3];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("NewNameField");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("Create");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("Cancel");
      
      fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      
      myWindows[windowCount] = new fridge.windows.NewGroupWindow(this,
                                                                 winMaker,
                                                                 CAL_list,
                                                                 nameField,
                                                                 menu,
                                                                 windowCount);
    }
    else if ("newFolderWin" == winType){
      newNamedWin("newFolderWin");
      JTextField nameField = new JTextField();
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[3];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("NewNameField");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("Create");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("Cancel");
      
      fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      myWindows[windowCount] = new fridge.windows.NewFolderWindow(this,
                                                                  winMaker,
                                                                  CAL_list,
                                                                  nameField,
                                                                  menu,
                                                                  windowCount);
    }
    else if("operationsWin" == winType){
      newNamedWin("operationsWin");
      
      boolean callByGroupWindow = false;
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[4];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("SaveCollection");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("LoadCollection");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("DeleteCollection");
      CAL_list[3] = new fridge.action_handling.ClassActionListener("Cancel");
      
      for (int i = 0; i < groupWindows.length; i++){
        if (groupWindows[i] == caller){
          callByGroupWindow = true;
        }
      }
      
      if (true == callByGroupWindow){
        System.err.println("[DEBUG] creating group quick access operations window");
        newGroupWindow(windowCount);
        myWindows[windowCount] = new fridge.windows.QAOperations(this, winMaker, CAL_list, windowCount,
                                                                 "group");
      }
      else{
        System.err.println("[DEBUG] creating folder quick access operations window");
        myWindows[windowCount] = new fridge.windows.QAOperations(this, winMaker, CAL_list, windowCount,
                                                                 "folder");
      }
    }
    else if ("collectionSave" == winType){
      newNamedWin("collectionSave");
      
      JList<String> view0;
      JTextField nameField = new JTextField();
      boolean callByGroupWindow = false;
      fridge.action_handling.ClassListSelectionListener CLSListener = new fridge.action_handling.ClassListSelectionListener("collections");
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[3];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("SaveNameField");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("Save");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("Close");
      
      view0 = new JList<String>();
      
      for (int i = 0; i < groupWindows.length; i++){
        if (groupWindows[i] == caller){
          callByGroupWindow = true;
        }
      }
      
      if (true == callByGroupWindow){
        System.err.println("[DEBUG] creating group collectionSave");
        newGroupWindow(windowCount);
        myWindows[windowCount] = new fridge.windows.CollectionSave(this, winMaker, CLSListener, CAL_list,
                                                                   nameField, view0, windowCount,
                                                                   "group");
      }
      else{
        System.err.println("[DEBUG] creating folder collectionSave");
        myWindows[windowCount] = new fridge.windows.CollectionSave(this, winMaker, CLSListener, CAL_list,
                                                                   nameField, view0, windowCount,
                                                                   "folder");
      }
    }
    else if ("collectionLoad" == winType){
      newNamedWin("collectionLoad");
      
      JComboBox collectionList = new JComboBox();
      boolean callByGroupWindow = false;
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[3];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("CollectionList");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("Load");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("Close");
      
      for (int i = 0; i < groupWindows.length; i++){
        if (groupWindows[i] == caller){
          callByGroupWindow = true;
        }
      }
      
      if (true == callByGroupWindow){
        System.err.println("[DEBUG] creating group collectionLoad");
        newGroupWindow(windowCount);
        myWindows[windowCount] = new fridge.windows.CollectionLoad(this, winMaker, CAL_list,
                                                                   collectionList, windowCount,
                                                                   "group");
      }
      else{
        System.err.println("[DEBUG] creating folder collectionLoad");
        myWindows[windowCount] = new fridge.windows.CollectionLoad(this, winMaker, CAL_list,
                                                                   collectionList, windowCount,
                                                                   "folder");
      }
    }
    else if ("collectionDelete" == winType){
      newNamedWin("collectionDelete");
      
      JComboBox collectionList = new JComboBox();
      boolean callByGroupWindow = false;
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[3];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("CollectionList");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("Delete");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("Close");
      
      for (int i = 0; i < groupWindows.length; i++){
        if (groupWindows[i] == caller){
          callByGroupWindow = true;
        }
      }
      
      if (true == callByGroupWindow){
        System.err.println("creating group collectionDelete");
        newGroupWindow(windowCount);
        myWindows[windowCount] = new fridge.windows.CollectionDelete(this, winMaker, CAL_list,
                                                                     collectionList, windowCount,
                                                                     "group");
      }
      else{
        myWindows[windowCount] = new fridge.windows.CollectionDelete(this, winMaker, CAL_list,
                                                                     collectionList, windowCount,
                                                                     "folder");
      }
    }
    else{
      System.err.println("[DEBUG] no such winType. windowCount not incremented");
      windowCount--;
    }
    windowCount++;
  }
  
  private void newNamedWin(String par_name){
    namedWindows[windowCount] = new fridge.window_content.NamedWindow(par_name, windowCount);
  }
  
  public void addFile(String fileName, String fileType){
    if (null != fileName && null != fileType){
      ((fridge.windows.MainWindow1)myWindows[getMyWindowsIndex("MainWin1")]).createFile(fileName, fileType);
    }
  }
  
  public void deleteGroupFiles(String groupName, int[] deleteIndexes){
    for (int i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].deleteItems(deleteIndexes, this);
        ((fridge.windows.MainWindow1)myWindows[getMyWindowsIndex("MainWin1")]).updateContent();
        break;
      }
    }
  }
  
  public int getGroupItemAmount(String groupName){
    for (int i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        return groups[i].getItemsLength();
      }
    }
    return -1;
  }
  
  /*returns indexes of groups which contain at least one item in pathList*/
  public String[] groupsContainPath(Path[] pathList){
    String[] containNames;
    int[] doContain = null;
    int[] tempContain = new int[groupCount];
    int containAmount = 0;
    
    for (int i = 0; i < groupCount; i++){
      if (groups[i].hasPath(pathList)){
        tempContain[containAmount] = i;
        containAmount++;
      }
    }
    
    doContain = new int[containAmount];
    
    for (int i = 0; i < doContain.length; i++){
      doContain[i] = tempContain[i];
    }
    
    containNames = new String[doContain.length];
    for (int i = 0; i < containNames.length; i++){
      containNames[i] = groups[doContain[i]].getName();
    }
    
    return containNames;
  }
  
  public void groupOpenFile(String groupName, int itemIndex){
    for (int i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].openFile(itemIndex);
        break;
      }
    }
  }
  
  public void updateItemPaths(String groupName, Path[] pathsToUpdate){
    for (int i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].updatePaths(pathsToUpdate);
        break;
      }
    }
  }
  
  public void setMoveUpdateIndexes(String groupName, Path[] changedPaths){
    for (int i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].setUpdatePathIndexes(changedPaths);
        break;
      }
    }
  }
  
  public void setMoveUpdateIndexes(String groupName, int[] changedIndexes){
    for (int i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].setUpdatePathIndexes(changedIndexes);
        break;
      }
    }
  }
  
  // create new group with given name
  public void addGroup(String newGroupName){
    boolean groupWithSameName = false;
    int i;
    
    if (groupCount + 1 == groups.length){
      groups = makeLonger(groups);
    }
    
    for (i = 0; i < groupCount; i++){
      if (groups[i].getName().equals(newGroupName)){
        groupWithSameName = true;
      }
    }
    
    if (true == groupWithSameName){
      errorWindow("Could not create group. Group with given name already exists.");
    }
    else{ 
      groups[groupCount] = new fridge.group.Group(newGroupName);
      System.err.println("addedd group " + groups[groupCount].getName());
      groupCount++;
      
      newGroupNotify();
    }
  }
  
  public void setGroupItems(String groupName, Path[] newGroupItems, Path[] newItemPaths){
    int i;
    
    for (i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].setItems(newGroupItems);
        groups[i].setItemPaths(newItemPaths);
        break;
      }
    }
    
    groupWindowsItemsChangeNotify();
  }
  
  public void addGroupItems(String groupName, Path[] newGroupItems, Path[] newPaths){
    int i;
    
    for (i = 0; i < groupCount; i++){
      if (groupName == groups[i].getName()){
        groups[i].addItems(newGroupItems, newPaths);
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
  
  private JFrame[] makeLonger(JFrame[] oldList){
    int i;
    JFrame[] newList = new JFrame[oldList.length + 4];
    //garbage collection should deal with the oldList now?
    System.err.println("[DEBUG] making windowList longer. this might cause problems!!");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  private fridge.windows.MyWindow[] makeLonger(fridge.windows.MyWindow[] oldList){
    int i;
    fridge.windows.MyWindow[] newList = new fridge.windows.MyWindow[oldList.length + 4];
    
    System.err.println("[DEBUG] making myWindows longer. this might cause problems!!");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  private fridge.window_content.NamedWindow[] makeLonger(fridge.window_content.NamedWindow[] oldList){
    int i;
    fridge.window_content.NamedWindow[] newList = new fridge.window_content.NamedWindow[oldList.length + 4];
    
    System.err.println("[DEBUG] making namedWindows longer. this might cause problems!!");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  private fridge.group.Group[] makeLonger(fridge.group.Group[] oldList){
    int i;
    fridge.group.Group[] newList = new fridge.group.Group[oldList.length + 10];
    
    System.err.println("[DEBUG] making groups longer");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  public void windowClosed(WindowEvent e){
    windowCount--;
    if (windowCount <= 0){
      System.err.println("All windows gone. Exiting");
      fridgeInstance.controlledExit();
    }
    if (true == allWindowsHidden()){
      System.err.println("All remaining windows are hidden. Exiting");
      fridgeInstance.controlledExit();
    }
    else{
      System.err.println("Some windows open or considered hidden.");
    }
  }
  
  public boolean hideNotificationOk(String windowName, int myWindowIndex){
    int i;
    
    if (namedWindows[myWindowIndex].getName() == windowName){
      namedWindows[myWindowIndex].setIsHidden(true);
      return true;
    }
    
    System.err.println("[DEBUG] hideNotificationOk got invalid window data. window should not be hidden now");
    return false;
  }
  
  public void showWindow(String windowName){
    int i;
    
    for (i = 0; i < windowCount; i++){
      if (namedWindows[i].getName() == windowName &&
          true == namedWindows[i].getIsHidden()){
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
  
  private fridge.window_content.NamedWindow getNamedWindow(String searchedWindowName){
    int i;
    
    for (i = 0; i < windowCount; i++){
      if (namedWindows[i].getName().equals(searchedWindowName)){
        return namedWindows[i];
      }
    }
    return null;
  }
  
  private boolean allWindowsHidden(){
    int i;
    
    for (i = 0; i < windowCount; i++){
      if (false == namedWindows[i].getIsHidden()){
        System.err.println("[DEBUG] namedWindows[" + i + "] (" + namedWindows[i].getName() + ") is not hidden");
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
        System.err.println("    " + namedWindows[i].getName() + " " + namedWindows[i].getMyWindowIndex());
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
