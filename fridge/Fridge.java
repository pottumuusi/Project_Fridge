package fridge;

import javax.swing.JFrame;

public class Fridge{
  private static void createAndShowGUI(){
    //Create and set up the window
    JFrame frame = new JFrame("Fridge");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Create and set up the content pane.
    fridge.window_content.Menu menu = new fridge.window_content.Menu();
    fridge.window_content.Containers cont = new fridge.window_content.Containers();
    frame.setJMenuBar(menu.createMenuBar(0));
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