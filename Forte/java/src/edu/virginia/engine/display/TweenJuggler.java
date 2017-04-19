package edu.virginia.engine.display;

import java.util.ArrayList;
import java.util.List;

public class TweenJuggler {

	List<Tween> tweens = new ArrayList<Tween>();
	
	public TweenJuggler() {
		
	}
	
	public void add(Tween tween) {
		tweens.add(tween);
	}
	
	public void nextFrame() {
		if (tweens != null) {
			for (Tween t : tweens) t.update();
		}
	}
	
}
