/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("Listing file list please choose one: ");
            //display number of files call
        }
        else if (args.length == 1){
            System.out.println("Now printing file " + args[0] + " using default cipher key");
            //return the deciphered file
        }
        else if (args.length == 2){
            System.out.println("Now printing file " + args[0] + " using the key from file: " + args[1]);
        }
        else{
            System.out.println("Invalid number of arguments");
        }
    }
}
