package fridge.data;

public class FolderCollectionItem{
  private String collectionCreator;
  private String[] names;
  private Path[] paths;
  
  public FolderCollectionItem(){
    collectionCreator = null;
    names = null;
    paths = null;
  }
  
  public FolderCollectionItem(String[] par_names, Path[] par_paths){
    names = par_names;
    paths = par_paths;
  }
  
  public String[] getNames(){
    return names;
  }
  
  public Path[] getPaths(){
    return paths;
  }
  
  public String getCreator(){
    return collectionCreator;
  }
  
  public void setCreator(String par_creator){
    collectionCreator = par_creator;
  }
}