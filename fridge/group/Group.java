package fridge.group;

import java.nio.file.Path;

public class Group{
  private String name;
  private Path[] items;
  private int itemCount;
  
  public Group(String par_name){
    name = par_name;
    itemCount = 0;
  }
  
  public String getName(){
    return name;
  }
  
  public int getItemsLength(){
    return items.length;
  }
  
  public Path getItem(int itemIndex){
    return items[itemIndex];
  }
  
  public Path[] getItems(){
    return items;
  }
  
  public void addItems(Path[] newItems){
    int i;
    int k;
    Path[] temp;
    temp = items;
    
    items = new Path[items.length + newItems.length];
    
    for (i = 0; i < temp.length; i++){
      items[i] = temp[i];
    }
    for (k = 0; k < newItems.length; k++){
      items[i] = newItems[k];
      i++;
    }
  }
  
  public void setItems(Path[] newItems){
    int i;
    
    items = new Path[newItems.length];
    for (i = 0; i < items.length; i++){
      items[i] = newItems[i];
    }
    itemCount = items.length;
  }
  
  public void addItem(Path newItem){
    
    
    
  }
  
  public void removeItem(){
    
  }
  
}
