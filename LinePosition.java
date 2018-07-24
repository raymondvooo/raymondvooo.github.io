public class LinePosition {
    private int lineNumber;
    private int position;

    /**
     * Line position class is linked list for getting the line and position of words.
     * @param line
     * @param position
     */
    public LinePosition(int line, int position) {
        this.lineNumber = line;
        this.position = position;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPosition() {
        return position;
    }

}
