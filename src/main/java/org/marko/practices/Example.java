package org.marko.practices;

import lombok.Getter;
import lombok.Setter;

public class Example {

	public static void main(String[] args) {
		Integer a=10;
		Integer b=10;
		Person person=new Person();
		person.setLastName("");
		person.setName("");
	}
	
	@Getter
	@Setter
	public static class Person{
		private String name;
		private String lastName;
	
	}

}
