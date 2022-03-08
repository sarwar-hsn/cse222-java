package partOne;
import java.util.Iterator;


/**
 * 
 * interface extending the iterator interface
 *
 * @param <E> generic type
 */
public interface CustomIterator<E> extends Iterator<E>{
	/**
	 * sets a value to the last item returned by the next method
	 * @param item
	 */
	public void set(E item);
}
