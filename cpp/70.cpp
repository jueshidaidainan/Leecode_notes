class Solution {
//爬楼梯：难在递推公式的推导，推导出来你会发现其实是斐波那契数列罢了
public:
    int climbStairs(int n) {
        int dp[4];
        int sum = 0;
        dp[1] =  1, dp[2] = 2;
        if(n < 3) return n;
        else{
            n -= 2;
            while(n-- ){
                sum = dp[1] + dp[2];
                dp[1] = dp[2];
                dp[2] = sum;
            }
        }
        return sum;
    }
};