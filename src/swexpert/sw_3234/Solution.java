package swexpert.sw_3234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, sum, ans;
	static int weight[], dp[][];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			weight = new int[N + 1];
			
			st = new StringTokenizer(br.readLine());
			sum = 0;
			for(int i = 0; i < N; ++i) {
				weight[i] = Integer.parseInt(st.nextToken());
				sum += weight[i];
			}
			
			dp = new int[sum + 1][(1 << N)];
			
			ans = solve(0,0,0,0);
			System.out.println("#" + t + " " + ans);
		}
	}
	
	private static int solve(int depth, int left, int right, int visit) {
		if(depth == N) return 1;
		if(dp[left][visit] != 0) return dp[left][visit];
		
		int candi = 0;
		for(int i = 0; i < N; ++i) {
			if((visit & (1 << i)) != 0) continue;
			candi += solve(depth + 1, left + weight[i], right, visit |= (1 << i));
			if(left < right + weight[i]) continue;
			candi += solve(depth + 1, left, right + weight[i], visit |= (1 << i));
		}
		return dp[left][visit] = candi;
	}
}
