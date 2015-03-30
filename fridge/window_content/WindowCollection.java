package fridge.window_content;

import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;


public class WindowCollection extends WindowAdapter{
  private int windowCount;
  private JFrame[] windowList;
  private JFrame[][] listContainer;
  private fridge.Fridge fridgeInstance;
  private fridge.window_content.Containers cont; // want this to be here?
  private fridge.window_content.WindowMaker winMaker;
  
  private fridge.windows.MyWindow[] myWindows;
  
  //private HelpWindow
  
  
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
  
  public WindowCollection(fridge.Fridge FI_ptr){
    fridgeInstance = FI_ptr;
    winMaker = new WindowMaker(FI_ptr);
    windowCount = 0;
    //lastLocation = null;
    
    windowList = new JFrame[4];
    myWindows = new fridge.windows.MyWindow[4];
    
    addNew("MainWin1");
  }
  
  /*public WindowCollection(fridge.action_handling.ActionHandlingContainer AHC_ptr,
                          fridge.Fridge FI_ptr){
    fridgeInstance = FI_ptr;
    winMaker = new WindowMaker(AHC_ptr);
    windowCount = 0;
    //lastLocation = null;
    
    windowList = new JFrame[4];
    System.out.println("[DEBUG] size of windowList == " 
                       + windowList.length
                       + " (should be 4)");
    
    //make the main window in initialization so that the user can interact with program
    addNew("MainWin1");
  }*/
  
  public void addNew(String winType){
    if (windowCount + 1 == windowList.length){
      windowList = makeLonger(windowList);
    }
    
    switch (winType){
    case "MainWin1":
      //JTextArea fileView0 = new JTextArea(5,30);
      JList<String> view0, view1;
      
      fridge.action_handling.ClassListSelectionListener[] CLSL_list;
      CLSL_list = new fridge.action_handling.ClassListSelectionListener[2];
      CLSL_list[0] = new fridge.action_handling.ClassListSelectionListener("folder");
      CLSL_list[1] = new fridge.action_handling.ClassListSelectionListener("quickAccess");
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[6];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("folderNameField");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("folderShowGroup");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("quickSave");
      CAL_list[3] = new fridge.action_handling.ClassActionListener("quickLoad");
      CAL_list[4] = new fridge.action_handling.ClassActionListener("qa_showGroup");
      CAL_list[5] = new fridge.action_handling.ClassActionListener("qa_operations");
      
      JTextField folderName = new JTextField("/");
  
      String[] view0Data = {"folder1    group1", "folder2"}; // still testing. will be initialized empty
      view0 = new JList<String>(view0Data);
      String[] view1Data = {"folder1", "folder2"};
      view1 = new JList<String>();
      
      fridge.window_content.Menu mw1_menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
	  
      myWindows[windowCount] = new fridge.windows.MainWindow1(this,
                                                              winMaker,
                                                              CLSL_list,
                                                              CAL_list,
                                                              folderName,
                                                              view0,
                                                              view1,
                                                              mw1_menu);
      //windowList[windowCount] = winMaker.newMainWin1(this, winMaker, listenerList);
      //windowList[windowCount] = winMaker.newMainWin1(this);
      //winMaker.newMainWin1(this);
      break;
    case "HelpWin":
      JTextArea helpTexts = new JTextArea(5, 30);
      fridge.window_content.Menu hw_menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      fridge.action_handling.ClassListSelectionListener CLSListener = new fridge.action_handling.ClassListSelectionListener("helpText");
      //windowList[windowCount] = winMaker.newHelpWin(this);
      //fridge.action_handling.MyListSelectionListener LSListener = new fridge.action_handling.MyListSelectionListener();
      //myWindows[0] = new fridge.windows.HelpWindow(winMaker.newHelpWin(this, CLSListener), CLSListener); //replace 0 with windowCount when ready
      myWindows[windowCount] = new fridge.windows.HelpWindow(this, winMaker, CLSListener, helpTexts, hw_menu); //replace 0 with windowCount when ready
      break;
    }
    windowCount++;
    System.out.println("[DEBUG] window count after creation == " + windowCount);
  }
  
  
  
  private JFrame[] makeShorter(JFrame[] oldList){
    return null;
  }
  
  private JFrame[] makeLonger(JFrame[] oldList){
    int i;
    JFrame[] newList = new JFrame[oldList.length + 4];
    //garbage collection should deal with the oldList now?
    System.out.println("[DEBUG] making windowList longer. this might cause problems!!");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  public void windowClosed(WindowEvent e){
    windowCount--;
    System.out.println("[DEBUG] window closed. windowCount == " + windowCount);
    if (windowCount <= 0){
      System.out.println("All windows gone. Exiting");
      fridgeInstance.controlledExit();
    }
    //winMaker.updateLocation();
  }
}
