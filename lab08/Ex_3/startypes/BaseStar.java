package startypes;
import java.awt.*;

public class BaseStar {
    private Color color;
    private int size;
    protected String description;
    protected Float[] physicalProperties;

    public BaseStar(Color color, int size, String description){
        this.color = color;
        this.size = size;
        this.description = description;
        this.physicalProperties = new Float[10];
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y , size, size);
    }

    public Color getColor() {
        return color;
    }
}
