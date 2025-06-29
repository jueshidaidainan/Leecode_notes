/*
不同路径：
dp[i][j]表示到达此处的路径数。
*/
class Solution {
public:
    int uniquePaths(int m, int n) {
        int dp[m][n];
        //初始化最左和最上方的格子为1
        for(int i = 0; i < m; i++) dp[i][0] = 1;
        for(int j = 0; j < n; j++) dp[0][j] = 1;
        //从左到右、从上到下进行遍历
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        return dp[m-1][n-1];
    }
};