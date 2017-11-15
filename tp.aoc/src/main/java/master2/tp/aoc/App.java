package master2.tp.aoc;

import master2.tp.aoc.activeobject.Canal;
import master2.tp.aoc.activeobject.CanalImpl;
import master2.tp.aoc.display.Display;
import master2.tp.aoc.generator.Generateur;
import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.generator.GenerateurImpl;
import master2.tp.aoc.observer.Observer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World!");
		
		scenarioM3();
	}

	public static void scenarioM1() {
//		Generateur generateur = new GenerateurImpl();
//
//		ObservateurGenerateur display1 = new Afficheur();
//		ObservateurGenerateur display2 = new Afficheur();
//		ObservateurGenerateur display3 = new Afficheur();
//		ObservateurGenerateur display4 = new Afficheur();
//
//		((Afficheur) display1).setName("Ecran1");
//		((Afficheur) display2).setName("Ecran2");
//		((Afficheur) display3).setName("Ecran3");
//		((Afficheur) display4).setName("Ecran4");
//
//		generateur.attach(display1);
//		generateur.attach(display2);
//		generateur.attach(display3);
//		generateur.attach(display4);
//
//		int loopParam = 10;
//		for (int i = 0; i < loopParam; i++) {
//			((GenerateurImpl) generateur).setValue(i);
//			// Thread.sleep(100);
//		}
	}

	public static void scenarioM3() {
		Generateur generateur = new GenerateurImpl();

		Canal canal1 = new CanalImpl(generateur);
		Canal canal2 = new CanalImpl(generateur);
		Canal canal3 = new CanalImpl(generateur);
		Canal canal4 = new CanalImpl(generateur);

		Observer<GenerateurAsync> display1 = new Display();
		Observer<GenerateurAsync> display2 = new Display();
		Observer<GenerateurAsync> display3 = new Display();
		Observer<GenerateurAsync> display4 = new Display();
		
		canal1.attach(display1);
		canal2.attach(display2);
		canal3.attach(display3);
		canal4.attach(display4);
		
		generateur.attach(canal1);
		generateur.attach(canal2);
		generateur.attach(canal3);
		generateur.attach(canal4);

		((Display)display1).setName("M2EcranAsynchrone1");
		((Display) display2).setName("M2EcranAsynchrone2");
		((Display) display3).setName("M2EcranAsynchrone3");
		((Display) display4).setName("M2EcranAsynchrone4");

//		generateur.attach(display1);
//		generateur.attach(display2);
//		generateur.attach(display3);
//		generateur.attach(display4);

		int loopParam = 10;
		for (int i = 0; i < loopParam; i++) {
			((GenerateurImpl) generateur).setValue(i);
			// Thread.sleep(100);
		}
	}
}
