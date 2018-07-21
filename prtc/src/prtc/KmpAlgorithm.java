package prtc;

public class KmpAlgorithm {
	  int[] fail;

	  
	  public static void main(String[] args) {
	   String s = "ㅂㅜㄱㅎㅏㄴㅇㅣ ㄷㅐㅎㅘㅇㅔ ㅇㅡㅇㅎㅏㄱㅔ ㄷㅚㄴ ㄱㅓㅅㄷㅗ ㅈㅔㅈㅐㅇㅔ "
	   		+ "ㅇㅢㅎㅐ ㅎㅏㄹ ㅅㅜ ㅇㅓㅄㅇㅣ ㅇㅡㅇㅎㅏㄴ ㄱㅓㅅㅇㅣㄹㅏㄴㅡㄴ ㅂㅜㄴㅅㅓㄱㄷㅗ ㅇ"
	   		+ "ㅣㅆㄷㅏ. ㄱㅣㅁㅇㅕㅇㅊㅓㄹㅇㅣ ㄴㅠㅇㅛㄱㅇㅡㄹ ㅎㅚㄷㅏㅁㅈㅏㅇㅇㅡㄹㅗ ㅌㅐㄱㅎㅏㄴ"
	   		+ " ㄱㅓㅅㄷㅗ ㅂㅜㄱㅎㅏㄴ ㅇㅠㅇㅔㄴㄷㅐㅍㅛㅂㅜㄹㅡㄹ ㅌㅗㅇㅎㅐ ㅂㅗㄴㄱㅜㄱ ㅈㅣㅊㅣㅁㅇㅡ"
	   		+ "ㄹ ㅅㅣㄹㅅㅣㄱㅏㄴㅇㅡㄹㅗ ㅂㅏㄷㅇㅡㄹ ㅅㅜ ㅇㅣㅆㄱㅣ ㄸㅐㅁㅜㄴㅇㅡㄹㅗ ㅇㅏㄹㄹㅕㅈㅕㅆㄷㅏ. "
	   		+ "ㅎㅏㄴ ㅇㅚㄱㅛ ㅅㅗㅅㅣㄱㅌㅗㅇㅇㅡㄴ \"ㄱㅣㅁㅇㅕㅇㅊㅓㄹㅇㅡㄴ ㄱㅣㅁㅈㅓㅇㅇㅡㄴㅇㅢ ㅈㅣㅅㅣ ㅇ"
	   		+ "ㅓㅄㅇㅣㄴㅡㄴ ㅇㅓㄸㅓㄴ ㅎㅏㅂㅇㅢㄷㅗ ㅎㅏㅈㅣ ㅇㅏㄶㅇㅡㄹ ㄱㅓㅅ\"ㅇㅣㄹㅏㄱㅗ ㅎㅐㅆㄷㅏ. ㄷㅐㅌㅗㅇ"
	   		+ "ㄹㅕㅇㅇㅢ ㅈㅣㅊㅣㅁ ㅇㅏㄹㅐ ㅇㅜㅁㅈㅣㄱㅇㅣㄴㅡㄴㄱㅓㅅㅇㅣㄷㅏ. ㅍㅗㅁㅍㅔㅇㅣㅇㅗ ㄱㅜㄱㅁㅜㅈㅏㅇㄱㅘㄴ"
	   		+ "ㅇㅡㄴ 30ㅇㅣㄹ ㅇㅗㅎㅜ 2ㅅㅣ ㅂㅐㄱㅇㅏㄱㄱㅘㄴㅇㅔ ㄷㅡㄹㄹㅓ ㅌㅡㄹㅓㅁㅍㅡ ㄷㅐㅌㅗㅇㄹㅕㅇㅇㅡㄹ"
	   		+ " ㅁㅏㄴㄴㅏㄴ ㄷㅟ ㄴㅠㅇㅛㄱㅇㅡㄹㅗ ㄸㅓㄴㅏㄴㄷㅏ.";
	   
	       KmpAlgorithm ka = new KmpAlgorithm(s.length());
	       System.out.println(ka.search(s, "ㅂ",1));
	 }
	  
	  public KmpAlgorithm(int size) {
	    fail = new int[size];
	  }

	  public void fail(String pattern) {
	    int i, j;
	    fail[0] = -1;
	    for (i = 1; i < pattern.length(); i++) {
	      j = fail[i - 1] + 1;
	      while (pattern.charAt(i) == pattern.charAt(j) && j > 0) {
	        j = fail[j - 1] + 1;
	      }
	      if (pattern.charAt(i) == pattern.charAt(j)) {
	        fail[i] = j;
	      } else {
	        fail[i] = -1;
	      }
	    }
	  }

	  public int search(String strings, String pattern, int startIndex) {
	    int i, j;
	    for (i = startIndex, j = 0; i < strings.length(); i++, j++) {
	      System.out.println(i + " " + j);
	      if (j == pattern.length() - 1 && strings.charAt(i) == pattern.charAt(j)) {
	        return i - j;
	      }
	      while ((strings.charAt(i) != pattern.charAt(j)) && (j > 0)) {
	        j = fail[j - 1] + 1;
	      }
	      if (strings.charAt(i) != pattern.charAt(j)) {
	        j = -1;
	        System.out.println(i + " " + j);
	      }
	    }
	    return -1;
	  }

	  public void printFail(String strings, String pattern) {
	    for (int i = 0; i < strings.length(); i++) {
	      System.out.print("\t" + i);
	    }
	    System.out.println();
	    for (int i = 0; i < strings.length(); i++) {
	      System.out.print("\t" + strings.charAt(i));
	    }
	    System.out.println();
	    for (int i = 0; i < pattern.length(); i++) {
	      System.out.print("\t" + pattern.charAt(i));
	    }
	    System.out.println();
	    for (int i = 0; i < fail.length; i++) {
	      System.out.print("\t" + fail[i]);
	    }
	    System.out.println();
	  }
	}