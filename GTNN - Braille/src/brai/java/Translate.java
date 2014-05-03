package brai.java;

public class Translate {
	String data;
	static boolean[][] braille;
	public Translate() {
		initBraille();
	}
	
	void initBraille() {
		braille = new boolean[256][6];
		braille['a'][0] = true; braille['a'][1] = false; braille['a'][2] = false; braille['a'][3] = false; braille['a'][4] = false; braille['a'][5] = false;
		braille['b'][0] = true; braille['b'][1] = false; braille['b'][2] = true; braille['b'][3] = false; braille['b'][4] = false; braille['b'][5] = false;
		braille['c'][0] = true; braille['c'][1] = true; braille['c'][2] = false; braille['c'][3] = false; braille['c'][4] = false; braille['c'][5] = false;
		braille['d'][0] = true; braille['d'][1] = true; braille['d'][2] = false; braille['d'][3] = true; braille['d'][4] = false; braille['d'][5] = false;
		braille['e'][0] = true; braille['e'][1] = false; braille['e'][2] = false; braille['e'][3] = true; braille['e'][4] = false; braille['e'][5] = false;
		braille['f'][0] = true; braille['f'][1] = true; braille['f'][2] = true; braille['f'][3] = false; braille['f'][4] = false; braille['f'][5] = false;
		braille['g'][0] = true; braille['g'][1] = true; braille['g'][2] = true; braille['g'][3] = true; braille['g'][4] = false; braille['g'][5] = false;
		braille['h'][0] = true; braille['h'][1] = false; braille['h'][2] = true; braille['h'][3] = true; braille['h'][4] = false; braille['h'][5] = false;
		braille['i'][0] = false; braille['i'][1] = true; braille['i'][2] = true; braille['i'][3] = false; braille['i'][4] = false; braille['i'][5] = false;
		braille['j'][0] = false; braille['j'][1] = true; braille['j'][2] = true; braille['j'][3] = true; braille['j'][4] = false; braille['j'][5] = false;
		braille['k'][0] = true; braille['k'][1] = false; braille['k'][2] = false; braille['k'][3] = false; braille['k'][4] = true; braille['k'][5] = false;
		braille['l'][0] = true; braille['l'][1] = false; braille['l'][2] = true; braille['l'][3] = false; braille['l'][4] = true; braille['l'][5] = false;
		braille['m'][0] = true; braille['m'][1] = true; braille['m'][2] = false; braille['m'][3] = false; braille['m'][4] = true; braille['m'][5] = false;
		braille['n'][0] = true; braille['n'][1] = true; braille['n'][2] = false; braille['n'][3] = true; braille['n'][4] = true; braille['n'][5] = false;
		braille['o'][0] = true; braille['o'][1] = false; braille['o'][2] = false; braille['o'][3] = true; braille['o'][4] = true; braille['o'][5] = false;
		braille['p'][0] = true; braille['p'][1] = true; braille['p'][2] = true; braille['p'][3] = false; braille['p'][4] = true; braille['p'][5] = false;
		braille['q'][0] = true; braille['q'][1] = true; braille['q'][2] = true; braille['q'][3] = true; braille['q'][4] = true; braille['q'][5] = false;
		braille['r'][0] = true; braille['r'][1] = false; braille['r'][2] = true; braille['r'][3] = true; braille['r'][4] = true; braille['r'][5] = false;
		braille['s'][0] = false; braille['s'][1] = true; braille['s'][2] = true; braille['s'][3] = false; braille['s'][4] = true; braille['s'][5] = false;
		braille['t'][0] = false; braille['t'][1] = true; braille['t'][2] = true; braille['t'][3] = true; braille['t'][4] = true; braille['t'][5] = false;
		braille['u'][0] = true; braille['u'][1] = false; braille['u'][2] = false; braille['u'][3] = false; braille['u'][4] = true; braille['u'][5] = true;
		braille['v'][0] = true; braille['v'][1] = false; braille['v'][2] = true; braille['v'][3] = false; braille['v'][4] = true; braille['v'][5] = true;
		braille['w'][0] = false; braille['w'][1] = true; braille['w'][2] = true; braille['w'][3] = true; braille['w'][4] = false; braille['w'][5] = true;
		braille['x'][0] = true; braille['x'][1] = true; braille['x'][2] = false; braille['w'][3] = false; braille['w'][4] = true; braille['w'][5] = true;
		braille['y'][0] = true; braille['y'][1] = true; braille['y'][2] = false; braille['y'][3] = true; braille['y'][4] = true; braille['y'][5] = true;
		braille['z'][0] = true; braille['z'][1] = false; braille['z'][2] = false; braille['z'][3] = true; braille['z'][4] = true; braille['z'][5] = true;		
	}
	
	boolean[][] convertWord(String word){
		boolean[][] result = new boolean[8][6];
		for(int i = 0; i < word.length(); i++){
			char a = word.charAt(i);
			result[i] = addcode(a);
		}
		return result;
	}
	
	
	boolean[] addcode(char a) {
		boolean symbol[] = new boolean[6];
		symbol[0] = braille[a][0];
		symbol[1] = braille[a][1];
		symbol[2] = braille[a][2];
		symbol[3] = braille[a][3];
		symbol[4] = braille[a][4];
		symbol[5] = braille[a][5];
		return symbol;
	}
	
	public void setData(String index) {
		data = index;
	}
	
	public void printWord() {
		boolean symbol[][] = convertWord(data);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < data.length(); j++)
				System.out.printf((symbol[j][0 + i * 2] ? "1" : "0") + "" + (symbol[j][1 + i * 2] ? "1" : "0") + "  ");
			System.out.println();
		}
	}
	
	public String getBrailleString() {
		String result = "";
		boolean symbol[][] = convertWord(data);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < data.length(); j++)
				result += ((symbol[j][0 + i * 2] ? "1" : "0") + "" + (symbol[j][1 + i * 2] ? "1" : "0") + "  ");
			result += "\n";
		}		
		return result;
	}
	
	public byte[] getBrailleByte() {
		byte result[] = new byte[data.length()];
		boolean symbol[][] = convertWord(data);
		for (int j = 0; j < data.length(); j++) {
			byte sum = 0;
			for (int i = 0; i < 6; i++) {
				sum += Math.pow(2, i) * (symbol[j][i]?1:0);
			}
			result[j] = sum;
//			System.out.print(sum + " ");
		}
		return result;

	}

}
