package fridge.windows;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import fridge.data.CollectionDataUser;
import fridge.data.FolderCollectionItem;

public class CollectionDelete extends CallableByListener{
  private fridge.window_content.WindowCollection windowCollection;
  private CollectionDataUser dataUser;
  private JComboBox collectionList;
  private String deleteWinType;
  
  public CollectionDelete(fridge.window_content.WindowCollection winColl,
                          fridge.window_content.WindowMaker winMaker,
                          fridge.action_handling.ClassActionListener[] CAL_ptrs,
                          JComboBox collList,
                          int par_myWindowIndex,
                          String par_winType){
    super(winMaker.newCollDeleteWin(winColl, CAL_ptrs, collList, par_winType),
          CAL_ptrs, par_myWindowIndex, winColl);
    
    DefaultComboBoxModel<String> strBoxModel = new DefaultComboBoxModel();
    dataUser = new CollectionDataUser();
    
    windowCollection = winColl;
    deleteWinType = par_winType;
    collectionList = collList;
    collectionList.setModel(strBoxModel);
    updateCollectionList();
    
    frame.pack();
  }
  
  public void openFile(){}
  public void updateViews(){
    updateCollectionList();
  }
  public void updateContent(){}
  protected void addItemsToGroup(String groupName){}
  protected void moveItemsToGroup(String groupName){}
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
    if ("ClassActionListener" == ML_ptr.getType()){
      if ("Close" == ML_ptr.getName()){
        close();
      }
      else if("Delete" == ML_ptr.getName()){
        if ("folder" == deleteWinType){
          deleteFolderCollection();
        }
      }
    }
  }
  
  private void deleteFolderCollection(){
    FolderCollectionItem collectionItem = null;
    
    String collectionName = (String)collectionList.getSelectedItem();
    System.err.println("deleting collection (" + collectionName + ")");
    dataUser.deleteFolderCollection(collectionName, frame);
    updateCollectionList();
    
    System.err.println("next inform window collection about the delete");
    windowCollection.deleteFolderQA(collectionName);
  }
  
  private void updateCollectionList(){
    int i;
    String[] collectionNames;
    
    collectionNames = dataUser.getCollectionNames(frame);
    if (null != collectionNames){
      System.out.println("collectionNames len ==" + collectionNames.length);
    }
    collectionList.removeAllItems();
    
    if (null != collectionNames){
      for (i = 0; i < collectionNames.length; i++){
        collectionList.addItem(collectionNames[i]);
      }
    }
  }
}