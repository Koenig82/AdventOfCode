package y2019.day06;

import java.util.ArrayList;
import java.util.List;

public class CelestialBody {
	
	public List<CelestialBody> inOrbit;
	public String name;
	public boolean counted;
	
	public CelestialBody(String name) {
		inOrbit = new ArrayList<>();
		this.name = name;
		counted = false;
	}
}
