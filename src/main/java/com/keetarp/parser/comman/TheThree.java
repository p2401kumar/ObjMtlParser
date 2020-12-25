package com.keetarp.parser.comman;

public class TheThree {
	float r;
	float g;
	float b;
	public TheThree(float r,float g,float b){
		this.r = r;
		this.g = g;
		this.b = b;
		
 	}
	public TheThree(float[] x){
		this.r = x[0];
		this.g = x[1];
		this.b = x[2];
	}
	public TheThree(String[] split) {
		this.r = Float.parseFloat(split[0]);
		this.g = Float.parseFloat(split[1]);
		this.b = Float.parseFloat(split[2]);
	}
	public float getX(){
		return this.r;
	}
	public float getY(){
		return this.g;
	}
	public float getZ(){
		return this.b;
	}	
	
}
