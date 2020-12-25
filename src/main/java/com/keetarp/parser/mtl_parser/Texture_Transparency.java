package com.keetarp.parser.mtl_parser;

import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.TextureIO;

public class Texture_Transparency {
	private int tex;
	private float trans;

	public Texture_Transparency(int tx, float tr) {
		this.tex = tx;
		this.trans = tr;
	}

	public Texture_Transparency(GL2 gl, float tx, String s) {
		if (!s.equals("")) {
			try {
				tex = TextureIO.newTexture(new File(s), true).getTextureObject(
						gl);
			} catch (GLException | IOException e) {
				e.printStackTrace();
				tex = -1;
			}
		} else {
			tex = -1;
		}
		trans = tx;
	}

	public int get_Texture_int() {
		return tex;
	}

	public float get_Transparency_float() {
		return trans;
	}
}
