package master2.tp.aoc.display;


import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.observer.Observer;


public class Display implements Observer<GenerateurAsync> {

	String name = "";

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(GenerateurAsync subject) {
		try {
			Integer a = subject.getValue().get();
			System.out.println(this.name + " future value : " + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}