package y2019.day18;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
	
	final public int x;
	final public int y;
	
	Map<Position,Integer> distanceTable;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		distanceTable = new HashMap<>();
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
	
	public void addDistance() {
		
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
