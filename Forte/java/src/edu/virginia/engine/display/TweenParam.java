package edu.virginia.engine.display;

public class TweenParam {
	
	TweenableParams param;
	double startVal;
	double endVal;
	double tweenTime;
	
	public TweenParam(TweenableParams paramToTween, double startVal, double endVal, double time) {
		this.param = paramToTween;
		this.startVal = startVal;
		this.endVal = endVal;
		this.tweenTime = time;
	}
	
	public TweenableParams getParam() {
		return param;
	}
	
	public double getStartVal() {
		return startVal;
	}
	
	public double getEndVal() {
		return endVal;
	}
	
	public double getTweenTime() {
		return tweenTime;
	}
	
}
