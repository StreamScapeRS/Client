package com.jagex;

import java.util.Arrays;

/**
 * Created at: Jan 22, 2017 4:31:47 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public class ThreadTools {

	/**
	 * 
	 */
	public static void dumpStack() {
		Arrays.asList(Thread.currentThread().getStackTrace()).forEach(System.out::println);

	}

}
