package master2.tp.aoc.display;


import java.util.concurrent.Future;

import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.observer.Observer;


public class Display implements Observer<GenerateurAsync> {

	String name = "";

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(GenerateurAsync subject) {
		Future<Integer> value = subject.getValue();
		System.out.println(this.name + ": " + subject.getValue());
	}
}