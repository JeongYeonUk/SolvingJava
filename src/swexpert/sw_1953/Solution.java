package swexpert.sw_1953;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static class Info {
		int y, x, type;

		Info(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}

	static int N, M, L, ans;
	static int hy, hx;
	static int board[][];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; ++t) {
			N = sc.nextInt();
			M = sc.nextInt();
			hy = sc.nextInt();
			hx = sc.nextInt();
			L = sc.nextInt();

			board = new int[N + 1][M + 1];
			for (int y = 0; y < N; ++y) {
				for (int x = 0; x < M; ++x) {
					board[y][x] = sc.nextInt();
				}
			}
			
			ans = 0;
			bfs();
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void print(boolean visited[][]) {
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < M; ++x) {
				if(y == hy && x == hx) {
					System.out.print(2 + " ");
					continue;
				}
				if(visited[y][x])
					System.out.print(1 + " ");
				else
					System.out.print(0 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static boolean isNextPossible(int dir, int next) {
		if(dir == 0) {
			if(next == 1 || next == 3 || next == 6 || next == 7)
				return true;
		} else if(dir == 1) {
			if(next == 1 || next == 3 || next == 4 || next == 5)
				return true;
		} else if(dir == 2) {
			if (next == 1 || next == 2 || next == 4 || next == 7)
				return true;
		} else if(dir == 3) {
			if (next == 1 || next == 2 || next == 5 || next == 6)
				return true;
		}
		return false;
	}
	
	// 0 : 오
	// 1 : 왼
	// 2 : 아래
	// 3 : 위
	private static boolean isTypePossible(int type, int dir, int next) {
		if(type == 1) {
			return isNextPossible(dir, next);
		}else if (type == 2) {
			return isNextPossible(dir, next);
		} else if (type == 3) {
			return isNextPossible(dir, next);			
		} else if (type == 4) {
			return isNextPossible(dir, next);
		} else if (type == 5) {
			return isNextPossible(dir, next);
		} else if (type == 6) {
			return isNextPossible(dir, next);
		} else if (type == 7) {
			return isNextPossible(dir, next);
		}
		return true;
	}

	// 0 : 오
	// 1 : 왼
	// 2 : 아래
	// 3 : 위
	private static boolean isDirPossible(int type, int dir) {
		if (type == 2) {
			if (dir == 0 || dir == 1)
				return false;
		} else if (type == 3) {
			if (dir == 2 || dir == 3)
				return false;
		} else if (type == 4) {
			if (dir == 1 || dir == 2)
				return false;
		} else if (type == 5) {
			if (dir == 1 || dir == 3)
				return false;
		} else if (type == 6) {
			if (dir == 0 || dir == 3)
				return false;
		} else if (type == 7) {
			if (dir == 0 || dir == 2)
				return false;
		}
		return true;
	}

	static int dy[] = { 0, 0, 1, -1 };
	static int dx[] = { 1, -1, 0, 0 };

	private static void bfs() {
		Queue<Info> q = new LinkedList<>();
		q.add(new Info(hy, hx, board[hy][hx]));

		boolean visited[][] = new boolean[N + 1][M + 1];
		visited[hy][hx] = true;

		int size;
		while (L != 0) {
			size = q.size();
			for (int i = 0; i < size; ++i) {
				ans++;
				Info cur = q.poll();
				for (int dir = 0; dir < 4; ++dir) {
					int ny = cur.y + dy[dir];
					int nx = cur.x + dx[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M)
						continue;
					if (visited[ny][nx] || board[ny][nx] == 0)
						continue;
					if (!isDirPossible(cur.type, dir))
						continue;
					if(!isTypePossible(cur.type, dir, board[ny][nx]))
						continue;
					
					visited[ny][nx] = true;
					q.add(new Info(ny, nx, board[ny][nx]));
				}
			}
			--L;
		}		
	}

}
