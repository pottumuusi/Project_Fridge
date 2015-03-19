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
    JMenuBar menuBar = null;
    
    if (menuBarType > menuBarTypeCount - 1 || menuBarType < 0){
      System.out.println("[DEBUG] invalid menuBarType. menuBar not created");
      return null;
    }
    
    switch (menuBarType){
    case 0:
      menuBar = fullMenuBar();
      System.out.println("[DEBUG] created menu with type: " + menuBarType);
    }
    
    //menuBar = fullMenuBar();
    //System.out.println("[DEBUG] created menu with type: " + menuBarType);
    
    return menuBar;
  }
  
  private JMenuBar fullMenuBar(){
    JMenuBar menuBar;
    
    menuBar = new JMenuBar();
    menuBar.add(createFileMenu());
    
    return menuBar;
  }
  
  
  private JMenu createMenu(String name, int keyEvent, String description){
    JMenu menu;
    
    menu = new JMenu(name);
    menu.setMnemonic(keyEvent);
    menu.getAccessibleContext().setAccessibleDescription(
        description);
    
    return menu;
  }
  
  
  private JMenu createSubmenu(String name, int keyEvent){
    JMenu submenu;
    
    submenu = new JMenu(name);
    submenu.setMnemonic(keyEvent);
    
    return submenu;
  }
  
  
  private JMenuItem createMenuItem(String name, int keyEvent, int actionEvent){
    JMenuItem menuItem;
    
    menuItem = new JMenuItem(name);
    /* constructorin avulla asetetaan KeyEvent.VK_T
     * Olisi voitu saada aikaan myös sanomalla:
     *  menuItem.setMnemonic(KeyEvent.VK_T);
     */
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
        keyEvent, actionEvent));
    menuItem.addActionListener(this);
    
    return menuItem;
  }
  
  
  private JMenuItem createMenuItem(String name, int mnemonic, int keyEvent, int actionEvent){
    JMenuItem menuItem;
    
    menuItem = new JMenuItem(name, mnemonic);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
        keyEvent, actionEvent));
    menuItem.addActionListener(this);
    
    return menuItem;
  }
  
  
  private JMenuItem createMenuItem(String name, int mnemonic, int keyEvent, int actionEvent, String description){
    JMenuItem menuItem;
    
    menuItem = new JMenuItem(name, mnemonic);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
        keyEvent, actionEvent));
    menuItem.getAccessibleContext().setAccessibleDescription(
        description);
    menuItem.addActionListener(this);
    
    return menuItem;
  }
  
  
  private JMenu createFileMenu(){
    JMenu menu, submenu;
    JMenuItem menuItem;
    
    menu = createMenu("File", KeyEvent.VK_F, "File menu");
    submenu = createSubmenu("New", KeyEvent.VK_N);
    
    /* Add content of submenu */
    menuItem = createMenuItem("Folder", KeyEvent.VK_L, ActionEvent.CTRL_MASK);
    submenu.add(menuItem);
    
    menuItem = createMenuItem("Group", KeyEvent.VK_G, ActionEvent.CTRL_MASK);
    submenu.add(menuItem);
    menu.add(submenu); // Add submenu New to File menu
    
    /* Add rest menu content */
    menuItem = createMenuItem("Refresh", KeyEvent.VK_T, KeyEvent.VK_F5, 0, "Reloads window");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Exit", KeyEvent.VK_E, KeyEvent.VK_Q,
                              ActionEvent.CTRL_MASK, "Exit the program");
    menu.add(menuItem);
    
    return menu;
  }
  
  
  private JMenu createEditMenu(){
    return null;
  }
  
  
  private JMenu createActionsMenu(){
    return null;
  }
  
  
  private JMenu createOptionsMenu(){
    return null;
  }
  
  
  private JMenu createHelpMenu(){
    return null;
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