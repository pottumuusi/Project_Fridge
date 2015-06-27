package fridge.windows;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.StandardOpenOption;
import java.nio.file.DirectoryStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.AtomicMoveNotSupportedException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.DefaultListModel;

import javax.swing.ActionMap;
import javax.swing.InputMap;

import fridge.data.FolderCollectionItem;

public class MainWindow1 extends fridge.windows.FileWindow{
  private Path currFolder;
  private Path prevFolder;
  private JTextField folderNameField;
  private JList<String> view0;
  private JList<String> view1;
  private int[] selectedFolders;
  private int[] selectedQuickAccess;
  
  private String currentPath;
  private String[] fullFileNames; //need this?
  private String[] quickAccessFolders;
  private String loadedQuickAccessCollection;
  
  public MainWindow1(fridge.window_content.WindowCollection winColl,
                     fridge.window_content.WindowMaker winMaker,
                     fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs,
                     fridge.action_handling.ClassActionListener[] CAL_ptrs,
                     JTextField fn_par,
                     JList view0_par,
                     JList view1_par,
                     fridge.window_content.Menu menu,
                     int par_myWindowIndex){
    
    super(winMaker.newMainWin1(winColl, CLSL_ptrs, CAL_ptrs, fn_par, view0_par, view1_par, menu),
        CLSL_ptrs, CAL_ptrs, par_myWindowIndex, menu, winColl);
    
    winCollection = winColl;
    selectedFolders = null;
    quickAccessFolders = null;
    selectedQuickAccess = null;
    loadedQuickAccessCollection = null;
    
    folderNameField = fn_par;
    view0 = view0_par;
    view1 = view1_par;
    currFolder = Paths.get(folderNameField.getText());
    prevFolder = Paths.get("/");
    
    frame.pack();
  }
  
  public String[] getQAFolders(){
    return quickAccessFolders;
  }
  
  private void setQuickAccessFolders(String[] newFolders){
    quickAccessFolders = newFolders;
  }
  
  private void setQuickAccessAliases(String[] newAliases){
    if (null == newAliases){
      clearQuickAccessAliases();
    }
    else{
      view1.setListData(newAliases);
    }
  }
  
  private void clearQuickAccessAliases(){
    String[] empty = new String[1];
    empty[0] = "";
    view1.clearSelection();
    view1.setListData(empty);
  }
  
  private void setLoadedQuickAccessCollection(String collectionName){
    loadedQuickAccessCollection = collectionName;
  }
  
  private void addFolder(){
    /*
     * Noticee!
     * all folder variables could be moved to WindowCollection. the group stuff is
     * already there. this would make it easier to access folder data from other locations
     * than MainWin1. Maybe even create a class for the folders and make WindowCollection
     * have an object made from this class. And of course make methods to WindowCollection
     * which access data from this object
     */
  }
  
  protected void moveItemsToGroup(String groupName){
    Path[] itemsToPass;
    Path[] pathsToPass;
    
    itemsToPass = getSelectedFolderNames();
    pathsToPass = getSelectedFullPaths();
    
    winCollection.setGroupItems(groupName, itemsToPass, pathsToPass);
  }
  
  protected void addItemsToGroup(String groupName){
    Path[] itemsToPass;
    Path[] pathsToPass;
    
    itemsToPass = getSelectedFolderNames();
    pathsToPass = getSelectedFullPaths();
    
    winCollection.addGroupItems(groupName, itemsToPass, pathsToPass);
  }
  
  private Path[] getSelectedFullPaths(){
    Path[] selectedPaths;
    
    selectedPaths = new Path[selectedFolders.length];
    
    for (int i = 0; i < selectedFolders.length; i++){
      selectedPaths[i] = Paths.get(fullFileNames[selectedFolders[i]]);
    }
    
    return selectedPaths;
  }
  
  private Path[] getSelectedFolderNames(){
    Path[] tempPaths;
    Path[] selectedNames;
    
    tempPaths = getPathsOfCurrentFolder();
    selectedNames = new Path[selectedFolders.length];
    
    for (int i = 0; i < selectedFolders.length; i++){
      selectedNames[i] = tempPaths[selectedFolders[i] - 1];
    }
    
    return selectedNames;
  }
  
