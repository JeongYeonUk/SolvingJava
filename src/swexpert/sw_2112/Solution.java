package swexpert.sw_2112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {	

	static int[][] cell;
	static int[] drug;
	static int T, D, W, K, ans;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; ++t) {
			st = new StringTokenizer(bf.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cell = new int[D + 1][W + 1];
			drug = new int[D + 1];
			
			for(int d = 0; d < D; ++d) {
				st = new StringTokenizer(bf.readLine());
				for(int w = 0; w < W; ++w) {
					cell[d][w] = Integer.parseInt(st.nextToken());
				}
			}
			
			Arrays.fill(drug, -1);
			
			ans = -1;
			int y = 0;
			while(ans == -1) {
				solve(0, 0, y++);
			}
			
			System.out.println("#" + t + " " + ans);
		}
	} // end main

	static boolean isPossible() {
		for(int w = 0; w < W; ++w) {
			int cnt = 1;
			int cur = drug[0] != -1 ? drug[0] : cell[0][w];
			for(int d = 1; d < D; ++d) {
				int next = drug[d] != -1 ? drug[d] : cell[d][w];
				if(cur == next) {
					cnt++;
				} else {
					cnt = 1;
					cur = next;
				}
				
				if(cnt >= K)
					break;
			}
			if(cnt < K)
				return false;
		}		
		return true;
	}
	
	static void solve(int idx, int cnt, int y) {
		if(cnt == y) {
			if(isPossible()) {
				ans = y;
			}
			return;
		}
		
		for(int i = idx; i < D; ++i) {
			for(int type = 0; type < 2; ++type) {
				drug[i] = type;
				solve(i + 1, cnt + 1, y);
				drug[i] = -1;
			}
		}
	}
}
