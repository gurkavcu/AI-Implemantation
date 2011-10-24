package com.fourprimes.slidingpuzzle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.fourprimes.observable.Path;
import com.fourprimes.observable.State;
import com.fourprimes.slidingpuzzle.MatrixOperations.Moves;

public class SlidingPuzzlePath extends Path {

	@Override
	public LinkedList<Path> getActions() {
		
		LinkedList<Path> resultList = new LinkedList<Path>();
		HashMap<String,Object> data = (HashMap<String, Object>) getState().getData();
		
		int[][] matrix = (int[][]) data.get("MATRIX");	
		
		ArrayList<Moves> newMoves = MatrixOperations.getAvaibleMoves(matrix);
		
		for( Moves m : newMoves) {
			
			HashMap<String,Object> newData = new HashMap<String, Object>();
			int[][] newMatrix = MatrixOperations.doMove(matrix, m);
			LinkedList<Moves> moveList  =   new LinkedList<MatrixOperations.Moves>((Collection<? extends Moves>) data.get("MOVES")); //data.get("MOVES");
			moveList.add(m);		
			
			newData.put("MATRIX", newMatrix);
			newData.put("MOVES",  moveList);
			newData.put("HFUNCTION", MatrixOperations.hFunction(newMatrix));
			
			resultList.add(createNewPath(newData));
		}
		
		return resultList;
	}

	@Override
	public Path getClone() throws CloneNotSupportedException {
		
		Path clone = new SlidingPuzzlePath();

		clone.cost = this.cost;

		List<State> stateList = this.getStateList();

		for (State state : stateList) {
			clone.addState(state, state.getCost());
		}

		return clone;
	}
	
	public boolean isEqualMoves(List<Moves> l1, List<Moves> l2) {
		if(l1.size() != l2.size())
			return false;
		String s1 = "";
		String s2 = "";
		for(Moves m1 : l1) {
			s1 += m1.toString();
		}
		for(Moves m2 : l2) {
			s2 += m2.toString();
		}		
		return s1.equals(s2);
	}
	
	@Override
	public int hashCode() {
		HashMap<String,Object> data = (HashMap<String, Object>) getState().getData();
		int[][] matrix = (int[][]) data.get("MATRIX");
		return MatrixOperations.hashCode(matrix);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if( !(obj instanceof SlidingPuzzlePath))
			return false;
		
		SlidingPuzzlePath instance = (SlidingPuzzlePath) obj;
		
		HashMap<String,Object> thisData = (HashMap<String, Object>) this.getState().getData();
		int[][] matrix1 = (int[][]) thisData.get("MATRIX");
		//LinkedList<Moves> list1 = (LinkedList<Moves>) thisData.get("MOVES");

		HashMap<String,Object> objData = (HashMap<String, Object>) instance.getState().getData();
		int[][] matrix2 = (int[][]) objData.get("MATRIX");
		//LinkedList<Moves> list2 = (LinkedList<Moves>) objData.get("MOVES");
		
		return MatrixOperations.compare(matrix1, matrix2) ;
	}

}
