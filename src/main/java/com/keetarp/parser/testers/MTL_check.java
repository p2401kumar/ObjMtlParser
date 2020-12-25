package com.keetarp.parser.testers;

import java.util.HashMap;

import com.keetarp.parser.mtl_parser.MTL_parser;
import com.keetarp.parser.mtl_parser.MTL_texture;

public class MTL_check {
	public static void main(String[] args){
		MTL_parser mtl = new MTL_parser("sample");
		HashMap<String, MTL_texture> sm = mtl.get_texture(); 
		String[] p = ("100 100").split(" ");
		System.out.println("@"+p[0]);
		System.out.println("@"+p[1]);
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Ka().getX());
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Ka().getY());
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Ka().getZ());
		System.out.println();
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Kd().getX());
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Kd().getY());
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Kd().getZ());
		System.out.println();
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Ks().getX());
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Ks().getY());
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_Ks().getZ());
		System.out.println();
		System.out.println(sm.get("Roofing_Shingles_GAF_Estates").get_d());
		
	}
}
