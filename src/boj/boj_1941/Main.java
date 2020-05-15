package boj_1941;

import java.util.Scanner;

/**
 * Main
 */
public class Main {
    static char input[][];
    static boolean visited[];
    static boolean map[][];
    static int ans, cnt;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        input = new char[5][5];

        for(int i = 0; i < 5; ++i){
            input[i] = sc.nextLine().toCharArray();
        }

        for(int i = 0; i < 25; ++i){
            visited = new boolean[25];
            map = new boolean[5][5];
            dfs(i, 1, 0);
        }
        System.out.println(ans);
        sc.close();
    }  

    private static void dfs(int pos, int cnt, int som){
        if(input[pos / 5][pos % 5] == 'S'){
            ++som;
        }

        visited[pos] = true;
        map[pos/5][pos%5] = true;

        if(cnt == 7){
            if(som >= 4){
                solve();
            }
        } else {
            for(int i = pos + 1; i < 25; ++i){
                if(!visited[i]){
                    dfs(i, cnt + 1, som);
                }
            }
        }

        visited[pos] = false;
        map[pos/5][pos%5] = false;
    }

    private static void solve(){
        for(int i = 0; i < 25; ++i){
            if(visited[i]){
                int y = i / 5;
                int x = i % 5;

                boolean checked[][] = new boolean[5][5];
                checked[y][x] = true;
                cnt = 1;
                isPossible(y, x, checked);
                return;
            }
        }
    }

    static int dy[] = {0,0,1,-1};
    static int dx[] = {1,-1,0,0};

    private static void isPossible(int sy, int sx, boolean[][] checked){
        if(cnt == 7){
            ++ans;
        } else {
            for(int dir = 0; dir < 4; ++dir){
                int ny = sy + dy[dir];
                int nx = sx + dx[dir];

                if(ny < 0 || nx < 0 || ny >= 5 || nx >= 5) continue;
                if(checked[ny][nx] || !map[ny][nx]) continue;

                checked[ny][nx] = true;
                ++cnt;
                isPossible(ny, nx, checked);
            }
        }
    }
}