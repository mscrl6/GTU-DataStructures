import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character, Map<Character, Character>>();

	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}

	private void fill_english_alphabet() {
		// do not edit this method
		for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
			english_alphabet.add(c);
		}
	}

	// Fills the cipher table using the characters in the English alphabet set
	private void fill_map() {
		Iterator<Character> iterator = english_alphabet.iterator();// Iterator for traversing the alphabet
		while (iterator.hasNext()) {
			char outerKey = iterator.next();
			Map<Character, Character> innerMap = new HashMap<>();// Creates a new map for each character
			for (char innerKey = 'A'; innerKey <= 'Z'; innerKey++) {// Iterates over the alphabet for the values of the
																	// inner map
				char value = (char) ((outerKey - 'A' + innerKey - 'A') % 26 + 'A');// Calculates the cipher character to
																					// shift the characters properly
				innerMap.put(innerKey, value);// Puts the calculated value in the inner map
			}
			map.put(outerKey, innerMap);// Puts the inner map in the main map with the outer character as key
		}
	}

	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for (Character k : map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");

	}

	public Map get_map() {
		return map;
	}
}