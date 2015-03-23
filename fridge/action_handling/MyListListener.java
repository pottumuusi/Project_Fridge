package fridge.action_handling;

//import javax.swing.event.*;
//import javax.swing.ListSelectionModel;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class MyListListener implements ListSelectionListener{
  
  public void valueChanged(ListSelectionEvent e){
    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
    //String output;
    
    int firstIndex = e.getFirstIndex();
    int lastIndex = e.getLastIndex();
    boolean isAdjusting = e.getValueIsAdjusting();
    
    /*output.append("[DEBUG] Event for indexes "
        + firstIndex + " - " + lastIndex
        + "; isAdjusting is " + isAdjusting
        + "; selected indexes:");*/
    System.out.println("[DEBUG] Event for indexes "
                       + firstIndex + " - " + lastIndex
                       + "; isAdjusting is " + isAdjusting
                       + "; selected indexes:");
    
    if (lsm.isSelectionEmpty()){
      System.out.println(" <none>");
    }
    else{
      // Find out which indexes are selected.
      int minIndex = lsm.getMinSelectionIndex();
      int maxIndex = lsm.getMaxSelectionIndex();
      for (int i = minIndex; i <= maxIndex; i++){
        if (lsm.isSelectedIndex(i)){
          System.out.print(" " + i);
        }
      }
    }
    
    
    
    /*if (e.getValueIsAdjusting() == false){
      if (list.getSelectedIndex() == -1){
        System.out.println("[DEBUG] no list selection");
      }
      else{
        System.out.println("[DEBUG] there is a list selection");
      }
    }*/
  }
}