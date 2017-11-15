package master2.tp.aoc.generator;
import master2.tp.aoc.observer.SubjectAsync;

public interface Generateur extends SubjectAsync<Generateur> {
	
	public Integer getValue();
}
