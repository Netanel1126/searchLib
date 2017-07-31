package searchLib;

import java.util.ArrayList;

public interface Searcher <T>{
	 ArrayList<Action> search(Searchable<T> ser);
	 int getNumberOfNodesEvaluated();
}
