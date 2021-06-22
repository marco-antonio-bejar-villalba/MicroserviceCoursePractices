package org.marko.practices;

import java.util.stream.IntStream;

public class Example {
	
	public static void main(String[] args) {
		IntStream.range(0, 11).forEach(System.out::println);
	}

}
