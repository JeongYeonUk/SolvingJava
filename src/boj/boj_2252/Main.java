package boj.boj_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int indegree[];
    static ArrayList<ArrayList<Integer>> adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList<ArrayList<Integer>>();
        indegree = new int[N + 1];
        for(int i = 0; i < N + 1; ++i){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < M; ++i){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adj.get(A).add(B);
            adj.get(B).add(A);
            indegree[B]++;
        }

        topologySort();
    }

    private static void topologySort(){
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 1; i <= N; ++i){
            if(indegree[i] == 0) q.add(i);
        }

        while(q.isEmpty() == false){
            int cur = q.peek(); q.poll();
            for(int i = 0, size = adj.get(cur).size(); i < size; ++i){
                int next = adj.get(cur).get(i);
                if(--indegree[next] == 0) q.add(next);
            }
            System.out.print(cur + " ");
        }

    }
}