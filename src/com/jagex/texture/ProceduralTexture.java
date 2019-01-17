package com.jagex.texture;

import com.jagex.ByteBuffer;
import com.jagex.GameClient;
import com.jagex.OnDemandFetcher;
import com.jagex.texture.impl.ARGBTexture;
import com.jagex.texture.impl.AlphaPalettedTexture;
import com.jagex.texture.impl.PalettedTexture;
import com.jagex.texture.impl.RGBTexture;

public class ProceduralTexture {
	public ProceduralTexture(int width, int height) {
		this.width = width;
		this.height = height;
		opaque = true;
	}

	public int getPixel(int i) {
		return 0xffffffff;
	}

	public static ProceduralTexture get(int id) {
		if (id < 0 || id >= textures.length)
			return null;

		if (loaded[id])
			return textures[id];
		resourceManager.requestFileData(GameClient.TEXTURE_IDX - 1, id);
		return null;
	}

	public static void init(int count, OnDemandFetcher updateManager_) {
		textures = new ProceduralTexture[count];
		loaded = new boolean[count];
		// Rasterizer.initTextures(count);
		resourceManager = updateManager_;
	}

	public static void load(int id, byte[] data) {
		loaded[id] = true;
		if (data != null && data.length >= 5) {
			ByteBuffer buffer = new ByteBuffer(data);
			int type = buffer.readUnsignedByte();
			int width = buffer.readUnsignedWord();
			int height = buffer.readUnsignedWord();
			if (type == 0)
				textures[id] = new PalettedTexture(width, height, buffer, false);
			else if (type == 1)
				textures[id] = new RGBTexture(width, height, buffer);
			else if (type == 2)
				textures[id] = new AlphaPalettedTexture(width, height, buffer);
			else if (type == 3)
				textures[id] = new ARGBTexture(width, height, buffer);
		}
	}

	@Override
	public String toString() {
		return width + " X " + height + "	" + (opaque ? "+opaque" : "-opaque") + "	"
				+ (hasAlpha ? "+alpha" : "-alpha");
	}

	public static void nullLoader() {
		loaded = null;
		textures = null;
		resourceManager = null;
	}

	public static int getTotal() {
		return textures.length;
	}

	public boolean opaque;
	public boolean hasAlpha;
	public final int width;
	public final int height;
	private static boolean[] loaded;
	public static ProceduralTexture[] textures;
	private static OnDemandFetcher resourceManager;
	public static ProceduralTexture NULL_TEXTURE = new ProceduralTexture(64, 64);
	public static GameClient clientInstance;
}
