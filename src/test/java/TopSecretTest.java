import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TopSecretTest {
    //there are four cases to test, 0 args, 1 arg, 2 args, 3 or more args
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream ogOut = System.out;
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(ogOut);
        output.reset();
    }

    @Test
    void main() throws FileNotFoundException {
        //make fake filehandler and programcontrol so that i can test it
        FileHandler fakeHandler = new FileHandler() {
            public String[] getNames() {
                return new String[]{"fake.txt"};
            }

            public String readFile(String f) {
                return "fake stuff";
            }
        };

        ProgramControl fakeControl = new ProgramControl(fakeHandler) {
            public String listFiles() {
                return "fake list";
            }
            public String getDecipheredFile(String f) {
                return "fake deciphered";
            }
            public String getEncipheredFile(String f, String k) {
                return "fake enciphered";
            }
        };

        //no arguments passed in
        String[] args0 = {};
        if (args0.length==0){
            System.out.println("Listing file list please choose one:");
            System.out.println(fakeControl.listFiles());
            }
        String out = output.toString();
        assertTrue(out.contains("Listing file list"));
        assertTrue(out.contains("fake list"));
        output.reset();


        //only one argument is given
        String[] arg1 = {"test.txt"};
        if(arg1.length == 1){
            System.out.println("Now printing file " + arg1[0] + " using default cipher key");
            System.out.println(fakeControl.getDecipheredFile(arg1[0]));
        }

        out = output.toString();
        assertTrue(out.contains("using default cipher key"));
        assertTrue(out.contains("fake deciphered"));
        output.reset();

        //2 args passed in
        String[] arg2 = {"test.txt", "key.txt"};
        if(arg2.length == 2){
            System.out.println("Now printing file " + arg2[0] + " using the key from file: " + arg2[1]);
            System.out.println(fakeControl.getEncipheredFile(arg2[0], arg2[1]));
        }
        out = output.toString();
        assertTrue(out.contains("using the key from file"));
        assertTrue(out.contains("fake enciphered"));
        output.reset();


        //too many arguments
        String[] arg3 = {"a", "b", "c"};
        if(arg3.length>2){
            System.out.println("Invalid number of arguments");
        }
        out = output.toString();
        assertTrue(out.contains("Invalid number of arguments"));
    }
}