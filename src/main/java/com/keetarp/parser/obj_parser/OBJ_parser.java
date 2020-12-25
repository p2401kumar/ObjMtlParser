package com.keetarp.parser.obj_parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.keetarp.parser.comman.TheThree;
import com.keetarp.parser.mtl_parser.MTL_parser;
import com.keetarp.parser.mtl_parser.MTL_texture;


public class OBJ_parser {
	/**
	 * setting params
	 */
	boolean use_default_mtl = false;	
	/**
	 * parsing parameters
	 */
	BufferedReader br;
	HashMap<String, MTL_texture> hm;
	ArrayList<ArrayList<Float>> all_vertices = new ArrayList<ArrayList<Float>>();
	ArrayList<ArrayList<Float>> all_normals = new ArrayList<ArrayList<Float>>();
	ArrayList<ArrayList<Float>> all_textures = new ArrayList<ArrayList<Float>>();
	ArrayList<ArrayList<Integer>> all_indices = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Float>> all_colors = new ArrayList<ArrayList<Float>>();
	ArrayList<MTL_texture> all_material = new ArrayList<MTL_texture>();
	
	/**
	 * default settings
	 * required
	 * @param mtl
	 */
	public void OBJ_parser_settings(boolean mtl){
		this.use_default_mtl = mtl;
	}

