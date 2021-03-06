package fridge.window_content;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JFrame;

public class Menu implements ActionListener, ItemListener{
  private static final int MENU_BAR_TYPE_AMOUNT = 5;
  private fridge.action_handling.ActionHandlingContainer AH_Container;
  private fridge.windows.CallableByListener containingWindow; //earlier this was MyWindow
  
  public Menu(){
    System.out.println("[DEBUG] Menu can't be created without providing fridgeInstance");
    System.exit(1);
  }
  
  public Menu(fridge.action_handling.ActionHandlingContainer AH_Container_ptr){
    AH_Container = AH_Container_ptr;
  }
  
  public void setContainingWindow(fridge.windows.CallableByListener set_par){
    containingWindow = set_par;
  }
  
  public void testFunction(){
    System.out.println("testing more");
  }
  
  public void update(fridge.window_content.WindowCollection winColl){
    System.out.println("[DEBUG] executing menu update");
    int i, k;
    int groupCount;
    JMenu tempMenu;
    JMenuItem tempItem, testItem;
    JMenuBar tempMenuBar;
    fridge.group.Group[] tempGroups;
    
    groupCount = winColl.getGroupCount();
    tempGroups = winColl.getGroups();
    
    tempMenuBar = containingWindow.getMenuBar();
    tempMenu = tempMenuBar.getMenu(2);
    
    for (i = 0; i < tempMenu.getItemCount(); i++){
      tempItem = tempMenu.getItem(i);
      System.out.println("    " + tempItem.getActionCommand());
      if ("Add to group" == tempItem.getActionCommand()){
        ((JMenu)tempItem).removeAll();
        for (k = 0; k < groupCount; k++){
          ((JMenu)tempItem).add(createMenuItem(tempGroups[k].getName()));
          ((JMenu)tempItem).getItem(k).setName("Add to group");
        }
        
        System.err.println("Added groups to menu");
      }
      else if("Move to group" == tempItem.getActionCommand()){
        ((JMenu)tempItem).removeAll();
        for (k = 0; k < groupCount; k++){
          ((JMenu)tempItem).add(createMenuItem(tempGroups[k].getName()));
          ((JMenu)tempItem).getItem(k).setName("Move to group");
        }
      }
    }
  }
  
  private void setGroups(fridge.window_content.WindowCollection winColl){
    winColl.getGroups();
  }
  
  public JMenuBar createMenuBar(String menuBarType){
    JMenuBar menuBar = null;
    int i;
    
    if (false == isAllowedBarType(menuBarType)){
      System.err.println("[DEBUG] invalid menuBarType: " + menuBarType);
      return null;
    }
    
    switch (menuBarType){
    case "full":
      menuBar = fullMenuBar();
      break;
    case "trimmed":
      menuBar = trimmedMenuBar();
      break;
    case "mw2":
      menuBar = mainWin2MenuBar();
      break;
    case "minimal":
      menuBar = minimalMenuBar();
      break;
    case "help":
      menuBar = helpMenuBar();
    }
    
    return menuBar;
  }
  
  
  private boolean isAllowedBarType(String menuBarType){
    String[] allowedTypes;
    allowedTypes = new String[MENU_BAR_TYPE_AMOUNT];
    allowedTypes[0] = "full";
    allowedTypes[1] = "search";
    allowedTypes[2] = "minimal";
    allowedTypes[3] = "help";
    allowedTypes[4] = "mw2";
    int i;
    
    for (i = 0; i < MENU_BAR_TYPE_AMOUNT; i++){
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
    menuBar.add(createActionsMenu());
    menuBar.add(createHelpMenu());
    
    return menuBar;
  }
  
  private JMenuBar mainWin2MenuBar(){
    JMenuBar menuBar = new JMenuBar();
    
    menuBar.add(createReducedFileMenu());
    menuBar.add(createReducedEditMenu());
    menuBar.add(createActionsMenu());
    menuBar.add(createHelpMenu());
    
    return menuBar;
  }
  
  private JMenuBar trimmedMenuBar(){
    JMenuBar menuBar;
    
    menuBar = new JMenuBar();
    menuBar.add(createReducedFileMenu());
    menuBar.add(createEditMenu());
    menuBar.add(createActionsMenu());
    
    return menuBar;
  }
  
  
  private JMenuBar minimalMenuBar(){
    JMenuBar menuBar;
    
    menuBar = new JMenuBar();
    menuBar.add(createFileMenu());
    menuBar.add(createEditMenu());
    
    return menuBar;
  }
  
  
  private JMenuBar helpMenuBar(){
    JMenuBar menuBar;
    
    menuBar = new JMenuBar();
    menuBar.add(createFileMenu());
    
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
    submenu.addActionListener(this);
    
    return submenu;
  }
  
  private JMenuItem createMenuItem(String name){
    JMenuItem menuItem;
    
    menuItem = new JMenuItem(name);
    menuItem.addActionListener(this);
    
    return menuItem;
  }
  
  private JMenuItem createMenuItem(String name, String description){
    JMenuItem menuItem;
    
    menuItem = new JMenuItem(name);
    menuItem.getAccessibleContext().setAccessibleDescription(description);
    menuItem.addActionListener(this);
    
    return menuItem;
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
     * Olisi voitu saada aikaan myos sanomalla:
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
    menuItem = createMenuItem("File", KeyEvent.VK_L, ActionEvent.CTRL_MASK);
    submenu.add(menuItem);
    
    menuItem = createMenuItem("Group", KeyEvent.VK_G, ActionEvent.CTRL_MASK);
    submenu.add(menuItem);
    menu.add(submenu); // Add submenu New to File menu
    
    /* Add rest menu content */
    menuItem = createMenuItem("Refresh", KeyEvent.VK_T, KeyEvent.VK_F5, 0, "Reloads window");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Close", KeyEvent.VK_C, "Close current window");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Quit", KeyEvent.VK_E, KeyEvent.VK_Q,
                              ActionEvent.CTRL_MASK, "Exit the program");
    menu.add(menuItem);
    
    return menu;
  }
  
