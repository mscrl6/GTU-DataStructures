import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";

	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		plain_text = text;
	}

	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}

	// Generates a keystream based on the length of the plain text
	private void generate_keystream() {
		if (plain_text.length() == key.length()) {
			keystream = key;// Uses the key as the keystream if lengths match
		}
		// Extends the key to match the length of the plain text
		else if (plain_text.length() > key.length()) {
			int j = 0;
			for (int i = 0; i < plain_text.length(); ++i) {
				if (j == key.length()) {
					j = 0;// Reset to the start of the key if we reach the end
				}
				keystream += key.charAt(j);
				j++;
			}
		}
		// Cuts the key to match the length of the plain text
		else if (plain_text.length() < key.length()) {
			for (int i = 0; i < plain_text.length(); ++i) {
				keystream += key.charAt(i);
			}
		}

	}

	// Uses the keystream to encrypt the plain text using the map
	private void generate_cipher_text() {
		for (int i = 0; i < keystream.length(); ++i) {
			cipher_text += map.get(plain_text.charAt(i)).get(keystream.charAt(i));
		}
	}

	public String get_keystream() {
		return keystream;
	}

	public String get_cipher_text() {
		return cipher_text;
	}
}
