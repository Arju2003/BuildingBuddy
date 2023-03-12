package ca.uwo.csteam14;

import java.util.LinkedList;

public class Data {

    private String dataFile;
    private boolean canRead;
    private boolean canWrite;
    private LinkedList createdPOIs;

    public void lockFile() {
        canWrite = false;
    }

    public void unlockFile() {
        canWrite = true;
    }

}
