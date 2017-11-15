package master2.tp.aoc.observer;


public interface Subject<T> {
	
	public void attach (Observer<T> o);

	public void detach (Observer<T> o);
}
