package fridge.data;

import java.nio.file.StandardOpenOption;
import fridge.data.FolderCollectionItem;
import fridge.windows.CollectionSave;
import java.nio.charset.Charset;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class CollectionDataUser{
  public CollectionDataUser(){
  }
  
  public void saveFolderCollection(String name,
                             String creator,
                             String[] folderAliases,
                             String[] folders,
                             CollectionSave caller){
    int i;
    System.out.println("[DEBUG] saving collection");
    
    String testData = "testing";
    Path path = Paths.get("fridge", "data", "collectionData.dat");
    Charset charset = Charset.forName("UTF-8");
    try (BufferedWriter writer = Files.newBufferedWriter(path, charset,
                                                         StandardOpenOption.CREATE,
                                                         StandardOpenOption.TRUNCATE_EXISTING,
                                                         StandardOpenOption.WRITE)){
      if (name.length() < 1){
        System.out.println("[DEBUG] could not save folder collection. no name given");
        //errorMessage("could not save folder collection. no name given")
      }
      else{
        /*write data to file so that each row contains all values of one variable*/
        name = name + "\n";
        writer.write(name, 0, name.length());
        
        creator = creator + "\n";
        writer.write(creator, 0, creator.length());
        
        for (i = 0; i < folderAliases.length; i++){
          writer.write((folderAliases[i] + ";"), 0, folderAliases[i].length() + 1);
        }
        
        writer.write("\n", 0, 1);
        
        for (i = 0; i < folders.length; i++){
          writer.write(folders[i] + ";", 0, folders[i].length() + 1);
        }
        
        writer.write("\n", 0, 1);
        
        /*make created collection show up in collections view of save window*/
        caller.updateViews();
      }
    }
    catch (IOException e){
      System.err.format("IOException: %s%n", e);
    }
  }
  
  public FolderCollectionItem loadFolderCollection(){
    /* read data from collectionData.dat.
     * Store this data to FolderCollectionItem
     * return the FolderCollectionItem*/
    
    //FolderCollectionItem folderData = new FolderCollectionItem
    // do set methods to folderCollection or waste memory by storing
    // data temporarily to variables before constructor call
  }
}