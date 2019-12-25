import java.util.ArrayList;
import java.util.Comparator;

public class MapBuilder<K extends Comparable<K>, V> {
	private static class Pair<K, V> {
		K key;
		V value;
		
		Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private final ArrayList<Pair<K, V>> list = new ArrayList<Pair<K, V>>();
	
	public MapBuilder<K, V> add(K key, V value) {
		list.add(new Pair<K, V>(key, value));
		return this;
	}
	
	public ArrayMap<K, V> build() {
		list.sort(Comparator.comparing(a -> a.key));
		
		var keys = new ArrayList<K>();
		var values = new ArrayList<ArrayList<V>>();
		
		var k = (K)null;
		var vs = new ArrayList<V>();
		
		for (int i = 0; i < list.size(); i++) {
			var p = list.get(i);
			
			if (k != null && k.compareTo(p.key) < 0) {
				keys.add(k);
				vs.trimToSize();
				values.add(vs);
				vs = new ArrayList<V>();
			}
			
			vs.add(p.value);
			k = p.key;
		}
		
		if (k != null) {
			keys.add(k);
			vs.trimToSize();
			values.add(vs);
			vs = new ArrayList<V>();
		}
		
		keys.trimToSize();
		values.trimToSize();

		return new ArrayMap<K, V>(keys, values);
	}
}
