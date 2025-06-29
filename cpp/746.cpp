/*
最小代价爬楼梯：dp[i]表示在第i阶台阶的最小代价，顶部是指cost的下一阶
*/
class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int dp[3];
        dp[0] = 0, dp[1] = 0;
        int sum =  0;
        //特殊情况：无法满足递推关系的时候
        if( cost.size() == 2) return min(cost[0], cost[1]);
        if( cost.size() == 1) return cost[0];
        else{
            //边界条件：取=是因为要计算到边界条件的下一个
            for(int i = 2; i <= cost.size(); i++){
                //递推关系
               sum = min(dp[0] + cost[i - 2], dp[1] + cost[i -1]);
               dp[0] = dp[1];
               dp[1] = sum;
            }
        }
        return sum;
    }
};