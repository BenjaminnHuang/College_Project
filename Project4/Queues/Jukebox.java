package queues;

import cs1c.SongEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that reads the requested file and enqueue the songs into the
 * playlists we create.
 *
 * @author Foothill College Hung-I,Huang.
 */
public class Jukebox {
    private Queue<SongEntry> favoritePL = new Queue("favorites");
    private Queue<SongEntry> roadTripPL = new Queue("road trip");
    private Queue<SongEntry> loungePL = new Queue("lounge");

    /**
     * Accessor that gets the favoritePl.
     *
     * @return the favorite playlist
     */
    public Queue<SongEntry> getFavoritePL() {
        return favoritePL;
    }

    /**
     * Accessor that gets the roadTripPl.
     *
     * @return the road trip playlist.
     */
    public Queue<SongEntry> getRoadTripPL() {
        return roadTripPL;
    }

    /**
     * Accessor that gets the loungePL.
     *
     * @return the lounge playlist.
     */
    public Queue<SongEntry> getLoungePL() {
        return loungePL;
    }

    /**
     * Reads the requested file and compares the songs we have with allSongs.
     * If we find the match then, put the song into the playlist it belongs.
     * Throws an Exception if the file is not found.
     *
     * @param requestedFile the source of the text file
     * @param allSongs      the object of class SongEntry(all songs from MillionSongDataSubset class).
     */
    public void fillPlaylists(String requestedFile, SongEntry[] allSongs) {

        File file = new File(requestedFile);
        try {
            Scanner input = new Scanner(file);
            String line = "";
            while (input.hasNextLine()) {
                line = input.nextLine();
                String[] tokens = line.split(",");

                if (line.contains("favorites")) {
                    String favoriteSong = tokens[1];
                    for (int i = 0; i < allSongs.length; i++) {
                        if (allSongs[i].getTitle().equals(favoriteSong)) {
                            favoritePL.enqueue(allSongs[i]);
                            break;
                        }
                    }
                }
                if (line.contains("road trip")) {
                    String roadTrip = tokens[1];
                    for (int j = 0; j < allSongs.length; j++) {
                        if (allSongs[j].getTitle().equals(roadTrip)) {
                            roadTripPL.enqueue(allSongs[j]);
                            break;
                        }
                    }
                }
                if (line.contains("lounge")) {
                    String lounge = tokens[1];
                    for (int k = 0; k < allSongs.length; k++) {
                        if (allSongs[k].getTitle().equals(lounge)) {
                            loungePL.enqueue(allSongs[k]);
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}