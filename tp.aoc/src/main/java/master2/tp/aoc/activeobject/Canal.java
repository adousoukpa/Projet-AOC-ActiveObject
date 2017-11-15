package master2.tp.aoc.activeobject;
import master2.tp.aoc.generator.Generateur;
import master2.tp.aoc.generator.GenerateurAsync;
import master2.tp.aoc.observer.ObserverAsync;
import master2.tp.aoc.observer.Subject;

public interface Canal extends ObserverAsync<Generateur>, GenerateurAsync, Subject<GenerateurAsync> {

}
