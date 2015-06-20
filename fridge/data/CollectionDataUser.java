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
  private final int NAME_POSITION = 0;
  private final int ALIASES_POSITION = 4;
  private final int FOLDERS_POSITION = 5;
  private final int ALIAS_LEN_POSITION = 2;
  private final int FOLDERS_LEN_POSITION = 3;
  private final int FOLDER_CREATOR_POSITION = 1;
  private final int COLLECTION_ITEM_FIELD_AMOUNT = 6;
  
  public CollectionDataUser(){
    path = Paths.get("fridge", "data", "collectionData.dat");
    lengthsFilePath = Paths.get("fridge", "data", "collectionLengths.dat");
    charset = Charset.forName("UTF-8");
  }
  
  public void saveFolderCollection(String name,
                             String creator,
                             String[] folderAliases,
                             String[] folders,
                             CollectionSave caller){
    
    System.out.println("[DEBUG] saving collection");
    
    if (preconditionsOK(folderAliases, folders, name)){
      /* tallenna tiedostojen nykyiset sisallot */
      try{
        storeCollectionToFile(folderAliases, folders, name, creator);
        incrementLengthFileContent();
      } catch(IOException ioe){
        /* palauta ennen try lausetta tallennetut sisallot */
      }
    }
  }
  
  private void storeCollectionToFile(String[] folderAliases, String[] folders,
                                     String name, String creator) throws IOException{
    
    try (BufferedWriter writer = Files.newBufferedWriter(path, charset,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.TRUNCATE_EXISTING,
                             StandardOpenOption.WRITE)){
        String lenVar = null;
        int i;
        
        /* First 2 rows contain length of folderAliases and folders */
        /* write data to file so that each row contains all values of one variable */
        System.err.println("writing names");
        name = name + "\n";
        writer.write(name, 0, name.length());
        creator = creator + "\n";
        writer.write(creator, 0, creator.length());
        
        System.err.println("writing lengths...");
        lenVar = Integer.toString(folderAliases.length) + '\n';
        writer.write(lenVar, 0, lenVar.length());
        lenVar = Integer.toString(folders.length) + '\n';
        writer.write(lenVar, 0, lenVar.length());
        
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
  }
  
  private boolean preconditionsOK(String[] folderAliases, String[] folders, String name){
    if (folderAliases.length != folders.length){
      System.err.print("[ERROR]  aborted writing to file: ");
      System.err.print("folderAliases and folders have different length\n");
      /* errorMessage("internal error: could not store folder collection */
      return false;
    }
    else if (folderAliases.length < 1 || folders.length < 1){
      System.err.println("[ERROR] aborted writing to file: ");
      System.err.print("length of folderAliases and folders is below 1");
      /* errorMessage("internal error: could not store folder collection */
      return false;
    }
    else if (name.length() < 1){
      System.out.println("[ERROR] aborted writing to file: no name given");
      //errorMessage("could not save folder collection. no name given")
      return false;
    }
    
    return true;
  }
  
  private void incrementLengthFileContent() throws IOException{
    createLengthFile(); // does not alter an existing length file
    incrementStoredCollectionAmount();
  }
  
  private void createLengthFile() throws IOException{
    try (BufferedWriter writer = Files.newBufferedWriter(lengthsFilePath, charset,
                                                         StandardOpenOption.CREATE)){
    }
  }
  
  private void incrementStoredCollectionAmount() throws IOException{
    int collectionAmount = -1;
    
    collectionAmount = readStoredCollectionAmount();
    writeNewStoredCollectionAmount(collectionAmount + 1);
  }
  
  private int readStoredCollectionAmount() throws IOException{
    int collectionAmount = 0;
    String line = null;
    int i;
    
    try (BufferedReader reader = Files.newBufferedReader(lengthsFilePath, charset)){
      line = reader.readLine();
      System.err.println("storing collection amount. read from lengthsFile: " + line);
      if (null != line){
        collectionAmount = Integer.parseInt(line);
      }
      
      testLengthFileCorrectness(reader);
    } catch (NumberFormatException nfe){
      System.err.println("IOException: " + nfe);
    }
    
    System.err.println("readStoredCollectionAmount returning: " + collectionAmount);
    return collectionAmount;
  }
  
  private void testLengthFileCorrectness(BufferedReader reader) throws IOException{
    if (null != reader.readLine()){
      throw new IOException(lengthsFilePath.toString() + " should contain only 1 row");
    }
  }
  
  private void writeNewStoredCollectionAmount(int newCollectionAmount) throws IOException{
    String amountStr = null;
    
    try (BufferedWriter writer = Files.newBufferedWriter(lengthsFilePath, charset,
                                                         StandardOpenOption.CREATE,
                                                         StandardOpenOption.TRUNCATE_EXISTING,
                                                         StandardOpenOption.WRITE)){
      System.err.println("incrementing collection amount. collectionAmount == " + newCollectionAmount);
      amountStr = Integer.toString(newCollectionAmount) + '\n';
      writer.write(amountStr, 0, amountStr.length());
    }
  }
  
  public String[] getCollectionNames(){
    int nameAmount;
    String line = null;
    String[] names = null;
    
    try{
      createLengthFile();
      nameAmount = readStoredCollectionAmount();
      
      if (nameAmount < 1){
        System.err.println("CollectionDataUser.getCollectionNames(): no collections yet");
      }
      else{
        names = readStoredCollectionNames(nameAmount);
        return names; 
      }
    } catch(IOException ioe){
      System.err.format("IOException in CollectionDataUser.getCollectionNames(): %s%n", ioe);
    }
    
    return null;
  }
  
  private String[] readStoredCollectionNames(int nameAmount) throws IOException{
    BufferedReader reader = Files.newBufferedReader(path, charset);
    int i = 0;
    int storeIndex = 0;
    String line;
    String[] names = new String[nameAmount];
    
    System.err.println("readStoredCollectionNames loop:");
    do{
      line = reader.readLine();
      System.err.println("\t" + line);
      
      if (NAME_POSITION == i){
        if (null != line){
          names[storeIndex] = line;
          storeIndex++;
        }
      }
      else if(null == line){
        throw new IOException("null row inside collection names datastructure");
      }
      i = (i + 1) % COLLECTION_ITEM_FIELD_AMOUNT;
    }while(null != line);
    
    return names;
  }
  
  public FolderCollectionItem loadFolderCollection(String collectionName){
    int[] lineItemAmount = null;
    
    String name = null;
    String creator = null;
    String[] folderAliases = null;
    String[] folderPaths = null;
    FolderCollectionItem folderCollection = null;
    
    try{
      int i;
      
      if (collectionExists(collectionName)){
        System.err.println("CollectionDataUser loading collection...");
        lineItemAmount = getFolderLineLengths(collectionName);
        System.err.println("lineItemAmount[0] == " + lineItemAmount[0]);
        System.err.println("lineItemAmount[1] == " + lineItemAmount[1]);
        folderAliases = new String[lineItemAmount[0]];
        folderPaths = new String[lineItemAmount[1]];
        System.err.println("length of folderAliases: " + folderAliases.length);
        System.err.println("length of folderPaths: " + folderPaths.length);
        
        name = collectionName;
        creator = getFolderCreator(collectionName);
        folderAliases = loadAliases(collectionName, folderAliases);
        folderPaths = loadFolderPaths(collectionName, folderPaths);
        
        System.err.println("collectionCreator: " + creator);
        
        System.err.println("folderAliases:");
        for (i = 0; i < folderAliases.length; i++){
          System.err.println("\t" + folderAliases[i]);
        }
        
        System.err.println("folderPaths");
        for (i = 0; i < folderPaths.length; i++){
          System.err.println("\t" + folderPaths[i]);
        }
      }
      else{
        //error
      }
      
      
      //folderCollection = createFolderCollectionItem(collectionName);
    } catch(IOException ioe){
      System.err.println("loadFolderCollection IOException: " + ioe.getMessage());
    }
    
    /*
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
    */
    /* read data from collectionData.dat.
     * Store this data to FolderCollectionItem
     * return the FolderCollectionItem*/
    
    //FolderCollectionItem folderData = new FolderCollectionItem
    // do set methods to folderCollection or waste memory by storing
    // data temporarily to variables before constructor call
    
    folderCollection = new FolderCollectionItem(name, creator, folderAliases, folderPaths);
    
    return folderCollection;
  }
  
  public boolean collectionExists(String collectionName){
    String[] collectionNames = null;
    int i;
    
    collectionNames = getCollectionNames();
    
    if (null == collectionNames){
      return false;
    }
    
    for (i = 0; i < collectionNames.length; i++){
      if (collectionName.equals(collectionNames[i])){
        return true;
      }
    }
    
    return false;
  }
  
  private int[] getFolderLineLengths(String collectionName) throws IOException{
    int[] lineLengths = new int[2];
    String readStr = null;
    
    readStr = getCollectionData(collectionName, ALIAS_LEN_POSITION);
    System.err.println("alias lengths row: " + readStr);
    readStr = getCollectionData(collectionName, FOLDERS_LEN_POSITION);
    System.err.println("collection folder lengths row" + readStr);
    
    lineLengths[0] = Integer.parseInt(getCollectionData(collectionName, ALIAS_LEN_POSITION));
    lineLengths[1] = Integer.parseInt(getCollectionData(collectionName, FOLDERS_LEN_POSITION));
    
    return lineLengths;
  }
  
  private String getFolderCreator(String collectionName) throws IOException{
    String creator = null;
    
    creator = getCollectionData(collectionName, FOLDER_CREATOR_POSITION);
    
    return creator;
  }
  
  private String getFolderAliases(String collectionName) throws IOException{
    String aliases = null;
    
    aliases = getCollectionData(collectionName, ALIASES_POSITION);
    
    return aliases;
  }
  
  private String getFolderPaths(String collectionName) throws IOException{
    return getCollectionData(collectionName, FOLDERS_POSITION);
  }
  
  private String[] loadAliases(String collectionName, String[] loadDestination) throws IOException{
    String unprocessedAliases = null;
    String[] aliases = null;
    
    unprocessedAliases = getFolderAliases(collectionName);
    loadDestination = unprocessedAliases.split(";", loadDestination.length);
    
    /* remove last char of last string in loadDestination which is ';' */
    loadDestination[loadDestination.length - 1] = loadDestination[loadDestination.length - 1].substring(0, loadDestination[loadDestination.length - 1].length() - 1);
    
    return loadDestination;
  }
  
  private String[] loadFolderPaths(String collectionName, String[] loadDestination) throws IOException{
    String unprocessedFolders = null;
    String[] folders = null;
    
    unprocessedFolders = getFolderPaths(collectionName);
    loadDestination = unprocessedFolders.split(";", loadDestination.length);
    
    /* remove last char of last string in loadDestination which is ';' */
    loadDestination[loadDestination.length - 1] = loadDestination[loadDestination.length - 1].substring(0, loadDestination[loadDestination.length - 1].length() - 1);
    
    return loadDestination;
  }
  
  private String getCollectionData(String collectionName, int offset) throws IOException{
    int i;
    String collectionData = null;
    BufferedReader reader = Files.newBufferedReader(path, charset);
    
    if ((offset + 1) > COLLECTION_ITEM_FIELD_AMOUNT || offset < 0){
      throw new IOException("getAliasesLineLength: invalid data field offset");
    }
    
    if (1 == rewindToCollection(collectionName, reader)){
      System.err.println("[error] could not find collection named: " + collectionName);
      return null;
    }
    
    collectionData = dataFromOffset(offset, reader);
    
    return collectionData;
  }
  
  private int rewindToCollection(String collectionName, BufferedReader reader) throws IOException{
    /* return 1 on error and 0 on success */
    String line = null;
    
    do {
      reader.mark(4096); // 4096 bytes can be read before reset
      line = reader.readLine();
      System.err.println("line == " + line + ", line.length == " + line.length());
      
      if (line.equals(collectionName)){
        reader.reset();
        return 0;
      }
    } while(line != null);
    
    return 1;
  }
  
  private String dataFromOffset(int offset, BufferedReader reader) throws IOException{
    String data = null; 
    
    while (0 <= offset){
      data = reader.readLine();
      offset--;
    }
    
    return data;
  }
  
  public void deleteFolderCollection(String collectionName){
    
  }
}