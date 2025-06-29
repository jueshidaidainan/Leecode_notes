 #include<iostream>
 #include<vector>
 using namespace std;
/*
分割等和子集：
首先判断集合的所有元素的和是否为偶数，如果不是偶数则无法进行等和子集的拆分
如果为偶数，则转化为一个01背包问题，其中背包的最大容量我这里认为是和target一致，注意背包是要从0开始的
物品的体积和价值是相等的
整体思路写好测评的时候不断出现超时或者超出内存的问题，debug不出来，因为code运行无误，无奈和carl的代码比对发现
是sum定义但没有进行初始化导致的。奇怪为什么code定义但不初始化可以运行成功，以后还是随手初始化吧，这种求和的特别
容易出问题。
*/
bool canPartition(vector<int>& nums) {
    int sum = 0;
    for(int i = 0; i < nums.size(); i++) sum += nums[i];
    int target = sum / 2;
    int r = sum % 2;
    if(r == 1) return false; 
    vector<int>dp(target  + 1);
    for(int i = 0; i < nums.size(); i++)
        for(int j = target; j >= nums[i]; j-- )
            dp[j] = max(dp[j], dp[j - nums[i]] + nums[i]);
    if(dp[target] == target) return true;      
    return false;        
}


int main(){
    vector<int> nums = {1,2,3,5};
    cout << canPartition(nums) ;
    system("pause");
    return 0;
}


