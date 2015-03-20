package fridge.window_content;

import java.awt.*;
import java.awt.event.*;

public class WindowCollection extends WindowAdapter{
  private int windowCount;
  private Point lastLocation;
  
  WindowCollection(){
    windowCount = 0;
    lastLocation = null;
  }
  
  public void addNew(){
    System.out.println("[DEBUG] create new window and store to WindowCollection");
  }
}