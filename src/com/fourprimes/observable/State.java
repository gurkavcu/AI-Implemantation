package com.fourprimes.observable;

public class State {	
	
	private State parent;
	
	private Long  cost;

	private Object data;
		
	public State() {		
	}
	
	public State(Object data) {
		this.data = data;
		this.parent = null;
		this.cost = 0l;
	}
	
	public State(Object data , Long cost) {
		this.data = data;
		this.parent = null;
		this.cost = cost;
	}
	
	public State(Object data , State parent , Long cost ) {
		this.data = data;
		this.parent = parent;
		this.cost = cost;
	}
	
	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}	
	
	
}