	public OBJ_parser(String s, float factor) {
		String line = "", tex = "";
		int i = 0;
		TheThree p_Kd = new TheThree(0f, 0f, 0f);
		/**
		 * List containing all arrays
		 */
		ArrayList<TheThree> vert = new ArrayList<TheThree>();
		ArrayList<TheThree> norm = new ArrayList<TheThree>();
		ArrayList<TheThree> text = new ArrayList<TheThree>();
		
		/**
		 * List containing working arrays
		 */
		ArrayList<Float> vert_xtra = new ArrayList<Float>();
		ArrayList<Float> norm_xtra = new ArrayList<Float>();
		ArrayList<Float> text_xtra = new ArrayList<Float>();
		ArrayList<Integer> inde_xtra = new ArrayList<Integer>();
		ArrayList<Float> col_xtra = new ArrayList<Float>();
		/**
		 * start
		 */
		hm = (new MTL_parser(s)).get_texture();
		br = get_Reader(s);
		/**
		 * Now Reading
		 */
		try {
			while ((line = br.readLine()) != null) {
				if (line.startsWith("usemtl ")) {
					/**
					 * last dump
					 */
					if (!tex.equals("")) {
						all_material.add(hm.get(tex));
						all_vertices.add(vert_xtra);
						all_normals.add(norm_xtra);
						all_textures.add(text_xtra);
						all_indices.add(inde_xtra);
						all_colors.add(col_xtra);
					}
					/**
					 * new Stuff
					 */
					i = 0;
					tex = line.substring(7);
					vert_xtra = new ArrayList<Float>();
					norm_xtra = new ArrayList<Float>();
					text_xtra = new ArrayList<Float>();
					inde_xtra = new ArrayList<Integer>();
					col_xtra = new ArrayList<Float>();
					p_Kd = hm.get(tex).get_Kd();
				} else if (line.startsWith("v ")) {
					vert.add(new TheThree(Float.parseFloat(line.substring(2)
							.split(" ")[0])/factor, Float.parseFloat(line.substring(2)
							.split(" ")[1])/factor, Float.parseFloat(line.substring(2)
							.split(" ")[2])/factor));
				} else if (line.startsWith("vt ")) {
					text.add(new TheThree(Float.parseFloat(line.substring(3)
							.split(" ")[0]), Float.parseFloat(line.substring(3)
							.split(" ")[1]), 1));
				} else if (line.startsWith("vn ")) {
					norm.add(new TheThree(line.substring(3).split(" ")));
				} else if (line.startsWith("f ")) {
					String[] c0 = line.substring(2).split(" ");
					String[] c1 = c0[0].split("/");
					String[] c2 = c0[1].split("/");
					String[] c3 = c0[2].split("/");
					//
					//
					// vert
					vert_xtra.add(vert.get(Integer.parseInt(c1[0]) - 1).getX());
					vert_xtra.add(vert.get(Integer.parseInt(c1[0]) - 1).getY());
					vert_xtra.add(vert.get(Integer.parseInt(c1[0]) - 1).getZ());
					//
					vert_xtra.add(vert.get(Integer.parseInt(c2[0]) - 1).getX());
					vert_xtra.add(vert.get(Integer.parseInt(c2[0]) - 1).getY());
					vert_xtra.add(vert.get(Integer.parseInt(c2[0]) - 1).getZ());
					//
					vert_xtra.add(vert.get(Integer.parseInt(c3[0]) - 1).getX());
					vert_xtra.add(vert.get(Integer.parseInt(c3[0]) - 1).getY());
					vert_xtra.add(vert.get(Integer.parseInt(c3[0]) - 1).getZ());
					//
					// text
					text_xtra.add(text.get(Integer.parseInt(c1[1]) - 1).getX());
					text_xtra.add(text.get(Integer.parseInt(c1[1]) - 1).getY());
					text_xtra.add(text.get(Integer.parseInt(c1[1]) - 1).getZ());
					//
					text_xtra.add(text.get(Integer.parseInt(c2[1]) - 1).getX());
					text_xtra.add(text.get(Integer.parseInt(c2[1]) - 1).getY());
					text_xtra.add(text.get(Integer.parseInt(c2[1]) - 1).getZ());
					//
					//text_xtra.add(text.get(Integer.parseInt(c3[1]) - 1).getX());
					//text_xtra.add(text.get(Integer.parseInt(c3[1]) - 1).getY());
					//text_xtra.add(text.get(Integer.parseInt(c3[1]) - 1).getZ());
					//
					// norm
					norm_xtra.add(norm.get(Integer.parseInt(c1[2]) - 1).getX());
					norm_xtra.add(norm.get(Integer.parseInt(c1[2]) - 1).getY());
					norm_xtra.add(norm.get(Integer.parseInt(c1[2]) - 1).getZ());
					//
					norm_xtra.add(norm.get(Integer.parseInt(c2[2]) - 1).getX());
					norm_xtra.add(norm.get(Integer.parseInt(c2[2]) - 1).getY());
					norm_xtra.add(norm.get(Integer.parseInt(c2[2]) - 1).getZ());
					//
					norm_xtra.add(norm.get(Integer.parseInt(c3[2]) - 1).getX());
					norm_xtra.add(norm.get(Integer.parseInt(c3[2]) - 1).getY());
					norm_xtra.add(norm.get(Integer.parseInt(c3[2]) - 1).getZ());
					//
					// indices
					inde_xtra.add(i++);
					inde_xtra.add(i++);
					inde_xtra.add(i++);
					//
					// colors
					for(int pk = 0;pk < 3; pk++){
						col_xtra.add(p_Kd.getX());
						col_xtra.add(p_Kd.getY());
						col_xtra.add(p_Kd.getZ());	
						col_xtra.add(1f);
					}
				}
			}
			if (!tex.equals("")) {
				all_material.add(hm.get(tex));
				all_vertices.add(vert_xtra);
				all_normals.add(norm_xtra);
				all_textures.add(text_xtra);
				all_indices.add(inde_xtra);
				all_colors.add(col_xtra);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedReader get_Reader(String s) {
		try {
			return new BufferedReader(new InputStreamReader(
					new FileInputStream("raw\\res\\" + s + ".obj")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error In obj reading");
			return null;
		}
	}

	public ArrayList<ArrayList<Float>> get_vertices() {
		return all_vertices;
	}

	public ArrayList<ArrayList<Float>> get_texture() {
		return all_textures;
	}

	public ArrayList<ArrayList<Float>> get_normals() {
		return all_normals;
	}

	public ArrayList<ArrayList<Integer>> get_indices() {
		return all_indices;
	}

	public ArrayList<MTL_texture> get_material() {
		return all_material;
	}
	public ArrayList<ArrayList<Float>> get_colors(){
		return all_colors;
	}
}
