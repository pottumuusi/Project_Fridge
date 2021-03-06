package fridge.action_handling;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ClassListSelectionListener extends fridge.action_handling.MyListener implements ListSelectionListener{
  private fridge.windows.CallableByListener listened;
  private int[] selectedIndexes;
  
  public ClassListSelectionListener(String name){
    super("ClassListSelectionListener", name);
    selectedIndexes = null;
  }
  
  public ClassListSelectionListener(fridge.windows.CallableByListener winToListen, String name){
    super("ClassListSelectionListener", name);
    listened = winToListen;
    selectedIndexes = null;
  }
  
  public int getSelectedIndexesLen(){
    if (null != selectedIndexes){
      return selectedIndexes.length;
    }
    return 0;
  }
  
  public int[] getSelectedIndexes(){
    return selectedIndexes;
  }
  
  protected void setListened(fridge.windows.CallableByListener CBL_ptr){
    listened = CBL_ptr;
  }
  
  public void valueChanged(ListSelectionEvent e){
    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
    boolean isAdjusting = e.getValueIsAdjusting();
    int[] tempSelected;
    int fillIndex = 0;
    int i;
    
    if (!(lsm.isSelectionEmpty()) && false == isAdjusting){
      int minIndex = lsm.getMinSelectionIndex();
      int maxIndex = lsm.getMaxSelectionIndex();
      tempSelected = new int[(maxIndex - minIndex) + 1]; // + 1 so that new int[0] does not happen... 
      
      //first find out how many are selected
      for (i = minIndex; i <= maxIndex; i++){
        if (lsm.isSelectedIndex(i)){
          tempSelected[fillIndex] = i;
          fillIndex++;
        }
      }
      
      selectedIndexes = new int[fillIndex];
      for (i = 0; i < fillIndex; i++){
        selectedIndexes[i] = tempSelected[i];
      }
      tempSelected = null;
    }
    
    if (false == isAdjusting){
      listened.listenerEvent((fridge.action_handling.MyListener)this);
    }
  }
}