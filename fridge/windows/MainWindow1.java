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

//public class MainWindow1 extends fridge.windows.CallableByListener implements DocumentListener, ActionListener{
  //private fridge.filesystem.fileOperator // = fridge.filesystem.fileOperator
  //private fridge.filesystem.folderOperator // = new fridge.filesystem.folderOperator
  //private fridge.filesystem.groupOperator // = new fridge.filesystem.groupOperator
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
  //private fridge.window_content.WindowCollection winCollection;
  
  /*public MainWindow1(fridge.window_content.WindowCollection winColl,
                     fridge.window_content.WindowMaker winMaker,
                     fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs,
                     fridge.action_handling.ClassActionListener[] CAL_ptrs,
                     JTextField fn_par,
                     JList view0_par,
                     JList view1_par,
                     fridge.Fridge fridgeInstance){*/
  public MainWindow1(fridge.window_content.WindowCollection winColl,
                     fridge.window_content.WindowMaker winMaker,
                     fridge.action_handling.ClassListSelectionListener[] CLSL_ptrs,
                     fridge.action_handling.ClassActionListener[] CAL_ptrs,
                     JTextField fn_par,
                     JList view0_par,
                     JList view1_par,
                     fridge.window_content.Menu menu,
                     int par_myWindowIndex){
    /*super(winMaker.newMainWin1(winColl, CLSL_ptrs, CAL_ptrs, fn_par, view0_par, view1_par),
          CLSL_ptrs, CAL_ptrs, fridgeInstance);*/
  super(winMaker.newMainWin1(winColl, CLSL_ptrs, CAL_ptrs, fn_par, view0_par, view1_par, menu),
        CLSL_ptrs, CAL_ptrs, par_myWindowIndex, menu, winColl);
    //menu.setContainingWindow(this); // move menu to upper class !!!!!!!!!!!!!!
    
    winCollection = winColl;
    selectedFolders = null;
    quickAccessFolders = null;
    selectedQuickAccess = null;
    loadedQuickAccessCollection = null;
    
    folderNameField = fn_par;
    view0 = view0_par;
    view1 = view1_par;
    //if (Files.isDirectory(folderNameField.getText())){
    currFolder = Paths.get(folderNameField.getText());
    prevFolder = Paths.get("/");
    //}
    //else{
    //  currFolder = null;
    //} 
    //folderNameField.getDocument().addDocumentListener(this);
    //folderNameField.addActionListener(this);
    /*InputMap im = folderNameField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = folderNameField.getActionMap();
    im.put(KeyStroke.getKeyStroke("ENTER"),*/
    
    System.out.println("[DEBUG] before pack()");
    frame.pack();
    System.out.println("[DEBUG] after pack()");
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
  
  /*public void groupButtonMenuPress(String buttonName, String containingMenu){
    if (){
      
    }
  }*/
  
  protected void moveItemsToGroup(String groupName){
    int i;
    Path[] itemsToPass;
    Path[] tempPaths;
    
    tempPaths = getPathsOfCurrentFolder();
    itemsToPass = new Path[selectedFolders.length];
    
    for (i = 0; i < selectedFolders.length; i++){
      itemsToPass[i] = tempPaths[selectedFolders[i]];
    }
    
    winCollection.setGroupItems(groupName, itemsToPass);
  }
  
  protected void addItemsToGroup(String groupName){
    int i;
    Path[] itemsToPass;
    Path[] tempPaths;
    
    tempPaths = getPathsOfCurrentFolder();
    itemsToPass = new Path[selectedFolders.length];
    
    for (i = 0; i < selectedFolders.length; i++){
      itemsToPass[i] = tempPaths[selectedFolders[i]];
    }
    
    winCollection.addGroupItems(groupName, itemsToPass);
    /*for (i = 0; i < selectedFolders.length; i++){
      //addItemsToGroups expects Path[] variable!!!
      addItemsToGroups();
      //((fridge.windows.MainWindow2)winCollection.getMyWindow(winCollection.getMyWindowsIndex("MainWin2"))).addItemsToGroup(itemsToPass);
    }*/
  }
  
  public void updateViews(){
    System.out.println("[DEBUG] MainWin1 updateViews not set");
  }
  
  public void updateContent(){
    //System.out.println("[DEBUG] MainWin1 updateCotnent not set");
    //updateMenu();
    updateFolderContent();
    updateQuickAccess();
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
    System.err.println("creating file: " + newFilePath.toString());
    
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
      System.err.println("quickaccessFolders:");
      for (i = 0; i < quickAccessFolders.length; i++){
        System.err.println(quickAccessFolders[i]);
        if (i != QAF_index){
          System.err.println("i == " + i + ", QAf_index == " + QAF_index);
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
          System.err.println("newAlias " + i + " == " + newAliases[i]);
        }
        
        
        setQuickAccessAliases(newAliases);
        //clearQuickAccessAliases();
        updateContent();
      }
    }
  }
  
  public void delete(){
    int answer = -1;
    boolean noo = false;
    
    if (null != selectedFolders){
      answer = yesNoWindow("Are you sure you want to delete " + selectedFolders.length +" selected file(s)");
      
      System.err.println("answer == " + answer);
      
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
  }
  
  public void cut(){
    moveSetup("cut");
  }
  
  private void moveSetup(String moveType){
    Path[] moveFiles;
    int i;
    
    if (null != selectedFolders){
      System.err.println("cutting " + selectedFolders.length + " files");
      moveFiles = new Path[selectedFolders.length];
      
      for (i = 0; i < moveFiles.length; i++){
        moveFiles[i] = Paths.get(fullFileNames[selectedFolders[i]]);
      }
      
      winCollection.setMoveSources(moveFiles);
      winCollection.setMoveType(moveType);
    }
  }
  
  public void paste(){
    Path[] sourceFiles = winCollection.getMoveSources();
    String moveType = winCollection.getMoveType();
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
        updateContent();
      }
    }
  }
  
  private boolean handleMove(Path sourceFile){
    try{
      System.err.println("\tmoving " + sourceFile.toString());
      Files.move(sourceFile, currFolder.resolve(sourceFile.getFileName()), StandardCopyOption.ATOMIC_MOVE);
    } catch(AtomicMoveNotSupportedException anse){
        System.err.println("atomic move not supported");
        try{
          Files.move(sourceFile, currFolder);
        } catch(IOException ioe){
          System.err.println("could not move file. " + ioe.getMessage());
          errorWindow("Could not move file " + sourceFile.toString());
          return false;
        }
    } catch(IOException ioe){
        System.err.println("could not move file. " + ioe.getMessage());
        errorWindow("Could not move file " + sourceFile.toString());
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
      /*if (fullFileNames[selectedFolders[0]].equals("..")){
        System.err.println("going to previous directory");
      }*/
      
      if (null != fullFileNames){
        tempPath = Paths.get(fullFileNames[selectedFolders[0]]);
      }
      
      /* open previous folder */
      if (0 == selectedFolders[0]){
        currFolder = prevFolder;
        System.err.println("getting file name from: " + currFolder.toString());
        if (!(currFolder.toString().equals("/"))){
          tempFileName = currFolder.getFileName().toString();
          tempWholePath = currFolder.toString();
          /*System.err.println("currFolder==" + currFolder.toString());
          System.err.println("curr");*/
          //System.err.println(currFolder.toString().substring(0, 5));
          System.err.println("prev folder trial: " + currFolder.toString().substring(0, tempWholePath.length() - tempFileName.length()));
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
    //int[] selectedIndexes;
    String tempStore = null;
    int i;
    
    //make it impossible to select the first row of folders of groups
    //if selectedFolders[i] == 0 then copy all else to selectedFolders
    //and update it to view?
    
    //switch (ML_ptr.getType()){
    if ("ClassListSelectionListener" == ML_ptr.getType()){
      if ("folder" == ML_ptr.getName()){
        selectedFolders = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedFolders = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
        printSelectedFiles();
      }
      else if ("quickAccess" == ML_ptr.getName()){
        selectedQuickAccess = new int[((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexesLen()];
        selectedQuickAccess = ((fridge.action_handling.ClassListSelectionListener)ML_ptr).getSelectedIndexes();
      }
    }
    else if ("ClassActionListener" == ML_ptr.getType()){
      if ("folderShowGroup" == ML_ptr.getName()){
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
      else if ("quickSave" == ML_ptr.getName()){
        System.out.println("quickSave press");
        //ask for alias. it could be passed to addToQuickAccess method as a parameter.
        addToQuickAccess();
      }
      else if ("quickLoad" == ML_ptr.getName()){
        System.out.println("quickLoad press");
        loadSelectedQuickAccessFolder();
      }
      else if ("qa_showGroup" == ML_ptr.getName()){
        System.out.println("qa_showGroup press");
      }
      else if ("qa_operations" == ML_ptr.getName()){
        winCollection.addNew("operationsWin", myWindowIndex);
      }
      else if ("folderNameField" == ML_ptr.getName()){
        System.out.println("actionCommand == " + ((fridge.action_handling.ClassActionListener)ML_ptr).getActionCommand());
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
  
  private void updateFolderContent(){
    String[] newContent = null;
    String[] temp = null;
    String currFileName = null;
    String nextRowString = null;
    int folderFileCount = 0;
    int testFileCount = 0;
    int i;
    //String temp;
    //System.out.println("[DEBUG] null new content size = " + newContent.length);
    //getFiles(currentPath);
    //newContent = createFolderViewContent();
    if (Files.isDirectory(currFolder)){
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(currFolder)){
        fullFileNames = null; //reset earlier file names
        /* set previous folder */
        /*fullFileNames = new String[1];
        fullFileNames[0] = currFolder.getParent().toString();*/
        
        for (Path file: stream){
          //temp = file.getFileName();
          //if ('.' != file.getFileName().getCharAt(0)){
          currFileName = file.getFileName().toString();
          
          
          //READ
          //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
          //fullFileNames = file.toString(); //make full fileNames size go up!!!!!!!
          //fullFileNames are used in storing folder information to groups
          //alternatively similar code to the code in this method could be
          //run and the result then stored to group
          //READ
          
          if (null == fullFileNames){
            fullFileNames = new String[1];
            System.err.println("setting fileName[0] to " + file.getParent().toString());
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
              //temp = new String[folderFileCount];
              temp = newContent;
              System.out.println(file.getFileName());
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
    else{
      //errorMessage("Given path does not match a directory");
    }
    
    System.out.println("newContent:");
    if (null == newContent){
      String[] prev = new String[1];
      prev[0] = "..";
      System.err.println("newContent == null");
      //fullFileNames = new String[1];
      //fullFileNames[0] = currFolder.getParent();
      //clearView(view0);
      view0.setListData(prev);
      //view0.removeAll();
    }
    else{
      System.err.println("newContent len == " + newContent.length);
      for (i = 0; i < newContent.length; i++){
        System.out.println("    " + newContent[i]);
      }
      
      view0.setListData(newContent);
    }
  }
  
  private void clearView(JList view){
    DefaultListModel viewListModel = (DefaultListModel) view.getModel();
    viewListModel.removeAllElements();
  }
  
  private void updateQuickAccess(){
    String[] folders = null;
    String[] aliases = null;
    String loadedFolderCollection = null;
    
    
    /*setQuickAccessFolders(winCollection.getQuickAccessFolders());
    setQuickAccessAliases(winCollection.getQA_folderAliases());
    loadedFolderCollection = winCollection.getLoadedFolderCollection();*/
    
    /*if (null == folders){
      
    }*/
  }
  
  private void addToQuickAccess(){
    String[] storeArray = null;
    //String[] temp = null;
    int i = 0;
    int maxIndex = -1;
    int[] allIndices = null;
    
    maxIndex = view1.getLastVisibleIndex();
    if (maxIndex > -1){
      storeArray = new String[maxIndex + 2];
      allIndices = new int[maxIndex + 1];
      //temp = new String[maxIndex + 1];
      
      //copy earlier quick access content to storeArray
      for (i = 0; i <= maxIndex; i++){
        //allIndices[i] = i;
        view1.setSelectedIndex(i);
        storeArray[i] = view1.getSelectedValue();
      }
      
      addQuickAccessFolder(currFolder.toString());
      storeArray[i] = getAlias();
      winCollection.setQA_folderAliases(storeArray);
      view1.setListData(storeArray);
      
      System.err.println("[DEBUG] storeArray:");
      for (i = 0; i < storeArray.length; i++){
        System.err.println("    " + storeArray[i]);
      }
      
      System.err.println("[DEBUG] quickAccessFolders:");
      for (i = 0; i < quickAccessFolders.length; i++){
        System.err.println("    " + quickAccessFolders[i]);
      }
    }
    else{
      storeArray = new String[1];
      addQuickAccessFolder(currFolder.toString());
      storeArray[0] = getAlias();
      winCollection.setQA_folderAliases(storeArray);
      System.err.println("[DEBUG] storeArray[0] = " + storeArray[0]);
      view1.setListData(storeArray);
    }
  }
  
  private String getAlias(){
    return defaultAlias();
  }
  
  private String defaultAlias(){
    Path alias = null;
    
    System.err.println("[DEBUG] currFolder == " + currFolder);
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
      //currFolder = view1.getListData(selectedQuickAccess[0]);
      //updateFolderContent();
    }
    else{
      errorWindow("Cannot load multiple Quick Access Folders");
    }
  }
  
  public void loadQuickAccessCollection(FolderCollectionItem collectionItem){
    int i;
    String[] aliases = collectionItem.getAliases();
    String[] paths = collectionItem.getPaths();
    
    System.err.println("MainWin1 loadQuickAccessCollection execution!");
    System.err.println(collectionItem.getName());
    System.err.println(collectionItem.getCreator());
    
    System.err.println("[DEBUG] folderAliases to load");
    for (i = 0; i < aliases.length; i++) {
      System.err.println("\t" + aliases[i]);
    }
    
    System.err.println("[DEBUG] folder paths to load");
    for (i = 0; i < paths.length; i++){
      System.err.println("\t" + paths[i]);
    }
    
    setQuickAccessFolders(collectionItem.getPaths());
    setQuickAccessAliases(collectionItem.getAliases());
    setLoadedQuickAccessCollection(collectionItem.getName());
    winCollection.setLoadedFolderCollection(collectionItem.getName());
  }
  
  private void getFiles (){
  }
  
  private String[] createFolderViewContent(){
    return null;
  }
  
  private void textFieldString(){
    //System.out.println("fieldStr == " + folderNameFieldField.getText());
  }
  
  private void printSelectedFiles(){
    int i;
    
    if (null != selectedFolders){
      System.err.print("Selected files:\n\t");
      for (i = 0; i < selectedFolders.length; i++){
        System.out.print(selectedFolders[i] + ", ");
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
        
        //currFileName = file.getFileName().toString();
        processedPath = file.getFileName();
        
        if ('.' != processedPath.getFileName().toString().charAt(0) || 
            '.' == processedPath.getFileName().toString().charAt(1)){
          if (null == paths){
            paths = new Path[1];
            paths[pathCount] = processedPath;
            pathCount++;
          }
          else{
            //temp = new String[folderFileCount];
            temp = paths;
            //System.out.println(file.getFileName());
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
      System.err.println(x);
    }
    
    return paths;
  }
  
  public void close(){
    System.out.println("[DEBUG] Close MainWindow1 here");
  }
  
  public void insertUpdate(DocumentEvent ev) {
    textFieldString();
  }

  public void removeUpdate(DocumentEvent ev) {
    textFieldString();
  }

  public void changedUpdate(DocumentEvent ev) {
  }
  
  public void actionPerformed(ActionEvent e){
    System.out.println("old");
  }
}
