package fridge.window_content;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;

public class WindowMaker{
  private fridge.action_handling.ActionHandlingContainer AH_Container;
  
  
  public WindowMaker(fridge.action_handling.ActionHandlingContainer AHC_ptr){
    AH_Container = AHC_ptr;
  }
  
  public JFrame newMainWin1(fridge.window_content.WindowCollection controller){
    //JFrame frame = new JFrame(controller, "Fridge"); // create this frame in constructor of windowCollection and store it in windowCollection
    JFrame frame = new NewFrame(controller, "Fridge");
    
    
    //Create and set up the content pane.
    /*fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance.MAE_broker,
                                                                     fridgeInstance.MAE_handler);*/
    //fridge.window_content.Menu menu = new fridge.window_content.Menu(fridgeInstance);
    
    
    fridge.window_content.Menu menu = new fridge.window_content.Menu(AH_Container);
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    
    frame.setJMenuBar(menu.createMenuBar("full"));
    frame.setContentPane(cont.createContentPane(0));
    
    //Display the window.
    frame.setSize(450, 260);
    frame.setVisible(true);
    
    return frame;
  }
  
  public void controlledExit(){
    AH_Container.performExit();
  }
  
  /*public void closed(WindowEvent e){
    System.out.println("window closed");
  }*/
  
  class NewFrame extends JFrame{
    protected Dimension defaultSize = new Dimension(200, 200);
    protected fridge.window_content.WindowCollection windowCollection = null;
    
    public NewFrame(fridge.window_content.WindowCollection controller, String name){
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