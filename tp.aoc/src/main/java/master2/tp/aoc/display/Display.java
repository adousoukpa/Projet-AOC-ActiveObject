package master2.tp.aoc.display;


import javafx.application.Platform;
import javafx.scene.control.Label;
import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.observer.Observer;


public class Display implements Observer<GenerateurAsync> {

	private String name = "";
	private int latencyInMilliseconds = 0;
	private boolean latency;
	private Label aff;
	
	public Display(Label aff) {
		this(aff, 0);
	}
	
	public Display(Label aff, int latencyValue) {
		this.aff = aff;
		this.latencyInMilliseconds = latencyValue;
		setLatency(true);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(GenerateurAsync subject) {
		try {
			// GetValue asynchrone
			Integer a = subject.getValue().get();
			
			// Wait
			if (this.latencyInMilliseconds > 0 && isLatency()) {
				Thread.sleep(latencyInMilliseconds);
			}
			
			// Update the screen
			Platform.runLater(() -> aff.setText(a + ""));
			System.out.println(this.name + " [latency="+latencyInMilliseconds+"] : " + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get value of current defined latency of the display
	 * @return value in milliseconds of the latency
	 */
	public int getLatencyInMilliseconds() {
		return latencyInMilliseconds;
	}

	/**
	 * Set value of latency for the display
	 * @param latencyInMilliseconds value to set
	 */
	public void setLatencyInMilliseconds(int latencyInMilliseconds) {
		this.latencyInMilliseconds = latencyInMilliseconds;
	}

	/**
	 * @return 	true if latency must be considered or not for the display
	 * 			false otherwise		
	 */
	public boolean isLatency() {
		return latency;
	}

	/**
	 * Setter for display latency
	 * @param activationMode value to set
	 */
	public void setLatency(boolean activationMode) {
		this.latency = activationMode;
	}
	
	/**
	 * Active latency of the screen
	 */
	public void activeLatency() {
		setLatency(true);
	}
	
	/**
	 * Deactive latency of the screen
	 */
	public void deActiveLatency() {
		setLatency(false);
	}
}