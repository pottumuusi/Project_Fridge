package fridge.windows;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import fridge.data.CollectionDataUser;
import fridge.data.FolderCollectionItem;

public class CollectionLoad extends CallableByListener{
  private fridge.window_content.WindowCollection windowCollection;
  private CollectionDataUser dataUser;
  private JComboBox collectionList;
  private String loadWinType;
  
  public CollectionLoad(fridge.window_content.WindowCollection winColl,
                        fridge.window_content.WindowMaker winMaker,
                        fridge.action_handling.ClassActionListener[] CAL_ptrs,
                        JComboBox collList,
                        int par_myWindowIndex,
                        String par_winType){
    super(winMaker.newCollLoadWin(winColl, CAL_ptrs, collList, par_winType),
          CAL_ptrs, par_myWindowIndex, winColl);
    DefaultComboBoxModel<String> strBoxModel = new DefaultComboBoxModel();
    dataUser = new CollectionDataUser();
    
    windowCollection = winColl;
    loadWinType = par_winType;
    collectionList = collList;
    collectionList.setModel(strBoxModel);
    updateCollectionList();
    
    frame.pack();
  }
  
  public void openFile(){
  }
  
  public void updateViews(){
    updateCollectionList();
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
      else if("Load" == ML_ptr.getName()){
        if ("folder" == loadWinType){
          loadFolderCollection();
        }
      }
    }
  }
  
  private void loadFolderCollection(){
    FolderCollectionItem collectionItem = null;
    
    String collectionName = (String)collectionList.getSelectedItem();
    System.err.println("loading collection (" + collectionName + ")");
    collectionItem = dataUser.loadFolderCollection(collectionName, frame);
    windowCollection.loadFolderQA(collectionItem);
  }
  
  private void updateCollectionList(){
    int i;
    String[] collectionNames;
    
    collectionNames = dataUser.getCollectionNames(frame);
    collectionList.removeAllItems();
    
    if (null != collectionNames){
      for (i = 0; i < collectionNames.length; i++){
        collectionList.addItem(collectionNames[i]);
      }
    }
  }
}