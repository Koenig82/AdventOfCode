package y2019.day18;

import java.util.Objects;

public class Position {
	
	final public int x;
	final public int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position move(Direction direction, int value) {
		switch (direction) {
		case down:
			return new Position(x, y+value);
		case left:
			return new Position(x-value, y);
		case right:
			return new Position(x+value, y);
		case up:
			return new Position(x, y-value);
	
		}
		throw new UnsupportedOperationException("agfag");
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		return x == other.x && y == other.y;
	}
}
