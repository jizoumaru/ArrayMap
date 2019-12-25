import java.util.ArrayList;

public class ArrayMap<K extends Comparable<K>, V> {
	public static <K extends Comparable<K>, V> MapBuilder<K, V> builder() {
		return new MapBuilder<K, V>();
	}
	
	private final ArrayList<K> keys;
	private final ArrayList<ArrayList<V>> values;
	
	public ArrayMap(ArrayList<K> keys, ArrayList<ArrayList<V>> values) {
		this.keys = keys;
		this.values = values;
	}
	
	public ArrayList<V> get(K key) {
		int f = 0;
		int t = keys.size();
		
		while (f < t) {
			int m = f + ((t - f) >>> 1);
			int c = key.compareTo(keys.get(m));
			
			if (c == 0) {
				return values.get(m);
			}
			
			if (c < 0) {
				t = m;
			} else {
				f = m + 1;
			}
		}
		
		return new ArrayList<V>(0);
	}
}