  public void updateViews(){
  }
  
  public void updateContent(){
    updateFolderContent();
    updateQuickAccess();
    updateMenu();
  }
  
  public void newFolder(){
    winCollection.addNew("newFolderWin");
  }
  
  public void createFile(String fileName, String fileType){
    System.err.println("MainWin1 create file " + fileName + " type (" + fileType + ")");
    Charset charset = Charset.forName("UTF-8");
    Path newFilePath = null;
    
    if ("/" == currFolder.toString()){
      newFilePath = Paths.get(currFolder.toString() + fileName);
    }
    else{
      newFilePath = Paths.get(currFolder.toString() + "/" + fileName);
    }
    
    try (BufferedWriter writer = Files.newBufferedWriter(newFilePath, charset,
                                                         StandardOpenOption.CREATE)) {
      updateContent();
    } catch (IOException ioe) {
        errorWindow("Could not create file " + newFilePath.toString());
        System.err.println("IOException: " + ioe.getMessage());
    }
  }
  
  public void exclude(){
    int i = 0;
    int tempIndex = 0;
    int QAF_index = -1;
    String[] tempFolders = null;
    String[] newAliases = null;
    Path tempPath = null;
    
    if (null != selectedQuickAccess && 1 == selectedQuickAccess.length){
      tempFolders = new String[quickAccessFolders.length - 1];
      QAF_index = view1.getMinSelectionIndex();
      for (i = 0; i < quickAccessFolders.length; i++){
        if (i != QAF_index){
          tempFolders[tempIndex] = quickAccessFolders[i];
          tempIndex++;
        }
      }
      quickAccessFolders = tempFolders;
      if (null != quickAccessFolders){
        newAliases = new String[quickAccessFolders.length]; 
        
        for (i = 0; i < quickAccessFolders.length; i++){
          tempPath = Paths.get(quickAccessFolders[i]);
          newAliases[i] = tempPath.getFileName().toString();
        }
        
        setQuickAccessAliases(newAliases);
        updateContent();
      }
    }
  }
  
  public void delete(){
    int answer = -1;
    boolean noo = false;
    
    if (null != selectedFolders){
      answer = yesNoWindow("Are you sure you want to delete " + selectedFolders.length +" selected file(s)");
      
      if (0 == answer){
        for (int i = 0; i < selectedFolders.length; i++){
          deleteFile(Paths.get(fullFileNames[selectedFolders[i]]));
        }
        updateContent();
      }
      else{
        updateContent();
        selectedFolders = null;
      }
    }
  }
  
  private void deleteFile(Path deleteFile){
    try{
      Files.delete(deleteFile);
    } catch (NoSuchFileException x) {
        errorWindow(deleteFile.toString() + ": no such" + " file or directory");
    } catch (DirectoryNotEmptyException x) {
        errorWindow(deleteFile.toString() + " is not empty");
    } catch (IOException ioe) {
        // File permission problems are caught here.
        System.err.println(ioe);
    }
  }
  
  public void copy(){
    moveSetup("copy");
    winCollection.setMovePerformers(null);
  }
  
  public void cut(){
    moveSetup("cut");
  }
  
  private void moveSetup(String moveType){
    String[] groupsToUpdate;
    Path[] moveFiles;
    int i;
    
    if (null != selectedFolders){
      moveFiles = new Path[selectedFolders.length];
      
      for (i = 0; i < moveFiles.length; i++){
        moveFiles[i] = Paths.get(fullFileNames[selectedFolders[i]]);
      }
      
      winCollection.setMoveSources(moveFiles);
      winCollection.setMoveType(moveType);
      
      if ("cut" == moveType){
        groupsToUpdate = winCollection.groupsContainPath(moveFiles);
        
        for (i = 0; i < groupsToUpdate.length; i++){
          winCollection.setMoveUpdateIndexes(groupsToUpdate[i], moveFiles);
        }
        
        winCollection.setMovePerformers(groupsToUpdate);
      }
    }
  }
  
