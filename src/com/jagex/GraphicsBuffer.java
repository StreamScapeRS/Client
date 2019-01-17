package com.jagex;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public final class GraphicsBuffer {

	public final int[] raster;
	public final int width;
	public final int height;
	public final BufferedImage image;

	public GraphicsBuffer(int width, int height) {
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		raster = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		initDrawingArea();
	}

	public void drawGraphics(int y, Graphics graphics, int x) {
		graphics.drawImage(image, x, y, null);
	}

	public void initDrawingArea() {
		DrawingArea.initDrawingArea(height, width, raster);
	}
}