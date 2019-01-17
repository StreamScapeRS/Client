package com.jagex;

public final class VarBit {

	public static VarBit cache[];

	public static void unpackConfig(CacheArchive streamLoader) {
		Stream stream = new Stream(streamLoader.getDataForName("varbit.dat"));
		int cacheSize = stream.readUnsignedWord();
		if (cache == null)
			cache = new VarBit[cacheSize];
		for (int j = 0; j < cacheSize; j++) {
			if (cache[j] == null)
				cache[j] = new VarBit();
			cache[j].readValues(stream);
		}

		if (stream.currentOffset != stream.buffer.length)
			System.out.println("varbit load mismatch");
	}

	public int configId;

	public int leastSignificantBit;
	public int mostSignificantBit;
	boolean aBoolean651;

	private VarBit() {
	}

	private void readValues(Stream stream) {
		do {
			int j = stream.readUnsignedByte();
			if (j == 0)
				return;
			if (j == 1) {
				configId = stream.readUnsignedWord();
				leastSignificantBit = stream.readUnsignedByte();
				mostSignificantBit = stream.readUnsignedByte();
			} else if (j == 10)
				stream.readString();
			else if (j == 3)
				stream.readDWord();
			else if (j == 4)
				stream.readDWord();
		} while (true);
	}
}