  public void paste(){
    Path[] sourceFiles = winCollection.getMoveSources();
    String moveType = winCollection.getMoveType();
    String[] movePerformers;
    boolean copyOK = true;
    int i;
    
    if (null != sourceFiles && null != moveType){
      if ("cut" == moveType){
        for (i = 0; i < sourceFiles.length; i++){
          copyOK = handleMove(sourceFiles[i]);
        }
      }
      else if ("copy" == moveType){
        for (i = 0; i < sourceFiles.length; i++){
          copyOK = handleCopy(sourceFiles[i]);
        }
      }
      
      if (true == copyOK){
        winCollection.setMoveSources(null);
        if (null != winCollection.getMovePerformers()){
          sourceFiles = getNewLocations(sourceFiles);
          
          movePerformers = winCollection.getMovePerformers();
          for (i = 0; i < movePerformers.length; i++){
            winCollection.updateItemPaths(movePerformers[i], sourceFiles);
          }
        }else{
          sourceFiles = getNewLocations(sourceFiles);
          winCollection.updateItemPaths(winCollection.getMovePerformer(), sourceFiles);
        }
        winCollection.setMovePerformers(null);
        winCollection.setMovePerformer(null);
        updateContent();
      }
      else {
        errorWindow("Error while pasting");
      }
    }
  }
  
  private Path[] getNewLocations(Path[] oldLocations){
    Path[] newLocations = new Path[oldLocations.length];
    
    for (int i = 0; i < newLocations.length; i++){
      newLocations[i] = currFolder.resolve(oldLocations[i].getFileName());
    }
    return newLocations;
  }
  
  private boolean handleMove(Path sourceFile){
    try{
      Files.move(sourceFile, currFolder.resolve(sourceFile.getFileName()), StandardCopyOption.ATOMIC_MOVE);
    } catch(AtomicMoveNotSupportedException anse){
        System.err.println("atomic move not supported");
        try{
          Files.move(sourceFile, currFolder.resolve(sourceFile.getFileName()));
        } catch(IOException ioe){
          System.err.println("could not move file. " + ioe.getMessage());
          return false;
        }
    } catch(IOException ioe){
        System.err.println("could not move file. " + ioe.getMessage());
        return false;
    }
    return true;
  }
  
  private boolean handleCopy(Path sourceFile){
    try{
      Files.copy(sourceFile, currFolder.resolve(sourceFile.getFileName()));
    } catch(IOException ioe){
      System.err.println("could not copy file. " + ioe.getMessage());
      errorWindow("Could not move file " + sourceFile.toString());
      return false;
    }
    return true;
  }
  
