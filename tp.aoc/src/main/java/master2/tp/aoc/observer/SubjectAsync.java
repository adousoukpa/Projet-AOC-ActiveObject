package master2.tp.aoc.observer;

import java.util.List;

public interface SubjectAsync<T> {
	
	public void attach (ObserverAsync<T> o);

	public void detach (ObserverAsync<T> o);
	
	public List<ObserverAsync<T>> getObservers();
}
