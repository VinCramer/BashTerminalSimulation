
package bashterminalsimulation;

import java.util.ArrayList;

/**
 *
 * @author Vincent Cramer
 */
public class DirectoryTree {
    private DirectoryNode cursor, root;
    
    /**
     * Constructor for a new directory tree, which will hold all of the 
     * directory nodes created by the user.
     * 
     * Postcondition:
     * The directory tree has been created.
     */
    public DirectoryTree(){
        root = new DirectoryNode("root",false);
        cursor=root;
    }
 
    /**
     * This method places the cursor such that it points to the root, just like
     * when the tree was first created
     * 
     * Postcondition:
     * Cursor now points to root.
     */
    public void resetCursor(){
        cursor=root;    
    }
    
    /**
     * This method allows the user to change what directory they are currently 
     * in by moving the cursor to the specified location.
     * 
     * @param name 
     * The name of the directory the user wishes to access. 
     * 
     * Postcondition:
     * Cursor now points to the designated directory.
     */
    public void changeDirectory(String name){ 
        if(name.equals("/")){
            cursor=root;
            return;
        }
        if(name.contains("/")){
            absolutePathChangeDirectory(name);
            return;
        }
       
        
        
        ArrayList<DirectoryNode> children = cursor.getChildren();
        for(DirectoryNode n : children){
            if(n.getName().equals(name) && !n.getIsFile()){
                cursor=n;
                return;
            }
        }
        
        System.out.println("Invalid directory, cursor unchanged.");
        
        
    }
    
    /**
     * Finds the path for the node with the given name, and returns it as a 
     * String
     * 
     * @param nodeName
     * The name of the node to be found in the tree
     * 
     * @return 
     * A formatted String with the path of the node
     */
    public String find(String nodeName){
        return presentWorkingDirectoryHelper(root,"",nodeName);
    }
    
    /**
     * Returns a String showing the present working directory, which is the path
     * of directories starting from the root and ending at the cursor.
     * 
     * @return
     * A String showing the present working directory.
     */
    public String presentWorkingDirectory(){
        if(root==null || cursor==null){
            System.out.println("No files exist.");
            return "";
        }
        return presentWorkingDirectoryHelper(root,"",cursor.getName());
    }
    
    /**
     * A recursive helper method for getting the present working directory and 
     * finding a certain node.
     * 
     * @param node
     * Current node in the tree. 
     * 
     * @param path
     * A String holding the values of different places visited in the tree thus 
     * far.
     * 
     * @param nameToFind
     * A String containing the name of the node we're searching for
     * 
     * @return 
     * A formatted String displaying the present working directory.
     */
    private String presentWorkingDirectoryHelper(DirectoryNode node, 
            String path, String nameToFind){

        /*
        Originally, a space was used as a token to see if we have already found 
        the path, but upon further research it appears Unix allows space in 
        filenames under certain circumstances. Thus, this method has been 
        updated to use the null character instead.
        */
        
        
        if(path.contains("\0")){
            return path;
        }
        if(node==null){
            return null;
        }
        
        if(node.getName().equals(nameToFind)){
            return path + nameToFind + "\0";
        }else{
            path+=node.getName()+"/"; 
            
            String returnedValue;
            
        ArrayList<DirectoryNode> children = node.getChildren();
        int i=0;
        for(i=0;i<children.size();i++){
            if(!children.get(i).getIsFile()){
                returnedValue = presentWorkingDirectoryHelper(children.get(i), 
                        path, nameToFind);
                if(returnedValue.contains("\0")){
                    return returnedValue;
                }
            }
            if(children.get(i).getIsFile() && nameToFind.contains(".")){
                if(children.get(i).getName().equals(nameToFind)){
                    return path + nameToFind + "\0";
                }
            }
        }
        
        }
        return "";
        
    }
    
    /**
     * A method which moves the cursor up to its parent directory, or does 
     * nothing if at the cursor
     * 
     * Postcondition:
     * The cursor has moved up a directory or is at the root
     */
    public void moveUp(){
        if(cursor.getName().equals("root")){
            return;
        }
        moveUpHelper(root,root);
        
    }
    
