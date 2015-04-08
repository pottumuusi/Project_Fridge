package fridge.windows;

import javax.swing.JComboBox;

public class CollectionLoad extends CallableByListener{
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
    loadWinType = par_winType;
    collectionList = collList;
    frame.pack();
  }
  
  public void openFile(){
  }
  
  public void updateViews(){
  }
  
  public void updateContent(){
  }
  
  protected void addItemsToGroup(String groupName){
  }
  
  protected void moveItemsToGroup(String groupName){
  }
  
  protected void handleEvent(fridge.action_handling.MyListener ML_ptr){
  }
}