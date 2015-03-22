package fridge.action_handling;

import javax.swing.event.*;

public class MyListListener implements ListSelectionListener{
  
  public void valueChanged(ListSelectionEvent e){
    ListSelectionModel lsm; //kesken
    
    if (e.getValueIsAdjusting() == false){
      if (list.getSelectedIndex() == -1){
        System.out.println("[DEBUG] no list selection");
      }
      else{
        System.out.println("[DEBUG] there is a list selection");
      }
    }
  }
}