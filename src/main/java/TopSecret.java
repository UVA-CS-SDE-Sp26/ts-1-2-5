import java.io.FileNotFoundException;

/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        ProgramControl c = new ProgramControl(fileHandler);

        try {
            if (args.length == 0) {
                System.out.println("Listing file list please choose one: ");
                System.out.println(c.listFiles());
            } else if (args.length == 1) {
                System.out.println("Now printing file " + args[0] + " using default cipher key");
                System.out.println(c.getDecipheredFile(args[0]));
            } else if (args.length == 2) {
                System.out.println("Now printing file " + args[0] + " using the key from file: " + args[1]);
                System.out.println(c.getAlternateKeyDecipheredFile(args[0], args[1]));
            } else {
                System.out.println("Invalid number of arguments");
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR : File not found");
        } catch (IndexOutOfBoundsException e){
            System.out.println("ERROR : Number too big");
        } catch (NumberFormatException e) {
            System.out.println("ERROR : Incorrect number formatting");
        } catch (Exception e) {
            System.out.println("ERROR : general error message, code exhibited unexpected behavior");
        }
    }
}
