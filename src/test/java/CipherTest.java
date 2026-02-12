import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {

    @Test
    void loadKey_validFile() throws IOException {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "abc\nxyz\n");

        Cipher c = new Cipher(key);

        assertDoesNotThrow(c::loadKey);
        assertEquals("abc", c.decode("xyz"));
    }

    @Test
    void loadKey_fakePath() {
        Path fake = Path.of("no_such_dir", "key.txt");

        Cipher c = new Cipher(fake);

        RuntimeException ex = assertThrows(RuntimeException.class, c::loadKey);
        assertEquals("Can't read key file", ex.getMessage());
    }

    @Test
    void loadKey_moreThanTwoLines() throws IOException {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "abc\nxyz\nextra\n");

        Cipher c = new Cipher(key);

        RuntimeException ex = assertThrows(RuntimeException.class, c::loadKey);
        assertEquals("Wrong number of lines", ex.getMessage());
    }

    @Test
    void loadKey_differentLengths() throws IOException {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "abcd\nxyz\n");

        Cipher c = new Cipher(key);

        RuntimeException ex = assertThrows(RuntimeException.class, c::loadKey);
        assertEquals("Wrong number of characters", ex.getMessage());
    }

    @Test
    void decode_emptyMap() throws IOException {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "abc\nxyz\n");

        Cipher c = new Cipher(key);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> c.decode("xyz"));
        assertEquals("Key not loaded. Call loadKey() first.", ex.getMessage());
    }

    @Test
    void hasDuplicates_cipherDuplicates() throws IOException {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "abc\nxxy\n");

        Cipher c = new Cipher(key);

        RuntimeException ex = assertThrows(RuntimeException.class, c::loadKey);
        assertEquals("Duplicate characters in cipher", ex.getMessage());
    }

    @Test
    void hasDuplicates_actualDuplicates() throws IOException {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "aab\nxyz\n");

        Cipher c = new Cipher(key);

        RuntimeException ex = assertThrows(RuntimeException.class, c::loadKey);
        assertEquals("Duplicate characters in inputted string", ex.getMessage());
    }

    @Test
    void hasDuplicates_noDuplicates() throws IOException {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "abcd\nwxyz\n");

        Cipher c = new Cipher(key);

        assertDoesNotThrow(c::loadKey);
    }

    @Test
    void hasDuplicates_bothDuplicates() throws IOException {
        Path key = Files.createTempFile("key", ".txt");
        Files.writeString(key, "aab\nxxy\n");

        Cipher c = new Cipher(key);

        RuntimeException ex = assertThrows(RuntimeException.class, c::loadKey);
        assertEquals("Duplicate characters in cipher", ex.getMessage());
    }
}

