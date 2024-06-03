import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;

	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		key = _key;
		cipher_text = text;
		map = _map;
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}

	private void generate_keystream() {
		keystream += key;
	}

	// Decrypts the ciphertext using the keystream and the cipher table
	private void generate_plain_text() {
		// Iterates over each character in the ciphertext
		for (int i = 0; i < cipher_text.length(); i++) {
			char keyChar = keystream.charAt(i);
			Map<Character, Character> reverseMap = map.get(keyChar); // the submap for the current keystream character

			// Uses an iterator to find the plaintext character corresponding to the
			// ciphertext character
			Iterator<Map.Entry<Character, Character>> iterator = reverseMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Character, Character> entry = iterator.next();
				// Checks if the character matches the current character in the ciphertext
				if (entry.getValue() == cipher_text.charAt(i)) {
					plain_text += entry.getKey();
					break;
				}
			}
		}
	}

	public String get_keystream() {
		return keystream;
	}

	public String get_plain_text() {
		return plain_text;
	}
}
