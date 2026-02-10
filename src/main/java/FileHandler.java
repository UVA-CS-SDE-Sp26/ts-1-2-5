import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FileHandler {
    private String[] fileList;

    public void setFileList(String[] fileList) {
        this.fileList = fileList;
    }

    public String[] getFileList() {
        return fileList;
    }

    public FileHandler() {
        fileList = new String[0];
    }

    public String[] getNames() {
        String path = "ts-1-2-5/data/";
        File folder = new File(path);
        fileList = folder.list();
        return fileList;
    }

    public String readFile(String fileName) throws FileNotFoundException {
        String path = "ts-1-2-5/data/";
        File file = new File(path + fileName);
        Scanner scanner = new Scanner(file);
        String res = "";
        while (scanner.hasNextLine()) {
            res += scanner.nextLine() + "\n";
        }
        return res;
    }


}
