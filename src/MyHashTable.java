/**
 * A hash table is a data structure that allows you to store a value with an associated key.
 * To access the value you provide the key to the hash table and it can return the value.
 * This hash table is build on an array whose size is maxElements.
 * When a key value pair is added, the hash table "hashes" the key to get the index
 * where this key value pair should be stored in the array. If the value needs to be retrieved
 * the same process is followed to find it. At each index of the array there is a short linked list
 * in case multiple key value pairs are mapped to the same index. Assuming that our distribution is
 * uniform across the array the hash table should have amortized insert, delete and look-up time
 * complexities which are O(1).
 * @author Andrew
 *
 * @param <K>
 * @param <E>
 */

public class MyHashTable<K, V> {
	
	private MyLinkedList<HashNode>[] array;
	private int size;
	
	/*
	 * Creates a new Hash Table object with underlying array size of maxElements
	 */
	@SuppressWarnings("unchecked")
	public MyHashTable(int maxElements) {
		
		array = (MyLinkedList<HashNode>[]) new MyLinkedList[maxElements];
		for(int i = 0; i < maxElements; i++) {
			array[i] = null;
		}
		size = 0;
		
	}

	/*
	 * Adds the given key value pair to the hash table
	 */
	public void add(K key, V value) {
		
		//Find the correct index to insert the pair
		int index = key.hashCode() % array.length;
		
		//Create a hashNode to store the pair in
		HashNode toInsert = new HashNode(key, value);
		
		//Create a linked list if there isn't one at the index
		if(array[index] == null) {
			
			array[index] = new MyLinkedList<HashNode>();
			
		} else {
		
			//Check if the pair is already in the list and delete if it is (reduce size)
			for(int i = 0; i < array[index].getLength(); i++) {
				
				if(array[index].get(i).key.equals(toInsert.key)) {
					array[index].delete(i);
					size--;
				}
				
			}
		
		}
			
		//Add the pair to the list (increase size)
		array[index].insert(toInsert);
		size++;
		
	}

	/*
	 * Prints a list of all the key value pairs in the table
	 */
	public void printTable() {
		
		//Go to every index of the array
		for(int i = 0; i < array.length; i++) {
			
			//If this index contains a linked list then print each element in it.
			if(array[i] != null) {
				
				for(int j = 0; j < array[i].getLength(); j++) {
					HashNode node = array[i].get(j);
					System.out.print("[" + node.key.toString() + ":" + node.value.toString() + "] ");
				}
				
			}
			
		}
		
		System.out.println();
		
	}

	/*
	 * Returns a linked list of all the keys in the hash table
	 */
	public MyLinkedList<K> getKeys() {
		
		MyLinkedList<K> keys = new MyLinkedList<K>();
		
		//Go to every index of the array
		for(int i = 0; i < array.length; i++) {
			
			//If this index contains a linked list add the key from each element.
			if(array[i] != null) {
				
				for(int j = 0; j < array[i].getLength(); j++) {
					HashNode node = array[i].get(j);
					keys.insert(node.key);
				}
				
			}
			
		}
		
		return keys;
		
	}

	/*
	 * Returns a linked list of all the elements in the hash table
	 */
	public MyLinkedList<V> getValues() {
		
		MyLinkedList<V> values = new MyLinkedList<V>();
		
		//Go to every index of the array
		for(int i = 0; i < array.length; i++) {
			
			//If this index contains a linked list add the key from each element.
			if(array[i] != null) {
				
				for(int j = 0; j < array[i].getLength(); j++) {
					HashNode node = array[i].get(j);
					values.insert(node.value);
				}
				
			}
			
		}
		
		return values;
		
	}

	/*
	 * Returns true if the given key maps to a value in the hash table
	 */
	public boolean contains(K key) {
				
		//Hash the key to find its index
		int index = key.hashCode() % array.length;
		
		//Check if the key can be found in the list at that index
		if(array[index] != null) {

			for(int i = 0; i < array[index].getLength(); i++) {
				
				if(array[index].get(i).key.equals(key)) {
					return true;
				}
				
			}
			
		}
		
		return false;
		
	}

	/*
	 * Removes the given key if it maps to a value in the hash table
	 */
	public void remove(K key) {
		
		//Hash the key to find its index
		int index = key.hashCode() % array.length;
		
		//Check if the key can be found in the list at that index
		if(array[index] != null) {

			for(int i = 0; i < array[index].getLength(); i++) {
				
				if(array[index].get(i).key.equals(key)) {
					//Remove the element
					array[index].delete(i);
					size--;
				}
				
			}
			
		}
		
	}

	/*
	 * Returns the value which the given key maps to in the hash table.
	 */
	public V get(K key) {
		
		//Hash the key to find its index
		int index = key.hashCode() % array.length;
		
		//Check if the key can be found in the list at that index
		if(array[index] != null) {

			for(int i = 0; i < array[index].getLength(); i++) {
				
				if(array[index].get(i).key.equals(key)) {
					return array[index].get(i).value;
				}
				
			}
			
		}
		
		return null;
		
	}
	
	private class HashNode {
		
		public K key;
		public V value;
		
		public HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		boolean equalsHashNode(HashNode other) {
			
			if(this.key.equals(other.key) && this.value.equals(other.value)) {
				return true;
			}
			
			return false;
		}
		
	}

}
