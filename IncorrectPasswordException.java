import java.lang.Exception;
public class IncorrectPasswordException extends Exception {
	private static final long serialVersionUID = 238401L;
	public IncorrectPasswordException() {
		super("Password non corretta");
	}
}
