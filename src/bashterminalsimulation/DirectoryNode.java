
package bashterminalsimulation;

/**
 *
 * @author Vincent Cramer
 */
public class DirectoryNode {
    private String name;
    
    //change to arraylist of nodes later for n-ary tree
    private DirectoryNode left, middle, right;
    public boolean isFile;
    
    /**
     * Constructor method for a "node", which is a file or directory in the tree
     * 
     * Precondition:
     *  The DirectoryTree must have been created for any nodes to be created
     * 
     * @param name
     * Name of the file or directory of node
     * 
     * @param isFile 
     * Value indicating if node is a file, and if the value is false, then the 
     * node is a directory
     * 
     * Postcondition:
     *  New DirectoryNode has been created
     */
    public DirectoryNode(String name, boolean isFile){
        this.name=name;
        this.isFile=isFile;
    }
    
    
    /**
     * Accessor for the name field of a node
     * 
     * @return 
     * Name of the node
     */
    public String getName(){
        return name;
    }
    
    /**
     * Accessor for the field indicating if node is a file or not
     * 
     * @return 
     * THe boolean value indicating if the node is a file
     */
    public boolean getIsFile(){
        return isFile;
    }
    
    /**
     * Accessor for the left child of the current node
     * 
     * @return 
     *  Left child node, or null if the child does not exist
     */
    public DirectoryNode getLeft(){
        return left;
    }
    
    /**
     * Accessor for the right child of the current node
     * 
     * @return 
     *  Right child node, or null if the child does not exist
     */
    public DirectoryNode getRight(){
        return right;
    }
    
    /**
     * Accessor for the middle child of the current node
     * 
     * @return 
     *  Middle child node, or null if the child does not exist
     */
    public DirectoryNode getMiddle(){
        return middle;
    }
    
    /**
     * A method which assigns the argument node to be a child of the method 
     * caller node. This method assigns children in a pre-order fashion, and 
     * will not do anything if the current node cannot hold any references to 
     * new children.
     * 
     * 
     * @param node 
     * The new node to potentially be added as a child of the node which called 
     * this method
     * 
     * Postcondition:
     * If the caller node can spare a reference for the argument node, the 
     * caller node will now reference said argument node. Otherwise, a message 
     * will be printed to the console stating that this node can't have any more
     * children.
     */
    public void addChild(DirectoryNode node){
        if(this.isFile){
            System.out.println("Cannot add children to file.");
            return;
        }
        if(this.getLeft()==null){
            left=node;
        }
        else if(this.getMiddle()==null){
            middle=node;
        }
        else if(this.getRight()==null){
            right=node;
        }
        else{
            System.out.println("No room for children in this folder.");
        }
    }
}
