package com.fourprimes.slidingpuzzle;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.fourprimes.observable.BreadthDepthSearch;
import com.fourprimes.observable.PathResult;
import com.fourprimes.slidingpuzzle.MatrixOperations.Moves;

public class SlidingPuzzleAI extends BreadthDepthSearch<SlidingPuzzlePath> {

	public static int[][] target() { return new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } }; };
	
	public int[][] initialData ;

	public SlidingPuzzleAI() {	

	      frontier = new PriorityQueue<SlidingPuzzlePath>(100,new SlidingPuzzleComparator());
	      explored = new HashSet<SlidingPuzzlePath>();
	}
	
	@Override
	public boolean isGoalReached(SlidingPuzzlePath path) {
		
		HashMap<String,Object> map = (HashMap<String, Object>) path.getState().getData();
		int[][] matrix = (int[][]) map.get("MATRIX");		
		return MatrixOperations.compare(matrix,target());
	}

	@Override
	public void setInitials() {
		
		SlidingPuzzlePath path = new SlidingPuzzlePath();
		HashMap<String,Object> map = new HashMap<String, Object>();
		initialData = MatrixOperations.random(100);		
		map.put("MATRIX", initialData );
		map.put("MOVES",  new LinkedList<SlidingPuzzlePath>());
		map.put("HFUNCTION",0);
		path.addState(map);		
		frontier.add(path);
		System.out.println("INITIAL MATRIX");
		System.out.println("**************");
        MatrixOperations.printMatrix(initialData);
        System.out.println("**************");
	}
	
	public static class SlidingPuzzleComparator implements Comparator<SlidingPuzzlePath> {		
	
		@Override
		public int compare(SlidingPuzzlePath o1, SlidingPuzzlePath o2) {

			HashMap<String,Object> map1 = (HashMap<String, Object>) o1.getState().getData();
			HashMap<String,Object> map2 = (HashMap<String, Object>) o2.getState().getData();
			
			int h1 = (Integer) map1.get("HFUNCTION");
			int h2 = (Integer) map2.get("HFUNCTION");
						
			return h1 -  h2;
		}
		
	}
	
	public static void main(String[] args) {

		SlidingPuzzleAI puzzle = new SlidingPuzzleAI();
		
		PathResult result = puzzle.find();
		
		System.out.println(result.type.toString());
		System.out.println("----------------");
		
		HashMap<String,Object> map = (HashMap<String, Object>) result.path.getState().getData();
		int[][] matrix = (int[][]) map.get("MATRIX");
		
		MatrixOperations.printMatrix(matrix);
		
		System.out.println("----------------");
		LinkedList<Moves> moves = (LinkedList<Moves>) map.get("MOVES");
		System.out.println(moves.size());
		System.out.println("----------------");
		
		for(Moves m : moves) {
			System.out.println(m.toString());
		}
		
	}
}
