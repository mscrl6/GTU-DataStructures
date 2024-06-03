public class preprocessor {
	private String initial_string;
	private String preprocessed_string;

	public preprocessor(String str) {
		initial_string = str;
		preprocessed_string = "";
	}

	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}

	private void capitalize() {
		initial_string = initial_string.toUpperCase();
	}

	private void clean() {
		for (char x : initial_string.toCharArray()) {
			if (x >= 'A' && x <= 'Z') {
				preprocessed_string += x;
			}
		}
	}

	public String get_preprocessed_string() {
		return preprocessed_string;
	}
}