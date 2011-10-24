package com.fourprimes.observable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import com.fourprimes.observable.PathResult.ResultType;
import com.fourprimes.slidingpuzzle.SlidingPuzzlePath;
import com.fourprimes.slidingpuzzle.SlidingPuzzleAI.SlidingPuzzleComparator;

public abstract class BreadthDepthSearch<T extends Path> extends BaseClass<T> {
	
	public BreadthDepthSearch() {
	     frontier = new PriorityQueue<T>();
	     explored = new HashSet<T>();
	}

	public PathResult find() {

		PathResult result = null;
		setInitials();
		if (frontier.isEmpty())
			return PathResult.getFailedResult();
		while (result == null || !frontier.isEmpty()) {
			T p = getPathFromFrontier();
			explored.add(p);
			if (isGoalReached(p))
				return new PathResult(ResultType.SUCCESS, p);
			List<T> actions = (List<T>) p.getActions();
			for (T pathItem : actions) {
				addToFrontier((T) p.createNewPath(pathItem.getState().getData()));
			}
		}
		return PathResult.getFailedResult();
	}

	private T getPathFromFrontier() {
		return frontier.remove();
	}

	/**
	 *  If path is not in frontier & explored set then
	 *  add it into the frontier set
	 *  
	 *  @param path   
	 */

	private void addToFrontier(T path) {
		
		boolean fc = !frontier.contains(path);
		boolean ec = !explored.contains(path);
		if (path != null && fc && ec)
			frontier.add(path);
	}

}
