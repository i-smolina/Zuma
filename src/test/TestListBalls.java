package test;

import java.util.ListIterator;

import zuma.Ball;
import zuma.Colors;
import zuma.ListBall;

public class TestListBalls {
	
	static ListBall balls;
	
	public static void main(String[] args){
		ListBall balls = new ListBall();
		
		balls.addBall(new Ball(Colors.red));
		balls.addBall(new Ball(Colors.blue));
		balls.addBall(new Ball(Colors.blue));
		balls.addBall(new Ball(Colors.green));
		
		ListIterator<Ball> cur_it = balls.insertBall(1, new Ball(Colors.blue));
		
		ListIterator<Ball> it = balls.listIterator();
		while (it.hasNext()) {
			System.out.print(it.next().getColor() + " ");
		}
		
		balls.removeNeighborBalls(cur_it);
		System.out.println();
		//System.out.println(balls.checkBalls(cur_it));
		
		it = balls.listIterator();
		while (it.hasNext()) {
			System.out.print(it.next().getColor() + " ");
		}
		
		
		
	}
	
	

}
