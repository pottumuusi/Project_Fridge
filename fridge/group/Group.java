package fridge.group;

import java.nio.file.Path;

public class Group{
  private String name;
  private Path[] items;
  private Path[] itemPaths;
  private int itemCount;
  private int[] updatePathIndexes;
  
  public Group(String par_name){
    name = par_name;
    itemCount = 0;
    items = null;
    itemPaths = null;
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
  
  public Path[] getItemPaths(){
    return itemPaths;
  }
  
  public void setUpdatePathIndexes(int[] newIndexes){
    System.err.println("setting updatePathIndexes:");
    for (int i = 0; i < newIndexes.length; i++){
      System.err.println("\t" + newIndexes[i]);
    }
    updatePathIndexes = newIndexes;
  }
  
  public void updatePaths(Path[] newPaths){
    for (int i = 0; i < updatePathIndexes.length; i++){
      System.err.println("updating " + itemPaths[updatePathIndexes[i]] + " with " + newPaths[i]);
      itemPaths[updatePathIndexes[i]] = newPaths[i];
    }
    
    updatePathIndexes = null;
  }
  
  public void setItems(Path[] newItems){
    int i;
    
    items = new Path[newItems.length];
    for (i = 0; i < items.length; i++){
      items[i] = newItems[i];
    }
    itemCount = items.length;
    
    System.out.println("[DEBUG] setItems complete");
  }
  
  public void setItemPaths(Path[] newItemPaths){
    itemPaths = newItemPaths;
  }
  
  public void addItems(Path[] newItems, Path[] newPaths){
    int i;
    int k;
    Path[] temp = items;
    Path[] tempPaths = itemPaths;
    
    if (null != items && newPaths.length == newItems.length){
      System.out.println("[DEBUG] items.length: " + items.length);
      System.out.println("[DEBUG] newItems.length: " + newItems.length);
      items = new Path[items.length + newItems.length];
      itemPaths = new Path[itemPaths.length + newPaths.length];
      
      for (i = 0; i < temp.length; i++){
        items[i] = temp[i];
        itemPaths[i] = tempPaths[i];
      }
      for (k = 0; k < newItems.length; k++){
        items[i] = newItems[k];
        itemPaths[i] = newPaths[k];
        i++;
      }
    }
    else if (newItems.length == newPaths.length){
      items = new Path[newItems.length];
      itemPaths = new Path[newPaths.length];
      
      for (k = 0; k < newItems.length; k++){
        items[k] = newItems[k];
        itemPaths[k] = newPaths[k]; 
      }
    }
    
    /*System.out.println("[DEBUG] added items:");
    for (i = 0; i < items.length; i++){
      System.out.println("    " + items[i].toString());
    }*/
  }
  
  
  
  public void addItem(Path newItem){
    
    
    
  }
  
  public void removeItem(){
    
  }
  
}
