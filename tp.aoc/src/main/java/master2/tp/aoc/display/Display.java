package master2.tp.aoc.display;


import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.observer.Observer;
import javafx.scene.control.Label;
import javafx.application.Platform;


public class Display implements Observer<GenerateurAsync> {

	String name = "";
	Label aff;
	
	public Display(Label aff) {
		this.aff = aff;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(GenerateurAsync subject) {
		try {
			Integer a = subject.getValue().get();
			Platform.runLater(() -> aff.setText(a + ""));
			System.out.println(this.name + " future value : " + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}