
package bashterminalsimulation;

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
        
        if(cursor.getLeft()!=null && name.equals(cursor.getLeft().getName()) 
                && !cursor.getLeft().getIsFile()){
            cursor=cursor.getLeft();
        }
        else if(cursor.getMiddle()!=null && name.equals(cursor.getMiddle()
                .getName()) 
                && !cursor.getMiddle().getIsFile()){
            cursor=cursor.getMiddle();
        }
        else if(cursor.getRight()!=null && name.equals(cursor.getRight()
                .getName()) 
                && !cursor.getRight().getIsFile()){
            cursor=cursor.getRight();
        }
        else{
            System.out.println("Invalid directory, cursor unchanged.");
        }
        
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

        //we use space as a token to see if we've found the cursor node
        if(path.contains(" ")){
            return path;
        }
        if(node==null){
            return null;
        }
        
        if(node.getName().equals(nameToFind)){
            return path+node.getName() + " ";
        }else{
            path+=node.getName()+"/"; 
            
            String l, m, r;
        if(node.getLeft()!=null && !node.getLeft().getIsFile()){
                l=presentWorkingDirectoryHelper(node.getLeft(), path, 
                        nameToFind);
                if(l.contains(" ")){
                    return l;
                }
        }
        if(node.getMiddle()!=null && !node.getMiddle().getIsFile()){
            
            m = presentWorkingDirectoryHelper(node.getMiddle(), path, 
                    nameToFind);
            if(m.contains(" ")){
                return m;
            }
        }
        if(node.getRight()!=null && !node.getRight().getIsFile()){
            
            r = presentWorkingDirectoryHelper(node.getRight(), path, nameToFind);
            if(r.contains(" ")){
                return r;
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
        if(current.getLeft()!=null && !current.getLeft().getIsFile()){
            moveUpHelper(current,current.getLeft());
        }
        if(current.getMiddle()!=null && !current.getMiddle().getIsFile()){
            moveUpHelper(current,current.getMiddle());
        }
        if(current.getRight()!=null && !current.getRight().getIsFile()){
            moveUpHelper(current,current.getRight());
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
        for(i=1;i<arr.length;i++){
            if(temp.getLeft()!=null && !temp.getLeft().getIsFile() && 
                    temp.getLeft().getName().equals(arr[i])){
                temp=temp.getLeft();
            }
            else if(temp.getMiddle()!=null && !temp.getMiddle().getIsFile() && 
                    temp.getMiddle().getName().equals(arr[i])){
                temp=temp.getMiddle();
            }
            else if(temp.getRight()!=null && !temp.getRight().getIsFile() && 
                    temp.getRight().getName().equals(arr[i])){
                temp=temp.getRight();
            }
            else{
                System.out.println("Path not found.");
                break;
            }
        }
        cursor=temp;
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
        if(cursor.getLeft()!=null){
            s+=cursor.getLeft().getName() + " ";
        }
        if(cursor.getMiddle()!=null){
            s+=cursor.getMiddle().getName() + " ";
        }
        if(cursor.getRight()!=null){
            s+=cursor.getRight().getName();
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
        
        if(node.getLeft()!=null){
            printDirectoryTreeHelper(node.getLeft(), tabs + "    ");
        }
        if(node.getMiddle()!=null){
            printDirectoryTreeHelper(node.getMiddle(), tabs + "    ");
        }
        if(node.getRight()!=null){
            printDirectoryTreeHelper(node.getRight(), tabs + "    ");
        }
    }
    
    /**
     * A method which creates a directory as a child of the cursor, if possible.
     * 
     * Precondition:
     * The directory name does not contain a space, slash, or period. Also, if 
     * cursor has the maximum amount of children, another child won't be added.
     * 
     * @param name 
     * Name of the node that will potentially be added to the tree.
     * 
     * Postcondition:
     * The node has been added, or the user has been informed about why it was 
     * not added.
     */
    public void makeDirectory(String name){
        if(name.contains(" ") || name.contains("/")||name.contains(".")){
            System.out.println("Invalid directory name");
            return;
        }
        DirectoryNode newNode = new DirectoryNode(name,false);
        cursor.addChild(newNode);
    }
    
   /**
     * A method which creates a file as a child of the cursor, if possible.
     * 
     * Precondition:
     * The name does not have a space or slash. Also, if the cursor has the 
     * maximum amount of children, another child won't be added.
     * 
     * @param name 
     * Name of the node that will potentially be added to the tree.
     * 
     * Postcondition:
     * The node has been added, or the user has been informed about why it was 
     * not added.
     */
    public void makeFile(String name){
        if(name.contains(" ") || name.contains("/")){
            System.out.println("Invalid directory name");
            return;
        }
        DirectoryNode newNode = new DirectoryNode(name,true);
        cursor.addChild(newNode);
    }
    
}
