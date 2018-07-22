package prtc;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
	char c;
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	boolean isLeaf;

	public TrieNode() {
	}

	public TrieNode(char c) {
		this.c = c;
	}
}

public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(String word) {
		HashMap<Character, TrieNode> children = root.children;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			TrieNode t;
			if (children.containsKey(c)) {
				t = children.get(c);
			} else {
				t = new TrieNode(c);
				children.put(c, t);
			}
			children = t.children;
			if (i == word.length() - 1)
				t.isLeaf = true;
		}
	}

	public boolean search(String word) {
		TrieNode t = searchNode(word);
		return t != null && t.isLeaf;		
	}

	public boolean startsWith(String prefix) {
		return searchNode(prefix) == null;
		
	}

	public TrieNode searchNode(String str) {
		Map<Character, TrieNode> children = root.children;
		TrieNode t = null;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (children.containsKey(c)) {
				t = children.get(c);
				children = t.children;
			} else {
				return null;
			}
		}
		return t;
	}

}