import java.lang.RuntimeException;

public class DuplicateEntryException extends RuntimeException {
	private static final long serialVersionUID = 238491L;
	public DuplicateEntryException(String msg) {
		super(msg);
	}

}
