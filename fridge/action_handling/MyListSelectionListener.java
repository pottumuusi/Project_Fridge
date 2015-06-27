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
  }
}