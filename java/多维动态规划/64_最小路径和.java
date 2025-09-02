// 和gemini改出来的递归写法（灵神好像叫记忆化搜索）。我主要是把递归的的边界条件写错了，（0,0）是边界，返回的是图中所对应的值。
//再者是由于是从终点往回算，所以不存在i和j大于长度的情况，只有i和j小于长度的情况。
//debug的时候，发现如果返回memo[m-1][n-1]，只有一个[[0]]的时候过不去，但是仔细想递归返回值和memo的定义，直接在调用dfs的时候返回就可以了。也可以在dfs里对于（0,0）也对memo进行赋值即可。（因为本质是这种特例直接返回，没有修改memo）

public class Solution {
    private int ans = Integer.MAX_VALUE;
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int [m + 1][n + 1];

        if(m == 0) return 0;
        for(int[] row : memo ){//注意用法+
           Arrays.fill(row, -1);
        }
        return dfs(grid, m - 1, n - 1,  memo);
        
    }

    private int dfs(int[][] grid, int i, int j, int [][]memo){
        if(i < 0  || j < 0){
            return Integer.MAX_VALUE;
        }
        if(i == 0 && j == 0) return grid[0][0];
        if(memo[i][j] != -1) return memo[i][j];
        int up = dfs(grid, i, j - 1, memo);
        int left = dfs(grid, i - 1, j, memo);
        return memo[i][j] = Math.min(up, left) + grid[i][j];
    }
}


//递归写法
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int f[][] = new int[m + 1][n + 1];
        Arrays.fill(f[0], Integer.MAX_VALUE);

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                f[i + 1][0] = Integer.MAX_VALUE;
                if(i == 0 && j ==0){
                    f[1][1] = grid[i][j];
                }else{
                    f[i + 1][j + 1] = Math.min(f[i][j + 1], f[i + 1][j]) + grid[i][j];//i+1的这种递推，只是相当于将状态矩阵向右下平移，逻辑还是一样的，grrid索引当然不用改
                }
            }
        }
        return f[m][n];
    }
}