package fr.synapsegaming.utils;

/**
 * <b>Page</b> stands for a link element to a dataset
 * 
 * @author Meidi
 * 
 */
public class Page {

    /**
     * The page number
     */
    private final int number;

    /**
     * The page path
     */
    private final String path;

    /**
     * True if it is the current page displayed
     */
    private final boolean active;

    public Page(int number, String path, boolean active) {
        super();
        this.number = number;
        this.path = path;
        this.active = active;
    }

    public int getNumber() {
        return number;
    }

    public String getPath() {
        return path;
    }

    public boolean isActive() {
        return active;
    }

}
