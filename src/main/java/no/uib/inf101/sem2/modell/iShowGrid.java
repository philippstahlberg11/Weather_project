package no.uib.inf101.sem2.modell;

public interface iShowGrid {

    /**
     * Makes the grid/table, with temperatures and all the values required
     * 
     * @param initialValue initial start-value at which time the info. and etc.
     *                     should show
     */
    public void showGridFirst(int initialValue);

    /**
     * Shows the next page to the grid/table, by adding the rows to the initalvalue
     * to the grid
     */
    public void showNextPage();

    /**
     * Shows the previous page to the grid/table, by subtracting the rows to the
     * initalvalue to the grid
     */
    public void showPreviousPage();
}
