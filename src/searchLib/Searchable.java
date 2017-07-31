package searchLib;

import java.util.HashMap;

public interface Searchable <T>{
	public State<T> getInitialState();
	public State<T> getGoal();
	public HashMap<Action, State<T>> getAllPossibleMoves(State<T> state);
}
