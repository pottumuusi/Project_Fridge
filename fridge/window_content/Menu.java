package fridge.window_content;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

//import javax.swing.JPanel;
import javax.swing.JFrame;

public class Menu implements ActionListener, ItemListener{
  
  
  private int menuBarTypeCount = 3;
  /*public Menu(){
    
  }
  
  public Menu(int menuType){
    
  }*/
  
  public void testFunction(){
    System.out.println("testing more");
  }
  
  /*public JMenuBar newMenuBar(int menuType){
    switch (menuType){
    case 0:
      return createMenuBar(menuType)
    case 1:
      break;
    }
  }*/
  
  public JMenuBar createMenuBar(int menuBarType){
    JMenuBar menuBar;
    
    if (menuBarType > menuBarTypeCount - 1 || menuBarType < 0){
      System.out.println("[DEBUG] invalid menuBarType. menuBar not created");
      return null;
    }
    
    /*switch (menuBarType){
    case 0:
      menuBar = fullMenuBar();
      System.out.println("[DEBUG] created menu with type: " + menuBarType);
    }*/
    
    menuBar = fullMenuBar();
    System.out.println("[DEBUG] created menu with type: " + menuBarType);
    
    return menuBar;
  }
  
  private JMenuBar fullMenuBar(){
    JMenuBar menuBar;
    
    menuBar = new JMenuBar();
    menuBar.add(createFileMenu());
    
    return menuBar;
  }
  
  private JMenu createFileMenu(){
    JMenu menu;
    JMenuItem menuItem;
    
    menu = new JMenu("A Menu");
    menu.setMnemonic(KeyEvent.VK_A);
    menu.getAccessibleContext().setAccessibleDescription(
        "File menu");
    
    menuItem = new JMenuItem("Item wants submenus", KeyEvent.VK_T);
    /* constructorin avulla asetetaan KeyEvent.VK_T
     * Olisi voitu saada aikaan myös sanomalla:
     *  menuItem.setMnemonic(KeyEvent.VK_T);
     */
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_1, ActionEvent.ALT_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription(
        "Exit");
    menuItem.addActionListener(this);
    menu.add(menuItem);
    
    return menu;
  }
  
  public void actionPerformed(ActionEvent e){
    JMenuItem source = (JMenuItem)(e.getSource());
    
    System.out.println("*Action event*\n"
                   + "Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")");
    /*String actStr = "*Action event*\n" 
                    + "Event source: " + source.getText()
                    + " (an instance of " + getClassName(source) + ")";*/
  }
  
  public void itemStateChanged(ItemEvent e){
    JMenuItem source = (JMenuItem)(e.getSource());

    System.out.println("*Item event*"
                     + "Event source: " + source.getText()
                     + " (an instance of " + getClassName(source) +")"
                     + "New state: "
                     + ((e.getStateChange() == ItemEvent.SELECTED) ?
                        "selected":"unselected"));
  }
  
  /* Class name will be returned */
  protected String getClassName(Object o){
    String classString = o.getClass().getName();
    int dotIndex = classString.lastIndexOf(".");
    return classString.substring(dotIndex+1);
  }
}