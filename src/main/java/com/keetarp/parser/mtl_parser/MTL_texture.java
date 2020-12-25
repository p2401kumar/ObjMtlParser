package com.keetarp.parser.mtl_parser;

import com.keetarp.parser.comman.TheThree;

public class MTL_texture {
	private TheThree Ka;
	private TheThree Kd;
	private TheThree Ks;
	private int illium;
	private float Ns;
	private float d;
	private String map_Ka;
	public MTL_texture() {
		Ka = new TheThree(0f, 0f, 0f);
		Kd = new TheThree(0f, 0f, 0f);
		Ks = new TheThree(0f, 0f, 0f);
		illium = 1;
		Ns = 60f;
		d = 1f;
		map_Ka = "";
	}
	public MTL_texture(TheThree x1,TheThree x2,TheThree x3,float x4,String x5,int x6){
		Ka = x1;
		Kd = x2;
		Ks = x3;
		illium = x6;
		Ns = 60;
		d = x4;
		map_Ka = x5;
	}
	public MTL_texture(TheThree x1,TheThree x2,TheThree x3){
		Ka = x1;
		Kd = x2;
		Ks = x3;
		illium = 1;
		Ns = 60f;
		d = 1f;
		map_Ka = "";
	}
	public MTL_texture(TheThree x1,TheThree x2,TheThree x3,String x4){
		Ka = x1;
		Kd = x2;
		Ks = x3;
		illium = 1;
		Ns = 60f;
		d = 1f;
		map_Ka = x4;		
	}
	public TheThree get_Ka(){
		return Ka;
	}
	public TheThree get_Kd(){
		return Kd;
	}
	public TheThree get_Ks(){
		return Ks;
	}
	public int get_illium(){
		return illium;
	}
	public float get_Ns(){
		return Ns;
	}
	public float get_d(){
		return d;
	}
	public String get_map_Ka(){
		return map_Ka;
	}
}
