
package bashterminalsimulation;

import java.util.Scanner;

/**
 *
 * @author Vincent Cramer
 */
public class BashTerminalSimulation {


    /**
     * Main method, used for reading and handling user input.
     */
    public static void main(String[] args) {
        DirectoryTree tree = new DirectoryTree();
        boolean isRunning=true;
        Scanner input = new Scanner(System.in);
        String command;
        printCommands();
        while(isRunning){
            
            System.out.print("[user@localhost]: $");
            
            command = input.nextLine();
            if(command.equals("exit")){
                isRunning=false;
            }
            else if(command.equals("pwd")){
                System.out.println(tree.presentWorkingDirectory());
            }
            else if(command.equals("ls")){
                System.out.println(tree.listDirectory());
            }
            else if(command.equals("ls -R")){
                tree.printDirectoryTree();
            }
            else if(command.substring(0,2).equals("cd") && command.substring(3).equals("..")){
                tree.moveUp();
            }
            else if(command.substring(0,2).equals("cd")){
                tree.changeDirectory(command.substring(3));
            }
            
            else if(command.substring(0,5).equals("mkdir")){
                tree.makeDirectory(command.substring(6));
            }
            
            else if(command.substring(0,4).equals("find")){
                System.out.println(tree.find(command.substring(5)));
            }
            
            else if(command.substring(0,5).equals("touch")){
                tree.makeFile(command.substring(6));
            }
            else{
                System.out.println("Invalid command.");
            }
        }
        System.out.println("Terminating program...goodbye.");
        
    }
    
    /**
     * Prints available commands for the user. Called on each loop itertion
     * 
     * Postcondition:
     *  Available commands have been printed to the console
     */
    public static void printCommands(){
        System.out.println("Here is a list of valid commands:");
        System.out.println("pwd (display the present working directory)");
        System.out.println("ls (list names of child directory/folders of "
                + "current pwd)");
        System.out.println("ls -R (prints entire tree in pre-order traversal)");
        System.out.println("cd {dir} (change directory to given directory, "
                + "and can use relative or absolute path)");
        System.out.println("cd / (change cursor to point at the root of the "
                + "tree)");
        System.out.println("cd .. (Moves the cursor up to its parent directory, "
                + "and has no effect at root)");
        System.out.println("mkdir {name} (make a directory with some name, "
                + "brackets not required)");
        System.out.println("touch {name} (make a file with some name, brackets "
                + "not required)");
        System.out.println("find {name} (searches tree for node of same name, "
                + "and prints path)");
        System.out.println("exit (terminates program)");   
        
    }
    
}
