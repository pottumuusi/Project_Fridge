package fridge.window_content;

import javax.swing.JTextField;
import javax.swing.JTextArea;
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
  
  /*public JFrame newMainWin1(fridge.window_content.WindowCollection controller){
    //JFrame frame = new JFrame(controller, "Fridge"); // create this frame in constructor of windowCollection and store it in windowCollection
    JFrame frame = new ListenedFrame(controller, "Fridge");
    
    
    //Create and set up the content pane.
    /*fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.MAE_broker,
                                                                     fridgeInstance.MAE_handler);*/
    //fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance);
    /*
    
    fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("full"));
    frame.setContentPane(cont.createContentPane("mainWin1"));
    
    decideLocation(frame);
    
    //Display the window.
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    return frame;
  }*/
  
  /*public JFrame newMainWin1(fridge.window_content.WindowCollection controller,
                            fridge.action_handling.ClassListSelectionListener[] CLSListenerList,
                            JTextField folderName){
    //JFrame frame = new JFrame(controller, "Fridge"); // create this frame in constructor of windowCollection and store it in windowCollection
    JFrame frame = new ListenedFrame(controller, "Fridge");
    
    
    //Create and set up the content pane.
    /*fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.MAE_broker,
                                                                     fridgeInstance.MAE_handler);*/
    //fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance);
    /*
    
    fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("full"));
    frame.setContentPane(cont.createContentPane("mainWin1", CLSListenerList, folderName));
    
    decideLocation(frame);
    
    //Display the window.
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    return frame;
  }*/
  
  public JFrame newMainWin1(fridge.window_content.WindowCollection controller,
                            fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                            fridge.action_handling.ClassActionListener[] CAL_list,
                            JTextField folderName,
                            JList view0,
                            JList view1){
    JFrame frame = new ListenedFrame(controller, "Fridge");
    
    fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("full"));
    frame.setContentPane(cont.createContentPane("mainWin1", CLSL_list, CAL_list, folderName, view0, view1));
    
    decideLocation(frame);
    
    //Display the window.
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    return frame;
  }
  
  /*public JFrame newHelpWin(fridge.window_content.WindowCollection controller){
    JFrame frame = new ListenedFrame(controller, "Help");
    fridge.action_handling.ContainerListeners contListeners = fridgeInstance.getContListeners();
    //fridge.action_handling.MyListListener listListener = contListeners.getListListener();
    
    fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
    fridge.window_content.Containers cont = new fridge.window_content.Containers(contListeners);
    
    frame.setJMenuBar(menu.createMenuBar("help"));
    frame.setContentPane(cont.createContentPane("help"));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    // deal with window spawn location!!!!!!
    
    return frame;
  }*/
  
  public JFrame newHelpWin(fridge.window_content.WindowCollection controller,
                           fridge.action_handling.ClassListSelectionListener CLSListener,
                           JTextArea helpText){
    JFrame frame = new ListenedFrame(controller, "Help");
    
    
    fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("help"));
    frame.setContentPane(cont.createContentPane("help", CLSListener, helpText));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }
  
  /*public JFrame newHelpWin(fridge.window_content.WindowCollection controller,
                           fridge.action_handling.MyListSelectionListener LSListener){
    JFrame frame = new ListenedFrame(controller, "Help");
    
    fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.getAH_container());
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("help"));
    frame.setContentPane(cont.createContentPane("help", LSListener));
    
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    decideLocation(frame);
    
    return frame;
  }*/
  
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
