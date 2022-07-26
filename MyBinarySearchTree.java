package hw3;

public class MyBinarySearchTree<Type extends Comparable<Type>> {

    private Node root;
    
    private int size;
    
    private boolean balancing;
    
    private int comparisons;
    
    private int rotations;
    
    public MyBinarySearchTree() {
        root = null;
        size = 0;
        balancing = false;
    }
    
    public MyBinarySearchTree(boolean isBalancing) {
        root = null;
        size = 0;
        balancing = isBalancing;
    }
    
    public void add(Type item) {
        root = add(item, root);
    }
    
    private Node add(Type item, Node subTree) {
        if (subTree == null) {
            subTree = new Node(item);
        }
        if (item.compareTo(subTree.item) > 0) {
                subTree.right = add(item, subTree.right);
        } else if (item.compareTo(subTree.item) < 0) {
                subTree.left = add(item, subTree.left);
        }
        updateStats(subTree);
        if (balancing) {
            return rebalance(subTree);
        }
        return subTree;
    }
    
    public void remove(Type item) {
        root = remove(item, root);
    }
    
    private Node remove(Type item, Node subTree) {
    	if (subTree != null) {
    		int difference = item.compareTo(subTree.item);
            if (difference == 0) {
            	if (subTree.left == null && subTree.right == null) {
            		return null;
            	} else if (subTree.left == null && subTree.right != null) {
            		subTree = subTree.right;
            	} else if (subTree.left != null && subTree.right == null) {
            		subTree = subTree.left;
            	} else {
            		Node temp = getReplacement(subTree.right);
            		subTree.item = temp.item;
            		subTree.right = remove(temp.item, subTree.right);
            	}
            } else if (difference < 0) {
            	subTree.left = remove(item, subTree.left);
            } else {
            	subTree.right = remove(item, subTree.right);
            }
    	}
        updateStats(subTree);
    	if (balancing) {
    	    updateStats(subTree);
            if (subTree != null && (subTree.balanceFactor < -1 || subTree.balanceFactor > 1)) {
              subTree = rebalance(subTree);
            }
    	}
    	return subTree;
    }
    
    private Node getReplacement(Node subTree) {
    	Node node = subTree;
    	while (node.left != null) {
    	   node = node.left;
    	}
    	return node;
    }
    
    public boolean contains(Type item) {
        return contains(item, root);
    }
    
    private boolean contains(Type item, Node subTree) {
        if (subTree == null) {
            return false;
        }
        comparisons++;
        if (item.compareTo(subTree.item) == 0) {
            return true;
        } else if (item.compareTo(subTree.item) < 0) {
            return contains(item, subTree.left); 
        } else {
            return contains(item, subTree.right);
        } 
    }
    
    public int getComparisons() {
        return comparisons;
    }
    
    public int getRotations() {
    	return rotations;
    }
    
    public int height() {
        return height(root);
    }
    
    private int height(Node subTree) {
        if (subTree == null) {
            return 0;
        }
        return subTree.height;
    }
    
    public int size() {
    	return size(root);
    }
    
    private int size(Node subTree) {
        if (subTree == null) {
            return 0;
        } else if (subTree.left == null && subTree.right == null) {
        	return 1;
        } else if (subTree.right == null) {
        	return size(subTree.left) + 1;
        } else if (subTree.left == null) {
        	return size(subTree.right) + 1;
        } else {
            return size(subTree.right) + size(subTree.left) + 1;  
        }
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    private void updateStats(Node subTree) {
        subTree.height = Math.max(height(subTree.left), height(subTree.right)) + 1; 
        if (balancing) {
            subTree.balanceFactor = height(subTree.left) - height(subTree.right); 
        }
    }
    
    private Node rotateRight(Node theNode) {
        Node subTree = theNode.left;
        theNode.left = subTree.right;
        subTree.right = theNode;
        updateStats(theNode);
        updateStats(subTree);
        rotations++;
        return subTree;
    }
    
    private Node rotateLeft(Node theNode) {
        Node subTree = theNode.right;
        theNode.right = subTree.left;
        subTree.left = theNode;
        updateStats(theNode);
        updateStats(subTree);
        rotations++;
        return subTree;
    }
    
    private Node rebalance(Node subTree) {
        if (subTree.balanceFactor < -1) {
            if (subTree.right.balanceFactor < 0) {
                return rotateLeft(subTree);
            } else if (subTree.right.balanceFactor > 0) {
                subTree.right = rotateRight(subTree.right);
                return rotateLeft(subTree);
            }
        } else if (subTree.balanceFactor > 1) {
            if (subTree.left.balanceFactor < 0) {
                subTree.left = rotateLeft(subTree.left);
                return rotateRight(subTree);
            } else if (subTree.left.balanceFactor > 0) {
                return rotateRight(subTree);
            }
        }
        return subTree;
    }
    
    public String toString() {
        return inOrder(root);
    }
    
    private String inOrder(Node subTree) {
        if (subTree == null) {
            return "";
        } else {
            return inOrder(subTree.left) + " " + subTree.item + " " + inOrder(subTree.right);
        }
    }
    
    private class Node {
        public Type item;
        
        public Node left;
        
        public Node right;
        
        public int height;
        
        public int balanceFactor;
        
        public Node(Type theItem) {
            item = theItem;
            height = 0;
        }
        
        public String toString() {
            String result = this.getClass().getSimpleName() + "( item: " + item + ", left: ";
            return result += left.item + ", right:" + right.item + ", height: " + height + " )";
        }
    }
    
}
