package searchLib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra<T> extends MySearcher<T> {
	
	private PriorityQueue<State<T>> unsettled;
	private ArrayList<State<T>> settled;
	private ArrayList<Action> unable;
	
	public Dijkstra() {
		unsettled = new PriorityQueue<State<T>>(new Comparator<State<T>>() {
			@Override
			public int compare(State<T> o1, State<T> o2) {
				if(o1.getCost() < o2.getCost())
					return -1;
				return 1;
			}
		});
		settled = new ArrayList<>();
		unable = new ArrayList<>();
	}
	
	@Override
	public ArrayList<Action> search(Searchable<T> ser) {
		ser.getInitialState().setCost(0);
		unsettled.add(ser.getInitialState());
		
		while(!unsettled.isEmpty()){
			State<T> vertex = unsettled.poll();
			settled.add(vertex);
			HashMap<Action, State<T>> adj = ser.getAllPossibleMoves(vertex);
			for (Action action : adj.keySet()) {
				State<T> s = adj.get(action);
				
				if(!settled.contains(s) && !unsettled.contains(s)){
					s.setCameFrom(vertex);
					s.setAction(action);
					unsettled.add(s);
				}
				else if (!settled.contains(s))
					relax(s, action);
			}
		}
		
		if(settled.contains(ser.getGoal())){
			for (int i = 0; i < settled.size(); i++) {
				if(settled.get(i).equals(ser.getGoal()))
					return backTrace(settled.get(i));
			}
			unable = new ArrayList<>();
			unable.add(new Action("The goal can not be reached"));	
		}
		
		return unable;
	}
	
	private void relax(State<T> s,Action ac){
			
			Stack<State<T>> tempStack = new Stack<State<T>>();
			
			while(!unsettled.isEmpty()){
				tempStack.push(unsettled.poll());
				if(s.equals(tempStack.firstElement())){
					break;
				}
			}					
			if (s.getCost() < tempStack.firstElement().getCost()){
				tempStack.pop();
				s.setCameFrom(s);
				s.setAction(ac);
				tempStack.push(s);
			}
			while(!tempStack.isEmpty())
				unsettled.add(tempStack.pop()); 
		}
}
	
