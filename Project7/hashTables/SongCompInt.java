package hashTables;

import cs1c.SongEntry;

/**
 * Class that overrides the compareTo and equals....methods, so that
 * it allow us to compare with the keys.
 * */
class SongCompInt
        implements Comparable<Integer>
{
    private SongEntry data;

    /**
     * Constructor that sets the data(song)
     * @param e
     */
    public SongCompInt(SongEntry e){ data = e; }

    /**
     * Override the toString that return the song's toString()
     * @return the song's information
     * */
    public String toString() { return data.toString(); }

    // we'll use compareTo() to implement our find on key
    /**
     * Override the compareTo which takes key for comparing.
     * @param key
     * @return 0 if the keys are the same.
     * */
    public int compareTo(Integer key)
    {
        return data.getID() - key;
    }

    // let equals() preserve the equals() provided by embedded data
    /**
     * Override the equals method that compare the IDs
     * @param rhs
     * @return true if IDs are the same
     * */
    public boolean equals(Object rhs)
    {
        return rhs.equals(data.getID());
    }

    /**
     * Populate the hashCode for ID
     * @return the hashCode of ID
     * */
    public int hashCode()
    {
        return data.getID();
    }
}