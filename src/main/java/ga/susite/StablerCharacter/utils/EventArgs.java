package ga.susite.StablerCharacter.utils;

public class EventArgs<T> {
	public static final EventArgs<Void> none = new EventArgs<Void>(null);
	
	T data;
	
	public EventArgs(T data) {
		this.data = data;
	}
}
