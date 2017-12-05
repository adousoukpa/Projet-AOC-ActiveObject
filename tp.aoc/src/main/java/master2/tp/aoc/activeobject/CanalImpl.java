package master2.tp.aoc.activeobject;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import master2.tp.aoc.generator.Generateur;
import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.observer.Observer;


public class CanalImpl implements Canal{
	/**
	 * Sujet du canal
	 */
	private Generateur generator;
	
	/**
	 * Le scheduler
	 */
	private ExecutorService scheduler;
	
	/**
	 * Les observateurs du canal (les écrans)
	 */
	private Observer<GenerateurAsync> display;

	
	/**
	 * Constructeur
	 * @param generator générateur à observer
	 * @param scheduler ordonnanceur pour les appels asynchrones
	 */
	public CanalImpl (Generateur generator, ExecutorService scheduler) {
		this.generator = generator;
		this.scheduler = scheduler;
	}

	/**
	 * Commande asynchrone Update à exécuter
	 * @param subject le générateur
	 */
	@Override
	public Future<Void> update(Generateur subject) {
		return this.scheduler.submit(() -> {
			this.display.update(this);
			return null;
		});
	}

	/**
	 * Commande asynchrone GetValue à exécuter
	 * @param subject le générateur
	 */
	@Override
	public Future<Integer> getValue() {
		return this.scheduler.submit(() -> {
			return this.generator.getValue();
		});
	}

	/**
	 * Ajoute un observateur à ce sujet
	 * @param ObserverAsync<Generateur> o observateur à ajouter
	 */
	@Override
	public void attach(Observer<GenerateurAsync> o) {
		this.display = o;
	}

	/**
	 * Retire l'observateur de ce sujet
	 * @param ObserverAsync<Generateur> o observateur à retirer
	 */
	@Override
	public void detach(Observer<GenerateurAsync> o) {
		this.display = null;
	}

}
