package com.keetarp.parser.parser;
/**
 * Author: Prateek Kumar
 * @keetarp2401
 */

/**
 * This com.keetarp.parser.parser requires obj's, mtl's and texture to be placed inside correct directory
 * and it can generate required vectors for indices, vertices etc for rendering
 * 
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import com.keetarp.parser.obj_parser.OBJ_parser;
import com.keetarp.parser.mtl_parser.MTL_texture;
import com.keetarp.parser.mtl_parser.Texture_Transparency;

public class Tex_Parser {
	/**
	 * Buffers
	 */
	private final ArrayList<FloatBuffer> mVertexBuffer = new ArrayList<FloatBuffer>();
	private final ArrayList<FloatBuffer> mColorBuffer = new ArrayList<FloatBuffer>();
	private final ArrayList<FloatBuffer> mNormalBuffer = new ArrayList<FloatBuffer>();
	private final ArrayList<FloatBuffer> mTextureBuffer = new ArrayList<FloatBuffer>();
	private final ArrayList<IntBuffer> mIndexBuffer = new ArrayList<IntBuffer>();
	private ArrayList<MTL_texture> mtl = null;
	private ArrayList<Texture_Transparency> tex_arr = new ArrayList<Texture_Transparency>();
	/**
	 * Arrays
	 */
	private float[] ver;
	private int[] rev;

	/**
	 * getter
	 * 
	 * @return
	 */
	public ArrayList<FloatBuffer> get_vertex_buffer() {
		return mVertexBuffer;
	}

	public ArrayList<FloatBuffer> get_color_buffer() {
		return mColorBuffer;
	}

	public ArrayList<FloatBuffer> get_normal_buffer() {
		return mNormalBuffer;
	}

	public ArrayList<FloatBuffer> get_texture_buffer() {
		return mTextureBuffer;
	}

	public ArrayList<IntBuffer> get_index_buffer() {
		return mIndexBuffer;
	}

	public ArrayList<MTL_texture> get_mtl_stuff() {
		return mtl;
	}

	public ArrayList<Texture_Transparency> get_tex_trans() {
		return tex_arr;
	}

	/**
	 * constructor
	 */

	public Tex_Parser(GL2 gl, String s, float p) {
		OBJ_parser obj = new OBJ_parser(s, p);
		ArrayList<ArrayList<Float>> vertices = obj.get_vertices();
		ArrayList<ArrayList<Float>> texture = obj.get_texture();
		ArrayList<ArrayList<Float>> normal = obj.get_normals();
		ArrayList<ArrayList<Integer>> index = obj.get_indices();
		ArrayList<ArrayList<Float>> colors = obj.get_colors();
		mtl = obj.get_material();
		for (int i = 0; i < mtl.size(); i++) {
			tex_arr.add(new Texture_Transparency(gl, mtl.get(i).get_d(), mtl
					.get(i).get_map_Ka()));
		}
		/**
		 * vertices
		 */
		for (int i = 0; i < vertices.size(); i++) {
			restore_array_vertices(vertices.get(i));
		}
		/**
		 * textures
		 */
		for (int i = 0; i < texture.size(); i++) {
			restore_array_textures(texture.get(i));
		}
		/**
		 * normals
		 */
		for (int i = 0; i < normal.size(); i++) {
			restore_array_normals(normal.get(i));
		}
		/**
		 * colors
		 */
		for (int i = 0; i < colors.size(); i++) {
			restore_array_colors(colors.get(i));
		}
		/**
		 * indices
		 */
		for (int i = 0; i < index.size(); i++) {
			restore_array_index(index.get(i));
		}
	}

	/**
	 * Function Declaration
	 * 
	 * @param p
	 */
	void restore_array_vertices(ArrayList<Float> p) {
		ver = new float[p.size()];
		for (int j = 0; j < p.size(); j++) {
			ver[j] = p.get(j);
		}
		mVertexBuffer.add(makeFloatBuffer(ver));
	}

	void restore_array_textures(ArrayList<Float> p) {
		ver = new float[p.size()];
		for (int j = 0; j < p.size(); j++) {
			ver[j] = p.get(j);
		}
		mTextureBuffer.add(makeFloatBuffer(ver));
	}

	void restore_array_normals(ArrayList<Float> p) {
		ver = new float[p.size()];
		for (int j = 0; j < p.size(); j++) {
			ver[j] = p.get(j);
		}
		mNormalBuffer.add(makeFloatBuffer(ver));
	}

	void restore_array_colors(ArrayList<Float> p) {
		ver = new float[p.size()];
		for (int j = 0; j < p.size(); j++) {
			ver[j] = p.get(j);
			// System.out.println(ver[j]);
		}
		mColorBuffer.add(makeFloatBuffer(ver));
	}

	void restore_array_index(ArrayList<Integer> p) {
		rev = new int[p.size()];
		for (int j = 0; j < p.size(); j++) {
			rev[j] = p.get(j);
		}
		mIndexBuffer.add(makeIntBuffer(rev));
	}

	/**
	 * Making Float Buffers
	 * 
	 * @param arr
	 * @return
	 */
	private FloatBuffer makeFloatBuffer(float[] arr) {
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(arr);
		fb.position(0);
		return fb;
	}

	private IntBuffer makeIntBuffer(int[] arr) {
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
		bb.order(ByteOrder.nativeOrder());
		IntBuffer ib = bb.asIntBuffer();
		ib.put(arr);
		ib.position(0);
		return ib;
	}

	public void draw(GL2 gl) {
		ArrayList<FloatBuffer> vert = this.get_vertex_buffer();
		ArrayList<FloatBuffer> norm = this.get_normal_buffer();
		ArrayList<FloatBuffer> text = this.get_texture_buffer();
		ArrayList<FloatBuffer> col = this.get_color_buffer();
		ArrayList<IntBuffer> indi = this.get_index_buffer();
		ArrayList<MTL_texture> mtl = this.get_mtl_stuff();
		ArrayList<Texture_Transparency> tex_trans = this.get_tex_trans();
		
		gl.glFrontFace(GL2.GL_CCW); // Front face in counter-clockwise
		// orientation
		gl.glEnable(GL2.GL_CULL_FACE); // Enable cull face
		gl.glCullFace(GL2.GL_BACK); // Cull the back face (don't display)

		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);

		for (int i = 0; i < vert.size(); i++) {
			if (tex_trans.get(i).get_Transparency_float() != 1) {
				gl.glEnable(GL2.GL_BLEND);
				gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
			}
			if (tex_trans.get(i).get_Texture_int() != -1) {
				gl.glEnable(GL2.GL_TEXTURE_2D);
				gl.glBindTexture(GL2.GL_TEXTURE_2D, tex_trans.get(i)
						.get_Texture_int());
				gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S,
						GL2.GL_REPEAT);
				gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T,
						GL2.GL_REPEAT);
				// gl.glTexParameterf(GL2.GL_TEXTURE_2D,
				// GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
				// gl.glTexParameterf(GL2.GL_TEXTURE_2D,
				// GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
			}
			gl.glVertexPointer(3, GL2.GL_FLOAT, 0, vert.get(i));
			gl.glColorPointer(4, GL2.GL_FLOAT, 0, col.get(i));
			gl.glNormalPointer(GL2.GL_FLOAT, 0, norm.get(i));
			gl.glTexCoordPointer(2, GL2.GL_FLOAT, 0, text.get(i));
			gl.glDrawElements(GL2.GL_TRIANGLES, indi.get(i).remaining(),
					GL2.GL_UNSIGNED_INT, indi.get(i));
			if (tex_trans.get(i).get_Texture_int() != -1) {
				gl.glDisable(GL2.GL_TEXTURE_2D);
			}
			if (tex_trans.get(i).get_Transparency_float() != -1) {
				gl.glDisable(GL2.GL_BLEND);
			}
		}

		gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);

		gl.glFlush();

	}
}
