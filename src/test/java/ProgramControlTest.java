import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramControlTest {
    @Test
    public void testListFiles() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);
        String actual = "01 carnivore.txt" + "\n" + "02 cointelpro.cip" + "\n" + "03 carnivore.cip" + "\n" + "04 cointelpro.txt" + "\n";
        String expected = programControl.listFiles();
        assertEquals(actual, expected, "List of files must be numbered and match the ordered list of files available");
    }

    @Test
    public void testGetFileName1() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);
        String actual = "carnivore.txt";
        String expected = programControl.getFileName("01");
        assertEquals(actual, expected, "The file name does not match the file name of the number inputted");
    }

    @Test
    public void testGetFileName2() {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);
        String actual = "cointelpro.txt";
        String expected = programControl.getFileName("04");
        assertEquals(actual, expected, "The file name does not match the file name of the number inputted");
    }

    @Test
    public void testGetDecipheredFile1() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);
        String actual = "Carnivore, later renamed DCS1000, was a system implemented by the Federal Bureau of Investigation (FBI) that was\n" +
                "designed to monitor email and electronic communications. It used a customizable packet sniffer that could monitor all\n" +
                "of a target user's Internet traffic. Carnivore was implemented in October 1997. By 2005 it had been replaced with\n" +
                "improved commercial software.";
        String expected = programControl.getDecipheredFile("03");
        assertEquals(actual, expected, "The contents of the file do not match up to the correct file deciphered using the default key");
    }


    @Test
    public void testGetDecipheredFile2() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);
        String actual = "COINTELPRO (a syllabic abbreviation derived from Counter Intelligence Program) was a series of covert and illegal\n" +
                "projects conducted between 1956 and 1971 by the United States Federal Bureau of Investigation (FBI) aimed at\n" +
                "surveilling, infiltrating, discrediting, and disrupting American political parties and organizations that the FBI\n" +
                "perceived as subversive.";
        String expected = programControl.getDecipheredFile("02");
        assertEquals(actual, expected, "The contents of the file do not match up to the correct file deciphered using the default key");
    }

    @Test
    public void testGetAlternateKeyDecipheredFile1() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);
        String actual = "COINTELPRO (a syllabic abbreviation derived from Counter Intelligence Program) was a series of covert and illegal\n" +
                "projects conducted between 1956 and 1971 by the United States Federal Bureau of Investigation (FBI) aimed at\n" +
                "surveilling, infiltrating, discrediting, and disrupting American political parties and organizations that the FBI\n" +
                "perceived as subversive.";
        String expected = programControl.getAlternateKeyDecipheredFile("02", "key.txt");
        assertEquals(actual, expected, "The contents of the file do not match up to the correct file deciphered using the alternate key given");
    }

    @Test
    public void testGetAlternateKeyDecipheredFile2() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);
        String actual = "Carnivore, later renamed DCS1000, was a system implemented by the Federal Bureau of Investigation (FBI) that was\n" +
                "designed to monitor email and electronic communications. It used a customizable packet sniffer that could monitor all\n" +
                "of a target user's Internet traffic. Carnivore was implemented in October 1997. By 2005 it had been replaced with\n" +
                "improved commercial software.";
        String expected = programControl.getAlternateKeyDecipheredFile("03", "key.txt");
        assertEquals(actual, expected, "The contents of the file do not match up to the correct file deciphered using the alternate key given");
    }


}

