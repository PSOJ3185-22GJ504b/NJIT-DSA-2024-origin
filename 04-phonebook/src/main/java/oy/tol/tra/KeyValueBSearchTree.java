package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table
    // implementation

    private TreeNode<K, V> root;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public Type getType() {
        return Type.NONE;
    }

    @Override
    public int size() {
        // TODO: Implement this
        return count;
    }

    /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something
     * about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter
     * value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function
     * is good or bad (too much collisions against data size).
     */
    @Override
    public String getStatus() {
        String toReturn = "Tree has max depth of " + maxTreeDepth + ".\n";
        toReturn += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        toReturn += "Min path height to bottom: " + visitor.minHeight + "\n";
        toReturn += "Max path height to bottom: " + visitor.maxHeight + "\n";
        toReturn += "Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n";
        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        // TODO: Implement this
        // Remember null check.
        // If root is null, should go there.
        
            // update the root node. But it may have children
            // so do not just replace it with this new node but set
            // the keys and values for the already existing root.   
                if (key == null || value == null) {  
                    throw new IllegalArgumentException("Key and value cannot be null!");  
                }  
                TreeNode<K, V> newNode = new TreeNode<>(key, value);    
                if (root == null) {  
                    root = newNode;  
                    count++;  
                    return true;   
                } else {    
                    if (insertNode(root, newNode)) {  
                        count++;   
                        return true;  
                    } else {  
                        return false;   
                    }  
                }  
            }  
              
    private boolean insertNode(TreeNode<K, V> presentNode, TreeNode<K, V> newNode) {
        int cmp = newNode.keyValue.getKey().compareTo(presentNode.keyValue.getKey());
        if (cmp < 0) {
            if (presentNode.left == null) {
                presentNode.left = newNode;
                return true;
            } else {
                return insertNode(presentNode.left, newNode);
            }
        } else if (cmp > 0) {
            if (presentNode.right == null) {
                presentNode.right = newNode;
                return true;
            } else {
                return insertNode(presentNode.right, newNode);
            }
        } else {
            presentNode.keyValue.setValue(newNode.keyValue.getValue());
            return false;
        }
    }
        

    @Override
    public V find(K key) throws IllegalArgumentException {
        // TODO: Implement this. //Think about this
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null!");

        TreeNode<K, V> currentNode = root;

        while (currentNode != null) {
            int a = key.compareTo(currentNode.keyValue.getKey());

            if (a < 0) {
                currentNode = currentNode.left;
            } else if (a > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode.keyValue.getValue();
            }
        }
        return null;
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // Nothing to do here. Trees need no capacity.
    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
        root.accept(visitor);
        Pair<K, V>[] sorted = visitor.getArray();
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @Override
    public void compress() throws OutOfMemoryError {
        // Nothing to do here, since BST does not use extra space like array based
        // structures.
    }

}
