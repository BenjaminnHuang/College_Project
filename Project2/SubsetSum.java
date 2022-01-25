package subsetsum;

import cs1c.SongEntry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class SubsetSum {

    //Method that helps us to find the subset we want inside the collection.
    public static ArrayList<Double> findSubset(ArrayList<Double> shoppingList, double budget) {


        ArrayList<Double> targetSubset = new ArrayList<Double>(); //what we want.
        ArrayList<ArrayList<Double>> collection = new ArrayList<ArrayList<Double>>(); // whole collection
        ArrayList<Double> empty = new ArrayList<Double>();
        //push a empty subset to collection.
        collection.add(empty);
        //"current" biggest subset.
        ArrayList<Double> biggestSubset = new ArrayList<Double>();

        //Get the subsets from items and push to collection if the sum is <= budget.
        for (int i = 0; i < shoppingList.size(); i++) {
            int size = collection.size();

            for (int k = 0; k < size; k++) {

                if (sumHelper(collection.get(k)) + shoppingList.get(i) <= budget) {
                    ArrayList<Double> newSubset = (ArrayList<Double>) collection.get(k).clone();
                    newSubset.add(shoppingList.get(i));
                    collection.add(newSubset);
                }
                if (sumHelper(collection.get(k)) + shoppingList.get(i) == budget) {
                    targetSubset = collection.get(collection.size() - 1);
                    return targetSubset;
                }
            }
        }

        //clone the collection I have so that I can make change of it safely.
        ArrayList<ArrayList<Double>> cloneCollection = (ArrayList<ArrayList<Double>>) collection.clone();

        //find the subset that is closest to the budget if there is no exact match.
        for (int j = 0; j < cloneCollection.size() - 1; j++) {
            if (sumHelper(cloneCollection.get(j)) > sumHelper(cloneCollection.get(j + 1))) {
                cloneCollection.set(j + 1, cloneCollection.get(j));
                targetSubset = cloneCollection.get(j + 1);
            } else {
                targetSubset = cloneCollection.get(j + 1);
            }
        }
        return targetSubset;
    }

    //private helper that get the sum of the subsets.
    private static double sumHelper(ArrayList<Double> subsets) {
        double totalSum = 0.0;
        for (int i = 0; i < subsets.size(); i++) {
            totalSum += subsets.get(i);
        }
        return totalSum;
    }

    //For Part II-----------------------------------------------------------------------------------------------

    public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double duration) {

        ArrayList<SongEntry> targetSubset = new ArrayList<SongEntry>();
        ArrayList<ArrayList<SongEntry>> collection = new ArrayList<ArrayList<SongEntry>>();
        ArrayList<SongEntry> empty = new ArrayList<SongEntry>();
        collection.add(empty);
        ArrayList<SongEntry> biggestSubset = new ArrayList<SongEntry>();


        //songList has all the prices in it.
        for (int i = 0; i < songList.size(); i++) {
            int size = collection.size();

            for (int k = 0; k < size; k++) {

                if (sumHelperForSongs(collection.get(k)) + songList.get(i).getDuration() <= duration) {
                    ArrayList<SongEntry> newSubset = (ArrayList<SongEntry>) collection.get(k).clone();
                    newSubset.add(songList.get(i));
                    collection.add(newSubset);
                }
                if (sumHelperForSongs(collection.get(k)) + songList.get(i).getDuration() == duration) {
                    targetSubset = collection.get(collection.size() - 1);
                    return targetSubset;
                }
            }
        }

        ArrayList<ArrayList<SongEntry>> cloneCollection = (ArrayList<ArrayList<SongEntry>>) collection.clone();

        for (int j = 0; j < cloneCollection.size() - 1; j++) {
            if (sumHelperForSongs(cloneCollection.get(j)) > sumHelperForSongs(cloneCollection.get(j + 1))) {
                cloneCollection.set(j + 1, cloneCollection.get(j));
                targetSubset = cloneCollection.get(j + 1);
            } else {
                targetSubset = cloneCollection.get(j + 1);
            }
        }
        return targetSubset;
    }

    private static double sumHelperForSongs(ArrayList<SongEntry> subsets) {
        double totalSum = 0.0;
        for (int i = 0; i < subsets.size(); i++) {
            totalSum += subsets.get(i).getDuration();
        }
        return totalSum;
    }
}
