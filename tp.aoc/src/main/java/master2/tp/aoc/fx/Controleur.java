package master2.tp.aoc.fx;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import master2.tp.aoc.activeobject.Canal;
import master2.tp.aoc.activeobject.CanalImpl;
import master2.tp.aoc.display.Display;
import master2.tp.aoc.generator.Generateur;
import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.generator.GenerateurImpl;
import master2.tp.aoc.observer.Observer;
import master2.tp.aoc.strategy.AlgoDiffusion;
import master2.tp.aoc.strategy.DiffusionAtomique;
import master2.tp.aoc.strategy.DiffusionSequentielle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controleur implements Initializable {

	private static final int GENERATION_VALUE_FREQUENCY = 300;
	@FXML
	private Label Afficheur1;
	@FXML
	private Label Afficheur2;
	@FXML
	private Label Afficheur3;
	@FXML
	private Label Afficheur4;

	@FXML
	private RadioButton RBatomic;
	@FXML
	private RadioButton RBsequential;

	@FXML
	private Button BPstart;
	@FXML
	private Button BPstop;

	private Generateur generateur;
	private ScheduledExecutorService service;

	private boolean running;

	/**
	 * Initialise le générateur, les algorithmes de diffusion ainsi que les
	 * afficheurs et les canaux simulant l'asynchronisme.
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		generateur = new GenerateurImpl();
		ExecutorService scheduler = Executors.newScheduledThreadPool(Integer.MAX_VALUE);

		Canal canal1 = new CanalImpl(generateur, scheduler, 250);
		Canal canal2 = new CanalImpl(generateur, scheduler, 500);
		Canal canal3 = new CanalImpl(generateur, scheduler, 1000);
		Canal canal4 = new CanalImpl(generateur, scheduler, 1500);

		
		
		Observer<GenerateurAsync> display1 = new Display(Afficheur1, 250);
		Observer<GenerateurAsync> display2 = new Display(Afficheur2, 500);
		Observer<GenerateurAsync> display3 = new Display(Afficheur3, 1000);
		Observer<GenerateurAsync> display4 = new Display(Afficheur4, 1500);

		generateur.attach(canal1);
		generateur.attach(canal2);
		generateur.attach(canal3);
		generateur.attach(canal4);

		((Display) display1).setName("Afficheur1");
		((Display) display2).setName("Afficheur2");
		((Display) display3).setName("Afficheur3");
		((Display) display4).setName("Afficheur4");

		canal1.attach(display1);
		canal2.attach(display2);
		canal3.attach(display3);
		canal4.attach(display4);

		// Configuration des stratégies et des boutons "setters"
		AlgoDiffusion atomique = new DiffusionAtomique(generateur);
		AlgoDiffusion sequentielle = new DiffusionSequentielle(generateur);
		
		RBatomic.setOnAction((event) -> {
			this.setStrategy(atomique);
		});
		
		RBsequential.setOnAction((event) -> {
			this.setStrategy(sequentielle);
		});
		
		// IHM INITIALISATION PAR DEFAUT
		RBatomic.setSelected(true);
		BPstop.setDisable(true);

		// On initialise une stratégie initiale pour le générateur
		((GenerateurImpl) generateur).addStrategy(atomique);
	}
	
	private void setStrategy(AlgoDiffusion strategy) {		
		((GenerateurImpl) generateur).addStrategy(strategy);
	}

	@FXML
	private void start(ActionEvent event) {
		if (!running) { // Si le générateur n'est pas déjà lancé
			// schedule l'appel a generate() toutes les n ms
			service = Executors.newScheduledThreadPool(1);
			service.scheduleAtFixedRate(((GenerateurImpl) generateur)::generate, 0, GENERATION_VALUE_FREQUENCY, TimeUnit.MILLISECONDS);
			running = true;
			BPstart.setDisable(true);
			BPstop.setDisable(false);
		}
	}

	@FXML
	private void stop(ActionEvent event) {
		if (running && service != null) {
			service.shutdown();
			running = false;
			BPstart.setDisable(false);
			BPstop.setDisable(true);
		}
	}
	
}
