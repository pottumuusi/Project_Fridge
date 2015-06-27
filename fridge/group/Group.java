package fridge.group;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
  
  public boolean hasPath(Path[] pathList){
    int k = 0;
    
    if (null != itemPaths){
      for (int i = 0; i < pathList.length; i++){
        for (k = 0; k < itemPaths.length; k++){
          if (null != pathList[i] && itemPaths[k].equals(pathList[i])){
            return true;
          }
        }
      }
    }
    
    return false;
  }
  
  public void setUpdatePathIndexes(Path[] newPaths){
    int newIndexCount = 0;
    int[] tempIndexes = new int[itemPaths.length]; 
    
    System.err.println("updating indexes of " + name);
    
    for (int i = 0; i < itemPaths.length; i++){
      for (int k = 0; k < newPaths.length; k++){
        if (newPaths[k].toString().equals(itemPaths[i].toString())){
          tempIndexes[newIndexCount] = i;
          newIndexCount++;
          break;
        }
      }
    }
    
    updatePathIndexes = new int[newIndexCount];
    for (int i = 0; i < updatePathIndexes.length; i++){
      updatePathIndexes[i] = tempIndexes[i];
    }
  }
  
  public void setUpdatePathIndexes(int[] newIndexes){
    System.err.println("setting updatePathIndexes:");
    updatePathIndexes = newIndexes;
  }
  
  public void updatePaths(Path[] newPaths){
    for (int i = 0; i < updatePathIndexes.length; i++){
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
  }
  
  public void setItemPaths(Path[] newItemPaths){
    itemPaths = newItemPaths;
  }
  
  public void deleteItems(int[] deleteIndexes, fridge.window_content.WindowCollection winCollection){
    int i;
    int k;
    int assignIndex = 0;
    int deleteIndex = 0;
    boolean keep = true;
    Path[] itemsToKeep = new Path[items.length - deleteIndexes.length];
    Path[] pathsToKeep = new Path[itemPaths.length - deleteIndexes.length];
    
    Path[] pathsToDelete = new Path[deleteIndexes.length];
    
    for (i = 0; i < items.length; i++){
      for (k = 0; k < deleteIndexes.length; k++){
        if (i == deleteIndexes[k]){
          keep = false;
        }
      }
      
      if (true == keep){
        itemsToKeep[assignIndex] = items[i];
        assignIndex++;
      }
      keep = true;
    }
    
    assignIndex = 0;
    
    for (i = 0; i < itemPaths.length; i++){
      for (k = 0; k < deleteIndexes.length; k++){
        if (i == deleteIndexes[k]){
          keep = false;
        }
      }
      
      if (true == keep){
        pathsToKeep[assignIndex] = itemPaths[i];
        assignIndex++;
      }
      else{
        pathsToDelete[deleteIndex] = itemPaths[i];
        deleteIndex++;
      }
      keep = true;
    }
    
    deleteFiles(pathsToDelete, winCollection);
    
    items = itemsToKeep;
    itemPaths = pathsToKeep;
    if (0 == items.length){
      System.err.println("setting null");
      items = null;
    }
    if (0 == itemPaths.length){
      itemPaths = null;
    }
  }
  
  private void deleteFiles(Path[] deletePaths, fridge.window_content.WindowCollection winCollection){
    for (int i = 0; i < deletePaths.length; i++){
      try{
        Files.delete(deletePaths[i]);
      } catch (NoSuchFileException x) {
          winCollection.errorWindow("MainWin2", deletePaths[i].toString() + ": no such" + " file or directory");
      } catch (DirectoryNotEmptyException x) {
          winCollection.errorWindow("MainWin2", deletePaths[i].toString() + " is not empty");
      } catch (IOException ioe) {
          // File permission problems are caught here.
          System.err.println(ioe.getMessage());
      }
    }
    
  }
  
  public void addItems(Path[] newItems, Path[] newPaths){
    int i;
    int k;
    Path[] temp = items;
    Path[] tempPaths = itemPaths;
    
    if (null != items && newPaths.length == newItems.length){
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
  }
  
  public void openFile(int itemIndex){
    if (!(Files.isDirectory(itemPaths[itemIndex]))){
      try{
        java.awt.Desktop.getDesktop().open(new File(itemPaths[itemIndex].toString()));
      }
      catch (IOException ex){
        System.out.println("IOException: " + ex.getMessage());
      }
    }
  }
}
