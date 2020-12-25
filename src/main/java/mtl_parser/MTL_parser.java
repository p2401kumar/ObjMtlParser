package mtl_parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import comman.TheThree;

public class MTL_parser {
	private HashMap<String, MTL_texture> MTL_TEXTURE;
	/**
	 * make sure the raw folder contains drawable folder with texture placed
	 * inside a folder named with 3d files name as shown
	 */
	final String IMAGE_LOCATION = "src/main/resources/drawable/";

	public MTL_parser(String s) {
		MTL_TEXTURE = new HashMap<String, MTL_texture>();

		FileInputStream fis = null;
		/**
		 * Reading mtl file into BufferReader br
		 */
		try {
			//fis = new FileInputStream("raw\\res\\" + s + ".mtl");
			fis = new FileInputStream("src/main/resources/res/" + s + ".mtl");

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			/**
			 * Reading into Hashmap
			 */
			String line = "", tex_name = "", img_map = "";
			TheThree Ka = null, Kd = null, Ks = null;
			int illium = 1;
			float d = 1;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("newmtl ")) {
					MTL_TEXTURE.put(tex_name, new MTL_texture(Ka, Kd, Ks, d,
							img_map, illium));
					tex_name = line.substring(7);
					Ka = new TheThree(0f, 0f, 0f);
					Kd = new TheThree(0f, 0f, 0f);
					Ks = new TheThree(0f, 0f, 0f);
					illium = 1;
					d = 1;
					img_map = "";
				} else if (line.startsWith("Ka ")) {
					Ka = new TheThree(line.substring(3).split(" "));
				} else if (line.startsWith("Kd ")) {
					Kd = new TheThree(line.substring(3).split(" "));
				} else if (line.startsWith("Ks ")) {
					Ks = new TheThree(line.substring(3).split(" "));
				} else if (line.startsWith("illium ")) {
					illium = Integer.parseInt(line.substring(7));
				} else if (line.startsWith("d ")) {
					d = Float.parseFloat(line.substring(2));
				} else if (line.startsWith("map_Ka ")
						|| line.startsWith("map_Kd ")
						|| line.startsWith("map_Ks ")) {
					img_map = IMAGE_LOCATION
							+ line.substring(7);
				}
			}
			MTL_TEXTURE.put(tex_name, new MTL_texture(Ka, Kd, Ks, d, img_map,
					illium));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, MTL_texture> get_texture() {
		return MTL_TEXTURE;
	}
}
