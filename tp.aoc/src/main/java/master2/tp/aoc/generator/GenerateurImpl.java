package master2.tp.aoc.generator;


import java.util.ArrayList;
import java.util.List;

import master2.tp.aoc.observer.ObserverAsync;


public class GenerateurImpl implements Generateur {
	
	private List<ObserverAsync<Generateur>> canaux;
	private Integer value;

	public GenerateurImpl() {
		this.canaux = new ArrayList<ObserverAsync<Generateur>>();
	}
	
	
	@Override
	public void attach(ObserverAsync<Generateur> o) {
		if (!this.canaux.contains(o)) {
			this.canaux.add(o);
		}
	}

	@Override
	public void detach(ObserverAsync<Generateur> o) {
		if (this.canaux.contains(o)) {
			this.canaux.remove(o);
		}
	}

	public void notifyObservers() {
		for (ObserverAsync<Generateur> canal: this.canaux) {
			canal.update(this);
		}
	}
	
	@Override
	public Integer getValue() {
		return this.value;
	}
	
	public void setValue(Integer v) {
		this.value = v;
		notifyObservers();
	}
}
