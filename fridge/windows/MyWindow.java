package fridge.windows;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;

public class MyWindow{
  protected JFrame frame;
  private fridge.action_handling.ActionHandlingContainer AH_container;
  protected int myWindowIndex;
  
  /*protected MyWindow(JFrame initFrame, fridge.Fridge fridgeInstance){
    frame = initFrame;
    //AH_container = new fridge.action_handling.ActionHandlingContainer(fridgeInstance);
    AH_container = fridgeInstance.getAH_container();
  }*/
  
  protected MyWindow(JFrame initFrame, int par_myWindowIndex){
    frame = initFrame;
    myWindowIndex = par_myWindowIndex;
  }
  
  public void close(){
    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
  }
  
  public void show(){
    frame.setVisible(true);
  }
  
  //abstract method getType();
}
