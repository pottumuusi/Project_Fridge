package fridge.action_handling;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class MyListListener implements ListSelectionListener{
  
  public void valueChanged(ListSelectionEvent e){
    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
    
    int firstIndex = e.getFirstIndex();
    int lastIndex = e.getLastIndex();
    boolean isAdjusting = e.getValueIsAdjusting();
  }
}