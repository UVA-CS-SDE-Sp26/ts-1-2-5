import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProgramControl {
    private FileHandler fileHandler;

    public ProgramControl(FileHandler fileHandler){
        this.fileHandler = fileHandler;
    }

    public String listFiles(){
        String[] files = fileHandler.getNames();
        String returnFiles = "";
        for(int i = 0; i < files.length; i++){
            returnFiles += String.format("%02d", i+1) + " " + files[i] + "\n";
        }
        return returnFiles;
    }

    public String getFileName(String fileNumber){
        int fileIndex = Integer.parseInt(fileNumber) - 1;
        String fileName = fileHandler.getNames()[fileIndex];
        return fileName;
    }

    public String getDecipheredFile(String fileNumber) throws FileNotFoundException {
        Path keyPath = Paths.get("ciphers/key.txt");
        Cipher cipher = new Cipher(keyPath);
        cipher.loadKey();
        String fileName = getFileName(fileNumber);
        String fileContents = fileHandler.readFile(fileName);
        return cipher.decode(fileContents);
    }

    public String getAlternateKeyDecipheredFile(String fileNumber, String decipheringKey) throws FileNotFoundException {
        Path keyPath = Paths.get("ciphers/" + decipheringKey);
        Cipher cipher = new Cipher(keyPath);
        cipher.loadKey();
        String fileName = getFileName(fileNumber);
        String fileContents = fileHandler.readFile(fileName);
        return cipher.decode(fileContents);
    }



}
