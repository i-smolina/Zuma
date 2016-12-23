package zuma;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author irina
 *
 */

public class ListBall {
	private LinkedList<Ball> listBalls;
	
	public ListBall() {
		listBalls = new LinkedList<Ball>();
	}

	public void addBall(Ball newBall) {
		listBalls.add(newBall);
	}
	
	public ListIterator<Ball> insertBall(int pos, Ball insBall) throws NullPointerException{
		// check value of position
		if (pos < 0 || pos > listBalls.size()) return null;
		if (insBall == null) throw new NullPointerException("Ball is null!");
		
		ListIterator<Ball> it = listBalls.listIterator();
		for (int i = 0; i < pos; i++)
			it.next();
		
		it.add(insBall);
		// итератор будет стоять перед шаром, который только что был вставлен
		it.previous();
	
		return it;
	}
		
	/**
	 * найти соседей вставленного шарика одного с ним цвета и удалить их
	 * @param it - итератор стоит перед шаром, который был вставлен
	 */
	public void removeNeighborBalls(ListIterator<Ball> it) throws NullPointerException{
		
		if (it == null) throw new NullPointerException("removeNeighborBalls: ListIterator is null");
		
		PairIteratorCount c_it = calcBalls(it);
		removeBalls(c_it.getListIterator(), c_it.getCount());	
	}
		
	
	/**
	 * посчитать количество шаров подлежащих удалению
	 * @param it - итератор стоит перед шаром, который был вставлен
	 * 			  (будем искать соседние шарики одного с ним цвета)
	 * @return пара: итератор, стоит перед первым шаром подлежащим удалению 
	 * 		   и количество шаров для удаления
	 */
	private PairIteratorCount calcBalls(ListIterator<Ball> it) throws NullPointerException{
		
		if (it == null) throw new NullPointerException("ListIterator is null!");
		if (!it.hasNext()) return null;
		
		// определяем цвет шариков, которые будем считать
		Colors cur_color = it.next().getColor();
				
		// найдем крайний последний шар данного цвета
		while (it.hasNext()) {
			if (it.next().getColor() != cur_color) {
					it.previous();
					break;
				}
			}
				
		// теперь возвращаемся, движемся вначало списка и считаем все шары данного цвета
		int count = 0;			
		while (it.hasPrevious()) {
			if (it.previous().getColor() == cur_color)
				count++;
			else {
				it.next();
				break;
			}	
		}
		
		return (new PairIteratorCount(it, count));				
	}
	
	
	/**
	 * удалить count шаров
	 * @param it - итератор стоит перед первым шаром, подлежащем удалению
	 * @param count - количество шаров для удаления
	 */
	private void removeBalls(ListIterator<Ball> it, int count) 
			throws NullPointerException{
		
		if (it == null) throw new NullPointerException("ListIterator is null!");
		if (count < 0) return;
		
		for (int i = 0; i < count; i++) {
			it.next();
			it.remove();			
		}		
	}
	
	public ListIterator<Ball> listIterator() {
		return listBalls.listIterator();
	}	
	
	/**
	 * @author irina
	 * вспомогательный класс
	 * будем использовать для возврата из метода пары значений:
	 * итератор, который стоит перед первым шаром, подлежащем удалению
	 * и количестов шаров для удаления
	 */
	private class PairIteratorCount {
		final ListIterator<Ball> c_it;
		final int count;
		
		public PairIteratorCount(ListIterator<Ball> c_it, int count) {
			this.c_it = c_it;
			this.count = count;
		}
		
		ListIterator<Ball> getListIterator() {
			return c_it;
		}
		
		int getCount() {
			return count;
		}
	}
}
