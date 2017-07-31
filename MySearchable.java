package searchLib;
import java.util.HashMap;

public class MySearchable<T> implements Searchable<Position> {

	int maze[][] = {{7,2,5},{4,8,6},{9,3,1}};
	
	@Override
	public State<Position> getInitialState() {
		Position p = new Position(0,0);
		State<Position> initialGoal = new State<Position>(p);
		initialGoal.setCost(maze[0][0]);
		return initialGoal;
	}

	@Override
	public State<Position> getGoal() {
		Position p = new Position(2,2);
		State<Position> goal = new State<Position>(p);
		return goal;
	}

	@Override
	public HashMap<Action,State<Position>> getAllPossibleMoves(State<Position> state) {
		HashMap<Action,State<Position>> PossibleMoves = new HashMap<>();
		
		int y =  state.getState().getHeight(); 
		int x =  state.getState().getWidth();
		
	//Check to see if he can move up
		try{
			Position posUp = new Position(y + 1,x);
			State<Position> su = new State<Position>(posUp);
			su.setCost(maze[posUp.getHeight()][posUp.getWidth()] + state.getCost());
			PossibleMoves.put(new Action("Move up"), su);
		}catch (Exception e) {}
		
	//Check to see if he can move down
		try{
			Position posDown = new Position(y - 1,x);
			State<Position> sd = new State<Position>(posDown);
			sd.setCost(maze[posDown.getHeight()][posDown.getWidth()]+state.getCost());
			PossibleMoves.put(new Action("Move down"), sd);
		}catch (Exception e) {}
		
	//Check to see if he can move right
		try{
			Position posRight = new Position(y,x + 1);
			State<Position> sr = new State<Position>(posRight);
			sr.setCost(maze[posRight.getHeight()][posRight.getWidth()] + state.getCost());
			PossibleMoves.put(new Action("Move right"), sr);
		}catch (Exception e) {}
		
	//Check to see if he can move left
		try{
			Position posLeft = new Position(y,x - 1);
			State<Position> sl = new State<Position>(posLeft);
			sl.setCost(maze[posLeft.getHeight()][posLeft.getWidth()] + state.getCost());
			PossibleMoves.put(new Action("Move left"), sl);
		}catch (Exception e) {}
		
		return PossibleMoves;
	}
}