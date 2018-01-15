
package bashterminalsimulation;

/**
 *
 * @author Vincent Cramer
 */
public class DirectoryTree {
    private DirectoryNode cursor, root;
    
    public DirectoryTree(){
        root = new DirectoryNode("root");
        cursor=root;
    }
 
    public void resetCursor(){
        cursor=root;    
    }
    
    public void changeDirectory(String name){ 
        if(name.equals("/")){
            cursor=root;
            return;
        }
        
        if(cursor.getLeft()!=null && name.equals(cursor.getLeft().getName()) 
                && !cursor.getLeft().getIsFile()){
            cursor=cursor.getLeft();
        }
        else if(cursor.getMiddle()!=null && name.equals(cursor.getMiddle().getName()) 
                && !cursor.getMiddle().getIsFile()){
            cursor=cursor.getMiddle();
        }
        else if(cursor.getRight()!=null && name.equals(cursor.getRight().getName()) 
                && !cursor.getRight().getIsFile()){
            cursor=cursor.getRight();
        }
        else{
            System.out.println("Invalid directory, cursor unchanged.");
        }
        
    }
    
    public String presentWorkingDirectory(){
        if(root==null || cursor==null){
            System.out.println("No files exist.");
            return "";
        }
        return presentWorkingDirectoryHelper(root,"");
    }
    
    //pre-order traversal
    //no worst-case, because if built right cursor is always somewhere in tree
    private String presentWorkingDirectoryHelper(DirectoryNode node, String s){

        //we use space as a token to see if we've found the cursor node
        if(s.contains(" ")){
            return s;
        }
        if(node==null){
            return null;
        }
        
        if(node.getName().equals(cursor.getName())){
            return s+node.getName() + " ";
        }else{
            s+=node.getName()+"/"; 
            
            String l, m, r;
        if(node.getLeft()!=null && !node.getLeft().getIsFile()){
                l=presentWorkingDirectoryHelper(node.getLeft(), s);
                if(l.contains(" ")){
                    return l;
                }
        }
        if(node.getMiddle()!=null && !node.getMiddle().getIsFile()){
            
            m = presentWorkingDirectoryHelper(node.getMiddle(), s);
            if(m.contains(" ")){
                return m;
            }
        }
        if(node.getRight()!=null && !node.getRight().getIsFile()){
            
            r = presentWorkingDirectoryHelper(node.getRight(), s);
            if(r.contains(" ")){
                return r;
            }
            }
        }
        return "";
        
    }
    
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
    
    //pre-order traversal
    public void printDirectoryTree(){
        printDirectoryTreeHelper(root, "");
    }
    
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
    
    public void makeDirectory(String name){
        if(name.contains(" ") || name.contains("/")){
            System.out.println("Invalid directory name");
            return;
        }
        DirectoryNode newNode = new DirectoryNode(name);
        cursor.addChild(newNode);
    }
    
    public void makeFile(String name){
        if(name.contains(" ") || name.contains("/")){
            System.out.println("Invalid directory name");
            return;
        }
        DirectoryNode newNode = new DirectoryNode(name);
        cursor.addChild(newNode);
    }
    
}
