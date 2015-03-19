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
  private static final int MENU_BAR_TYPE_AMOUNT = 3;
  
  //private int menuBarTypeCount = 3;
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
  
  public JMenuBar createMenuBar(String menuBarType){
    JMenuBar menuBar = null;
    int i;
    
    /*if (menuBarType > menuBarTypeCount - 1 || menuBarType < 0){
      System.out.println("[DEBUG] invalid menuBarType. menuBar not created");
      return null;
    }*/
    
    if (false == isAllowedBarType(menuBarType)){
      System.out.println("[DEBUG] invalid menuBarType: " + menuBarType);
      return null;
    }
    
    switch (menuBarType){
    case "full":
      System.out.println("[DEBUG] in case: \"full\"");
      menuBar = fullMenuBar();
      System.out.println("[DEBUG] created menu with type: " + menuBarType);
    }
    
    //menuBar = fullMenuBar();
    //System.out.println("[DEBUG] created menu with type: " + menuBarType);
    
    return menuBar;
  }
  
  
  private boolean isAllowedBarType(String menuBarType){
    String[] allowedTypes;
    allowedTypes = new String[MENU_BAR_TYPE_AMOUNT];
    allowedTypes[0] = "full";
    allowedTypes[1] = "trimmed";
    allowedTypes[2] = "minimal";
    int i;
    
    for (i = 0; i < MENU_BAR_TYPE_AMOUNT - 1; i++){
      if (allowedTypes[i].equals(menuBarType)){
        return true;
      }
    }
    
    return false;
  }
  
  
  private JMenuBar fullMenuBar(){
    JMenuBar menuBar;
    
    menuBar = new JMenuBar();
    menuBar.add(createFileMenu());
    menuBar.add(createEditMenu());
    //menuBar.add(createActionsMenu());
    //menuBar.add(createOptionsMenu);
    //menuBar.add(createHelpMenu);
    
    return menuBar;
  }
  
  
  private JMenu createMenu(String name, int keyEvent){
    JMenu menu;
    
    menu = new JMenu(name);
    menu.setMnemonic(keyEvent);
    
    return menu;
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
  
  
  private JMenu createSubmenu(String name, int keyEvent, String description){
    JMenu submenu;
    
    submenu = new JMenu(name);
    submenu.setMnemonic(keyEvent);
    submenu.getAccessibleContext().setAccessibleDescription(
        description);
    
    return submenu;
  }
  
  
  private JMenuItem createMenuItem(String name, int mnemonic, String description){
    JMenuItem menuItem;
    
    menuItem = new JMenuItem(name, mnemonic);
    menuItem.getAccessibleContext().setAccessibleDescription(
        description);
    menuItem.addActionListener(this);
    
    return menuItem;
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
    JMenu menu, submenu;
    JMenuItem menuItem;
    
    menu = createMenu("Edit", KeyEvent.VK_E, "Edit menu");
    
    menuItem = createMenuItem("Open file", KeyEvent.VK_O, "Open chosen file/folder");
    menu.add(menuItem);
    
    submenu = createSubmenu("Rename", KeyEvent.VK_R);
    menuItem = createMenuItem("Selected item", KeyEvent.VK_S,
                              KeyEvent.VK_F2, 0, "Rename selected item");
    submenu.add(menuItem);
    
    menuItem = createMenuItem("Current group", KeyEvent.VK_E, KeyEvent.VK_R,
                              ActionEvent.CTRL_MASK, "Rename group that is being used");
    submenu.add(menuItem);
    menu.add(submenu);
    
    menuItem = createMenuItem("Cut", KeyEvent.VK_U, KeyEvent.VK_X,
                              ActionEvent.CTRL_MASK, "Cut selected item");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Copy", KeyEvent.VK_C, KeyEvent.VK_C,
                              ActionEvent.CTRL_MASK, "Copy selected item");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Paste", KeyEvent.VK_P, KeyEvent.VK_V,
                              ActionEvent.CTRL_MASK, "Paste copied or cut item");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Delete", KeyEvent.VK_D, KeyEvent.VK_DELETE, 0,
                              "Delete selected item");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Exclude", KeyEvent.VK_X, KeyEvent.VK_E,
                              ActionEvent.CTRL_MASK, "Exclude selected item (from some list)");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Select all", KeyEvent.VK_S, KeyEvent.VK_A,
                              ActionEvent.CTRL_MASK, "Select all items in a field");
    menu.add(menuItem);
    
    //submenu = createSubmenu("Select all", KeyEvent.VK_S);
    //menuItem = createMenuItem("Folders", KeyEvent.VK_);
    
    /*copy, cut, paste, select all (folders, groups, quick access)?, delete, exclude,
     * rename (current group, selected item), open file
     */
    
    return menu;
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
    fridge.action_handling.MenuActionEventBroker MAE_broker = new fridge.action_handling.MenuActionEventBroker();
    
    /*System.out.println("*Action event*\n"
                   + "Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")");*/
    /*String actStr = "*Action event*\n" 
                    + "Event source: " + source.getText()
                    + " (an instance of " + getClassName(source) + ")";*/
    
    MAE_broker.invokeAction(source.getText(), getClassName(source));
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