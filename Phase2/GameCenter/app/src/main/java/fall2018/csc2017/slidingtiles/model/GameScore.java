package fall2018.csc2017.slidingtiles.model;

import java.io.Serializable;
import java.util.Map;

import fall2018.csc2017.slidingtiles.controller.StorageIndexer;

/**
 * abstract class for defining the score model
 */
public abstract class GameScore implements Serializable {
    public abstract Map<String, int[]> data(); // return the raw data
    protected StorageIndexer indexer = new StorageIndexer(); // the indexer to help identify games
}
