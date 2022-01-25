package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * Populate two hash table(Genre hash table and ID hash table)
 * */
public class TableGenerator {

    private FHhashQPwFind<Integer, SongCompInt> IdTable;
    private FHhashQPwFind<String, SongsCompGenre> genreTable;
    private SongCompInt songID;
    private SongsCompGenre songGenre;
    private ArrayList genresList;

    /**
     * Constructor that sets the array
     * */
    public TableGenerator() {
        genresList = new ArrayList();
    }

    /**
     * Populate a ID hash table
     * @param songs
     * @return the id hash table
     * */
    public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] songs) {

        IdTable = new FHhashQPwFind<>();
        //goes through every songs in the songs datasets.
        for (SongEntry currentSong : songs) {
            songID = new SongCompInt(currentSong);
            IdTable.insert(songID);
        }
        return IdTable;
    }

    /**
     * Populate a Genre hash table
     * @param songs
     * @return the Genre hash table
     * */
    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] songs) {

        genreTable = new FHhashQPwFind();

        for (SongEntry currentSong : songs) {
            //only add the same genre once in to the genresList ArrayList.
            if (!genresList.contains(currentSong.getGenre())) {
                songGenre = new SongsCompGenre(currentSong);
                genresList.add(currentSong.getGenre());

                for (SongEntry song : songs) {
                    //only add the song to the list if their genres are the same
                    if( song.getGenre().equals(currentSong.getGenre()))
                        songGenre.addSong(song);
                    genreTable.insert(songGenre);
                }
            }
        }
        return genreTable;
    }

    /**
     * Get the whole list of genreList
     * @return genreList
     * */
    public ArrayList getGenreNames() {
        return genresList;
    }

}
