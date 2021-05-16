import java.awt.Color;
import java.util.ArrayList;

import startypes.*;

public class EnhancedDemo {
    static int CANVAS_SIZE = 1200;
    static int STARS_TO_DRAW = 1000000;
    static ArrayList<BaseStar> baseStars = new ArrayList<BaseStar>();

    public static void main(String[] args) {
        Sky sky = new Sky();

        // https://astrobackyard.com/wp-content/uploads/2020/03/types-of-stars.jpg
        char[] starTypes = {'O', 'B', 'A', 'F', 'G', 'K', 'M'};
        char type;

		Runtime runtime = Runtime.getRuntime();
		long before = runtime.totalMemory() - runtime.freeMemory();

        baseStars.add(new BaseStar(new Color(225, 250, 250), 5, "This is a long description of the O type star...."));
        baseStars.add(new BaseStar(Color.WHITE, 2, "This is a long description of the A type star...."));
        baseStars.add(new BaseStar(new Color(230, 252, 252), 5, "This is a long description of the B type star...."));
        baseStars.add(new BaseStar(new Color(255, 255, 245), 2, "This is a long description of the F type star...."));
        baseStars.add(new BaseStar(new Color(245, 250, 250), 1, "This is a long description of the G type star...."));
        baseStars.add(new BaseStar(new Color(230, 160, 10), 1, "This is a long description of the K type star...."));
        baseStars.add(new BaseStar(Color.RED, 1, "This is a long description of the M type star...."));

        for (int i = 0; i < STARS_TO_DRAW; i++) {
            type = starTypes[random(0, starTypes.length-1)];
            sky.placeStar(createStar(type));
        }
        sky.setSize(CANVAS_SIZE, CANVAS_SIZE);
        sky.setBackground(Color.BLACK);
        sky.setVisible(true);

        long after = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory: " + (after - before) / 1024 / 1024 + " MB");

    }

    private static StarType createStar(char type) {
        int x = random(0, CANVAS_SIZE);
        int y = random(0, CANVAS_SIZE);
        StarType star = null;
        switch (type) {
            case 'O': 
                star = new OStar(x, y, baseStars.get(0));
                break;
            case 'A':
                star = new AStar(x, y, baseStars.get(1));
                break;
            case 'B':
                star = new BStar(x, y, baseStars.get(2)); 
                break;
            case 'F':
                star = new FStar(x, y, baseStars.get(3));
                break;
            case 'G':
                star = new GStar(x, y, baseStars.get(4));
                break;
            case 'K':
                star = new KStar(x, y, baseStars.get(5));
                break;
            case 'M':
                star = new MStar(x, y, baseStars.get(6));
                break;
        }
        return star;
    }

	private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
