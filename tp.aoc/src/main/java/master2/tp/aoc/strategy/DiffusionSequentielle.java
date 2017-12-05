package master2.tp.aoc.strategy;

import master2.tp.aoc.generator.Generateur;
import master2.tp.aoc.observer.ObserverAsync;

public class DiffusionSequentielle implements AlgoDiffusion {

	private Generateur generateur;

	public DiffusionSequentielle(Generateur generateur) {
			this.generateur = generateur;
		}

	/**
	 * Exécution de la stratégie
	 */
	public void execute() {
		// Tant que TOUS lLES OBSERVEURS n'ont pas eu la valeur, on bloque
		for (ObserverAsync<Generateur> o : generateur.getObservers()) {
			o.update(generateur);
		}
	}

	/**
	 * Configuration de la stratégie de diffusion
	 * 
	 * @param generateur
	 *            le générateur sur lequel appliquer ladite stratégie
	 */
	@Override
	public void configure(Generateur generateur) {
		this.generateur = generateur;
	}

}
