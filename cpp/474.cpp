/*
一和零： 核心在于将问题转化为01背包问题
而不同点在于背包有两个属性，0和1的数量限制，所以要进行双重循环，注意也不能将双重循环写在一个for循环里，因为
i和j不是同步变化的
递推公式：若是放入当前物品则子集个数加1
*/

class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<vector<int>> dp (m + 1, vector<int>(n + 1, 0));
        dp[0][0] = 0;
        for(string str : strs){//遍历物品
            int x = 0, y = 0;
            for(char c : str){
                if(c == '0') x++;
                else y++;
            }
            for(int i = m; i >= x; i--)//倒序遍历背包
                for(int j = n; j >= y; j--)
                    dp[i][j] = max(dp[i - x][j - y] + 1, dp[i][j]);
        }
        return dp[m][n];
    }
};