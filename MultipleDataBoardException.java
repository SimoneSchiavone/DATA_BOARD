import java.lang.RuntimeException;

public class MultipleDataBoardException extends RuntimeException {
	private static final long serialVersionUID = 2038491L;
	public MultipleDataBoardException(String message,int id) {
		super(message+" già usato per altra databoard con id "+id);
	}
}
