package fridge.windows;

import javax.swing.JList;
import javax.swing.JTextField;
import fridge.data.CollectionDataUser;


public class CollectionSave extends CallableByListener{
  private JList<String> collections_view;
  private CollectionDataUser dataUser;
  private JTextField saveNameField;
  private String saveWinType;
  private String[] collection_names;
  private String creator;
  
  public CollectionSave(fridge.window_content.WindowCollection winColl,
                        fridge.window_content.WindowMaker winMaker,
                        fridge.action_handling.ClassListSelectionListener CLSL_ptr,
                        fridge.action_handling.ClassActionListener[] CAL_ptrs,
                        JTextField par_nameField,
                        JList par_view0,
                        int par_myWindowIndex,
                        String par_saveWinType){
    
    super(winMaker.newCollSaveWin(winColl, CLSL_ptr, CAL_ptrs, par_nameField, par_view0, par_saveWinType),
          CLSL_ptr, CAL_ptrs, par_myWindowIndex, winColl);
    
    dataUser = new CollectionDataUser();
    saveWinType = par_saveWinType;
    saveNameField = par_nameField;
    collections_view = par_view0;
    creator = "-";
    
    updateViews();
    frame.pack();
  }
  
  public void openFile(){
  }
  
  public void updateViews(){
    collection_names = dataUser.getCollectionNames(); 
    if (null != collection_names){
      collections_view.setListData(collection_names);
    }
  }
  
  public void updateContent(){
  }
  
  protected void addItemsToGroup(String groupName){
  }
  
  protected void moveItemsToGroup(String groupName){
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    if ("ClassActionListener" == ML_ptr.getType()){
      if ("Close" == ML_ptr.getName()){
        close();
      }
      else if ("Save" == ML_ptr.getName()){
        if ("folder" == saveWinType){
          saveFolderCollection();
        }
        else if ("group" == saveWinType){
        }
        else{
          System.out.println("[DEBUG] ");
        }
      }
    }
  }
  
  private void saveFolderCollection(){
    int i;
    String[] aliases = winCollection.getQA_folderAliases();
    String[] folders = winCollection.getQuickAccessFolders();
    
    System.err.println("\nCollectionSave.saveFolderCollection()");
    System.err.println("[DEBUG] QA_folderAliases:");
    for (i = 0; i < aliases.length; i++){
      System.err.println("\t" + aliases[i]);
    }
    
    System.err.println("[DEBUG] QA_folders:");
    for (i = 0; i < folders.length; i++){
      System.err.println("\t" + folders[i]);
    }
    
    if (null == dataUser){
      System.err.println("Could not save Folder collection");
      System.err.println("CollectionSave.saveFolderCollection(): dataUser is null");
    }
    else{
      dataUser.saveFolderCollection(saveNameField.getText(),
                                    creator,
                                    winCollection.getQA_folderAliases(),
                                    winCollection.getQuickAccessFolders(),
                                    this);
      updateViews();
    }
  }
}
