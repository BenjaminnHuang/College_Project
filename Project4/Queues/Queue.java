package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly linked list of Queue(FIFO). It has two nodes, head and tail. Head points to
 * the first items that has been enqueued, and tail points to the last items of the queue.
 *
 * @author Foothill College, Hung-I, Huang.
 */
public class Queue<Type> implements Iterable<Type> {
    private String name;
    private Node head;
    private Node tail;
    private int mSize = 0;

    /**
     * Create a node for the queue. it has nodes' data and next in it.
     * (next points to the next item from the head.)
     */
    class Node {
        private Type data;
        private Node next;
    }

    /**
     * Override-method that is require when we implements Iterable.
     * */
    @Override
    public Iterator iterator() {
        return null;
    }


    /**
     * Constructor that gives a name to the queue.
     * @param name  name of the queue.
     */
    public Queue(String name) {
        this.name = name;

    }


    /**
     * Enqueue one element into the queue. Note: if the queue is empty,
     * after we enqueue the first element. Tail will equals to head.
     * Then increase the size of the queue.
     * @param inputData the element user want to put in.
     */
    public void enqueue(Type inputData) {
        Node temp = new Node();
        temp.data = inputData;

        if (inputData != null) {
            if (isEmpty()) {
                head = temp;
                tail = head;
            } else {
                tail.next = temp;
                tail = temp;
            }
            mSize++;
        }
    }


    /**
     * Remove the head from the queue. And update head with head.next.
     * Then decrease the size. If the queue is already empty, then throw
     * an Exception to the main().
     * @return the data of the item that is being removed.
     */
    public Type dequeue() {
        Node temp = new Node();

        if (!isEmpty()) {
            temp = head;
            head = head.next;
            mSize--;
        } else
            throw new NoSuchElementException();

        return temp.data;
    }


    /**
     * Has two Strings, one that prints out the whole queue.
     * Another one prints out the name of the queue.
     * @return the name of the queue and the whole queue.
     */
    public String toString() {

        String myQueue = "";
        String myName = "";
        Node temp = new Node();
        temp = head;
        while (temp != null) {
            myQueue += temp.data;
            temp = temp.next;

            if (temp != null) {
                myQueue = myQueue + "; ";
            }
        }
        myName = name + ": \n";
        myName += "[" + myQueue + "]";
        return myName;
    }


    /**
     * To see if the queue is empty.
     * @return true if it is empty, false if it is not.
     */
    public boolean isEmpty() {
        return head == null;
    }


    /**
     * Check if the queue is empty. If it's not return the head.
     * @return head if the queue is not empty, else return null.
     */
    public Type peek() {
        if (!isEmpty())
            return head.data;
        return null;
    }

    /**
     * Size of the queue.
     * @return the size of the queue.
     */
    public int size() {
        return mSize;
    }


    /**
     * Get the name of the queue.
     * @return the name.
     */
    public String getName() {
        return name;
    }

}

