package fridge.window_content;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.*;
import java.awt.event.*;

public class WindowMaker{
  //private fridge.action_handling.ActionHandlingContainer AH_Container;
  private fridge.Fridge fridgeInstance;
  private Point lastLocation;
  //private Point newLocation;
  private int maxX;
  private int maxY;
  
  /*public WindowMaker(fridge.action_handling.ActionHandlingContainer AHC_ptr){
    AH_Container = AHC_ptr;
  }*/
  
  public WindowMaker(fridge.Fridge FI_ptr){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    maxX = screenSize.width - 50;
    maxY = screenSize.height - 50;
    fridgeInstance = FI_ptr;
    lastLocation = null;
    
  }
  
  private void decideLocation(JFrame frame){
    if (lastLocation != null){
      lastLocation.translate(40, 40); // move the window 40 pixels
      if ((lastLocation.x > maxX) || (lastLocation.y > maxY)){
        lastLocation.setLocation(0, 0);
      }
      frame.setLocation(lastLocation);
    }
    else{
      lastLocation = frame.getLocation();
    }
  }
  
  public JFrame newMainWin1(fridge.window_content.WindowCollection controller,
                            fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                            fridge.action_handling.ClassActionListener[] CAL_list,
                            JTextField folderName,
                            JList view0,
                            JList view1,
                            fridge.window_content.Menu menu){
    JFrame frame = new ListenedFrame(controller, "Fridge");
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("full"));
    frame.setContentPane(cont.createContentPane("mainWin1", CLSL_list, CAL_list, folderName, view0, view1));
    
    decideLocation(frame);
    
    //Display the window.
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    return frame;
  }
  
  public JFrame newMainWin2(fridge.window_content.WindowCollection controller,
                            fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                            fridge.action_handling.ClassActionListener[] CAL_list,
                            JComboBox groupList,
                            JList view0,
                            JList view1,
                            fridge.window_content.Menu menu){
    JFrame frame = new ListenedFrame(controller, "Fridge");
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("full"));
    frame.setContentPane(cont.createContentPane("mainWin2", CLSL_list, CAL_list, groupList, view0, view1));
    
    decideLocation(frame);
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    return frame;
  }
  
  public JFrame newHelpWin(fridge.window_content.WindowCollection controller,
                           fridge.action_handling.ClassListSelectionListener CLSListener,
                           JTextArea helpText,
                           fridge.window_content.Menu menu){
    JFrame frame = new ListenedFrame(controller, "Help");
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("help"));
    frame.setContentPane(cont.createContentPane("help", CLSListener, helpText));
    
    frame.setSize(600, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }
  
  public JFrame newNewFolderWin(fridge.window_content.WindowCollection controller,
                                fridge.action_handling.ClassActionListener[] CAL_list,
                                JTextField groupNameField,
                                fridge.window_content.Menu menu){
    JFrame frame = new ListenedFrame(controller, "New file");
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("minimal"));
    frame.setContentPane(cont.createContentPane("newFolderWin", CAL_list, groupNameField));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }
  
  public JFrame newNewGroupWin(fridge.window_content.WindowCollection controller,
                               fridge.action_handling.ClassActionListener[] CAL_list,
                               JTextField groupNameField,
                               fridge.window_content.Menu menu){
    JFrame frame = new ListenedFrame(controller, "New group");
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("minimal"));
    frame.setContentPane(cont.createContentPane("newGroupWin", CAL_list, groupNameField));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }
  
  public JFrame newQAOperationsWin(fridge.window_content.WindowCollection controller,
                                  fridge.action_handling.ClassActionListener[] CAL_list){
    JFrame frame = new ListenedFrame(controller, "Quick Access operations");
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    frame.setContentPane(cont.createContentPane("QAOperations", CAL_list));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }
  
  public JFrame newCollSaveWin(fridge.window_content.WindowCollection controller,
                               fridge.action_handling.ClassListSelectionListener CLSListener,
                               fridge.action_handling.ClassActionListener[] CAL_list,
                               JTextField saveNameField,
                               JList view0,
                               String saveWinType){
    JFrame frame;
    
    if ("group" == saveWinType){
      frame = new ListenedFrame(controller, "Save group collection");
    }
    else{
      frame = new ListenedFrame(controller, "Save folder collection");
    }
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    frame.setContentPane(cont.createContentPane("collSaveWin", CLSListener, CAL_list, saveNameField, view0));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }
  
  public JFrame newCollLoadWin(fridge.window_content.WindowCollection controller,
                               fridge.action_handling.ClassActionListener[] CAL_list,
                               JComboBox collList,
                               String loadWinType){
    JFrame frame;
    
    if ("group" == loadWinType){
      frame = new ListenedFrame(controller, "Load group collection");
    }
    else{
      frame = new ListenedFrame(controller, "Load folder collection");
    }
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    frame.setContentPane(cont.createContentPane("collLoadWin", CAL_list, collList));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }
  
  public JFrame newCollDeleteWin(fridge.window_content.WindowCollection controller,
                                 fridge.action_handling.ClassActionListener[] CAL_list,
                                 JComboBox collList,
                                 String loadWinType){
    JFrame frame;
    
    if ("group" == loadWinType){
      frame = new ListenedFrame(controller, "Delete group collection");
    }
    else {
      frame = new ListenedFrame(controller, "Delete folder collection");
    }
    
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    frame.setContentPane(cont.createContentPane("collDeleteWin", CAL_list, collList));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }
  
  /*public void closed(WindowEvent e){
    System.out.println("window closed");
  }*/
  
  class ListenedFrame extends JFrame{
    protected Dimension defaultSize = new Dimension(200, 200);
    protected fridge.window_content.WindowCollection windowCollection = null;
    
    public ListenedFrame(fridge.window_content.WindowCollection controller, String name){
      super (name);
      windowCollection = controller;
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // class as every other window.
      addWindowListener(windowCollection);
      
      /*fridge.window_content.Menu menu = new fridge.window_content.Menu(AH_Container);
      fridge.window_content.Containers cont = new fridge.window_content.Containers();
      
      frame.setJMenuBar(menu.createMenuBar("full"));
      frame.setContentPane(cont.createContentPane(0));
      
      //Display the window.
      frame.setSize(450, 260);
      frame.setVisible(true);*/
      
      
    }
  }
}
