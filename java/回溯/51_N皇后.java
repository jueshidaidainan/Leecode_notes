//N皇后问题：使用回溯来解决，要求每行每列只有一个皇后，且对角线上也不能有皇后。
//用一个数组queens来表示每行的皇后的列数，queens[i]表示第i行皇后的列数。onPath[]数组来表示某列是否放了皇后就可保证行列不重复。
//但是对角线如何判断呢：我们从头开始遍历行的话，只需要↖️和↗️这两个方向判断即可。观察这种条件下的坐标：规律是已经放置了皇后的行和列的和与列和行和列的差值，与当前行和列的和或差相等，则判断对角线上已经有了皇后（valid函数中判断）。
class Solution {
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        List<List<String>> ans = new ArrayList<>();
        boolean[] onPath = new boolean[n];
        dfs(queens, ans, onPath, n, 0);
        return ans;

    }

    private void dfs(int[] queens, List<List<String>> ans, boolean[] onPath, int n, int r) {
        if (r == n) {
            List<String> board = new ArrayList<>(n);//棋盘的一个放法
            for (int c : queens) {
                char[] row = new char[n];//构建行
                Arrays.fill(row, '.');
                row[c] = 'Q';
                board.add(new String(row));
            }
            ans.add(new ArrayList<>(board));
            return;
        }

        for (int c = 0; c < n; c++) {
            if (!onPath[c] && valid(r, c, queens)) {
                queens[r] = c;
                onPath[c] = true;
                dfs(queens, ans, onPath, n, r + 1);//只有成功放置之后才会递归
                onPath[c] = false;//恢复现场
            } 
        }
    }

    private boolean valid(int r, int c, int[] queens){
        for(int R = 0; R < r; R++){//只需要判断r行前的即可，因为是递增行判断的
            int C = queens[R];
            if(R + C == r + c || R - C == r -c){
                return false;
            }
        }
        return true;
    }
}