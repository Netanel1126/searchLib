package searchLib;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Position implements Serializable{
	
	private int width;
	private int height;
	
	public Position() {
		height = 0;
		width = 0;
	}
	
	public Position(int height , int width) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public String toString(){
		return height + " " + width;
	}
	
	// GET & SET //

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setLocation(int y,int x){
		this.width = x;
		this.height = y;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
}