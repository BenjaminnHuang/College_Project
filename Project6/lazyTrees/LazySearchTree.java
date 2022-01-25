package lazyTrees;

import javax.net.ssl.SSLContext;
import java.util.*;


/**
 * A Soft-Deletion tree data structure. It first of all create a root, and compare the new input
 * with the root. If it's bigger then it goes to the right of the tree, if it's smaller
 * goes to the left. When we remove a node, instead of hard remove it and reconstruct the tree,
 * we just mark the node as deleted. So later on we can go through the tree by soft and hard
 * traverses.*/
public class LazySearchTree<E extends Comparable<? super E>>
        implements Cloneable {
    protected int mSizeHard;
    protected int mSize;
    protected LazySTNode mRoot;

    /**
     * Default-Constructor, clear the tree.
     */
    public LazySearchTree() {
        clear();
    }

    /**
     * check if the tree is empty.
     * @return if the size is 0.
     * */
    public boolean empty() {
        return (mSize == 0);
    }

    /**
     * @return the size of the tree(not including the "deleted" nodes)
     * */
    public int size() {
        return mSize;
    }

    /**
     * @return the size of the tree(including "every" nodes)
     * */
    public int sizeHard() {
        return mSizeHard;
    }

    /**
     * clear the tree by setting the size and root.
     */
    public void clear() {
        mSizeHard = 0;
        mSize = 0;
        mRoot = null;
    }

    /**
     * @return the height of the tree.
     * */
    public int showHeight() {
        return findHeight(mRoot, -1);
    }

    /**
     * public method that calls the protected findMin()
     * Throws an exception if the tree is empty
     * */
    public E findMin() {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMin(mRoot).data;
    }

    /**
     * public method that calls the protected findMax()
     * Throws an exception if the tree is empty
     * */
    public E findMax() {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMax(mRoot).data;
    }

    /**
     * public method that calls the protected find()
     * throw an exception if the tree is empty
     * @param x
     * */
    public E find(E x) {
        LazySTNode resultNode;
        resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    /**
     * see if the input item is in the tree.
     * @param x
     * @return find it or not(boolean)
     * */
    public boolean contains(E x) {
        return find(mRoot, x) != null;
    }


    /**
     * public method that calls the protected insert()
     * @param x
     * @return true if we insert the item successfully
     * */
    public boolean insert(E x) {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }


    /**
     * public method that calls the protected remove()
     * @param x
     * @return true if we insert the item successfully
     * */
    public boolean remove(E x) {
        int OldSize = mSize;
        mRoot = remove(mRoot, x);
        return OldSize != mSize;
    }

    /**
     * Calls the protected collectGarbage() to do the recursive.
     * @return true if the method calls successfully.
     */
    public boolean collectGarbage()
    {
        int OldSize = mSizeHard;
        mRoot = collectGarbage(mRoot);
        return OldSize != mSizeHard;
    }


    /**
     * public method that calls the protected traverseHard()
     * @param func
     * */
    public <F extends Traverser<? super E>>
    void traverseHard(F func) {
        traverseHard(func, mRoot);
    }

    /**
     * public method that calls the protected traverseSoft()
     * @param func
     */
    public <F extends Traverser<? super E>>
    void traverseSoft(F func) {
        traverseSoft(func, mRoot);
    }

    /**
     * Prints out the nodes that have not being marked "deleted"
     * @param func
     * @param treeNode
     */
    protected <F extends Traverser<? super E>>
    void traverseSoft(F func, LazySTNode treeNode) {
        //check if we are done with the tree
        if (treeNode == null)
            return;

        traverseSoft(func, treeNode.lftChild);
        //if the nodes is marked "deleted", not print it out
        if (!treeNode.deleted) {
            func.visit(treeNode.data);
        }
        traverseSoft(func, treeNode.rtChild);
    }

    /**
     * Clone the tree's attributes
     * @return the cloned tree
     */
    public Object clone() throws CloneNotSupportedException {
        LazySearchTree<E> newObject = (LazySearchTree<E>) super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;
        newObject.mSizeHard = mSizeHard;

        return newObject;
    }

    // private helper methods ----------------------------------------
    /**
     * Hard deleted the nodes that has been marked deleted previously.
     * @param root
     * @return the root
     */
    protected LazySTNode collectGarbage(LazySTNode root){
        if(root == null)
        return null;

        if(root.rtChild != null)
            root.rtChild =  collectGarbage(root.rtChild);
        if(root.lftChild != null)
            root.lftChild =  collectGarbage(root.lftChild);
        if(root.deleted)
            root = removeHard(root, root.data);

        return root;
    }

    /**
     * find the smallest node in the tree(ignore the deleted nodes)
     * @param root
     * @return the smallest node(not including deleted nodes)
     */
    protected LazySTNode findMin(LazySTNode root) {
        if (root == null)
            return null;
        LazySTNode temp = findMin(root.lftChild);
        if (temp != null)
            return temp;
        if (root.deleted)
            return findMin(root.rtChild);
        else
            return root;
    }

    /**
     * find the smallest node in the tree
     * @param root
     * @return the smallest node
     */
    protected LazySTNode findMinHard(LazySTNode root){
        if (root == null)
            return null;
        if (root.lftChild == null)
            return root;
        return findMinHard(root.lftChild);
    }

    /**
     * find the biggest node in the tree(ignore the deleted nodes)
     * @param root
     * @return the biggest node(not including deleted nodes)
     */
    protected LazySTNode findMax(LazySTNode root) {

        if (root == null)
            return null;
        LazySTNode temp = findMax(root.rtChild);
        if (temp != null)
            return temp;
        if (root.deleted)
            return findMax(root.lftChild);
        else
            return root;
    }

    /**
     * find the biggest node in the tree
     * @param root
     * @return the biggest node
     */
    protected  LazySTNode findMaxHard(LazySTNode root){
        if (root == null)
            return null;
        if (root.rtChild == null)
            return root;
        return findMaxHard(root.rtChild);
    }

    /**
     * Inserts the item into the tree. If the node is already there
     * (just being marked as deleted), then change the flag from true to false.
     * If the node is not in the tree, then insert a new one at the bottom of
     * right side or left side, and increment the mSize.
     * @param root
     * @param x
     * @return the inserted node
     */
    protected LazySTNode insert(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null) {
            mSize++;
            mSizeHard++;
            return new LazySTNode(x, null, null, false);
        }

        compareResult = x.compareTo(root.data);

        if (compareResult < 0)
            root.lftChild = insert(root.lftChild, x);
        else if (compareResult > 0)
            root.rtChild = insert(root.rtChild, x);
        //set the flag to false if the node is already in the tree
        else if (root.deleted) {
            mSize++;
            root.deleted = false;
        }
        return root;
    }

    /**
     * Removes the item from the tree. Instead of hard deleting the node,
     * we simply set the flag to true to represent the node has been deleted.
     * and decrement the mSize.
     * @param root
     * @param x
     * @return the removed node
     */
    protected LazySTNode remove(LazySTNode root, E x) {

        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            root.lftChild = remove(root.lftChild, x);
        else if ( compareResult > 0 )
            root.rtChild = remove(root.rtChild, x);
            // found the node
        else{
            root.deleted = true;
            mSize--;
        }
        return root;
    }

    /**
     * Hard remove the target nodes and reconstruct the tree.
     * Resets the deletion flag to false again.
     * @param root
     * @param  x
     * @return root
     */
    protected LazySTNode removeHard( LazySTNode root, E x  )
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            root.lftChild = removeHard(root.lftChild, x);
        else if ( compareResult > 0 )
            root.rtChild = removeHard(root.rtChild, x);

            // found the node
        else if (root.lftChild != null && root.rtChild != null)
        {
            root.data = findMinHard(root.rtChild).data;
            root.rtChild = removeHard(root.rtChild, root.data);
            root.deleted = false;
        }
        else
        {
            root =
                    (root.lftChild != null)? root.lftChild : root.rtChild;
            mSizeHard--;
        }
        return root;
    }

    /**
     * Prints out "every" nodes in the tree(even though they
     * are marked as "deleted")
     * @param func
     * @param treeNode
     */
    protected <F extends Traverser<? super E>>
    void traverseHard(F func, LazySTNode treeNode) {
        if (treeNode == null)
            return;

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rtChild);
    }

    /**
     * Finds one item in the tree. If there is no such item, return null.
     * If we find the node and it is not marked deleted, return it!
     * @param root
     * @param x
     * @return the node we find or null when not found
     */
    protected LazySTNode find(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            return find(root.lftChild, x);
        if (compareResult > 0)
            return find(root.rtChild, x);
        if (!root.deleted)
            return root;

        return null;   // found
    }

    /**
     * clone the tree.
     * @param root
     * @return the cloned-tree
     */
    protected LazySTNode cloneSubtree(LazySTNode root) {
        LazySTNode newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new LazySTNode
                (
                        root.data,
                        cloneSubtree(root.lftChild),
                        cloneSubtree(root.rtChild),
                        root.deleted
                );
        return newNode;
    }

    /**
     * Count the height of the tree
     * @param treeNode
     * @param height
     * @return the height of the tree
     */
    protected int findHeight(LazySTNode treeNode, int height) {
        int leftHeight, rightHeight;
        if (treeNode == null)
            return height;
        height++;
        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rtChild, height);
        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }

    //inner class
    /**
     * Createe the node for the tree, the node has leftChild,
     * rightChild, data, and its deletion flag.
     */
    private class LazySTNode {
        // use public access so the tree or other classes can access members
        public boolean deleted;
        public LazySTNode lftChild, rtChild;
        public E data;
        public LazySTNode myRoot;  // needed to test for certain error

        /**
         * Constructor that sets the nodes' attributes
         * @param d data
         * @param deleted deletion flag
         * @param lft left child
         * @param rt right child
         */
        public LazySTNode(E d, LazySTNode lft, LazySTNode rt, boolean deleted) {
            lftChild = lft;
            rtChild = rt;
            data = d;
            this.deleted = deleted;
        }

        /**
         * Default-constructor that sets the nodes' attributes to default
         */
        public LazySTNode() {
            this(null, null, null, false);
        }
    }


}