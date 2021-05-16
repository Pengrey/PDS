package startypes;
import java.awt.*;

public abstract class StarType {

    private int x;
    private int y;
    private BaseStar base;

    public StarType( int x, int y, BaseStar base) {
        this.x = x;
        this.y = y;
        this.base = base;
    }

    public void draw(Graphics g) {
        base.draw(g, x, y);
    }
    
}
