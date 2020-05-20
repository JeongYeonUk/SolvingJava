package swexpert.sw_5644;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static int M, A;
    static int a[], b[];
    static int retA[], retB[];
    static BC bc[];
    static ArrayList<Integer> board[][];
    static POS pA, pB;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; ++test_case) {
            M = sc.nextInt();
            A = sc.nextInt();

            a = new int[M + 1];
            b = new int[M + 1];
            retA = new int[M + 1];
            retB = new int[M + 1];
            for (int m = 0; m < M; ++m) {
                a[m] = sc.nextInt();
            }
            for (int m = 0; m < M; ++m) {
                b[m] = sc.nextInt();
            }

            board = new ArrayList[11][11];
            
            for(int x = 0; x < 11; ++x) {
            	for(int y = 0; y < 11; ++y) {
            		board[x][y] = new ArrayList<>();
            	}
            }
            

            bc = new BC[A + 1];
            for (int i = 1; i <= A; ++i) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();
                int p = sc.nextInt();
                bc[i] = new BC(x,y,c,p);
                preWork(bc[i].x, bc[i].y, bc[i].c, bc[i].p, i);
            }

//            for(int x = 1; x < 11; ++x) {
//            	for(int y = 1; y < 11; ++y) {
//            		if(board[x][y].isEmpty())
//            			System.out.print(0 + " ");
//            		else
//            			System.out.print(board[x][y].get(0) + " ");
//            	}
//            	System.out.println();
//            }
            
            pA = new POS(1, 1);
            pB = new POS(10, 10);

            for (int m = 0; m <= M; ++m) {
                charge(m);
                if(m == M) break;
                move(m);
            }

//            for(int m = 0; m <= M; ++m) {
//            	System.out.println(m + " " + retA[m] + " " + retB[m]);
//            }
            
            int ans = 0;
            for(int m = 0; m <= M; ++m){
                ans += retA[m] + retB[m];
            }
            System.out.println("#" + test_case + " " + ans);
        }
    }

    private static void move(int idx){
        int anx = pA.x + dx[a[idx]];
        int any = pA.y + dy[a[idx]];
        int bnx = pB.x + dx[b[idx]];
        int bny = pB.y + dy[b[idx]];

        pA = new POS(anx, any);
        pB = new POS(bnx, bny);
    }

    private static void charge(int idx) {
        ArrayList<Integer> aBC = new ArrayList<>();
        ArrayList<Integer> bBC = new ArrayList<>();
        if (!board[pA.y][pA.x].isEmpty()) {
            for (int i = 0, size = board[pA.y][pA.x].size(); i < size; ++i) {
                aBC.add(board[pA.y][pA.x].get(i));
            }
        }

        if (!board[pB.y][pB.x].isEmpty()) {
            for (int i = 0, size = board[pB.y][pB.x].size(); i < size; ++i) {
                bBC.add(board[pB.y][pB.x].get(i));
            }
        }

        if (!aBC.isEmpty() && !bBC.isEmpty()) {
            if (aBC.size() < bBC.size()) {
                int aMax = 0;
                int bMax = 0;
                int sumMax = 0;
                int aTemp = 0;
                int bTemp = 0;
                int sumTemp = 0;
                for (int i = 0, aSize = aBC.size(); i < aSize; ++i) {
                    for (int j = 0, bSize = bBC.size(); j < bSize; ++j) {
                        // 같은 BC 범위에 있을 때
                        if (aBC.get(i) == bBC.get(j)) {
                            sumTemp = bc[aBC.get(i)].p;
                            if(sumMax < sumTemp){
                                aMax = sumTemp >>> 1;
                                bMax = sumTemp >>> 1;
                                sumMax = sumTemp;
                            }
                        } else {
                            aTemp = bc[aBC.get(i)].p;
                            bTemp = bc[bBC.get(j)].p;
                            if(sumMax < aTemp + bTemp){
                                aMax = aTemp;
                                bMax = bTemp;
                                sumMax = aTemp + bTemp;
                            }
                        }
                    }
                }
                retA[idx] = aMax;
                retB[idx] = bMax;
            } else {
                int aMax = 0;
                int bMax = 0;
                int sumMax = 0;
                int aTemp = 0;
                int bTemp = 0;
                int sumTemp = 0;
                for (int i = 0, bSize = bBC.size(); i < bSize; ++i) {
                    for (int j = 0, aSize = aBC.size(); j < aSize; ++j) {
                        // 같은 BC 범위에 있을 때
                        if (bBC.get(i) == aBC.get(j)) {
                            sumTemp = bc[aBC.get(j)].p;
                            if(sumMax < sumTemp){
                                aMax = sumTemp >>> 1;
                                bMax = sumTemp >>> 1;
                                sumMax = sumTemp;
                            }
                        } else {
                            aTemp = bc[aBC.get(j)].p;
                            bTemp = bc[bBC.get(i)].p;
                            if(sumMax < aTemp + bTemp){
                                aMax = aTemp;
                                bMax = bTemp;
                                sumMax = aTemp + bTemp;
                            }
                        }
                    }
                }
                retA[idx] = aMax;
                retB[idx] = bMax;
            }
        } else if (!aBC.isEmpty()) {
            int aMax = 0;
            for (int i = 0, aSize = aBC.size(); i < aSize; ++i) {
                if (aMax < bc[aBC.get(i)].p) {
                    aMax = bc[aBC.get(i)].p;
                }
            }
            retA[idx] = aMax;
        } else if (!bBC.isEmpty()) {
            int bMax = 0;
            for (int i = 0, bSize = bBC.size(); i < bSize; ++i) {
                if (bMax < bc[bBC.get(i)].p) {
                    bMax = bc[bBC.get(i)].p;
                }
            }
            retB[idx] = bMax;
        }
    }

    static int dy[] = { 0, -1, 0, 1, 0 };
    static int dx[] = { 0, 0, 1, 0, -1 };

    private static void preWork(int x, int y, int c, int p, int idx) {
        boolean visited[][] = new boolean[11][11];
        Queue<POS> q = new LinkedList<POS>();
        q.add(new POS(x, y));
        visited[y][x] = true;
        board[y][x].add(idx);

        for (int i = 0; i < c; ++i) {
            for (int j = 0, size = q.size(); j < size; ++j) {
                POS cur = q.poll();
                for (int dir = 1; dir < 5; ++dir) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    if (nx < 1 || ny < 1 || nx >= 11 || ny >= 11)
                        continue;
                    if (visited[ny][nx])
                        continue;

                    visited[ny][nx] = true;
                    q.add(new POS(nx, ny));
                    board[ny][nx].add(idx);
                }
            }
        }

    }
}

class BC {
    int x, y, c, p;

    BC(int x, int y, int c, int p) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.p = p;
    }
}

class POS {
    int x, y;

    POS(int x, int y) {
        this.x = x;
        this.y = y;
    }
}