package fridge.windows;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JComboBox;

public class MainWindow2 extends fridge.windows.FileWindow{
  private JComboBox groupListBox;
  private JList<String> view0;
  private JList<String> view1;
  private int[] selectedGroupItems;
  private int[] selectedQuickAccess;
  private boolean viewUpdateAllowed;
  private String currGroup;
  private String[] quickAccessGroups;
  private fridge.group.Group[] groups;
  
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
  
  protected void moveItemsToGroup(String groupName){
    Path[] itemsToPass;
    Path[] pathsToPass;
    
    itemsToPass = getPathsOfSelectedItems(groupName);
    pathsToPass = getFullPathsOfSelectedItems(groupName);
    
    winCollection.setGroupItems(groupName, itemsToPass, pathsToPass);
  }
  
  protected void addItemsToGroup(String groupName){
    Path[] itemsToPass;
    Path[] pathsToPass;
    
    itemsToPass = getPathsOfSelectedItems(groupName);
    pathsToPass = getFullPathsOfSelectedItems(groupName);
    
    winCollection.addGroupItems(groupName, itemsToPass, pathsToPass);
  }
  
  public void openFile(){
    if (1 == selectedGroupItems.length && (false == ((view0.getSelectedValue()).equals("<empty>")))){
      winCollection.groupOpenFile(currGroup, selectedGroupItems[0]);
    }
    else{
      errorWindow("Empty field can't be opened");
    }
  }
  
  public void exclude(){
    int i = 0;
    int tempIndex = 0;
    int QAF_index = -1;
    String[] tempGroups = null;
    String[] newAliases = null;
    Path tempPath = null;
    
    if (null != selectedQuickAccess && 1 == selectedQuickAccess.length){
      tempGroups = new String[quickAccessGroups.length - 1];
      QAF_index = view1.getMinSelectionIndex();
      for (i = 0; i < quickAccessGroups.length; i++){
        if (i != QAF_index){
          tempGroups[tempIndex] = quickAccessGroups[i];
          tempIndex++;
        }
      }
      quickAccessGroups = tempGroups;
      if (null != quickAccessGroups){
        updateContent();
      }
    }
  }
  
  private void setQuickAccess(String[] newAliases){
    
  }
  
  public void delete(){
    int answer;
    
    if (null != selectedGroupItems){
      answer = yesNoWindow("Are you sure you want to delete " + selectedGroupItems.length +" selected file(s)");
      
      if (0 == answer){  
        winCollection.deleteGroupFiles(currGroup, selectedGroupItems);
        updateContent();
      }
      else{
        updateContent();
        selectedGroupItems = null;
      }
    }
  }
  
  private int selectExcluding(int[] toExclude){
    int itemCount = winCollection.getGroupItemAmount(currGroup);
    int[] include = null;
    int includeIndex = 0;
    boolean doInclude = true;
    
    if (-1 != itemCount){
      include = new int[itemCount - toExclude.length];
      
      for (int i = 0; i < itemCount; i++){
        for (int k = 0; k < toExclude.length; k++){
          if (i == toExclude[k]){
            doInclude = false;
            break;
          }
        }
        
        if (doInclude){
          include[includeIndex] = i;
          includeIndex++;
        }
        
        doInclude = true;
      }
      
      view0.setSelectedIndices(include);
      return 0;
    }
    
    return 1;
  }
  
  public void copy(){}
  
  public void cut(){
    Path[] itemsToPass = null;
    
    itemsToPass = getFullPathsOfSelectedItems(currGroup);  
    winCollection.setupMove(itemsToPass, "cut");
    winCollection.setMovePerformer(currGroup);
    winCollection.setMoveUpdateIndexes(currGroup, selectedGroupItems);
  }
  
  public void paste(){}
  public void newFolder(){}
  
  public void updateViews(){
    int i;
    String[] newContent = null;
    Path[] groupItems;
    
    currGroup = groupListBox.getSelectedItem().toString();
    
    if (null != currGroup){
      if (null != winCollection.getGroup(currGroup).getName() &&
          null != winCollection.getGroup(currGroup).getItems()){
      
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
        System.err.println("setting empty list data");
        newContent = new String[2];
        newContent[0] = "<empty>";
        view0.setListData(newContent);
      }
    }
    else{
      newContent = new String[1];
      newContent[0] = "<empty>";
      view0.setListData(newContent);
    }
    
    if (null == quickAccessGroups){
      newContent = new String[1];
      newContent[0] = "";
      view1.setListData(newContent);
    }
    else{
      view1.setListData(quickAccessGroups);
    }
  }
  
