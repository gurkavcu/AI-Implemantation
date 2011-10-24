package com.fourprimes.observable;

import java.util.PriorityQueue;
import java.util.Set;

public abstract class BaseClass < T extends Path> {

	public PriorityQueue<T> frontier; 
	
	public Set<T> explored;

	public abstract boolean isGoalReached(T t);

	public abstract void setInitials();

}