  public void openFile(){
    Path tempPath = null;
    String tempFileName = null;
    String tempWholePath = null;
    
    if (null == selectedFolders){
      System.err.println("[ERROR] got null to selectedFolders at some point]");
      //handle error by not opening
    }
    else if (selectedFolders.length > 1){
      errorWindow("Only on file can be opened at a time");
    }
    else if (selectedFolders.length < 1){
      System.err.println("Trying to open file when none selected");
    }
    else{
      if (null != fullFileNames){
        tempPath = Paths.get(fullFileNames[selectedFolders[0]]);
      }
      
      /* open previous folder */
      if (0 == selectedFolders[0]){
        currFolder = prevFolder;
        if (!(currFolder.toString().equals("/"))){
          tempFileName = currFolder.getFileName().toString();
          tempWholePath = currFolder.toString();
          
          /* assign folder under current folder to prevFolder */
          prevFolder = Paths.get(currFolder.toString().substring(0, tempWholePath.length() - tempFileName.length()));
          System.err.println("prev folder set to: " + prevFolder);
        }
        folderNameField.setText(currFolder.toString());
        updateFolderContent();
      }
      //open the folder
      else if (Files.isDirectory(tempPath)){
        if (null != tempPath){
          prevFolder = currFolder;
          currFolder = tempPath;
          folderNameField.setText(currFolder.toString());
          updateFolderContent();
        }
      }
      //open the file
      else{
        try{
          java.awt.Desktop.getDesktop().open(new File(tempPath.toString()));
        }
        catch (IOException ex){
          System.out.println("IOException: " + ex.getMessage());
        }
      }
    }
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    String tempStore = null;
    int i;
    
    //make it impossible to select the first row of folders of groups
    if ("ClassListSelectionListener" == ML_ptr.getType()){
      if ("folder" == ML_ptr.getName()){
        selectedFolders = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedFolders = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
      else if ("quickAccess" == ML_ptr.getName()){
        selectedQuickAccess = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedQuickAccess = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
    }
    else if ("ClassActionListener" == ML_ptr.getType()){
      if ("folderShowGroup" == ML_ptr.getName()){
        showGroupWin();
      }
      else if ("quickSave" == ML_ptr.getName()){
        //ask for alias. it could be passed to addToQuickAccess method as a parameter.
        addToQuickAccess();
      }
      else if ("quickLoad" == ML_ptr.getName()){
        loadSelectedQuickAccessFolder();
      }
      else if ("qa_showGroup" == ML_ptr.getName()){
        showGroupWin();
      }
      else if ("qa_operations" == ML_ptr.getName()){
        winCollection.addNew("operationsWin", myWindowIndex);
      }
      else if ("folderNameField" == ML_ptr.getName()){
        tempStore = currFolder.toString();
        currFolder = Paths.get(((fridge.action_handling.ClassActionListener)ML_ptr).getActionCommand());
        if (Files.isDirectory(currFolder)){
          updateFolderContent();
          winCollection.setCurrFolder(currFolder);
        }
        else{
          currFolder = Paths.get(tempStore);
        }
      }
    }
  }
  
  private void showGroupWin(){
    if (true == hideSuccessful()){
      if (false == winCollection.namedWindowExists("MainWin2")){
        System.out.println("[DEBUG] creating MainWin2");
        winCollection.addNew("MainWin2");
      }
      else if (true == winCollection.namedWindowIsHidden("MainWin2")){
        System.out.println("[DEBUG] showing window: MainWin2");
        winCollection.showWindow("MainWin2");
      }
    }
    //if MainWin2 does not exist create it. if it is hidden show it
  }
  
  private void updateFolderContent(){
    String[] newContent = null;
    String[] temp = null;
    String currFileName = null;
    String nextRowString = null;
    int folderFileCount = 0;
    int testFileCount = 0;
    int i;
    
    if (Files.isDirectory(currFolder)){
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(currFolder)){
        fullFileNames = null; //reset earlier file names
        
        for (Path file: stream){
          currFileName = file.getFileName().toString();
          
          if (null == fullFileNames){
            fullFileNames = new String[1];
            fullFileNames[0] = file.toString();
          }
          if (null != fullFileNames){
            temp = fullFileNames;
            fullFileNames = new String[fullFileNames.length + 1];
            for (i = 0; i < fullFileNames.length - 1; i++){
              fullFileNames[i] = temp[i];
            }
            fullFileNames[i] = file.toString();
          }
          
          if ('.' != currFileName.charAt(0) || 
              '.' == currFileName.charAt(1) || true){
            if (null == newContent){
              if (true == Files.isDirectory(file)){
                nextRowString = "<dir> ";
              }
              else{
                nextRowString = "           ";
              }
              newContent = new String[folderFileCount + 2];
              newContent[folderFileCount] = "..";
              folderFileCount++;
              newContent[folderFileCount] = nextRowString + currFileName;;
              folderFileCount++;
            }
            else{
              temp = newContent;
              newContent = new String[folderFileCount + 1];
              for (i = 0; i < folderFileCount; i++){
                newContent[i] = temp[i];
              }
              if (true == Files.isDirectory(file)){
                nextRowString = "<dir> ";
              }
              else{
                nextRowString = "           ";
              }
              newContent[folderFileCount] = nextRowString + currFileName;
              folderFileCount++;
            }
          }
        }
      }
      catch (IOException | DirectoryIteratorException x){
        System.err.println(x);
      }
    }
    
    if (null == newContent){
      String[] prev = new String[1];
      prev[0] = "..";
      view0.setListData(prev);
    }
    else{
      view0.setListData(newContent);
    }
  }
  
  private void clearView(JList view){
    DefaultListModel viewListModel = (DefaultListModel) view.getModel();
    viewListModel.removeAllElements();
  }
  
  private void updateQuickAccess(){}
  
  private void addToQuickAccess(){
    String[] storeArray = null;
    int i = 0;
    int maxIndex = -1;
    int[] allIndices = null;
    
    maxIndex = view1.getLastVisibleIndex();
    if (maxIndex > -1){
      storeArray = new String[maxIndex + 2];
      allIndices = new int[maxIndex + 1];
      
      //copy earlier quick access content to storeArray
      for (i = 0; i <= maxIndex; i++){
        view1.setSelectedIndex(i);
        storeArray[i] = view1.getSelectedValue();
      }
      
      addQuickAccessFolder(currFolder.toString());
      storeArray[i] = getAlias();
      winCollection.setQA_folderAliases(storeArray);
      view1.setListData(storeArray);
    }
    else{
      storeArray = new String[1];
      addQuickAccessFolder(currFolder.toString());
      storeArray[0] = getAlias();
      winCollection.setQA_folderAliases(storeArray);
      view1.setListData(storeArray);
    }
  }
  
  private String getAlias(){
    return defaultAlias();
  }
  
  private String defaultAlias(){
    Path alias = null;
    
    alias = currFolder.getFileName();
    
    /* fixes problem with root ("/") folder */
    if (null == alias){
      return currFolder.toString();
    }
    
    return alias.toString();
  }
  
  private void addQuickAccessFolder(String newFolder){
    int i;
    String[] temp = quickAccessFolders;
    
    if (null == quickAccessFolders){
      quickAccessFolders = new String[1];
      quickAccessFolders[0] = newFolder;
    }
    else{
      quickAccessFolders = new String[temp.length + 1];
      for (i = 0; i < temp.length; i++){
        quickAccessFolders[i] = temp[i];
      }
      quickAccessFolders[i] = newFolder;
    }
    winCollection.setQuickAccessFolders(quickAccessFolders);
  }
  
  private void loadSelectedQuickAccessFolder(){
    int QAF_index;
    if (null != selectedQuickAccess && 1 == selectedQuickAccess.length){
      QAF_index = view1.getMinSelectionIndex();
      currFolder = Paths.get(quickAccessFolders[QAF_index]);
      folderNameField.setText(quickAccessFolders[QAF_index]);
      updateFolderContent();
    }
    else{
      errorWindow("Cannot load multiple Quick Access Folders");
    }
  }
  
  public void loadQuickAccessCollection(FolderCollectionItem collectionItem){
    int i;
    String[] aliases = collectionItem.getAliases();
    String[] paths = collectionItem.getPaths();
    
    setQuickAccessFolders(collectionItem.getPaths());
    setQuickAccessAliases(collectionItem.getAliases());
    setLoadedQuickAccessCollection(collectionItem.getName());
    winCollection.setLoadedFolderCollection(collectionItem.getName());
  }
  
  private void printSelectedFiles(){
    int i;
    
    if (null != selectedFolders){
      System.err.print("Selected files:\n\t");
      for (i = 0; i < selectedFolders.length; i++){
        System.err.print(selectedFolders[i] + ", ");
      }
    }
  }
  
  public boolean hideSuccessful(){
    if (true == winCollection.hideNotificationOk("MainWin1", myWindowIndex)){
      frame.setVisible(false);
      winCollection.printHiddenWindows();
      return true;
    }
    return false;
  }
  
  
  private Path[] getPathsOfCurrentFolder(){
    int i;
    int pathCount = 0;
    Path[] temp = null;
    Path[] paths = null;
    Path processedPath = null;
    //edit this to make sense
    
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(currFolder)){
      for (Path file: stream){
        processedPath = file.getFileName();
        
        if ('.' != processedPath.getFileName().toString().charAt(0) || 
            '.' == processedPath.getFileName().toString().charAt(1)){
          if (null == paths){
            paths = new Path[1];
            paths[pathCount] = processedPath;
            pathCount++;
          }
          else{
            temp = paths;
            paths = new Path[pathCount + 1];
            for (i = 0; i < pathCount; i++){
              paths[i] = temp[i];
            }
            paths[pathCount] = processedPath;
            pathCount++;
          }
        }
      }
    }
    catch (IOException | DirectoryIteratorException x){
      System.err.println(x.getMessage());
    }
    
    return paths;
  }
}
