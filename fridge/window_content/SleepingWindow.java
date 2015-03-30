package fridge.window_content;

public class SleepingWindow{
  private String name;
  private int myWindowIndex;
  
  public SleepingWindow(String name_par, int index){
    name = name_par;
    myWindowIndex = index;
  }
  
  public String getName(){
    return name;
  }
  
  public int getMyWindowIndex(){
    return myWindowIndex;
  }
}
