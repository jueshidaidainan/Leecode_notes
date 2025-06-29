#include<iostream>
#include<vector>
#include <numeric>
using namespace std;
/*
最后一块石头的重量：
思路很巧妙：将此题和分割等和子集联系起来，将石头分为尽可能重量相等的两堆，撞击后剩下的便是最小的石头
所以剩下的就和分割等和子集(416)一样了，变为了一个01背包问题
*/
int lastStoneWeightII(vector<int>& stones) {
    int sum = accumulate(stones.begin(), stones.end(), 0);
    // int sum = 0;
    // for(int i = 0; i < stones.size(); i++) sum += stones[i]; 
    int target = sum / 2;
    vector<int>dp(target + 1);
    for(int i = 0; i < stones.size(); i++)
        for(int j = target; j >= stones[i]; j--)
        dp[j] = max(dp[j], dp[j - stones[i]] + stones[i]);

    int rest = sum - dp[target];//另一半
    return dp[target] > rest ? dp[target] - rest : rest - dp[target];
}

int main(){
    vector<int> nums = {2,7,4,1,8,1};
    lastStoneWeightII(nums);
    return 0;
}