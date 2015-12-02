package fr.synapsegaming.utils;

/**
 * <b>Paginator</b> help to display a specified number of data by page
 * 
 * @author Meidi
 * 
 */
public class Paginator {

    /**
     * The start data number
     */
    private final int pageStartNumber;

    /**
     * The number of data to display per page
     */
    private final int pageDataNumber;

    /**
     * The number of the page to display
     */
    private final int pageNumber;

    public Paginator(int pageStartNumber, int pageDataNumber, int pageNumber) {
        this.pageStartNumber = pageStartNumber;
        this.pageDataNumber = pageDataNumber;
        this.pageNumber = pageNumber;
    }

    public int getPageStartNumber() {
        return pageStartNumber;
    }

    public int getPageDataNumber() {
        return pageDataNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

}
