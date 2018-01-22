
package bashterminalsimulation;

import java.util.ArrayList;

/**
 *
 * @author Vincent Cramer
 */
public class DirectoryNode {
    private String name;
    
    //we'll treat position 0 as left, and size-1 as right
    private ArrayList<DirectoryNode> children; 
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
        children = new ArrayList();
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
     * The boolean value indicating if the node is a file
     */
    public boolean getIsFile(){
        return isFile;
    }
    

    /**
     * A method which assigns the argument node to be a child of the method 
     * caller node. This method assigns children in a pre-order fashion, with 
     * left being the 0th position in the children array, and size-1 being the
     * rightmost position
     * 
     * 
     * @param node 
     * The new node to potentially be added as a child of the node which called 
     * this method
     * 
     * Postcondition:
     * Argument node has been added a child to the caller node
     */
    public void addChild(DirectoryNode node){
        if(this.isFile){
            System.out.println("Cannot add children to file.");
            return;
        }
        
        children.add(node);
    }

    /**
     * Accessor for the ArrayList containing all of the children of this node
     * 
     * @return 
     * ArrayList of this node's children nodes
     */
    public ArrayList getChildren(){
        return children;
    }
    
    
    /**
     * Method which removes 1 node from the ArrayList of children at current
     * node
     * 
     * @param n 
     * Position of node to remove in ArrayList
     * 
     * Postcondition:
     * ArrayList of children is 1 element shorter, and the designated node has 
     * been removed
     */
    public void removeNthChild(int n){
        children.remove(n);
    }
    
}
