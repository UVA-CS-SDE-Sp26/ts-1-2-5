import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void getNamesTest() {
        FileHandler fileHandler = new FileHandler();
        String[] actual = new String[]{"carnivore.cip", "carnivore.txt", "cointelpro.cip", "cointelpro.txt"};
        String[] expected = fileHandler.getNames();
        Arrays.sort(expected);
        Arrays.sort(actual);
        assertArrayEquals(actual, expected, "Names of files must match data/ directory");
    }

    @Test
    void readFileTestCarnivore() throws FileNotFoundException {
        String carnivoreText = "Carnivore, later renamed DCS1000, was a system implemented by the Federal Bureau of Investigation (FBI) that was\n" +
                "designed to monitor email and electronic communications. It used a customizable packet sniffer that could monitor all\n" +
                "of a target user's Internet traffic. Carnivore was implemented in October 1997. By 2005 it had been replaced with\n" +
                "improved commercial software.";
        FileHandler fileHandler = new FileHandler();
        assertEquals(fileHandler.readFile("carnivore.txt"), carnivoreText, "Text read from file must match actual text found in file");
    }

    @Test
    void readFileTestCointelPro() throws FileNotFoundException {
        String carnivoreText = "COINTELPRO (a syllabic abbreviation derived from Counter Intelligence Program) was a series of covert and illegal\n" +
                "projects conducted between 1956 and 1971 by the United States Federal Bureau of Investigation (FBI) aimed at\n" +
                "surveilling, infiltrating, discrediting, and disrupting American political parties and organizations that the FBI\n" +
                "perceived as subversive.";
        FileHandler fileHandler = new FileHandler();
        assertEquals(fileHandler.readFile("cointelpro.txt"), carnivoreText, "Text read from file must match actual text found in file");
    }
}