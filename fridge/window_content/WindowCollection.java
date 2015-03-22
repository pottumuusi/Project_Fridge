package fridge.window_content;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

public class WindowCollection extends WindowAdapter{
  private int windowCount;
  private Point lastLocation;
  private int maxX;
  private int maxY;
  private JFrame[] windowList;
  private JFrame[][] listContainer;
  private fridge.window_content.Containers cont; // want this to be here?
  private fridge.window_content.WindowMaker winMaker;
  
  /*WindowCollection(){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    windowCount = 0;
    lastLocation = null;
    maxX = ;
    
    //create the first frame here. The fridge window
  }*/
  
  /*public WindowCollection(){
    windowCount = 0;
    lastLocation = null;
    
    windowList = new JFrame[4];
    System.out.println("[DEBUG] size of windowList == " 
                       + windowList.length
                       + " (should be 4)");
    
    //create the first frame here
    windowList[windowCount] = winMaker.newMainWin1();
    windowCount = 1;
    
  }*/
  
  public WindowCollection(fridge.action_handling.ActionHandlingContainer AHC_ptr){
    winMaker = new WindowMaker(AHC_ptr);
    windowCount = 0;
    lastLocation = null;
    
    windowList = new JFrame[4];
    System.out.println("[DEBUG] size of windowList == " 
                       + windowList.length
                       + " (should be 4)");
    
    addNew("MainWin1");
    
    //create the first frame here
    /*windowList[windowCount] = winMaker.newMainWin1();
    windowCount++;*/
  }
  
  public void addNew(String winType){
    /*if (windowCount + 1 == windowList.length){
      windowList = makeLonger(windowList);
    }*/
    
    switch (winType){
    case "MainWin1":
      windowList[windowCount] = winMaker.newMainWin1();
      windowCount++;
      break;
    }
  }
  
  /*private JFrame[] makeLonger(JFrame[]){
    
  }*/
}