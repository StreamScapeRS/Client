package com.jagex;

public class XPGain {
	/**
	 * The skill which gained the xp
	 */
	private int skill;

	/**
	 * The XP Gained
	 */
	private int xp;
	private int y;
	private int alpha = 0;

	public XPGain(int skill, int xp) {
		this.skill = skill;
		this.xp = xp;
	}

	public void decreaseAlpha() {
		alpha -= alpha > 0 ? 30 : 0;
		alpha = alpha > 256 ? 256 : alpha;
	}

	public int getAlpha() {
		return alpha;
	}

	public int getSkill() {
		return skill;
	}

	public int getXP() {
		return xp;
	}

	public int getY() {
		return y;
	}

	public void increaseAlpha() {
		alpha += alpha < 256 ? 30 : 0;
		alpha = alpha > 256 ? 256 : alpha;
	}

	public void increaseY() {
		y++;
	}
}