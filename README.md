# BashTerminalSimulation
A Java program simulating a Bash shell, using a tree-based data structure. Originally designed with a ternary tree, it has since been updated to be an n-ary tree, or a tree that supports any conceivable number of nodes on each level after the root.

# Available functions:
pwd (display the present working directory)

ls (list names of child directory/folders of current pwd)

ls -R (prints entire tree in pre-order traversal)

cd {dir} (change directory to given directory, and the command supports relative and absolute paths)

Example: if the cursor is pointed at the root, and there is a subdirectory dev, both of these commands (without quotes) would move the cursor to the dev directory: "cd dev" and "cd root/dev"

cd / (change cursor to point at the root of the tree)

cd .. (Moves the cursor up to its parent directory, and has no effect at root)

mkdir {name} (make a directory with some name as a child of the cursor node)

touch {name} (make a file with some name as a child of the cursor node)

find {name} (searches tree for node of same name, and prints its path)

mv {source} {destination} (moves a file or directory from one absolute path to another)

Example: mv root/dev/filea.txt root/bin

exit (terminates program)

One thing to note is that this simulation does not support having children nodes with the same names in the same directories, and this affects the mv, mkdir, and touch commands. Additionally, find will only find the first instance of a file or directory found in preorder traversal.
