package brai.java;

import java.util.ArrayList;

public class TranslatePara {

	private String paragram;
	public TranslatePara(String para) {
		paragram = para;
	}
	
	public ArrayList<String> getArrayList() {
		ArrayList<String> result = new ArrayList<String>();
		String current = "";
		for (int i = 0; i < paragram.length(); i++) {
			if (paragram.charAt(i) == ' ') {
				if (current != "")
					result.add(current);
				current = "";
			}
			else {
				current += paragram.charAt(i);
			}
		}
		if (current != "")
			result.add(current);
		return result;
	}
	
	
}
