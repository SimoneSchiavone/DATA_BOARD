import java.lang.RuntimeException;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 23849441L;
	public NotFoundException(String d) {
		super(d+" non trovato");
	}
}
