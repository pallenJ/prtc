package prtc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
public class Prtc {
	public static void main(String args[]) {
		String path = "C:\\Users\\준모\\Desktop";//
		String input = "\\corpus.txt";
		String output = "\\output.txt";
		try {
			Method m = new Method(path, input, output);
			m.finalCal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
class Method {
	private Map<String,Integer> pattern;
	
	public void finalCal() throws Exception {
		
		BufferedReader in = new BufferedReader(new FileReader(new File(path + input)));
		BufferedWriter out = new BufferedWriter(new FileWriter(path+output,true));
		String s;
		int k=0;
		while ((s=in.readLine())!=null) {
			
			for (String p : pattern.keySet()) {
				int num = pattern.get(p)+search(s, p).size();
				pattern.put(p, num);
			}
			
			System.out.println(k++);
			
		}
		Set<String> print =new TreeSet<>(pattern.keySet());
		for (String p : print) {
			out.write(p+" "+pattern.get(p));
			out.newLine();
		}
		in.close();
		
	
		out.close();
	}
	
	public int count(String p) throws Exception {
		
		BufferedReader in = new BufferedReader(new FileReader(new File(path + input)));
		String s;
		int num = 0;
		while ((s = in.readLine()) != null) {
			
		num+= search(s, p).size();
		}
		/*System.out.println(num);*/
		in.close();
		return num;
	}
	
	private Map<String,Integer> ptrIdx() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File(path + input)));
		Map<String,Integer> ptn = new HashMap<>();
		String s;
		while ((s=in.readLine())!=null) {
			String [] temp = s.split(" ");
			for (String string : temp) {
				ptn.put(string,0);
			}
			
		}
		in.close();
		return ptn;
	}
	
	public Set<Integer> search(String s, String p) {
		Set<Integer> sIdx = new HashSet<>();
		char[] str = s.toCharArray();
		char[] ptn = p.toCharArray();
		int[] pi = getPi(p);
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			while (cnt > 0 && str[i] != ptn[cnt]) {
				cnt = pi[cnt - 1];
			}
			try {
				
			if (str[i] == ptn[cnt]) {
				if (cnt == p.length() - 1) {
					sIdx.add(i - p.length() + 1);
					cnt = pi[cnt];
				} else {
					cnt++;
				}
			}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return sIdx;
	}
	private int[] getPi(String s) {
		int[] pi = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			pi[i] = pi(s, i + 1);
		}
		return pi;
	}
	private int pi(String s, int i) {
		if (i == 0)
			return 0;
		s = s.substring(0, i);
		int result = 0;
		for (int j = 1; j <= i / 2; j++) {
			if (!s.substring(0, j).equals(s.substring(i - j, i))) {
				continue;
			} else if (result < j) {
				result = j;
			}
		}
		return result;
	}
	private String path;
	private String input;
	private String output;
	
	
	public Method(String path, String input, String output) throws Exception {
		super();
		this.path = path;
		this.input = input;
		this.output = output;
		pattern = ptrIdx();
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
}