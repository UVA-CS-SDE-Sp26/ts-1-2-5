import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Decipher {
    private Map<Character, Character> keyMap;
    private Path keyPath;

    public Decipher(Path keyPath) {
        this.keyPath = keyPath;
        keyMap = new HashMap<>();
    }

    public void loadKey(){
        List<String> lines;
        try{
            lines = Files.readAllLines(keyPath);
        }catch(IOException e){
            throw new RuntimeException("Can't read key file");
        }

        if(lines.size() != 2){
            throw new RuntimeException("Wrong number of lines");
        }

        String actual = lines.get(0);
        String cipher = lines.get(1);

        if(actual.isEmpty() || cipher.isEmpty()){
            throw new RuntimeException("At least one line is empty");
        }

        if(actual.length() != cipher.length()){
            throw new RuntimeException("Wrong number of characters");
        }

        if(hasDuplicates(cipher)){
            throw new RuntimeException("Duplicate characters in cipher");
        }

        if(hasDuplicates(actual)){
            throw new RuntimeException("Duplicate characters in inputted string");
        }

        keyMap.clear();
        for(int i = 0; i < cipher.length();i++) {
            keyMap.put(cipher.charAt(i), actual.charAt(i));
        }
    }

    public String decode(String text){
        if (keyMap.isEmpty()) {
            throw new RuntimeException("Key not loaded. Call loadKey() first.");
        }

        StringBuilder result = new StringBuilder();

        for(char letter : text.toCharArray()){
            if(keyMap.containsKey(letter)){
                result.append(keyMap.get(letter));
            }else{
                result.append(letter);
            }
        }

        return result.toString();
    }

    private boolean hasDuplicates(String s){
        Set<Character> seen = new HashSet<>();

        for (char letter : s.toCharArray()){
            if(!seen.add(letter)){
                return true;
            }
        }
        return false;
    }
}
