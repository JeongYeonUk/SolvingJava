package swexpert.sw_7502;

import java.util.Scanner;

public class Solution {
	static int cache[] = new int[100000];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; ++t) {
			int num = sc.nextInt();
			System.out.println("#" + t + " " + solve(num));
		}
	}
	
	private static int solve(int num) {
		if(cache[num] != 0) return cache[num];
		if(num < 10) return 0;
		else {
			int count = counting(num);
			int left = num;
			int right = 0;
			int idx = 1;
			for(int i = 0; i < count; ++i) {
				// 2개로만 나뉠 때
				right += (left % 10) * idx;
				left /= 10;				
				cache[num] = Math.max(cache[num], solve(left * right) + 1);
				
				// 그 이상일 때
				// 왼쪽에 대해서
				int val = 1;
				int temp = left;
				int tRight = right;
				while(temp >= 10) {
					int tCount = counting(temp);
					for(int j = 0; j < tCount; ++j) {
						cache[num] = Math.max(cache[num], solve(divide(temp, j + 1) * tRight) + val);
					}
					tRight *= temp % 10;
					temp /= 10;
					val++;
				}
				
				// 그 이상일 때
				// 오른쪽에 대해서
				val = 1;
				int tLeft = left;
				temp = right;
				while(temp >= 10) {
					int tCount = counting(temp);
					for(int j = 0; j < tCount; ++j) {
						cache[num] = Math.max(cache[num], solve(divide(temp, j + 1) * tLeft) + val);
					}
					tLeft *= temp % 10;
					temp /= 10;
					val++;
				}
				
				idx *= 10;
			}
			return cache[num];
		}
	}
	
	// 나눌 횟수
	private static int counting(int num) {
		int ret = 0;
		while(num >= 10) {
			num /= 10;
			ret++;
		}
		return ret;
	}
	
	private static int divide(int num, int count) {
		int left = num;
		int right = 0;
		int idx = 1;
		for(int i = 0; i < count; ++i) {
			right += (left % 10) * idx;
			left /= 10;
		}
		return left * right;
	}
}