  public void updateContent(){
    int i;
    int groupCount;
    fridge.group.Group[] tempGroups;
    tempGroups = winCollection.getGroups();
    groupCount = winCollection.getGroupCount();
    
    viewUpdateAllowed = false;
    groupListBox.removeAllItems();
    
    for (i = 0; i < groupCount; i++){
      groupListBox.addItem(tempGroups[i].getName());
    }
    
    viewUpdateAllowed = true;
    currGroup = groupListBox.getSelectedItem().toString();
    updateMenu();
    updateViews();
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    int i;
    String[] tempQuickAccess;
    
    if ("ClassListSelectionListener" == ML_ptr.getType()){
      if ("group" == ML_ptr.getName()){
        selectedGroupItems = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
      else if ("quickAccess" == ML_ptr.getName()){
        selectedQuickAccess = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
    }
    else if ("ClassActionListener" == ML_ptr.getType()){
      if ("groupShowFolder" == ML_ptr.getName()){
        showFolderWin();
      }
      else if ("quickSave" == ML_ptr.getName()){
        addToQuickAccess();
      }
      else if ("quickLoad" == ML_ptr.getName()){
        loadSelectedQuickAccessGroup();
      }
      else if ("qa_showFolder" == ML_ptr.getName()){
        showFolderWin();
      }
      else if ("qa_operations" == ML_ptr.getName()){
        winCollection.addNew("operationsWin", myWindowIndex);
      }
      else if ("groupNameBox" == ML_ptr.getName()){
        if (true == viewUpdateAllowed){
          updateViews();
        }
      }
    }
  }
  
  private void showFolderWin(){
    if (true == hideSuccessful()){
      if (true == winCollection.namedWindowIsHidden("MainWin1")){
        winCollection.showWindow("MainWin1");
      }
    }
  }
  
  private void addToQuickAccess(){
    int i;
    String[] tempQuickAccess = quickAccessGroups;
    
    if (null == quickAccessGroups){
      quickAccessGroups = new String[1];
      quickAccessGroups[0] = currGroup;
      updateViews();
    }
    else{
      tempQuickAccess = quickAccessGroups;
      quickAccessGroups = new String[quickAccessGroups.length + 1];
      for (i = 0; i < tempQuickAccess.length; i++){
        quickAccessGroups[i] = tempQuickAccess[i];
      }
      quickAccessGroups[i] = currGroup;
      updateViews();
    }
  }
  
  private void loadSelectedQuickAccessGroup(){
    int i;
    String selection;
    
    if (1 == selectedQuickAccess.length){
      selection = quickAccessGroups[view1.getMinSelectionIndex()];
      
      for (i = 0; i < groupListBox.getItemCount(); i++){
        if (groupListBox.getItemAt(i).toString() == selection){
          groupListBox.setSelectedIndex(i);
          updateViews();
          break;
        }
      }
    }
    else{
      errorWindow("Cannot load multiple Quick Access Groups");
    }
  }
  
  private Path[] getPathsOfSelectedItems(String groupName){
    int i;
    Path[] pathsToPass;
    Path[] tempPaths;
    
    pathsToPass = new Path[selectedGroupItems.length];
    tempPaths = winCollection.getGroup(currGroup).getItems();
    
    for (i = 0; i < selectedGroupItems.length; i++){
      pathsToPass[i] = tempPaths[selectedGroupItems[i]];
    }
    
    return pathsToPass;
  }
  
  private Path[] getFullPathsOfSelectedItems(String groupName){
    int i;
    Path[] pathsToPass;
    Path[] tempPaths;
    
    pathsToPass = new Path[selectedGroupItems.length];
    tempPaths = winCollection.getGroup(currGroup).getItemPaths();
    
    for (i = 0; i < selectedGroupItems.length; i++){
      pathsToPass[i] = tempPaths[selectedGroupItems[i]];
    }
    
    return pathsToPass;
  }
  
  private void updateGroupView(){
    winCollection.getGroups();
  }
  
  public boolean hideSuccessful(){
    if (true == winCollection.hideNotificationOk("MainWin2", myWindowIndex)){
      frame.setVisible(false);
      return true;
    }
    return false;
  }
}
