package boj.boj_1062;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int n, k, max = Integer.MIN_VALUE;
	static String[] word;
	static boolean[] alpha = new boolean[26];
	static ArrayList<Character> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()) - 5;
		if (k < 0) {
			System.out.println(0);
			return;
		}
		word = new String[n];
		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			word[i] = temp.substring(4, temp.length() - 4); // [rc, hello, car]
		}
		setAlpha(new char[] { 'a', 'c', 'i', 'n', 't' });
		list = getList(); // [r, e, h, l, o]
		if (list.size() <= k)
			max = n;
		else
			comb(0, 0);
		System.out.println(max);
	}

	static void comb(int start, int depth) {
		if (depth == k) {
			getMin();
			return;
		}
		for (int i = start; i < list.size(); i++) {
			char c = list.get(i);
			alpha[c - 'a'] = true;
			comb(i + 1, depth + 1);
			alpha[c - 'a'] = false;
		}
	}

	static void getMin() {
		int count = 0;
		boolean flag;
		for (int i = 0; i < n; i++) {
			flag = true;
			for (int j = 0; j < word[i].length(); j++) {
				char c = word[i].charAt(j);
				if (!alpha[c - 'a']) {
					flag = false;
					break;
				}
			}
			if (flag)
				count++;
		}
		if (max < count)
			max = count;
	}

	static ArrayList<Character> getList() {
		HashSet<Character> set = new HashSet<>();
		ArrayList<Character> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < word[i].length(); j++) {
				char c = word[i].charAt(j);
				if (!alpha[c - 'a'])
					set.add(c);
			}
		}
		result.addAll(set);
		return result;
	}

	static void setAlpha(char[] character) {
		for (char c : character) {
			alpha[c - 'a'] = true;
		}
	}
}
