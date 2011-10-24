package com.fourprimes.observable;

import java.util.LinkedList;

public abstract class Path {

	public long cost;

	private State state = null;

	public abstract LinkedList<Path> getActions();

	public abstract Path getClone() throws CloneNotSupportedException;

	public Path createNewPath(Object data) {

		Path newPath;

		try {
			newPath = getClone();

			State temp = new State(data);

			if (newPath.state == null) {
				newPath.state = temp;
			} else {
				temp.setParent(state);
				newPath.state = temp;
			}

			return newPath;

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Path createNewPath(Object data, long _cost) {

		Path newPath;

		try {
			newPath = getClone();

			State temp = new State(data, _cost);

			if (newPath.state == null) {
				newPath.state = temp;
				newPath.cost = _cost;
			} else {
				temp.setParent(state);
				newPath.state = temp;
				newPath.cost += _cost;
			}

			return newPath;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}

	}

	public State addState(Object data, long _cost) {

		State temp = new State(data, _cost);

		if (state == null) {
			state = temp;
			cost = _cost;
		} else {
			temp.setParent(state);
			state = temp;
			cost += _cost;
		}

		return state;
	}

	public State addState(Object data) {

		State temp = new State(data);

		if (state == null) {
			state = temp;
		} else {
			temp.setParent(state);
			state = temp;
		}

		return state;
	}

	public LinkedList<State> getStateList() {

		LinkedList<State> actionList = new LinkedList<State>();

		State root = state;

		while (root.getParent() != null) {
			actionList.addFirst(root);
			root = root.getParent();
		}

		actionList.addFirst(root);

		root = null;

		return actionList;
	}

	public State getState() {
		return state;
	}

}
