package com.fourprimes.observable;

public class PathResult {
	
	public ResultType type ;
	
	public Path path ;
	
	public enum ResultType {
		SUCCESS {
		 public String toString() {
			 return "SUCCESS";
		 } 
		},
		FAILED {
			 public String toString() {
				 return "FAILED";
			 } 
		}		
	};
	
	public PathResult(ResultType type, Path path) {
		this.type = type;
		this.path = path;
	}
	
	public boolean isSuccess() {
		return type == ResultType.SUCCESS;
	}
	
	public static PathResult getFailedResult() {
		return new PathResult(ResultType.FAILED,null);		
	}

}
