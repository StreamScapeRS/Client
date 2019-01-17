package com.jagex;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

@SuppressWarnings("all")
final class TextInput {

	public static String decodeToString(int i, Stream stream) {
		return Formatter.format(FormatTypes.CHAT_MESSAGE, stream.readString());
	}

	public static void appendToStream(String s, Stream stream) {
		if (s.length() > 80)
			s = s.substring(0, 80);
		int i = -1;
		stream.writeString(s);
	}

	public static String processText(String s) {
		stream.currentOffset = 0;
		appendToStream(s, stream);
		int j = stream.currentOffset;
		stream.currentOffset = 0;
		String s1 = decodeToString(j, stream);
		return s1;
	}

	private static final char[] decodedString = new char[100];
	private static final Stream stream = new Stream(new byte[100]);
	private static final char[] validChars = { ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n', 's', 'r', 'd', 'l', 'u', 'm',
			'w', 'c', 'y', 'f', 'g', 'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', ' ', '!', '^', '|', '<', '?', '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\', '\'', '/', '@',
			'#', '+', '=', '\243', '$', '%', '"', '[', ']' };

}
