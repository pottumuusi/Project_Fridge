package fridge.data;

public class FolderCollectionItem{
  private String collectionCreator;
  private String collection_name;
  private String[] aliases;
  private Path[] paths;
  
  public FolderCollectionItem(String par_name,
                              String par_creator,
                              String[] par_aliases,
                              Path[] par_paths){
    collectionCreator = par_creator;
    collection_name = par_name;
    aliases = par_aliases;
    paths = par_paths;
  }
  
  public String[] getAliases(){
    return aliases;
  }
  
  public Path[] getPaths(){
    return paths;
  }
  
  public String getCreator(){
    return collectionCreator;
  }
  
  public String getName(){
    return collection_aliases;
  }
}