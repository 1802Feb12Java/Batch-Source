package com.revature.generics;

public class Calculator <T extends Number>{
public T add(T a, T b){
		
		Number ret = a.doubleValue() + b.doubleValue();
		
		if(!(a instanceof Double) && !(a instanceof Float)){
			
			ret = Math.floor(ret.doubleValue());
			ret = Math.round(ret.doubleValue());
			
		}
		
		return (T) ret;
		
	}
	
	public T subtract(T a, T b){
		
		Number ret = a.doubleValue() - b.doubleValue();
		
		if(!(a instanceof Double) && !(a instanceof Float)){
			
			ret = Math.floor(ret.doubleValue());
			ret = Math.round(ret.doubleValue());
			
		}
		
		return (T) ret;
		
	}
}
