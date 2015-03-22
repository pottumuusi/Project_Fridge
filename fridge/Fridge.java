package fridge;



public class Fridge{
  private String[] programStatus;
  private fridge.action_handling.ActionHandlingContainer AH_container;
  private fridge.window_content.WindowCollection windowCollection;
  private fridge.action_handling.ContainerListeners contListeners;
  
  public Fridge(){
    initProgramStatus();
    AH_container = new fridge.action_handling.ActionHandlingContainer(this);
    windowCollection = new fridge.window_content.WindowCollection(this);
    contListeners = new fridge.action_handling.ContainerListeners(this);
    //windowCollection = new fridge.window_content.WindowCollection(AH_container);
  }
  
  public fridge.action_handling.ActionHandlingContainer getAH_container(){
    return AH_container;
  }
  
  public fridge.action_handling.ContainerListeners getContListeners(){
    return contListeners;
  }
  
  public fridge.window_content.WindowCollection getWinCollection(){
    return windowCollection;
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
  
  public void controlledExit(){
    int i;
    
    for (i = 0; i < programStatus.length; i++){
      if (programStatus[i].equals("no_unsaved_data")){
        System.out.println("No unsaved data. Exiting");
        System.exit(0);
      }
    }
    saveData();
    System.exit(0);
  }
  
  private void saveData(){
    
  }
  
  private static void createAndShowGUI(){
    fridge.action_handling.ActionHandlingContainer AH_container;
    fridge.window_content.WindowCollection windowCollection;
    
    //Create and set up the window
    fridge.Fridge fridgeInstance = new fridge.Fridge();
    
    
    
    
    //fridgeInstance = new fridge.Fridge();
  }
  
  public static void main(String[] args){
    //fridge.window_content.Menu menu = new fridge.window_content.Menu();
    
    javax.swing.SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        
        createAndShowGUI();
      }
    });
    
  }
}