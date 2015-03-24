package fridge.action_handling;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


//public class ClassListSelectionListener extends fridge.action_handling.MyListSelectionListener{
public class ClassListSelectionListener extends fridge.action_handling.MyListener implements ListSelectionListener{
  fridge.windows.CallableByListener listened;
  private int[] selectedIndexes;
  
  public ClassListSelectionListener(){
    super("ClassListSelectionListener");
    selectedIndexes = null;
  }
  
  public ClassListSelectionListener(fridge.windows.CallableByListener winToListen){
    super("ClassListSelectionListener");
    listened = winToListen;
    selectedIndexes = null;
  }
  
  public int getSelectedIndexesLen(){
    return selectedIndexes.length;
  }
  
  public int[] getSelectedIndexes(){
    return selectedIndexes;
  }
  
  protected void setListened(fridge.windows.CallableByListener CBL_ptr){
    listened = CBL_ptr;
    System.out.println("[DEBUG] CLSListener listens");
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
      System.out.println("[DEBUG] assigning " + (maxIndex - minIndex) + " slots to tempSelected");
      tempSelected = new int[(maxIndex - minIndex) + 1]; // + 1 so that new int[0] does not happen... 
      
      //first find out how many are selected
      for (i = minIndex; i <= maxIndex; i++){
        if (lsm.isSelectedIndex(i)){
          System.out.println("[DEBUG] assigning " + i + "to tempSelected[" + fillIndex + "]");
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
    
    listened.listenerEvent((fridge.action_handling.MyListener)this);
    /*if (false == isAdjusting){
      listened.listenerEvent(this);
    }*/
    //listened.listSelectionListenerCall()
  }
}