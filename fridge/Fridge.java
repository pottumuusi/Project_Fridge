package fridge;

import javax.swing.JFrame;

public class Fridge{
  private String[] programStatus;
  //private fridge.action_handling.MenuActionEventBroker MAE_broker;
  //private fridge.action_handling.MenuActionEventHandler MAE_handler;
  //private fridge.Fridge fridgeInstance;
  
  public Fridge(){
    initProgramStatus();
    
    /*MAE_broker = new fridge.action_handling.MenuActionEventBroker();
    MAE_handler = new fridge.action_handling.MenuActionEventHandler();*/
  }
  
  private void initProgramStatus(){
    programStatus = new String[1];
    programStatus[0] = "no_unsaved_data";
  }
  
  public String[] getProgramStatus(){
    return programStatus;
  }
  
  private void setProgramStatus(String newStatus, int statusIndex){
    System.out.println("[DEBUG] setProgramStatus");
  }
  
  public void updateProgramStatus(){
    System.out.println("[DEBUG] updateProgramStatus()");
  }
  
  public void printProgramStatus(){
    int i;
    
    System.out.println("Program status is:");
    for (i = 0; i < programStatus.length; i++){
      System.out.println(programStatus[i]);
    }
  }
  
  /*public fridge.action_handling.MenuActionEventHandler getMAE_handler(){
    return MAE_handler;
  }*/
  
  /*public void MAE_brokerCall(String eventSource, String className, fridge.Fridge fridgeInstance){
    MAE_broker.solveAction(eventSource, className, MAE_handler, fridgeInstance);
  }*/
  
  /*public void MAE_handlerCall(){
    
  }*/
  
  /*private static void createActionEventObjects(){
    MAE_broker = new fridge.action_handling.MenuActionEventBroker();
    MAE_handler = new fridge.action_handling.MenuActionEventHandler();
  }*/
  
  /*private static void createFridgeInstance(){
    fridge.Fridge fridgeInstance = new fridge.Fridge();
  }*/
  
  private static void createAndShowGUI(){
    //Create and set up the window
    fridge.Fridge fridgeInstance = new fridge.Fridge();
    //fridgeInstance = new fridge.Fridge();
    fridge.action_handling.ActionHandlingContainer AH_Container = new fridge.action_handling.ActionHandlingContainer(fridgeInstance);
    JFrame frame = new JFrame("Fridge");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
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
  }
  
  public static void main(String[] args){
    //fridge.window_content.Menu menu = new fridge.window_content.Menu();
    
    //System.out.println("Hello World!");
    //menu.testFunction();
    javax.swing.SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        
        createAndShowGUI();
      }
    });
    
  }
}