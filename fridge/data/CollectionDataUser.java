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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CollectionDataUser{
  private Path folderPath;
  private Path folderLengthsFilePath;
  private Charset charset;
  private JFrame frame;
  private final int NAME_POSITION = 0;
  private final int ALIASES_POSITION = 4;
  private final int FOLDERS_POSITION = 5;
  private final int ALIAS_LEN_POSITION = 2;
  private final int FOLDERS_LEN_POSITION = 3;
  private final int FOLDER_CREATOR_POSITION = 1;
  private final int COLLECTION_ITEM_FIELD_AMOUNT = 6;
  
  public CollectionDataUser(){
    folderPath = Paths.get("fridge", "data", "collectionData.dat");
    folderLengthsFilePath = Paths.get("fridge", "data", "collectionLengths.dat");
    charset = Charset.forName("UTF-8");
    frame = null; // frame is used to make error windows
  }
  
  public void saveFolderCollection(String name,
                                   String creator,
                                   String[] folderAliases,
                                   String[] folders,
                                   CollectionSave caller,
                                   JFrame par_frame){
    frame = par_frame;
    
    saveFolderCollection(name,
                         creator,
                         folderAliases,
                         folders,
                         caller);
  }
  
  private void saveFolderCollection(String name,
                             String creator,
                             String[] folderAliases,
                             String[] folders,
                             CollectionSave caller){
    
    System.err.println("saving collection...");
    
    if (preconditionsOK(folderAliases, folders, name)){
      try{
        storeCollectionToFile(folderAliases, folders, name, creator);
        incrementLengthFileContent();
      } catch(IOException ioe){
        System.err.println(ioe.getMessage());
      }
    }
  }
  
  private void errorWindow(String errorMsg){
    if (null != frame){
      JOptionPane.showMessageDialog(frame,
          errorMsg,
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
  
  private void clearCollectionFile(Path filePath) throws IOException{
    BufferedWriter writer = Files.newBufferedWriter(filePath, charset,
                                                    StandardOpenOption.CREATE,
                                                    StandardOpenOption.TRUNCATE_EXISTING,
                                                    StandardOpenOption.WRITE);
    
    writer.write("", 0, 0);
  }
  
  private void clearCollectionFile(String filePathStr) throws IOException{
    Path filePath = Paths.get(filePathStr);
    
    BufferedWriter writer = Files.newBufferedWriter(filePath, charset,
                                                    StandardOpenOption.CREATE,
                                                    StandardOpenOption.TRUNCATE_EXISTING,
                                                    StandardOpenOption.WRITE);
    
    writer.write("", 0, 0);
  }
  
  private void storeFolderCollectionToFile(FolderCollectionItem collection) throws IOException{
    storeCollectionToFile(collection.getAliases(),
                          collection.getPaths(),
                          collection.getName(),
                          collection.getCreator());
  }
  
  private void storeCollectionToFile(String[] folderAliases, String[] folders,
                                     String name, String creator) throws IOException{
    
    try (BufferedWriter writer = Files.newBufferedWriter(folderPath, charset,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.APPEND)){
      String lenVar = null;
      int i;
      
      /* First 2 rows contain length of folderAliases and folders */
      /* write data to file so that each row contains all values of one variable */
      System.err.println("writing names...");
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
      errorWindow("internal error: could not store folder collection");
      return false;
    }
    else if (folderAliases.length < 1 || folders.length < 1){
      System.err.println("[ERROR] aborted writing to file: ");
      System.err.print("length of folderAliases and folders is below 1");
      errorWindow("internal error: could not store folder collection");
      return false;
    }
    else if (name.length() < 1){
      System.out.println("[ERROR] aborted writing to file: no name given");
      errorWindow("Could not save folder collection. no name given.");
      return false;
    }
    
    return true;
  }
  
  private void incrementLengthFileContent() throws IOException{
    createFile(folderLengthsFilePath); // does not alter an existing length file
    incrementStoredCollectionAmount(folderLengthsFilePath);
  }
  
  private void createFile(Path filePath) throws IOException{
    try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset,
                                                         StandardOpenOption.CREATE)){
    }
  }
  
  private void incrementStoredCollectionAmount(Path lengthsPath) throws IOException{
    int collectionAmount = -1;
    
    collectionAmount = readStoredCollectionAmount(lengthsPath);
    writeNewStoredCollectionAmount(collectionAmount + 1, lengthsPath);
  }
  
  private void decrementStoredCollectionAmount(Path lengthsPath) throws IOException{
    int collectionAmount = -1;
    
    createFile(lengthsPath);
    collectionAmount = readStoredCollectionAmount(lengthsPath);
    if (collectionAmount > 0){
      writeNewStoredCollectionAmount(collectionAmount - 1, lengthsPath);
    }
  }
  
  private int readStoredCollectionAmount(Path lengthsPath) throws IOException{
    int collectionAmount = 0;
    String line = null;
    int i;
    
    try (BufferedReader reader = Files.newBufferedReader(lengthsPath, charset)){
      line = reader.readLine();
      if (null != line){
        collectionAmount = Integer.parseInt(line);
      }
      
      testLengthFileCorrectness(reader);
    } catch (NumberFormatException nfe){
      System.err.println("IOException: " + nfe.getMessage());
    }
    
    return collectionAmount;
  }
  
  private void testLengthFileCorrectness(BufferedReader reader) throws IOException{
    if (null != reader.readLine()){
      throw new IOException(folderLengthsFilePath.toString() + " should contain only 1 row");
    }
  }
  
  private void writeNewStoredCollectionAmount(int newCollectionAmount, Path filePath) throws IOException{
    String amountStr = null;
    
    try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset,
                                                         StandardOpenOption.CREATE,
                                                         StandardOpenOption.TRUNCATE_EXISTING,
                                                         StandardOpenOption.WRITE)){
      amountStr = Integer.toString(newCollectionAmount) + '\n';
      writer.write(amountStr, 0, amountStr.length());
    }
  }
  
  public String[] getCollectionNames(JFrame par_frame){
    frame = par_frame;
    return getCollectionNames();
  }
  
  private String[] getCollectionNames(){
    int nameAmount;
    String line = null;
    String[] names = null;
    
    try{
      createFile(folderLengthsFilePath);
      nameAmount = readStoredCollectionAmount(folderLengthsFilePath);
      
      if (nameAmount < 1){
        System.err.println("CollectionDataUser.getCollectionNames(): no collections detected");
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
    BufferedReader reader = Files.newBufferedReader(folderPath, charset);
    int i = 0;
    int storeIndex = 0;
    String line;
    String[] names = new String[nameAmount];
    
    do{
      line = reader.readLine();
      
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
  
  public FolderCollectionItem loadFolderCollection(String collectionName, JFrame par_frame){
    frame = par_frame;
    return loadFolderCollection(collectionName);
  }
  
  private FolderCollectionItem loadFolderCollection(String collectionName){
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
        folderAliases = new String[lineItemAmount[0]];
        folderPaths = new String[lineItemAmount[1]];
        
        name = collectionName;
        creator = getFolderCreator(collectionName);
        folderAliases = loadAliases(collectionName, folderAliases);
        folderPaths = loadFolderPaths(collectionName, folderPaths);
      }
      else{
        //error
      }
    } catch(IOException ioe){
      errorWindow("internal error: could not load folder collection");
      System.err.println("loadFolderCollection IOException: " + ioe.getMessage());
    }
    
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
    readStr = getCollectionData(collectionName, FOLDERS_LEN_POSITION);
    
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
    BufferedReader reader = Files.newBufferedReader(folderPath, charset);
    
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
  
  public void deleteFolderCollection(String collectionName, JFrame par_frame){
    frame = par_frame;
    deleteFolderCollection(collectionName);
  }
  
  private void deleteFolderCollection(String collectionName){
    String[] collectionNames = null;
    FolderCollectionItem[] collectionsToSave = null;
    
    /* save current file content to an array excluding collection to delete,
     * clear file and save content 
     */
    
    try{
      System.err.println("deleting " + collectionName);
      collectionsToSave = getCollectionsExcept(collectionName);
      clearCollectionFile(folderPath);
      for (int i = 0; i < collectionsToSave.length; i++){
        storeFolderCollectionToFile(collectionsToSave[i]);
      }
      decrementStoredCollectionAmount(folderLengthsFilePath);
    } catch(IOException ioe){
      errorWindow("Could not delete " + collectionName);
      System.err.println("error while deleting " + collectionName + ", " + ioe.getMessage());
    }
  }
  
  private FolderCollectionItem[] getCollectionsExcept(String collectionToExclude){
    FolderCollectionItem[] collections = null;
    String[] allNames = null;
    int saveIndex = 0;
    
    allNames = getCollectionNames();
    collections = new FolderCollectionItem[allNames.length - 1];
    
    for (int i = 0; i < allNames.length; i++){
      if (!(allNames[i].equals(collectionToExclude))){
        collections[saveIndex] = loadFolderCollection(allNames[i]);
        saveIndex++;
      }
    }
    
    return collections;
  }
}