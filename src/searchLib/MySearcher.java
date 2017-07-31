package searchLib;

import java.util.ArrayList;
import java.util.Collections;

import javafx.print.Collation;

public abstract class MySearcher<T> implements Searcher<T> {
	
	protected int nodes;
	
	public MySearcher() {
		nodes = 0;
	}
	
	@Override
	public int getNumberOfNodesEvaluated() {		
		return nodes;
	}
	
	protected ArrayList<Action> backTrace(State<T> goalState) {
		ArrayList<Action> actions = new ArrayList<Action>();
		nodes = 0;
		
		State<T> currState = goalState;
		while (currState.getCameFrom() != null) {			
			actions.add(currState.getAction());
			currState = currState.getCameFrom();
			nodes++;
		}
		 Collections.reverse(actions);
		return actions;
	}
	
}
