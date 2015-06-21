package fridge.windows;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
  
  protected void errorWindow(String errorMsg){
    JOptionPane.showMessageDialog(frame,
        errorMsg,
        "Error",
        JOptionPane.ERROR_MESSAGE);
  }
  
  protected int yesNoWindow(String questionMsg){
    int answer = -1;
    
    answer = JOptionPane.showConfirmDialog(frame,
                                           questionMsg,
                                           "Delete",
                                           JOptionPane.YES_NO_OPTION);
    
    return answer;
  }
  
  //abstract method getType();
}