    /**
     * A recursive helper method for finding the parent node of the cursor, and 
     * moving the cursor to point to its parent
     * 
     * @param parent
     * The parent node of the current node
     * 
     * @param current 
     * The current node we are referencing in the pre-order traversal
     * 
     * Postcondition:
     * The cursor has moved up a directory or is at the root
     */
    private void moveUpHelper(DirectoryNode parent, DirectoryNode current){
        if(current.getName().equals(cursor.getName())){
            cursor=parent;
            return;
        }
        
        ArrayList<DirectoryNode> children = current.getChildren();
        int i=0;
        for(i=0;i<children.size();i++){
            if(!children.get(i).getIsFile()){
                moveUpHelper(current,children.get(i));
            }
        }
        
    }
    
    /**
     * A method which allows for the changing of paths if the user specified an 
     * absolute path
     * 
     * @param path 
     * The absolute path the user wishes to move the cursor to
     * 
     * Postcondition:
     * Cursor now points to the final directory in the given path, or the 
     * console displays an error message
     */
    public void absolutePathChangeDirectory(String path){
        String[] arr = path.split("/");
        
        if(!arr[0].equals("root")){
            System.out.println("Invalid path, no change to cursor.");
            return;
        }
        if(arr[0].equals("root") && arr.length==1){
            cursor=root;
            return;
        }
        
        DirectoryNode temp = root;
        int i=1;
        boolean found;
        for(i=1;i<arr.length;i++){
            found=false;
            ArrayList<DirectoryNode> children = temp.getChildren();
            
            int j=0;
            for(j=0;j<children.size();j++){
                if(!children.get(j).getIsFile() && children.get(j).getName()
                        .equals(arr[i])){
                    temp=children.get(j);
                    found=true;
                }
            }
            if(!found){
                System.out.println("Path not found.");
                return;
            }
            
        }
        cursor=temp;
    }
    
    /**
     * A method which moves one file or directory from a given source path to a 
     * given destination path
     * 
     * Precondition:
     * Both the source and destination paths exist, and the file or directory 
     * being moved does not have the same name as another file or directory in 
     * the directory it will be moved to
     * 
     * @param source
     * The source path of the node that will be moved
     * 
     * @param destination 
     * The source path where the node will wind up
     * 
     * Postcondition:
     * The node has been moved, or the user has been informed about why it was 
     * not moved
     */
    public void move(String source, String destination){
        
        if(destination.contains(".")){
            System.out.println("Invalid destination.");
            return;
        }
        /*parent is one level above the node we're moving. node is what will be 
        moved. temp is for traversal, and tempParent is for 
        storing the parent, temporarily. All are initialized to root to appease 
        the compiler's whims.
        */
        DirectoryNode parent=root, node, temp;
        
        String[] sourceArr = source.split("/");
        String[] destArr = destination.split("/");
        
        String parentName = sourceArr[sourceArr.length-2];
        String nodeName = sourceArr[sourceArr.length-1];
        
        temp = root;
        
        int i;
        boolean found;
        for(i=1;i<sourceArr.length;i++){
            found=false;
            if(temp.getName().equals(parentName)){
                parent=temp;
            }
            
            int j=0;
            ArrayList<DirectoryNode> children = temp.getChildren();
            for(j=0;j<children.size();j++){
                if(children.get(j).getName().equals(sourceArr[i])){
                    temp=children.get(j);
                    found=true;
                }
                
            }
            if(!found){
                System.out.println("Invalid source path");
                return;
            }
        }
        
        node=temp;
        
        ArrayList<DirectoryNode> parentNodeChildren = parent.getChildren();
        for(i=0;i<parentNodeChildren.size();i++){
            if(parentNodeChildren.get(i).getName().equals(nodeName)){
                parent.removeNthChild(i);
                break;
            }
        }
        
        //reset iterator for next loop
        temp=root;
        
        for(i=1;i<destArr.length;i++){
            found=false;
            ArrayList<DirectoryNode> children = temp.getChildren();
            for(int j=0;j<children.size();j++){
                if(!children.get(j).getIsFile() && children.get(j).getName()
                        .equals(destArr[i])){
                    temp=children.get(j);
                    found=true;
                }
            }
            if(!found){
                System.out.println("Invalid destination path");
                return;
            }
        }
        
        for(Object o : temp.getChildren()){
            if(((DirectoryNode)o).getName().equals(node.getName())){
                System.out.println("This simulation does not handle files or "
                        + "directories of the same name in the same "
                        + "directory.");
                return;
            }
        }
        
        temp.addChild(node);
    }
    
