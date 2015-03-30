package fridge.window_content;

public class NamedWindow{
  private String name;
  private int myWindowIndex;
  private boolean isHidden;
  
  public NamedWindow(String name_par, int index){
    name = name_par;
    myWindowIndex = index;
    
  }
  
  public String getName(){
    return name;
  }
  
  public int getMyWindowIndex(){
    return myWindowIndex;
  }
  
  public boolean getIsHidden(){
    return isHidden;
  }
  
  public void setIsHidden(boolean set_par){
    isHidden = set_par;
  }
}
