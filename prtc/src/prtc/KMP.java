package prtc;

import java.util.HashSet;
import java.util.Set;

public class KMP {

	public static int count(String s, String p) {return search(s, p).size();}
	public static Set<Integer> search(String s, String p) {
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
	
	private static  int[] getPi(String s) {
		int[] pi = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			pi[i] = pi(s, i + 1);
		}
		return pi;
	}
	
	private static  int pi(String s, int i) {
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
	
}
