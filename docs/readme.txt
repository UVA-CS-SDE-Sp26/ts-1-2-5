TopSecret documentation starter file
public class TopSecret {
    public static void main(String[] args) throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        ProgramControl c = new ProgramControl(fileHandler);

        if (args.length == 0){
            System.out.println("Listing file list please choose one: ");
            System.out.println(c.listFiles());
            //display number of files call
        }
        else if (args.length == 1){
            System.out.println("Now printing file " + args[0] + " using default cipher key");
            System.out.println(c.getDecipheredFile(args[0]));
            //return the deciphered file
        }
        else if (args.length == 2){
            System.out.println("Now printing file " + args[0] + " using the key from file: " + args[1]);
            System.out.println(c.getEncipheredFile(args[0], args[1]));
        }
        else{
            System.out.println("Invalid number of arguments");
        }
    }
