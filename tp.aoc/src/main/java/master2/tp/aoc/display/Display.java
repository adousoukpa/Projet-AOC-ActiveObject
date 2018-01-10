package master2.tp.aoc.display;


import javafx.application.Platform;
import javafx.scene.control.Label;
import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.observer.Observer;


public class Display implements Observer<GenerateurAsync> {

	private String name = "";
	private int latencyInMilliseconds = 0;
	private Label aff;
	
	public Display(Label aff) {
		this(aff, 0);
	}
	
	public Display(Label aff, int latencyValue) {
		this.aff = aff;
		this.latencyInMilliseconds = latencyValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(GenerateurAsync subject) {
		try {
			// GetValue asynchrone
			Integer a = subject.getValue().get();
			
			// Update the screen
			Platform.runLater(() -> aff.setText(a + ""));
			System.out.println(this.name + " [latency="+latencyInMilliseconds+"] : " + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}