package fridge.windows;

import javax.swing.JFrame;

public abstract class FileWindow extends CallableByListener{
  public FileWindow(JFrame frame,
                    fridge.action_handling.ClassListSelectionListener[] CLSL_list,
                    fridge.action_handling.ClassActionListener[] CAL_list,
                    int par_myWindowIndex,
                    fridge.window_content.Menu par_menu,
                    fridge.window_content.WindowCollection winColl){
    super(frame, CLSL_list, CAL_list, par_myWindowIndex, par_menu, winColl);
  }
  
  public abstract void newFolder();
  public abstract void openFile();
  public abstract void exclude();
  public abstract void delete();
  public abstract void paste();
  public abstract void copy();
  public abstract void cut();
}