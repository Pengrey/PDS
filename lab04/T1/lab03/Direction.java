/**
 * @author Raquel Ferreira
 * @author Sophie Pousinho
 **/

public enum Direction {
    // (column, row)
    UP(0, -1),
    UPRIGHT(1, -1),
    RIGHT(1, 0),
    DOWNRIGHT(1, 1),
    DOWN(0, 1),
    DOWNLEFT(-1, 1),
    LEFT(-1, 0),
    UPLEFT(-1, -1);

    private int valueDirection1;
    private int valueDirection2;

    Direction(int i, int i1) {
        valueDirection1 = i;
        valueDirection2 = i1;
    }

    public int getRowDirection(){
        return valueDirection2;
    }
    public int getColDirection(){
        return valueDirection1;
    }
}