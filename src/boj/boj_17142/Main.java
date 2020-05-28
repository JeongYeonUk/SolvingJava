package boj.boj_17142;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class VIRUS{
		int y, x;
		VIRUS(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static int N, M, ans, cnt, blank;
	static int board[][];
	static boolean used[];
	static List<VIRUS> list = new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		board = new int[N + 1][N + 1];
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				board[y][x] = sc.nextInt();
				if(board[y][x] == 2) {
					cnt++;
					list.add(new VIRUS(y,x));
				} else if(board[y][x] == 0) {
					blank++;
				}
			}
		}
		
		if(blank == 0) {
			System.out.println(0);
			return;
		}
		
		used = new boolean[cnt];
		ans = 987654321;
		
		solve(0, 0);
		System.out.println((ans == 987654321 ? -1 : ans));
	}
	
	static int dy[] = {0,0,1,-1};
	static int dx[] = {1,-1,0,0};
	private static int bfs() {
		Queue<VIRUS> q = new LinkedList<>();
		int backup[][] = new int[N+1][N+1];
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				backup[y][x] = board[y][x];
			}
		}
		
		for(int i = 0; i < cnt; ++i) {
			if(used[i]) {
				q.add(list.get(i));
			}
		}		
		int candi = 0;
		int size = 0;
		boolean isUpdate = false;		
		while(true) {
			isUpdate = false;
			size = q.size();
			for(int i = 0; i < size; ++i) {
				VIRUS cur = q.poll();
				backup[cur.y][cur.x] = 3;
				for(int dir = 0; dir < 4; ++dir) {
					int ny = cur.y + dy[dir];
					int nx = cur.x + dx[dir];
					if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
					if(backup[ny][nx] == 2) {
						for(int ddir = 0; ddir < 4; ++ddir) {
							int nny = ny + dy[ddir];
							int nnx = nx + dx[ddir];
							if(nny < 0 || nnx < 0 || nny >= N || nnx >= N) continue;
							if(backup[nny][nnx] == 0) {
								q.add(new VIRUS(ny, nx));
								isUpdate = true;
								break;
							}
						}
						continue;
					}
					if(backup[ny][nx] != 0) continue;
					backup[ny][nx] = 3;
					q.add(new VIRUS(ny, nx));
					isUpdate = true;
				}
			}
			
			if(!isUpdate) break;
			++candi;
		}
		
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				if(backup[y][x] == 0) {
					candi = 987654321;
					break;
				}
			}
		}
		
		return candi;
	}
	
	private static void solve(int idx, int depth) {
		if(depth == M) {
			int candi = bfs();
			if(ans > candi)
				ans = candi;
			return;
		}
		
		for(int i = idx; i < cnt; ++i) {
			used[i] = true;
			solve(i + 1, depth + 1);
			used[i] = false;
		}
	}

}
























