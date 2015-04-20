package fridge.data;

import java.nio.file.Path;

public class FolderCollectionItem{
  private String collectionCreator;
  private String collection_name;
  private String[] aliases;
  private String[] paths;
  
  public FolderCollectionItem(String par_name,
                              String par_creator,
                              String[] par_aliases,
                              String[] par_paths){
    collectionCreator = par_creator;
    collection_name = par_name;
    aliases = par_aliases;
    paths = par_paths;
  }
  
  public String[] getAliases(){
    return aliases;
  }
  
  public String[] getPaths(){
    return paths;
  }
  
  public String getCreator(){
    return collectionCreator;
  }
  
  public String getName(){
    return collection_name;
  }
}
