package stacks;

import java.util.Currency;
import java.util.Iterator;

/**
 * The class that create the singly stack list with iterator.
 * Got node for pushing, popping and other methods that work
 * with stack.
 */
public class StackList<Type> implements Iterable<Type> {

    private Node top;
    private String name;
    private int size = 0;

    @Override
    //create a Iterator.
    public Iterator<Type> iterator() {
        return new StackIterator();
    }

    //create a Node that has two variable(data and next)
    public class Node {
        private Type data;
        private Node next;
    }

    //create a StackIterator
    public class StackIterator implements Iterator {

        Node currentNode = new Node();

        //Assign currentNode with top.
        public StackIterator() {
            currentNode = top;
        }

        //check if we point to the last node in the stack.
        @Override
        public boolean hasNext() {
            if (currentNode != null)
                return (currentNode.next != null);
            else
                return false;
        }

        //point to the next node of the stack.
        @Override
        public Type next() {
            Type data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }

    //Default-constructor : set size to 0, set top to null and set name to "".
    public StackList() {
        this.size = 0;
        this.top = null;
        this.name = "";
    }

    //constructor that helps us declare the stack's name.
    public StackList(String name) {
        this.name = name;
    }

    //add the elements to the stack.
    public void push(Type inputData) {
        if (inputData != null) {
            Node temp = new Node();
            temp.data = inputData;
            temp.next = top;
            top = temp;
            size++;
        }
    }

    //remove the elements from the stack.
    public Type pop() {
        Type temp;

        if (top == null)
            return null;

        temp = top.data;
        top = top.next;
        size--;
        return temp;
    }

    //Check if the stack is empty.
    public boolean isEmpty() {
        return top == null;
    }

    //Return the top of the stack.(If the stack is empty, return null)
    public Type peek() {
        if (!isEmpty())
            return top.data;
        else
            return null;
    }

    //clear the whole stack by popping it.
    public void clear() {
        while (top != null)
            pop();
    }

    //print out the whole stack.
    public String toString() {
        String stackString = "";
        String theString = "";

        Node temp = new Node();
        Node tempForCommaDeletion;
        temp = top;

        while (temp != null) {
            stackString += temp.data;
            temp = temp.next;

            if (temp != null) {
                stackString = stackString + ", ";
            }
        }
        stackString = "[" + stackString + "]";
        theString = name + " with " + size + " links:\n";
        theString += stackString;
        return theString;
    }

    //return the number of elements in the stack.
    public int size() {
        return size;
    }
}