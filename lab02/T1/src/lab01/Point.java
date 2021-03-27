package lab01;

public class Point {
	private int x;
	private int y;
	
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Point subtract(Point p) {
		return new Point(y - p.y, x - p.x);
	}
	
	public Point subtract(int y, int x) {
		return new Point(this.y - y, this.x - x);
	}

	@Override
	public String toString() {
		return "Point (" + y + "," + x + ")";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public Point addDir(Direction dir) {
		Point p = new Point(this.y,this.x);
		p.setX(p.getX() + dir.getPonto().getX());
		p.setY(p.getY() + dir.getPonto().getY());
		return p;
	}
	
}
