package boj.boj_2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *  logic 1
 *  - �엯�젰�쓣 諛쏆쑝硫댁꽌 媛믪쓽 理쒖냼�� 理쒕�瑜� 援ы븳�떎.
 *  - 2以� for臾몄쑝濡� �깘�깋�쓣 �븯硫댁꽌 �옣留쏅퉬�쓽 媛믩낫�떎 �옉怨� 諛⑸Ц�쓣 �븯吏� �븡�븯�떎硫� bfs濡� 臾쇱뿉 �옞湲곌쾶 �븳�떎.
 *  - bfs瑜� �룎�븣�뒗 8諛⑺뼢�쑝濡� �룉�떎.
 *  
 *  logic 2
 *  - 2以� for臾몄쑝濡� 臾쇱뿉 �옞湲곌쾶 �븯�뒗 寃껊낫�떎
 *  - 4諛� �깘�깋�쓣 �빐�꽌 �븞�옞湲곕뒗 �쁺�뿭�쓣 援ы븯�뒗 寃껋씠 �썾�뵮 �뜑 鍮좊Ⅴ�떎.
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

        // �엯�젰�쓣 諛쏆쑝硫댁꽌 理쒕�媛믨낵 理쒖넖媛믪쓣 援ы븳�떎.
        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < N; ++x){
                board[y][x] = Integer.parseInt(st.nextToken());
                if(min > board[y][x]) min = board[y][x];
                if(max < board[y][x]) max = board[y][x];
            }
        }

        // �젙�떟�쓣 �꽭�뒗 蹂��닔
        int cnt = 0;
        int ansCnt = 0;
        // 1 - 理쒖넖媛믩��꽣 理쒕뙎媛믨퉴吏�留� �솗�씤�쓣 �븳�떎.
        // 2 - �븘臾댁��뿭�룄 �븞�옞湲몄닔�룄 �엳�쑝誘�濡� min - 1 遺��꽣 �솗�씤�쓣 �븳�떎.
        for(int rain = min - 1; rain <= max; ++rain){
            // 鍮꾩쓽 �뼇�뿉 �뵲�씪 �깉濡� �솗�씤�쓣 �빐�빞�븯誘�濡�
            // 諛⑸Ц泥섎━瑜� �쐞�븳 諛곗뿴�쓣 珥덇린�솕
            visited = new boolean[N+1][N+1];
            cnt = 0;
            for(int y = 0; y < N; ++y){
                for(int x = 0; x < N; ++x){
                    // 臾쇱뿉 �븞�옞湲대떎硫�
                    // 諛⑸Ц�쓣 �븘吏� �븞�뻽�떎硫�
                    if(rain < board[y][x] && !visited[y][x]){
                        bfs(y,x, rain);
                        cnt++;
                    }
                }
            }

            // �떎 �솗�씤�뻽�떎硫� 理쒕뙎媛믪쓣 李얜뒗�떎.
            if(ansCnt < cnt)
                ansCnt = cnt;
        }
        System.out.println(ansCnt);
    }

    // 4諛� �깘�깋�쓣 �쐞�븳 留듯븨諛곗뿴
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

                // 留� 諛뽰쑝濡� �굹媛�嫄곕굹
                if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                // �씠誘� 諛⑸Ц�뻽�떎硫�
                if(visited[ny][nx]) continue;
                // 臾쇱뿉 �옞湲대떎硫�
                if(board[ny][nx] <= rain) continue;

                visited[ny][nx] = true;
                q.add(new Point(ny,nx));
            }
        }
    }
}

// 醫뚰몴 泥섎━瑜� �쐞�븳 �겢�옒�뒪
class Point{
    int y, x;
    Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}