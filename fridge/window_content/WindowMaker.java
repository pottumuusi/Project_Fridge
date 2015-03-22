package fridge.window_content;

import javax.swing.JFrame;

public class WindowMaker{
  fridge.action_handling.ActionHandlingContainer AH_Container;
  
  
  public WindowMaker(fridge.action_handling.ActionHandlingContainer AHC_ptr){
    AH_Container = AHC_ptr;
  }
  
  public JFrame newMainWin1(){
    JFrame frame = new JFrame("Fridge"); // create this frame in constructor of windowCollection and store it in windowCollection 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // class as every other window.
    
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
}