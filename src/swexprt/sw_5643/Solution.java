package swexprt.sw_5643;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, ans;
    static List<List<Integer>> tall;
    static List<List<Integer>> small;
    static boolean visit[];
    static int result[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; ++t){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());

            tall = new ArrayList<List<Integer>>();
            small = new ArrayList<List<Integer>>();

            for(int n = 0; n < N + 1; ++n){
                tall.add(new ArrayList<>());
                small.add(new ArrayList<>());
            }

            for(int m = 0; m < M; ++m){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                tall.get(a).add(b);
                small.get(b).add(a);
            }

            result = new int[N + 1];
            visit = new boolean[N + 1];

            // 자신보다 큰 사람이 몇명인지 계산한다.
            for(int i = 1; i <= N; ++i){
                Arrays.fill(visit, false);
                ans = 0;
                taller(i);
                result[i] = ans;
            }

            // 자신보다 작은 사람이 몇명인지 계산한다.
            for(int i = 1; i <= N; ++i){
                Arrays.fill(visit, false);
                ans = 0;
                smaller(i);
                result[i] += ans;
            }

            // 최종 계산 결과가 사람 수와 동일하다면
            // 자신이 몇번째인지 알 수 있다.
            int cnt = 0;
            for(int i = 1; i <= N; ++i){
                if(result[i] + 1 == N)
                    cnt++;
            }
            System.out.println("#" + t + " " + cnt);
        }
    }

    private static void taller(int start){
        int size = tall.get(start).size();
        if(size == 0) return;

        for(int i = 0; i < size; ++i){
            if(!visit[tall.get(start).get(i)]){
                visit[tall.get(start).get(i)] = true;
                ans++;
                taller(tall.get(start).get(i));
            }
        }
        return;
    }

    private static void smaller(int start){
        int size = small.get(start).size();
        if(size == 0) return;

        for(int i = 0; i < size; ++i){
            if(!visit[small.get(start).get(i)]){
                visit[small.get(start).get(i)] = true;
                ans++;
                smaller(small.get(start).get(i));
            }
        }
        return;
    }
}