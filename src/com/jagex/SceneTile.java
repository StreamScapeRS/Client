package com.jagex;

public final class SceneTile extends Node {

	int tileZ;

	final int tileX;
	final int tileY;
	final int plane;
	public SimpleTile plainTile;
	public ShapedTile shapedTile;
	public WallObject wallObject;
	public WallDecoration wallDecoration;
	public GroundDecoration groundDecoration;
	public GroundItem groundItem;
	int entityCount;
	public final InteractableObject[] interactableObjects;
	final int[] anIntArray1319;
	int anInt1320;
	int logicHeight;
	boolean aBoolean1322;
	boolean aBoolean1323;
	boolean aBoolean1324;
	int anInt1325;
	int anInt1326;
	int anInt1327;
	int anInt1328;
	public SceneTile tileBelowThisTile;

	public SceneTile(int i, int j, int k) {
		interactableObjects = new InteractableObject[5];
		anIntArray1319 = new int[5];
		plane = tileZ = i;
		tileX = j;
		tileY = k;
	}
}
