/*
目标和;
此题可以使用回溯法进行求解，但是较优的解决方案是01背包的滚动数组一维解法
positive + negative = sum 和 positive - negative = target 可得 positive = (sum + target) / 2
所以我们要求的既是装满positive的方案数
递推公式便是结合物品与之前方案数的和
注意：测试用例后面几个是target为负且绝对值大于sum的情况，需要特殊处理
*/
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int sum = accumulate(nums.begin(), nums.end(), 0);
        if((sum + target) % 2 == 1 || abs(target) > sum) return 0;
        int postive =  (sum + target) / 2;
        vector<int>dp(postive + 1);
        dp[0] = 1;
        for(int i = 0; i < nums.size(); i++)
            for(int j = postive; j >= nums[i]; j--)
                dp[j] += dp[j - nums[i]];
        return dp[postive];
    }
};