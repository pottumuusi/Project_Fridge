package fridge.data;

import java.nio.file.StandardOpenOption;

import fridge.data.FolderCollectionItem;
import fridge.windows.CollectionSave;

import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class CollectionDataUser{
  private Path path;
  private Path lengthsFilePath;
  private Charset charset;
  private int collectionItemFieldAmount;
  
  public CollectionDataUser(){
    path = Paths.get("fridge", "data", "collectionData.dat");
    lengthsFilePath = Paths.get("fridge", "data", "collectionLengths.dat");
    charset = Charset.forName("UTF-8");
    collectionItemFieldAmount = 4;
  }
  
  public void saveFolderCollection(String name,
                             String creator,
                             String[] folderAliases,
                             String[] folders,
                             CollectionSave caller){
    int i;
    int collectionAmount = 0;
    String lenVar = null;
    String line = null;
    System.out.println("[DEBUG] saving collection");
    
    String testData = "testing";
    try (BufferedWriter writer = Files.newBufferedWriter(path, charset,
                                                         StandardOpenOption.CREATE,
                                                         StandardOpenOption.TRUNCATE_EXISTING,
                                                         StandardOpenOption.WRITE)){
      if (folderAliases.length != folders.length){
        System.err.print("[ERROR]  aborted writing to file: ");
        System.err.print("folderAliases and folders have different length\n");
        /* errorMessage("internal error: could not store folder collection */
      }
      else if (folderAliases.length < 1 || folders.length < 1){
        System.err.println("[ERROR] aborted writing to file: ");
        System.err.print("length of folderAliases and folders is below 1");
        /* errorMessage("internal error: could not store folder collection */
      }
      else if (name.length() < 1){
        System.out.println("[ERROR] aborted writing to file: no name given");
        //errorMessage("could not save folder collection. no name given")
      }
      else{
        /* First 2 rows contain length of folderAliases and folders */
        /* write data to file so that each row contains all values of one variable */
        System.err.println("writing lengths...");
        lenVar = Integer.toString(folderAliases.length) + '\n';
        writer.write(lenVar, 0, lenVar.length());
        lenVar = Integer.toString(folders.length) + '\n';
        writer.write(lenVar, 0, lenVar.length());
        
        name = name + "\n";
        writer.write(name, 0, name.length());
        creator = creator + "\n";
        writer.write(creator, 0, creator.length());
        
        System.err.println("writing folders...");
        
        for (i = 0; i < folderAliases.length; i++){
          writer.write(folderAliases[i] + ";", 0, folderAliases[i].length() + 1);
          System.err.println("wrote: " + folderAliases[i]);
        }
        
        writer.write("\n", 0, 1);
        
        for (i = 0; i < folders.length; i++){
          writer.write(folders[i] + ";", 0, folders[i].length() + 1);
        }
        
        writer.write("\n", 0, 1);
      }
    } catch (IOException e){
      System.err.format("IOException: %s%n", e);
    }
    
    try (BufferedWriter writer = Files.newBufferedWriter(lengthsFilePath, charset,
                                                         StandardOpenOption.CREATE)){
    } catch(IOException e){
    System.err.format("IOException: %s%n", e);
    }
    
    /* Increment amount of stored collections. Amount is stored to a file */
    try (BufferedReader reader = Files.newBufferedReader(lengthsFilePath, charset)){
      for (i = 0; null != line; i++){
        if (i > 0){
          throw new IOException(lengthsFilePath.toString() + " should not contain only 1 row");
        }
        
        line = reader.readLine();
        collectionAmount = Integer.parseInt(line);
        collectionAmount++;
        lenVar = Integer.toString(collectionAmount) + '\n';
      }
    } catch (IOException e){
      System.err.format("IOException: %s%n", e);
    } catch (NumberFormatException nfe){
      System.err.println("IOException: " + nfe);
    }
    
    try (BufferedWriter writer = Files.newBufferedWriter(lengthsFilePath, charset,
                                                         StandardOpenOption.CREATE,
                                                         StandardOpenOption.TRUNCATE_EXISTING,
                                                         StandardOpenOption.WRITE)){
      writer.write(lenVar, 0, lenVar.length());
    } catch(IOException e){
      System.err.format("IOException: %s%n", e);
    }
  }
  
  public String[] getCollectionNames(){
    String line = null;
    String[] names = null;
    int i = 0;
    int storeIndex = 0;
    
    try (BufferedWriter writer = Files.newBufferedWriter(lengthsFilePath, charset,
                                                         StandardOpenOption.CREATE)){
    } catch(IOException e){
    System.err.format("IOException: %s%n", e);
    }
    
    try (BufferedReader reader = Files.newBufferedReader(lengthsFilePath, charset)){
      do {
        if (i > 1){
          throw new IOException(lengthsFilePath.toString() + " should not contain only 1 row");
        }
        
        line = reader.readLine();
        System.err.println("line == " + line);
        names = new String[Integer.parseInt(line)];
        i++;
      }while (null != line);
    } catch (IOException e){
      System.err.format("IOException: %s%n", e);
    } catch (NumberFormatException nfe){
      System.err.println("IOException: " + nfe);
    }
    
    if (null == names){
      System.err.println("CollectionDataUser.getCollectionNames(): null == names");
      return null;
    }
    
    try (BufferedReader reader = Files.newBufferedReader(path, charset)){
      i = 0;
      
      System.err.println("bufferedReader loop:");
      do{
        line = reader.readLine();
        System.err.println("\t" + line);
        if (2 == i){
          if (null == line){
            throw new IOException("luettiin null rivi odottamattomasti");
          }
          names[storeIndex] = line;
        }
        i = (i + 1) % 6; // jokaisen nimen jalkeen on 3 rivia muuta tietoa
      }while(null != line);
      
      return names;
    }catch (IOException e){
      System.err.format("IOException in CollectionDataUser.getCollectionNames(): %s%n", e);
    }
    
    return null;
  }
  
  public FolderCollectionItem loadFolderCollection(){
    int folderAliases_len = 0;
    int folders_len = 0;
    
    String name = null;
    String creator = null;
    String[] folderAliases = null;
    String[] folderPaths = null;
    FolderCollectionItem folderCollection = null;
    
    try (BufferedReader reader = Files.newBufferedReader(path, charset)){
      String line = null;
      int i = 0;
      
      line = reader.readLine();
      if (null == line){
        throw new IOException("luettiin null rivi odottamattomasti");
      }
      folderAliases = new String[Integer.parseInt(line)];
      
      line = reader.readLine();
      if (null == line){
        throw new IOException("luettiin null rivi odottamattomasti");
      }
      folderPaths = new String[Integer.parseInt(line)];
      
      for (i = 0; i < 3; i++){
        line = reader.readLine();
      }
      
      System.err.println("line to parse to folderAliases: " + line);
    }catch (IOException e){
      System.err.format("IOException in CollectionDataUser.getCollectionNames(): %s%n", e);
    } catch (NumberFormatException nfe){
      System.err.println("IOException: " + nfe);
    }
    
    /* read data from collectionData.dat.
     * Store this data to FolderCollectionItem
     * return the FolderCollectionItem*/
    
    //FolderCollectionItem folderData = new FolderCollectionItem
    // do set methods to folderCollection or waste memory by storing
    // data temporarily to variables before constructor call
    
    folderCollection = new FolderCollectionItem(name, creator, folderAliases, folderPaths);
    
    if (null != folderCollection){
      return folderCollection;
    }
    return null;
  }
  
  /*public void loadFolderCollection(){
  }*/
}