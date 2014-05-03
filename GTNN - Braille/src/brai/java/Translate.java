package brai.java;

public class Translate {
	String data;
	public boolean[][] braille;
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
		
		for (int i = 65; i < 91; i++) 
			for (int j = 0; j < 6; j++) {
				braille[i][j] = braille[i + 32][j];
			}
		
		braille['!'][0] = false; braille['!'][1] = true; braille['!'][2] = true; braille['!'][3] = false; braille['!'][4] = true; braille['!'][5] = true;
		braille['&'][0] = true; braille['&'][1] = true; braille['&'][2] = true; braille['&'][3] = false; braille['&'][4] = true; braille['&'][5] = true;
		braille['='][0] = true; braille['='][1] = true; braille['='][2] = true; braille['='][3] = true; braille['='][4] = true; braille['='][5] = true;
		braille['('][0] = true; braille['('][1] = false; braille['('][2] = true; braille['('][3] = true; braille['('][4] = true; braille['('][5] = true;
		braille[')'][0] = false; braille[')'][1] = true; braille[')'][2] = true; braille[')'][3] = true; braille[')'][4] = true; braille[')'][5] = true;
		braille['*'][0] = true; braille['*'][1] = false; braille['*'][2] = false; braille['*'][3] = false; braille['*'][4] = false; braille['*'][5] = true;
		braille['<'][0] = true; braille['<'][1] = false; braille['<'][2] = true; braille['<'][3] = false; braille['<'][4] = false; braille['<'][5] = true;
		braille['%'][0] = true; braille['%'][1] = true; braille['%'][2] = false; braille['%'][3] = false; braille['%'][4] = false; braille['%'][5] = true;
		braille['?'][0] = true; braille['?'][1] = true; braille['?'][2] = false; braille['?'][3] = true; braille['?'][4] = false; braille['?'][5] = true;
		braille[':'][0] = true; braille[':'][1] = false; braille[':'][2] = false; braille[':'][3] = true; braille[':'][4] = false; braille[':'][5] = true;
		braille['$'][0] = true; braille['$'][1] = true; braille['$'][2] = true; braille['$'][3] = false; braille['$'][4] = false; braille['$'][5] = true;
		braille[']'][0] = true; braille[']'][1] = true; braille[']'][2] = true; braille[']'][3] = true; braille[']'][4] = false; braille[']'][5] = true;
		braille['\\'][0] = true; braille['\\'][1] = false; braille['\\'][2] = true; braille['\\'][3] = true; braille['\\'][4] = false; braille['\\'][5] = true;
		braille['['][0] = false; braille['['][1] = true; braille['['][2] = true; braille['['][3] = false; braille['['][4] = false; braille['['][5] = true;
		braille['/'][0] = false; braille['/'][1] = true; braille['/'][2] = false; braille['/'][3] = false; braille['/'][4] = true; braille['/'][5] = false;
		braille['+'][0] = false; braille['+'][1] = true; braille['+'][2] = false; braille['+'][3] = false; braille['+'][4] = true; braille['+'][5] = true;
		braille['#'][0] = false; braille['#'][1] = true; braille['#'][2] = false; braille['#'][3] = true; braille['#'][4] = true; braille['#'][5] = true;
		braille['>'][0] = false; braille['>'][1] = true; braille['>'][2] = false; braille['>'][3] = true; braille['>'][4] = true; braille['>'][5] = false;
		braille['\''][0] = false; braille['\''][1] = false; braille['\''][2] = false; braille['\''][3] = false; braille['\''][4] = true; braille['\''][5] = false;
		braille['-'][0] = false; braille['-'][1] = false; braille['-'][2] = false; braille['-'][3] = false; braille['-'][4] = true; braille['-'][5] = true;
		braille['@'][0] = false; braille['@'][1] = true; braille['@'][2] = false; braille['@'][3] = false; braille['@'][4] = false; braille['@'][5] = false;
		braille['^'][0] = false; braille['^'][1] = true; braille['^'][2] = false; braille['^'][3] = true; braille['^'][4] = false; braille['^'][5] = false;
		braille['_'][0] = false; braille['_'][1] = true; braille['_'][2] = false; braille['_'][3] = true; braille['_'][4] = false; braille['_'][5] = true;
		braille['"'][0] = false; braille['"'][1] = false; braille['"'][2] = false; braille['"'][3] = true; braille['"'][4] = false; braille['"'][5] = false;
		braille['^'][0] = false; braille['^'][1] = true; braille['^'][2] = false; braille['^'][3] = true; braille['^'][4] = false; braille['^'][5] = false;
		braille['.'][0] = false; braille['.'][1] = true; braille['.'][2] = false; braille['.'][3] = false; braille['.'][4] = false; braille['.'][5] = true;
		braille[';'][0] = false; braille[';'][1] = false; braille[';'][2] = false; braille[';'][3] = true; braille[';'][4] = false; braille[';'][5] = true;
		braille[','][0] = false; braille[','][1] = false; braille[','][2] = false; braille[','][3] = false; braille[','][4] = false; braille[','][5] = true;
		
		braille['1'][0] = false; braille['1'][1] = false; braille['1'][2] = true; braille['1'][3] = false; braille['1'][4] = false; braille['1'][5] = false;
		braille['2'][0] = false; braille['2'][1] = false; braille['2'][2] = true; braille['2'][3] = false; braille['2'][4] = true; braille['2'][5] = false;
		braille['3'][0] = false; braille['3'][1] = false; braille['3'][2] = true; braille['3'][3] = true; braille['3'][4] = false; braille['3'][5] = false;
		braille['4'][0] = false; braille['4'][1] = false; braille['4'][2] = true; braille['4'][3] = true; braille['4'][4] = false; braille['4'][5] = true;
		braille['5'][0] = false; braille['5'][1] = false; braille['5'][2] = true; braille['5'][3] = false; braille['5'][4] = false; braille['5'][5] = true;
		braille['6'][0] = false; braille['6'][1] = false; braille['6'][2] = true; braille['6'][3] = true; braille['6'][4] = true; braille['6'][5] = false;
		braille['7'][0] = false; braille['7'][1] = false; braille['7'][2] = true; braille['7'][3] = true; braille['7'][4] = true; braille['7'][5] = true;
		braille['8'][0] = false; braille['8'][1] = false; braille['8'][2] = true; braille['8'][3] = false; braille['8'][4] = true; braille['8'][5] = true;
		braille['9'][0] = false; braille['9'][1] = false; braille['9'][2] = false; braille['9'][3] = true; braille['9'][4] = true; braille['9'][5] = false;
		braille['0'][0] = false; braille['0'][1] = false; braille['0'][2] = false; braille['0'][3] = true; braille['0'][4] = true; braille['0'][5] = true;
	}
	
	boolean[][] convertWord(String word){
		boolean[][] result = new boolean[30][6];
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
