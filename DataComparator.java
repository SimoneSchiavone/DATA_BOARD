import java.util.Comparator;

public class DataComparator implements Comparator<Data> {

	public int compare(Data d1, Data d2) {
	      return d2.getnlike()-d1.getnlike();
	}
}
