
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
    
    public DirectoryNode(String name, boolean isFile){
        this.name=name;
        this.isFile=isFile;
    }
    
    public DirectoryNode(String name){
        this.name=name;
        if(name.contains(".")){
            isFile=true;
        }
        else{
            isFile=false;
        }
    }
    
    public String getName(){
        return name;
    }
    
    public boolean getIsFile(){
        return isFile;
    }
    
    //children are null if not created
    public DirectoryNode getLeft(){
        return left;
    }
    
    public DirectoryNode getRight(){
        return right;
    }
    
    public DirectoryNode getMiddle(){
        return middle;
    }
    
    //children are added left to right
    public void addChild(DirectoryNode node){
        //throws notADirectory + FullDirectory exceptions
        
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
