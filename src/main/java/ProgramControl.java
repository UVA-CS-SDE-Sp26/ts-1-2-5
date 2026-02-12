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
        int fileCounter = 0;
        for(int i = 0; i < files.length; i++){
            fileCounter++;
            returnFiles += (fileCounter + " " + files[i] + "/n");
        }
        return returnFiles;
    }

    public String getDecipheredFile(String fileName) throws FileNotFoundException {
        Path keyPath = Paths.get("ciphers/key.txt");
        Cipher cipher = new Cipher(keyPath);
        cipher.loadKey();
        String fileContents = fileHandler.readFile(fileName);
        return cipher.decode(fileContents);
    }

    public String getEncipheredFile(String fileName, String decipheringKey) throws FileNotFoundException {
        Path keyPath = Paths.get("ciphers/" + decipheringKey);
        Cipher cipher = new Cipher(keyPath);
        cipher.loadKey();
        String fileContents = fileHandler.readFile(fileName);
        return cipher.decode(fileContents);
    }



}
