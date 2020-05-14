package boj.boj_2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  logic 1
 *  - 입력을 받으면서 값의 최소와 최대를 구한다.
 *  - 2중 for문으로 탐색을 하면서 장맛비의 값보다 작고 방문을 하지 않았다면 bfs로 물에 잠기게 한다.
 *  - bfs를 돌때는 8방향으로 돈다.
 *  
 *  logic 2
 *  - 2중 for문으로 물에 잠기게 하는 것보다
 *  - 4방 탐색을 해서 안잠기는 영역을 구하는 것이 훨씬 더 빠르다.
 * 
 */

public class Main {
    static int N;
    static int board[][];
    static boolean visited[][];
    static int min, max;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N+1][N+1];

        min = 987654321;
        max = 0;

        // 입력을 받으면서 최대값과 최솟값을 구한다.
        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < N; ++x){
                board[y][x] = Integer.parseInt(st.nextToken());
                if(min > board[y][x]) min = board[y][x];
                if(max < board[y][x]) max = board[y][x];
            }
        }

        // 정답을 세는 변수
        int cnt = 0;
        int ansCnt = 0;
        // 1 - 최솟값부터 최댓값까지만 확인을 한다.
        // 2 - 아무지역도 안잠길수도 있으므로 min - 1 부터 확인을 한다.
        for(int rain = min - 1; rain <= max; ++rain){
            // 비의 양에 따라 새로 확인을 해야하므로
            // 방문처리를 위한 배열을 초기화
            visited = new boolean[N+1][N+1];
            cnt = 0;
            for(int y = 0; y < N; ++y){
                for(int x = 0; x < N; ++x){
                    // 물에 안잠긴다면
                    // 방문을 아직 안했다면
                    if(rain < board[y][x] && !visited[y][x]){
                        bfs(y,x, rain);
                        cnt++;
                    }
                }
            }

            // 다 확인했다면 최댓값을 찾는다.
            if(ansCnt < cnt)
                ansCnt = cnt;
        }
        System.out.println(ansCnt);
    }

    // 4방 탐색을 위한 맵핑배열
    static int dy[] = {0,0,1,-1};
    static int dx[] = {1,-1,0,0};    

    private static void bfs(int sy, int sx, int rain){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sy, sx));
        visited[sy][sx] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int dir = 0; dir < 4; ++dir){
                int ny = cur.y + dy[dir];
                int nx = cur.x + dx[dir];

                // 맵 밖으로 나가거나
                if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                // 이미 방문했다면
                if(visited[ny][nx]) continue;
                // 물에 잠긴다면
                if(board[ny][nx] <= rain) continue;

                visited[ny][nx] = true;
                q.add(new Point(ny,nx));
            }
        }
    }
}

// 좌표 처리를 위한 클래스
class Point{
    int y, x;
    Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}