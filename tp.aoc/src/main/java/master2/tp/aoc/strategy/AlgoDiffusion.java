package master2.tp.aoc.strategy;

import master2.tp.aoc.generator.Generateur;

public interface AlgoDiffusion {
	
	public void configure (Generateur generateur);
	
	public void execute ();

}
