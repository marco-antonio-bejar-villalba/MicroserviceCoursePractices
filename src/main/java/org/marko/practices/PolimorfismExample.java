package org.marko.practices;

import java.util.List;

public class PolimorfismExample {
	
	public static void main(String[] args) {
		List<Operation> list=List.of(new Multiplication(), new Substraction());
		list.stream().map(a->a.apply(3, 2)).forEach(System.out::println);
	}

}
