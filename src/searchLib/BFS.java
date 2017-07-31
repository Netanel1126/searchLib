package searchLib;

import searchLib.Action;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class BFS<T> extends MySearcher<T> implements Searcher<T>{
	
	PriorityQueue<State<T>> OPEN;
	ArrayList<State<T>> CLOSE;
	
	public BFS() {
		OPEN = new PriorityQueue<State<T>>(new Comparator<State<T>>() {
			@Override
			public int compare(State<T> o1, State<T> o2) {
				if(o1.getCost() < o2.getCost())
					return -1;
				else if (o1.getCost() > o2.getCost())
					return 1;
				return 0;
			}
		});
		
		CLOSE = new ArrayList<State<T>>();
	}
	
	@Override
	public ArrayList<Action> search(Searchable<T> ser) {
		OPEN.add(ser.getInitialState());
		while (!OPEN.isEmpty()) {
			State<T> temp = OPEN.poll();
			
			CLOSE.add(temp);
			
			if(temp.equals(ser.getGoal())){
				return backTrace(temp);
			}
			HashMap<Action, State<T>> map = ser.getAllPossibleMoves(temp);
			
			for (Action action : map.keySet()) {
				State<T> s = map.get(action);
				
				if(!openContains(s) && !closeContains(s)){
					s.setCameFrom(temp);
					s.setAction(action);
					OPEN.add(s);
				}
				
				else if(openContains(s)){
					Stack<State<T>> tempStack = new Stack<State<T>>();
					
					while(!OPEN.isEmpty()){
						tempStack.push(OPEN.poll());
						if(s.equals(tempStack.firstElement())){
							break;
						}
					}					
					if (s.getCost() < tempStack.firstElement().getCost()){
						tempStack.pop();
						s.setCameFrom(temp);
						s.setAction(action);
						tempStack.push(s);
					}
					while(!tempStack.isEmpty())
						OPEN.add(tempStack.pop()); 
				}	
				else{
					for (int i = 0; i < CLOSE.size(); i++){
						if(s.equals(CLOSE.get(i))){
							if(s.getCost() < CLOSE.get(i).getCost()){
								CLOSE.remove(i);
								s.setCameFrom(temp);
								s.setAction(action);
								OPEN.add(s);
							}
						}
					}
				}
			}
		}
		ArrayList<Action> unable = new ArrayList<>();
		unable.add(new Action("The goal cannot be reached"));
		return unable;
	}

	private boolean openContains(State<T> s){
		Stack<State<T>> tempStack = new Stack<State<T>>();
		while(!OPEN.isEmpty()){
			tempStack.push(OPEN.poll());
			if(s.equals(tempStack.firstElement())){
				while(!tempStack.isEmpty())
					OPEN.add(tempStack.pop());
				return true;
			}
		}
		while(!tempStack.isEmpty())
			OPEN.add(tempStack.pop());
		return false;
	}
	
	private boolean closeContains(State<T> t){
		for (int i = 0; i < CLOSE.size(); i++) {
			if(t.equals(CLOSE.get(i)))
					return true;
		}
		return false;
	}
}
