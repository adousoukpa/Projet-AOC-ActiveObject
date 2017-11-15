package master2.tp.aoc.observer;


public interface SubjectAsync<T> {
	
	public void attach (ObserverAsync<T> o);

	public void detach (ObserverAsync<T> o);
}
