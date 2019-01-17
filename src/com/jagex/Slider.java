package com.jagex;

public class Slider {

	private int position = 86;

	private double value;

	private int x, y;

	private final double minValue, maxValue, length;

	private final Sprite[] images = new Sprite[2];

	public Slider(Sprite icon, Sprite background, double minimumValue, double maximumValue) {
		this.images[0] = icon;
		this.images[1] = background;
		this.minValue = this.value = minimumValue;
		this.maxValue = maximumValue;
		this.length = this.images[1].myWidth;
	}

	public void draw(int x, int y) {
		this.x = x;
		this.y = y;
		images[1].drawSprite(x, y);
		images[0].drawSprite(x + position - (int) (position / length * images[0].myWidth),
				y - images[0].myHeight / 2 + images[1].myHeight / 2);
	}

	public void handleClick(int mouseX, int mouseY, int offsetX, int offsetY, int contentType) {
		if (mouseX - offsetX >= x && mouseX - offsetX <= x + length
				&& mouseY - offsetY >= y + images[1].myHeight / 2 - images[0].myHeight / 2
				&& mouseY - offsetY <= y + images[1].myHeight / 2 + images[0].myHeight / 2) {
			position = mouseX - x - offsetX;
			if (position >= length) {
				position = (int) length;
			}
			if (position <= 0) {
				position = 0;
			}
			if (contentType == 2) {
				value = maxValue - ((mouseX - x - offsetX) / length) * (maxValue - minValue);
			} else {
				value = minValue + ((mouseX - x - offsetX) / length) * (maxValue - minValue);
			}
			if (value < minValue) {
				value = minValue;
			}
			if (value > maxValue) {
				value = maxValue;
			}
			switch (contentType) {
			case 1:
				Rasterizer.calculatePalette(minValue + maxValue - value);
				break;
			case 2:
				GameClient.handleVolumeAdjust((int) (minValue + maxValue - value));
				System.out.println("[Adjust SoundPlayer] " + (int) (minValue + maxValue - value));
				break;
			case 3:
				int vol_value = (int) (minValue + maxValue - value);
				switch (vol_value) {
				case 0:
					SoundPlayer.setVolume(vol_value);
					break;
				case 1:
					SoundPlayer.setVolume(1);
					break;
				case 2:
					SoundPlayer.setVolume(2);
					break;
				case 3:
					SoundPlayer.setVolume(3);
					break;
				case 4:
					SoundPlayer.setVolume(4);
					break;
				}
				GameClient.soundEnabled = vol_value != 4;
				// GameClient.saveSettings();
				System.out.println("[Adjust SoundPlayer] " + (int) (minValue + maxValue - value));
				break;
			default:
				System.out.println("[unhandled slider] " + "Type : " + contentType);
				break;
			}
		}

	}

	public double getPercentage() {
		return ((position / length) * 100);
	}

}