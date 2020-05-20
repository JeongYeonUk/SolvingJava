package boj.boj_2636;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int board[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        board = new int[N+1][M+1];

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                board[y][x] = sc.nextInt();
            }
        }

        int time = 0;
        int ans = 0;
        while(true){
            if(check()){
                break;
            }

            // 녹는 치즈 찾기
            // 치즈를 녹이면 다 녹아 없어 질수 있으므로
            // 치즈의 갯수를 미리 받아 놓는다.
            int cheese[][] = new int[N+1][M+1];
            ans = findCheese(cheese);

            // 치즈 제거
            removeCheese(cheese);

            time++;
        }
        System.out.println(time);
        System.out.println(ans);
        sc.close();
    }

    private static void removeCheese(int cheese[][]){
        
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                // 녹는 치즈라면
                if(cheese[y][x] == 1){
                    board[y][x] = 0;
                }
            }
        }
    }

    private static boolean check(){
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(board[y][x] == 1)
                    return false;
            }
        }
        return true;
    }

    static int dy[] = {0,0,1,-1};
    static int dx[] = {1,-1,0,0};
    private static int findCheese(int cheese[][]){
        boolean visited[][] = new boolean[N+1][M+1];

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0));
        visited[0][0] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int dir = 0; dir < 4; ++dir){
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                if(ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if(visited[ny][nx]) continue;
                
                visited[ny][nx] = true;

                // 치즈 일 때
                // 녹을 치즈이므로 치즈 배열에다가 표시를 해준다.
                if(board[ny][nx] == 1){
                    cheese[ny][nx] = 1;
                    cnt++;
                    continue;
                }
                // 치즈가 아닐 때
                // q에다가 넣어서 bfs를 돌린다.
                q.add(new Point(ny,nx));
            }
        }

        return cnt;
    }
}

class Point{
    int y, x;
    Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}