package fridge;



public class Fridge{
  private String[] programStatus;
  //public fridge.action_handling.ActionHandling Container AH_container;
  //public fridge.window_content.WindowCollection windowCollection;
  
  public Fridge(){
    initProgramStatus();
    
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
  
  
  private static void createAndShowGUI(){
    fridge.action_handling.ActionHandlingContainer AH_container;
    fridge.window_content.WindowCollection windowCollection;
    
    //Create and set up the window
    fridge.Fridge fridgeInstance = new fridge.Fridge();
    
    
    AH_container = new fridge.action_handling.ActionHandlingContainer(fridgeInstance);
    windowCollection = new fridge.window_content.WindowCollection(AH_container);
    
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