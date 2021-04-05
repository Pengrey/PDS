//=================Done by=========================
//          Rodrigo Lima nMec 98475
//          Gonçalo Machado nMec 98359
//=================================================

public class word {
    
    private String name;
    private int length;
    private direction dir;
    private int starterX,starterY;
    
    public word(String name, int length, direction dir, int starterX, int starterY) {
        this.name = name;
        this.length = length;
        this.dir = dir;
        this.starterX = starterX;
        this.starterY = starterY;
    }

    public String getName(){
        return this.name;
    }

    public int getLength(){
        return this.length;
    }

    public direction getDirection(){
        return this.dir;
    }

    public int getStarterX(){
        return this.starterX;
    }

    public int getStarterY(){
        return this.starterY;
    }
}
