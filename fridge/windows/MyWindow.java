package fridge.windows;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;

public class MyWindow{
  JFrame frame;
  fridge.action_handling.ActionHandlingContainer AH_container;
  
  /*protected MyWindow(JFrame initFrame, fridge.Fridge fridgeInstance){
    frame = initFrame;
    //AH_container = new fridge.action_handling.ActionHandlingContainer(fridgeInstance);
    AH_container = fridgeInstance.getAH_container();
  }*/
  
  protected MyWindow(JFrame initFrame){
    frame = initFrame;
  }
  
  public void close(){
    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
  }
  
  //abstract method getType();
}
