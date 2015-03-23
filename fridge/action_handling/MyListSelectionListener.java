package fridge.action_handling;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MyListSelectionListener implements ListSelectionListener{
  public void valueChanged(ListSelectionEvent e){
    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
    
    int firstIndex = e.getFirstIndex();
    int lastIndex = e.getLastIndex();
    boolean isAdjusting = e.getValueIsAdjusting();
    System.out.print("Event for indexes "
                       + firstIndex + " - " + lastIndex
                       + "; isAdjusting is " + isAdjusting
                       + "; selected indexes:");
    
    if (lsm.isSelectionEmpty()){
      System.out.println(" <none>");
    }
    else{
      int minIndex = lsm.getMinSelectionIndex();
      int maxIndex = lsm.getMaxSelectionIndex();
      for (int i = minIndex; i <= maxIndex; i++){
        if (lsm.isSelectedIndex(i)){
          System.out.print(" " + i);
        }
      }
    }
  }
}