package lazyTrees;

/**
 * Print the nodes in the tree.
 */
class PrintObject<E> implements Traverser<E>
{
    public void visit(E x)
    {
        System.out.print( x + " ");
    }
};
