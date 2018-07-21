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
public class Prtc {
	public static void main(String args[]) {
		String path = "C:\\Users\\준모\\Desktop";//
		String input = "\\corpus.txt";
		String output = "\\output.txt";
		try {
			Method m = new Method(path, input, output);
			m.finalCal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
class Method {
	

	public void finalCal() throws Exception {
/*		Map<String,Integer> result = new HashMap<>();*/
		/*System.out.println(pattern.size());*/
		Map<String,Set<String>> ptrIdx = new HashMap<>();
		
		BufferedReader in = new BufferedReader(new FileReader(new File(path + input)));
		BufferedWriter out = new BufferedWriter(new FileWriter(path+output,true));
		String s;
		int k=0;
		while ((s=in.readLine())!=null) {
			
			String [] temp = s.split(" ");
			
			for (String string : temp) {
				if(string.length()>12||string.length()<2) continue;
				String key = "";
				try {
					key = string.substring(0,1);
				} catch (Exception e) {
					continue;
				}
				
				int n = count(string);
				
				if(ptrIdx.get(key)==null) {
					ptrIdx.put(key, new HashSet<>());
					ptrIdx.get(key).add(string);
				}
				else if(ptrIdx.get(key).contains(string)) {
					continue;
				}else {
				ptrIdx.get(key).add(string);
				}
				out.write(string+" "+n);
				out.newLine();
			}
			System.out.println(k++);
			
		}
		in.close();
		
		
		/*for (String string : ptrIdx) {
			out.write(string+" "+idx.get(string.substring(0,1)).get(string));
			out.newLine();
		}*/
		
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
	
	/*private Set<String> ptrIdx() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File(path + input)));
		Set<String> pattern = new HashSet<>();
		String s;
		while ((s=in.readLine())!=null) {
			String [] temp = s.split(" ");
			for (String string : temp) {
				pattern.add(string);
			}
			
		}
		in.close();
		return pattern;
	}
*/	
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