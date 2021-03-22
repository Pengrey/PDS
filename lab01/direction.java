import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum direction {
    Up(-1,0),
    UpRight(-1,1),
    Right(0,1),
    DownRight(1,1),
    Down(1,0),
    DownLeft(1,-1),
    Left(0,-1),
    UpLeft(-1,-1);

    int x;
    int y;
    private static final List<direction> dirValues = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = dirValues.size();
    private static final Random random = new Random();

    direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static direction randomDirection(){
        return dirValues.get(random.nextInt(size));
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
