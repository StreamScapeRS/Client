package com.jagex;

import java.util.zip.CRC32;

/**
 * A class representing the Jaggrab update-server system for a 317 client.
 * 
 * This system also supports preloadable files, basically files which haven't
 * been packed into the main cache files.
 * 
 * @author Professor Oak
 */
public class Jaggrab {

	// Archive CRCs
	public static final int TITLE_CRC = 1;
	public static final int CONFIG_CRC = 2;
	public static final int INTERFACE_CRC = 3;
	public static final int MEDIA_CRC = 4;
	public static final int UPDATE_CRC = 5;
	public static final int TEXTURES_CRC = 6;
	public static final int CHAT_CRC = 7;
	public static final int SOUNDS_CRC = 8;

	// CRCs
	public static final int TOTAL_ARCHIVE_CRCS = 9;
	public static final int[] CRCs = new int[TOTAL_ARCHIVE_CRCS];

	// CRC32, used for checking if files are matching (up to date)
	public static final CRC32 CRC_32 = new CRC32();

	/**
	 * Compares crcs to check if a file's buffer is up to date or not.
	 * 
	 * @param buffer
	 * @param expectedCrc
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean compareCrc(byte[] buffer, int expectedCrc) {
		CRC_32.reset();
		CRC_32.update(buffer);
		int crc = (int) CRC_32.getValue();
		return crc == expectedCrc;
	}

	/**
	 * Checks if we have succesfully loaded all our crcs.
	 * 
	 * @return
	 */
	private static boolean crcsLoaded() {
		for (int i = 0; i < CRCs.length; i++) {
			if (CRCs[i] == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Resets the crc table
	 */
	private static void resetCrcs() {
		for (int i = 0; i < CRCs.length; i++) {
			CRCs[i] = -1;
		}
	}

	static {
		resetCrcs();
	}
}
