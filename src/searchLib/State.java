package searchLib;

public class State<T> {
	
	private T state;
	private State<T> cameFrom;
	private Action action;
	private int cost = Integer.MAX_VALUE;
	

	public State(T state) {
		this.state = state; 
	}	
	
	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {		
		return state.toString();
	}
	
	@Override
	public int hashCode() {		
		return state.hashCode();
	}
		
	@Override
	public boolean equals(Object obj) {
		State<T> s = (State<T>)obj;
		boolean f = state.toString().equals(s.getState().toString());
		return f;//state.equals(s.state.toString());
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
}