  private JMenu createReducedFileMenu(){
    JMenu menu, submenu;
    JMenuItem menuItem;
    
    menu = createMenu("File", KeyEvent.VK_F, "File menu");
    submenu = createSubmenu("New", KeyEvent.VK_N);
    
    /* Add content of submenu */
    
    menuItem = createMenuItem("Group", KeyEvent.VK_G, ActionEvent.CTRL_MASK);
    submenu.add(menuItem);
    menu.add(submenu); // Add submenu New to File menu
    
    /* Add rest menu content */
    menuItem = createMenuItem("Refresh", KeyEvent.VK_T, KeyEvent.VK_F5, 0, "Reloads window");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Close", KeyEvent.VK_C, "Close current window");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Quit", KeyEvent.VK_E, KeyEvent.VK_Q,
                              ActionEvent.CTRL_MASK, "Exit the program");
    menu.add(menuItem);
    
    return menu;
  }
  
  private JMenu createEditMenu(){
    JMenu menu, submenu;
    JMenuItem menuItem;
    
    menu = createMenu("Edit", KeyEvent.VK_E, "Edit menu");
    
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
    
    return menu;
  }
  
  private JMenu createReducedEditMenu(){
    JMenu menu;
    JMenuItem menuItem;
    
    menu = createMenu("Edit", KeyEvent.VK_E, "Edit menu");
    
    menuItem = createMenuItem("Cut", KeyEvent.VK_U, KeyEvent.VK_X,
                              ActionEvent.CTRL_MASK, "Cut selected item");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Delete", KeyEvent.VK_D, KeyEvent.VK_DELETE, 0,
                              "Delete selected item");
    menu.add(menuItem);
    
    menuItem = createMenuItem("Exclude", KeyEvent.VK_X, KeyEvent.VK_E,
                              ActionEvent.CTRL_MASK, "Exclude selected item (from some list)");
    menu.add(menuItem);
    
    return menu;
  }
  
  private JMenu createActionsMenu(){
    JMenu menu, submenu;
    JMenuItem menuItem;
    
    menu = createMenu("Actions", KeyEvent.VK_A, "Actions menu");
    
    menuItem = createMenuItem("Open file", KeyEvent.VK_O, "Open chosen file/folder");
    menu.add(menuItem);
    
    submenu = createSubmenu("Add to group", KeyEvent.VK_A);
    menu.add(submenu);
    
    submenu = createSubmenu("Move to group", KeyEvent.VK_M);
    menu.add(submenu);
    
    return menu;
  }
  
  
  private JMenu createOptionsMenu(){
    JMenu menu;
    JMenuItem menuItem;
    
    menu = createMenu("Options", KeyEvent.VK_O, "Options menu");
    
    menuItem = createMenuItem("Settings", KeyEvent.VK_S, "Open settings window");
    menu.add(menuItem);
    
    return menu;
  }
  
  
  private JMenu createHelpMenu(){
    JMenu menu;
    JMenuItem menuItem;
    
    menu = createMenu("Help", KeyEvent.VK_H, "Help menu");
    
    menuItem = createMenuItem("View help", KeyEvent.VK_V,
                              KeyEvent.VK_F1, 0, "Open help window");
    menu.add(menuItem);
    
    return menu;
  }
  
  
  public void actionPerformed(ActionEvent e){
    JMenuItem source = (JMenuItem)(e.getSource());
    
    if (null == source.getName()){
      AH_Container.MAE_brokerCall(source.getText(),
                                  getClassName(source),
                                  containingWindow);
    }
    else{
      AH_Container.MAE_brokerCall(source.getText(),
                                  getClassName(source),
                                  containingWindow,
                                  source.getName());
    }
  }
  
  public void itemStateChanged(ItemEvent e){
    JMenuItem source = (JMenuItem)(e.getSource());
    
    System.err.println("*Item event*"
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
