package master2.tp.aoc.activeobject;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import master2.tp.aoc.generator.Generateur;
import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.observer.Observer;


public class CanalImpl implements Canal{
	private Generateur generator;
	private ExecutorService scheduler;
	private Observer<GenerateurAsync> display;

	
	public CanalImpl (Generateur generator) {
		this.generator = generator;
		this.scheduler = Executors.newSingleThreadExecutor();
	}

	@Override
	public Future<Void> update(Generateur subject) {
		return this.scheduler.submit(() -> {
			this.display.update(this);
			return null;
		});
	}

	@Override
	public Future<Integer> getValue() {
		return this.scheduler.submit(() -> {
			return this.generator.getValue();
		});
	}

	@Override
	public void attach(Observer<GenerateurAsync> o) {
		this.display = o;
	}

	@Override
	public void detach(Observer<GenerateurAsync> o) {
		this.display = null;
	}

}
