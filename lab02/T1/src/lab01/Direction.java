package lab01;

public enum Direction {
	UP(new Point(-1,0)),
	DOWN(new Point(1,0)),
	LEFT(new Point(0,-1)),
	RIGHT(new Point(0,1)),
	UPLEFT(new Point(-1,-1)),
	UPRIGHT(new Point(-1,1)),
	DOWNLEFT(new Point(1,-1)),
	DOWNRIGHT(new Point(1,1));
	
	private Point ponto;
	
	Direction(Point ponto) {
		this.ponto = ponto;
	}

	public Point getPonto() {
		return ponto;
	}
}