    /**
     * Displays the names of children nodes of the cursor. 
     * 
     * @return 
     * A String with the names of children nodes of the cursor, each separated 
     * by a space.
     */
    public String listDirectory(){
        String s = "";
        
        int i=0;
        
        for(i=0;i<cursor.getChildren().size();i++){
            s+=((DirectoryNode)(cursor.getChildren().get(i))).getName() + " ";
        }
        return s;
    }
    
    /**
     * A method which prints out a formatted tree to the console in preorder 
     * notation.
     * 
     * Postcondition:
     * The directory tree has been printed to the console.
     */
    public void printDirectoryTree(){
        printDirectoryTreeHelper(root, "");
    }
    
    /**
     * A recursive helper method for printing the directory tree.
     * 
     * @param node
     * The current node in the tree. 
     * 
     * @param tabs 
     * A String which contains tabs. This is used to indicate "levels" of the 
     * tree. At the top of the tree with the root, there are no spaces. The 
     * root's children, however, have a tab before their identifying symbols and
     * name, and their children have another tab, and so on.
     * 
     * Postcondition:
     * The directory tree has been printed to the console.
     */
    private void printDirectoryTreeHelper(DirectoryNode node, String tabs){
        if(node.getIsFile()){
            System.out.println(tabs + "- " + node.getName());
        }
        else{
            System.out.println(tabs + "-| " + node.getName());
        }
        
        
        int i=0;
        ArrayList<DirectoryNode> children = node.getChildren();
        for(i=0;i<children.size();i++){
            printDirectoryTreeHelper(children.get(i), tabs+ "    ");
        }
    }
    
    /**
     * A method which creates a directory as a child of the cursor, if possible.
     * 
     * Precondition:
     * The directory name does not contain a null character, slash, or period. 
     * Additionally, the given name is not the same as the name of any other 
     * nodes that are children of the cursor. 
     * 
     * @param name 
     * Name of the node that will potentially be added to the tree.
     * 
     * Postcondition:
     * The node has been added, or the user has been informed about why it was 
     * not added.
     */
    public void makeDirectory(String name){
        if(name.contains("\0") || name.contains("/")||name.contains(".")){
            System.out.println("Invalid directory name");
            return;
        }
        for (Object o : cursor.getChildren()){
            if(((DirectoryNode)o).getName().equals(name)){
                System.out.println("This simulation does not handle drectories"
                        + " of the same name in the same directory.");
                return;
            }
        }
        DirectoryNode newNode = new DirectoryNode(name,false);
        cursor.addChild(newNode);
    }
    
   /**
     * A method which creates a file as a child of the cursor, if possible.
     * 
     * Precondition:
     * The name does not have a null character or slash. Additionally, the given
     * name is not the same as the name of any other nodes that are children of 
     * the cursor. 
     * 
     * @param name 
     * Name of the node that will potentially be added to the tree.
     * 
     * Postcondition:
     * The node has been added, or the user has been informed about why it was 
     * not added.
     */
    public void makeFile(String name){
        if(name.contains("\0") || name.contains("/")){
            System.out.println("Invalid directory name");
            return;
        }
        for (Object o : cursor.getChildren()){
            if(((DirectoryNode)o).getName().equals(name)){
                System.out.println("This simulation does not handle files of "
                        + "the same name in the same directory.");
                return;
            }
        }
        DirectoryNode newNode = new DirectoryNode(name,true);
        cursor.addChild(newNode);
    }
    
}
