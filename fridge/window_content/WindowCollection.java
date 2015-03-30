package fridge.window_content;

import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JComboBox;
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
  private fridge.window_content.NamedWindow[] namedWindows;
  
  public WindowCollection(fridge.Fridge FI_ptr){
    fridgeInstance = FI_ptr;
    winMaker = new WindowMaker(FI_ptr);
    windowCount = 0;
    //lastLocation = null;
    
    windowList = new JFrame[4];
    myWindows = new fridge.windows.MyWindow[4];
    namedWindows = new fridge.window_content.NamedWindow[4];
    
    addNew("MainWin1");
  }
  
  public void addNew(String winType){
    System.out.println("start of addNew: myWindows.length = " + myWindows.length);
    System.out.println("windowCount = " + windowCount);
    if (windowCount + 1 == myWindows.length){
      System.out.println("now in if");
      //windowList = makeLonger(windowList);
      myWindows = makeLonger(myWindows);
      namedWindows = makeLonger(namedWindows);
      System.out.println("after makeLonger method myWindows.length = " + myWindows.length);
    }
    
    //switch (winType){
    //case "MainWin1":
    if ("MainWin1" == winType){
      newNamedWin("MainWin1");
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
                                                              mw1_menu,
                                                              windowCount);
      //break;
    }
    else if("MainWin2" == winType){
    //case "MainWin2":
      newNamedWin("MainWin2");
      JList<String> view0, view1;
      //JList<String> mw2_view0, mw2_view1;
      
      fridge.action_handling.ClassListSelectionListener[] CLSL_list;
      CLSL_list = new fridge.action_handling.ClassListSelectionListener[2];
      CLSL_list[0] = new fridge.action_handling.ClassListSelectionListener("group");
      CLSL_list[1] = new fridge.action_handling.ClassListSelectionListener("quickAccess");
      
      fridge.action_handling.ClassActionListener[] CAL_list;
      CAL_list = new fridge.action_handling.ClassActionListener[6];
      CAL_list[0] = new fridge.action_handling.ClassActionListener("groupNameBox");
      CAL_list[1] = new fridge.action_handling.ClassActionListener("groupShowFolder");
      CAL_list[2] = new fridge.action_handling.ClassActionListener("quickSave");
      CAL_list[3] = new fridge.action_handling.ClassActionListener("quickLoad");
      CAL_list[4] = new fridge.action_handling.ClassActionListener("qa_showFolder");
      CAL_list[5] = new fridge.action_handling.ClassActionListener("qa_operations");
      
      JComboBox groupList = new JComboBox();
      
      view0 = new JList<String>();
      view1 = new JList<String>();
      
      fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      
      myWindows[windowCount] = new fridge.windows.MainWindow2(this,
                                                              winMaker,
                                                              CLSL_list,
                                                              CAL_list,
                                                              groupList,
                                                              view0,
                                                              view1,
                                                              menu,
                                                              windowCount);
      //fridge.action_handling.ClassListSelectionListener[] mw2_CLSL_list;
      //mw2_CLSL_list = new fridge.action_handling.ClassListSelectionListener[2];
      //mw2_CLSL_list[0] = new fridge.action_handling.ClassListSelectionListener("group");
      //mw2_CLSL_list[1] = new fridge.action_handling.ClassListSelectionListener("quickAccess");
      
      
      //break;
    }
    else if("HelpWin" == winType){
    //case "HelpWin":
      //newNamedWin(numberedWinName("HelpWin"));
      newNamedWin("HelpWin");
      JTextArea helpTexts = new JTextArea(5, 30);
      fridge.window_content.Menu hw_menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
      fridge.action_handling.ClassListSelectionListener CLSListener = new fridge.action_handling.ClassListSelectionListener("helpText");
      //windowList[windowCount] = winMaker.newHelpWin(this);
      //fridge.action_handling.MyListSelectionListener LSListener = new fridge.action_handling.MyListSelectionListener();
      //myWindows[0] = new fridge.windows.HelpWindow(winMaker.newHelpWin(this, CLSListener), CLSListener); //replace 0 with windowCount when ready
      myWindows[windowCount] = new fridge.windows.HelpWindow(this, winMaker, CLSListener,
                                                             helpTexts, hw_menu, windowCount); //replace 0 with windowCount when ready
      //break;
    }
    else{
    //default:
      System.out.println("[DEBUG] no such winType. windowCount not incremented");
      windowCount--;
    //}
    }
    windowCount++;
    System.out.println("[DEBUG] window count after creation == " + windowCount);
    printNamedWindows(); //this is debug. delete pls
  }
  
  private void newNamedWin(String par_name){
    namedWindows[windowCount] = new fridge.window_content.NamedWindow(par_name, windowCount);
  }
  
  private String numberedWinName(String wantedName){
    int i, k;
    int largestNum;
    String nameWithoutNum;
    String windowName;
    
    for (k = 0; k < namedWindows.length; k++){
      windowName = namedWindows[k].getName();
      for(i = 0; i < wantedName.length(); i++){
        
      }
      
    }
    return null;
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
  
  private fridge.windows.MyWindow[] makeLonger(fridge.windows.MyWindow[] oldList){
    int i;
    fridge.windows.MyWindow[] newList = new fridge.windows.MyWindow[oldList.length + 4];
    
    System.out.println("[DEBUG] making myWindows longer. this might cause problems!!");
    
    for (i = 0; i < oldList.length; i++){
      newList[i] = oldList[i];
    }
    
    return newList;
  }
  
  private fridge.window_content.NamedWindow[] makeLonger(fridge.window_content.NamedWindow[] oldList){
    int i;
    fridge.window_content.NamedWindow[] newList = new fridge.window_content.NamedWindow[oldList.length + 4];
    
    System.out.println("[DEBUG] making namedWindows longer. this might cause problems!!");
    
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
  
  public boolean hideNotificationOk(String windowName, int myWindowIndex){
    //fridge.window_content.SleepingWindow[] temp = null;
    int i;
    
    if (namedWindows[myWindowIndex].getName() == windowName){
      namedWindows[myWindowIndex].setIsHidden(true);
      return true;
    }
    
    System.out.println("[DEBUG] hideNotificationOk got invalid window data. window should not be hidden now");
    return false;
  }
  
  /*public boolean isHidden(String windowName){
    //scan all namedWindows and see if they match given name and are hidden
    
  }*/
  
  public void showWindow(String windowName){
    int i;
    
    for (i = 0; i < windowCount; i++){
      //if (namedWindows[i].getName().equals(windowName)){
      if (namedWindows[i].getName() == windowName &&
          true == namedWindows[i].getIsHidden()){
        System.out.println("[DEBUG] showing window: " + windowName);
        myWindows[namedWindows[i].getMyWindowIndex()].show();
        namedWindows[i].setIsHidden(false);
        break;
      }
    }
  }
  
  public void printHiddenWindows(){
    int i;
    
    System.out.println("Hidden windows:");
    for (i = 0; i < windowCount; i++){
      if (true == namedWindows[i].getIsHidden()){
        System.out.println("    " + namedWindows[i].getName() + " " + namedWindows[i].getMyWindowIndex());
      }
    }
  }
  
  public void printNamedWindows(){
    int i;
    
    System.out.println("Named windows:");
    for (i = 0; i < windowCount; i++){
      System.out.println("    " + namedWindows[i].getName() +  " " + namedWindows[i].getMyWindowIndex());
    }
  }
}
