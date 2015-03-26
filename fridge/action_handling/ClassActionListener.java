package fridge.action_handling;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClassActionListener extends fridge.action_handling.MyListener implements ActionListener{
  private fridge.windows.CallableByListener listened;
  private String actionCommand;
  
  public ClassActionListener(String name){
    super("ClassActionListener", name);
  }
  
  public ClassActionListener(fridge.windows.CallableByListener winToListen, String name){
    super("ClassActionListener", name);
    listened = winToListen;
  }
  
  public String getActionCommand(){
    return actionCommand;
  }
  
  protected void setListened(fridge.windows.CallableByListener CBL_ptr){
    listened = CBL_ptr;
  }
  
  public void actionPerformed(ActionEvent e){
    actionCommand = e.getActionCommand();
    listened.listenerEvent((fridge.action_handling.MyListener)this);
  }
}