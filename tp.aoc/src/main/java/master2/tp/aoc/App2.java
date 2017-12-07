package master2.tp.aoc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import master2.tp.aoc.activeobject.Canal;
import master2.tp.aoc.activeobject.CanalImpl;
import master2.tp.aoc.display.Display;
import master2.tp.aoc.generator.Generateur;
import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.generator.GenerateurImpl;
import master2.tp.aoc.observer.Observer;
import master2.tp.aoc.strategy.AlgoDiffusion;
import master2.tp.aoc.strategy.DiffusionAtomique;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App2 {
	
   

    
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World!");
		
		scenarioM3();
	}

	public static void scenarioM3() throws InterruptedException {
		Generateur generateur = new GenerateurImpl();
		ExecutorService scheduler = Executors.newScheduledThreadPool(Integer.MAX_VALUE);

		Canal canal1 = new CanalImpl(generateur, scheduler);
		Canal canal2 = new CanalImpl(generateur, scheduler);
		Canal canal3 = new CanalImpl(generateur, scheduler);
		Canal canal4 = new CanalImpl(generateur, scheduler);

		Observer<GenerateurAsync> display1 = new Display(null);
		Observer<GenerateurAsync> display2 = new Display(null);
		Observer<GenerateurAsync> display3 = new Display(null);
		Observer<GenerateurAsync> display4 = new Display(null);
		
		generateur.attach(canal1);
		generateur.attach(canal2);
		generateur.attach(canal3);
		generateur.attach(canal4);
		
		((Display)display1).setName("M2EcranAsynchrone1");
		((Display) display2).setName("M2EcranAsynchrone2");
		((Display) display3).setName("M2EcranAsynchrone3");
		((Display) display4).setName("M2EcranAsynchrone4");

		canal1.attach(display1);
		canal2.attach(display2);
		canal3.attach(display3);
		canal4.attach(display4);
		
		AlgoDiffusion atomique = new DiffusionAtomique(generateur);
		((GenerateurImpl) generateur).addStrategy(atomique);
		
		// schedule l'appel a generate() toutes les n ms
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(((GenerateurImpl) generateur)::generate, 0, 1000, TimeUnit.MILLISECONDS);
	}


}

/*

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/afficheurs.fxml"));
        primaryStage.setTitle("Afficheurs");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws InterruptedException {
        launch(args);
        GenerateurImpl generateur = new GenerateurImpl();
        Afficheur afficheur = new Afficheur();
        Canal canal = new Canal();
        canal.attach(afficheur);
        generateur.attach(canal);
        generateur.genererValue();
    }
}
 */
