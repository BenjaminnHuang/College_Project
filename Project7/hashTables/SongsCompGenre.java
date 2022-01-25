package hashTables;

import cs1c.SongEntry;

import javax.naming.spi.ObjectFactoryBuilder;
import java.util.ArrayList;

/**
 * Class that overrides the compareTo and equals....methods, so that
 * it allow us to compare with the genres.
 * */
class SongsCompGenre
        implements Comparable<String> {

    private String genre;
    private ArrayList<SongEntry> songs = new ArrayList<>();

    /**
     * Constructor that sets the genre
     * @param song
     * */
    public SongsCompGenre(SongEntry song) {
        this.genre = song.getGenre();
    }

    /**
     * Override the toString that return the song's toString()
     * @return the song's information
     * */
    public String toString() {
        return songs.toString();
    }

    // let equals() preserve the equals() provided by embedded data
    public boolean equals(Object genre) {
        return genre.equals(this.genre);
    }

    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * Override the compareTo which takes genres for comparing.
     * @param genre
     * @return 0 if the keys are the same.
     * */
    public int compareTo(String genre) {
        return genre.compareTo(this.getName());
    }

    /**
     * Adds songs to the ArrayList
     * @param e
     * */
    public void addSong(SongEntry e) {
        songs.add(e);
    }

    /**
     * Get the genre's name
     * @return genre
     * */
    public String getName() {
        return genre;
    }

    /**
     * Get the ArrayList with bunch of songs in it
     * @return songs ArrayList
     * */
    public ArrayList<SongEntry> getData(){
        return songs;
    }

}
