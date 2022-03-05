package ga.susite.scfw2d.utils;

/**
 * An event argument that will be provided to the subscribers method.
 */
public class EventArgs<T> {
	/**
	 * The empty EventArgs.
	 */
	public static final EventArgs<Void> none = new EventArgs<Void>(null);
	
	T data;
	
	/**
	 * Initialize a new event argument with the given data.
	 * @param data The data.
	 */
	public EventArgs(T data) {
		this.data = data;
	}
}
