package com.fourprimes.slidingpuzzle;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.fourprimes.observable.Path;

public class MatrixOperations {
	
	public static int[][] testData = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 0, 11 }, { 13, 14, 15, 12 } };

	enum Moves {
		UP {
			@Override
			public String toString() {
				return "UP";
			}
		},
		DOWN {
			@Override
			public String toString() {
				return "DOWN";
			}
		},
		LEFT {
			@Override
			public String toString() {
				return "LEFT";
			}
		},
		RIGHT {
			@Override
			public String toString() {
				return "RIGHT";
			}
		}
	}

	public static ArrayList<Moves> getAvaibleMoves(int[][] matrix) {
		
		int size = matrix.length;
		
		ArrayList<Moves> moveList = new ArrayList<Moves>();

		Point blank = findBlank(matrix);

		if (blank.getY() > 0)
			moveList.add(Moves.RIGHT);

		if (blank.getY() < (size - 1))
			moveList.add(Moves.LEFT);

		if (blank.getX() > 0)
			moveList.add(Moves.DOWN);

		if (blank.getX() < (size - 1))
			moveList.add(Moves.UP);

		return moveList;
	}

	public static int[][] doMove(int[][] matrix, Moves move) {
		int[][] clone = arrayClone(matrix);
		Point blank = findBlank( matrix);
		Point target = null ;		
		switch(move.ordinal()) {
			// UP
			case 0 : target = new Point(blank.x+1, blank.y);  break; 
			// DOWN
			case 1 : target = new Point(blank.x-1, blank.y) ; break;
			// LEFT
			case 2 : target = new Point(blank.x, blank.y+1) ; break;
			// RIGHT
			case 3 : target = new Point(blank.x, blank.y-1) ; break;		
		}
		
		int temp = clone[target.x][target.y];		
		clone[target.x][target.y] = 0;
		clone[blank.x][blank.y] = temp;
		
		return clone;
	}

	public static Point findBlank( int[][] matrix) {
		int dimension = matrix.length;
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				if (matrix[i][j] == 0)
					return new Point(i, j);

		return null;
	}
	
	public static Point findNumber( int[][] matrix , int number) {
		int dimension = matrix.length;
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				if (matrix[i][j] == number)
					return new Point(i, j);

		return null;
	}
	
	public static boolean compare(int[][] source , int[][] target ) {
		
		int dimension = source.length;
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				if (source[i][j] != target[i][j])
					return false;
		return true;
	}
	
	public static int numberOfMisplacedBlocks(int[][] source)  {
		int number = 0;
		int dimension = source.length;
		int[][] target = SlidingPuzzleAI.target();
		
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				if (source[i][j] != target[i][j])
					number++;
		
		return number;
	}
	
	public static int numberOfMovesToBecomeTarget(int[][] source)  {
		int number = 0;
		int dimension = source.length;
		int[][] target = SlidingPuzzleAI.target();
		
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				if (source[i][j] != target[i][j])
					{
					   Point p = findNumber(source, target[i][j]);					   
					   number += (Math.abs(p.x-i)+Math.abs(p.y-j));
					}
		
		return number;
	}
	
	public static int hFunction(int[][] source) {
		return Math.max(numberOfMisplacedBlocks(source),numberOfMovesToBecomeTarget(source));
		//return numberOfMovesToBecomeTarget(source);
	}
	
	public static int[][] random(int moveSize) {
		
		int[][] target = SlidingPuzzleAI.target();
		Random random=new Random();
		for(int i = 0; i<moveSize; i++) {
			
			ArrayList<Moves> moveList = getAvaibleMoves(target);			
			target=doMove(target, moveList.get(random.nextInt(moveList.size())));
			
		}		
		return target;
	}
	
	public static void printMatrix(int[][] matrix) {
		
		int dimension = matrix.length;	
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("");
		}
		
	}
	
//	public static int hashCode(int[][] matrix) {
//		int dimension = matrix.length;
//		String out = "";
//		int temp = 1;
//		int total = 0;
//		for (int i = 0; i < dimension; i++) { 
//			for (int j = 0; j < dimension; j++) {
//				out += ""+matrix[i][j];
//			}
//			temp *= Integer.parseInt(out);
//			if(i==1)
//				total+=temp;
//			out = "";
//		}
//		
//		return total+temp;
//	}
	
	public static int hashCode(int[][] matrix) {
		int dimension = matrix.length;
		String out = "";		
		for (int i = 0; i < dimension; i++) { 
			for (int j = 0; j < dimension; j++) {
				out += ""+matrix[i][j];
			}			
		}		
		return out.hashCode();
	}
	
	public static int[][] arrayClone(int[][] source)
	{
		int size = source.length;		
		int[][] destination = new int[size][size];
		for (int a=0;a<size;a++)
		{
			System.arraycopy(source[a],0,destination[a],0,source[a].length);
		}
		return destination;
	}

}
