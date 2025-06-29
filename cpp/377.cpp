#include <iostream>
#include<vector>
#include<algorithm>
using namespace std;
/*
377 组合总和
和518 的零钱兑换很像，只是这里所求是排列数，所以先遍历背包再遍历物品的完全背包问题就行
只是递推公式前的判定要求很有讲究，j >= nums[i]很好理解，空间得装的下对吧，
INT_MAX - dp[j - nums[i]]而这个判定条件说是C++int类型的存储范围限制，我寻思初始化都为0，会出现越界问题，且java没有这个问题
所以，应该是越界的值应该是与所求值无关吧，或者说是我们所求的值在运算过程中不会越界。
*/
int combinationSum4(vector<int> &nums, int target)
{
    int minvalue = *min_element(nums.begin(), nums.end());
    if (minvalue > target) return 0;

    vector<int> dp(target + 1, 0);
    dp[0] = 1;
    for (int j = 0; j <= target; j++)
    { // 背包
        for (int i = 0; i < nums.size() ; i++)
        { // 物品
            if(j >= nums[i] &&  dp[j] < INT_MAX - dp[j - nums[i]]) dp[j] += dp[j - nums[i]];
            cout << dp[j] << ' ';
        }
        cout << endl;
    }

    return dp[target];
}

int main(){
    vector<int> nums = {10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200,210,220,230,240,250,260,270,280,290,300,310,320,330,340,350,360,370,380,390,400,410,420,430,440,450,460,470,480,490,500,510,520,530,540,550,560,570,580,590,600,610,620,630,640,650,660,670,680,690,700,710,720,730,740,750,760,770,780,790,800,810,820,830,840,850,860,870,880,890,900,910,920,930,940,950,960,970,980,990,111};
    combinationSum4(nums, 999);
    return 0;
}