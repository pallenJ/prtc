package prtc;
import java.io.*;
import java.util.*;
import java.util.regex.*;
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
				int num = pattern.get(p)+KMP.count(s, p);
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
			
		num+= KMP.count(s, p);
		}
		/*System.out.println(num);*/
		in.close();
		return num;
	}
	
	private void setPattern() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File(path + input)));
		pattern = new HashMap<>();
		String s;
		String regex = "^[ㄱ-ㅎㅏ-ㅣ]*$";
		while ((s=in.readLine())!=null) {
			String [] temp = s.split(" ");
			
			for (String string : temp) {
				if(!Pattern.matches(regex, string)) {
				ptnInsert(string);
				}else {
				pattern.put(string, 0);	
				}
			}
			
		}
		in.close();
	}
public void ptnInsert(String str) {
		
		System.out.println(str);
		
		int i   = 0 ;
		boolean[] flag = test(str);
		int key=0;
		while(true) {
			
			if(flag[i]) {
				String s = str.substring(i-key, i);
				if(key>0&&!pattern.containsKey(s))
				{
					System.out.println("s:"+s);
					pattern.put(s, 0);
				}	
				key=0;		
			}else {
				key++;
			}
			++i;
			if(i==str.length()) break;
		}
		String s = str.substring(i-key, i);
		if(key!=0&&!pattern.containsKey(s))
		{
			System.out.println("last:"+s);
			pattern.put(s, 0);
		}
	}
	
	private boolean[] test(String s) {
		boolean[] rs = new boolean [s.length()];
		String regex = "^[ㄱ-ㅎㅏ-ㅣ]*$";
		for (int i = 0; i < rs.length; i++) {
			String temp = s.substring(i, i+1);
			rs[i] = !Pattern.matches(regex, temp);
		}
		return rs;
	}
	
	
	
	
	
	private String path;
	private String input;
	private String output;
	
	
	public Method(String path, String input, String output) throws Exception {
		super();
		this.path = path;
		this.input = input;
		this.output = output;
		setPattern();
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