package hashTables;

import java.util.NoSuchElementException;

/**
 * Class that extends FHhashQP, has the find method that can locate where
 * the target elements(song we want to find) are.
 * */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>>
        extends FHhashQP<E> {

    /**
     * Find the target element in the hash table
     * Throw an exception if the element is not found
     * @param key
     * @return the data of from the hash table
     * */
    protected E find(KeyType key) {

        //get the index of where the target element is.
        int searchResult = findPosKey(key);
        if (mArray[searchResult].data != null && mArray[searchResult].state != EMPTY)
            return mArray[searchResult].data;
        else
            throw new NoSuchElementException();
    }

    /**
     * get the index of where we input the element
     * @param key
     * @return the index
     * */
    protected int myHashKey(KeyType key) {
        int hashVal;

        hashVal = key.hashCode() % mTableSize;
        if (hashVal < 0)
            hashVal += mTableSize;

        return hashVal;
    }

    /**
     * Find the position to insert the element
     * Deals with the collision with Quadratic Probing
     * @param key
     * @return the index
     */
    protected int findPosKey(KeyType key) {
        int kthOddNum = 1;
        int index = myHashKey(key);

        //Collision occurs
        while (mArray[index].state != EMPTY
                && mArray[index].data.compareTo(key) != 0) {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if (index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }

}
