import java.lang.RuntimeException;
public class UnauthorizedFriend extends RuntimeException {
	private static final long serialVersionUID = 2384944341L;
	public UnauthorizedFriend(String user) {
		super(user+" non autorizzato alla visione degli elementi della categoria del dato");
	}
}
