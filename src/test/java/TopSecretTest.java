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
    void noArgs() {
        TopSecret.main(new String[]{});
        String result = output.toString();
        assertTrue(result.contains("Listing file list"));
    }

    @Test
    void testOneArg() {
        TopSecret.main(new String[] {"test.txt"});
        String result = output.toString();
        assertTrue(result.contains("using default cipher key"));
    }

    @Test
    void testTwoArg() {
        TopSecret.main(new String[]{"a", "b", "c"});
        String result = output.toString();
        assertTrue(result.contains("Invalid number of arguments"));
    }
}