package fridge.action_handling;

public abstract class MyListener{
  //private fridge.windows.CallableByListener listened;
  private String listenerType;
  
  public MyListener(String initLType){
    listenerType = initLType;
  }
  
  public String getType(){
    return listenerType;
  }
  
  public void setListenedCall(fridge.windows.CallableByListener CBL_ptr){
    setListened(CBL_ptr);
  }
  
  protected abstract void setListened(fridge.windows.CallableByListener CBL_ptr);
  
  /*protected void setListened(fridge.windows.CallableByListener CBL_ptr){
    listened = CBL_ptr;
  }*/
}