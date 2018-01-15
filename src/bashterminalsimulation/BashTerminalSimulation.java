
package bashterminalsimulation;

import java.util.Scanner;

/**
 *
 * @author Vincent Cramer
 */
public class BashTerminalSimulation {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DirectoryTree tree = new DirectoryTree();
        boolean isRunning=true;
        Scanner input = new Scanner(System.in);
        String command;
        while(isRunning){
            //printCommands();
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
            
            else if(command.substring(0,2).equals("cd")){
                tree.changeDirectory(command.substring(3));
            }
            
            else if(command.substring(0,5).equals("mkdir")){
                tree.makeDirectory(command.substring(6));
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
    
    public static void printCommands(){
        System.out.println("Here is a list of valid commands:");
        System.out.println("pwd (display the present working directory)");
        System.out.println("ls (list names of child directory/folders of current pwd)");
        System.out.println("ls -R (prints entire tree in pre-order traversal)");
        System.out.println("cd {dir} (change directory to given directory, relative path only, brackets not required)");
        System.out.println("cd / (change cursor to point at the root of the tree)");
        System.out.println("mkdir {name} (make a directory with some name, brackets not required)");
        System.out.println("touch {name} (make a file with some name, brackets not required)");
        System.out.println("exit (terminates program)");
        System.out.println("Enter command: ");
        System.out.print("[user@localhost]: $");
    }
    
}