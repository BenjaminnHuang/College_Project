package lazyTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that has SongEntry objects store in a BST data structure.
 * Will read the text file.
 * */
public class FoothillTunesStore {
    private LazySearchTree<SongEntry> tunes;
    PrintObject<SongEntry> printObject = new PrintObject<SongEntry>();
    public static final boolean SHOW_DETAILS = true;


    /**
     * main method here.
     * Read the file and add songs or play songs.
     * */
    public static void main(String[] args)
    {

        FoothillTunesStore songs = new FoothillTunesStore();

        //test file 1 that adds some songs and plays(soft deleted) some.
        final  String TESTFILE = "resources/PartTwoTestFile01";

        //test file 2 that add 4 songs and play(soft deleted) all.
//        final  String TESTFILE = "resources/PartTwoTestFile02";

        //test file 3 that hard-empty tree, means nothing in the tree.An empty file
//        final  String TESTFILE = "resources/PartTwoTestFile03";

        System.out.printf("Test file: %s \n", TESTFILE);

        File infile = new File(TESTFILE);

        try
        {
            Scanner input = new Scanner(infile);

            String line = "";
            int lineNum = 0;
            while (input.hasNextLine())
            {
                lineNum++;
                line = input.nextLine();
                String [] tokens = line.split(",");

                String selection = tokens[0];
                String songTitle = tokens[1];
                String temp = tokens[2];
                int songDuration = Integer.parseInt(temp);
                String songArtist = tokens[3];
                String songGenre = tokens[4];

                String message = "at line #" + lineNum + ": " + line;

                // When an song is added:
                // If the song is not in our playlist,
                // create a new entry in our playlist.
                if (selection.equals("add")) {
                    songs.addSongs(songTitle, songDuration, songArtist, songGenre);

                    // NOTE: Currently displaying the contents is disabled to reduce cluttering the output.
                    // Suggestion: To start, enable displaying the contents of the tree to help you debug.
                    if (SHOW_DETAILS) {
                        System.out.printf("Add song %s.", songTitle);
                        songs.printTree();
                    }
                }
                // When an song is played:
                // remove the songs from playlist.
                //
                // Note: playing an non-exist song, is invalid. Handle it appropriately.
                else if (selection.equals("play"))
                {
                    try
                    {
                        songs.removeSongs(songTitle,songDuration,songArtist,songGenre);

                        // NOTE: Currently displaying the contents is disabled to reduce cluttering the output.
                        // Suggestion: To start, enable displaying the contents of the tree to help you debug.
                        if (SHOW_DETAILS) {
                            System.out.printf("Remove song %s.", songTitle);
                            songs.printTree();
                        }
                    }
                    catch (java.util.NoSuchElementException ex)
                    {
                        // NOTE: Ideally we'd print to the error stream,
                        //       but to allow correct interleaving of the output
                        //       we'll use the regular output stream.
                        System.out.printf("\nWarning: Unable to fulfill request: %s \n", message);
                        System.out.printf("Warning: Song %s is not in the playlist.\n", songTitle);
                    }
                }
                else
                {
                    System.out.println("Warning: playlist selection not recognized!");
                }

                // Display the first song and the last song before checking
                // if it's time to clean up our playlist.
                if (SHOW_DETAILS)
                    songs.findFistLast();
            }
            input.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        // Display the playlist
        System.out.println("\n");
        songs.printTree();

        // flush the error stream
        System.err.flush();

        System.out.println("\nDone with FoothillTunesStore.");
    }

    /**
     * Default-constructor for creating a LazySearchTree of object SongEntry
     * */
    public FoothillTunesStore() {
        tunes = new LazySearchTree<SongEntry>();
    }


    /**
     * insert SongEntry object to BST data structure.
     * @param title
     * @param duration
     * @param artist
     * @param genre
     * */
    private void addSongs(String title, int duration, String artist, String genre) {
        SongEntry songs = new SongEntry(title, duration, artist, genre);
        tunes.insert(songs);
    }

    /**
     * Removes SongEntry object from BST data structure.
     * if the target song is not found, throw a exception.
     * @param title
     * @param duration
     * @param artist
     * @param genre
     * */
    private void removeSongs(String title, int duration, String artist, String genre) {
        SongEntry songs = new SongEntry(title, duration, artist, genre);
        boolean isFound = tunes.contains(songs);
        // check if the item exists in the inventory disregarding lazy deletion
        if (!isFound)
        {
            throw new NoSuchElementException();
        }
        else
        tunes.remove(songs);
    }

    /**
     * "Soft" traverse the tree and print out the songs.
     * */
    private void printTree() {
        System.out.println();
        System.out.print("Current Playlist: ");
        tunes.traverseSoft(printObject);
        System.out.println("\n");
    }

    /**
     * Find and print out the the First(min) song and Last(max) song
     * If there is no nodes in the BST or all nodes have been marked deleted,
     * catches the exception and prints the warning messages.
     * */
    private void findFistLast() {
        try
        {
            SongEntry min = tunes.findMin();
            System.out.println ( "First song: " + min.toString());
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: minimum element not found!");
        }

        try
        {
            SongEntry max = tunes.findMax();
            System.out.println ( "Last song: " + max.toString()+ "\n");
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: maximum element not found!");
        }
    }
}
