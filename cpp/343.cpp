#include<iostream>
#include<vector>
using namespace std;
/*
整数拆分:将此数拆分为尽可能相差不大的数所得的积会大一些（所以遍历的时候到j/2即可）
据说C++11之后max可以接受两个以上参数，但是我这边我看源码只能接受两个参数
max函数接收三个参数的原因：将此数拆分为两个数的积，三个及三个以上的数的积，以及拆分过程情况的保存
*/
int integerBreak(int n) {
    vector<int>dp(n + 1);
    dp[0] = 0;
    dp[1] = 0;
    dp[2] = 1;
    int tmp = 0;
    for(int i = 3; i <= n; i++)
        for(int j = 1; j <= i / 2; j++){
            // dp[i] = max({(i * (i - j)), (j * dp[i - j]), dp[i]});
            tmp = max(j * (i - j), j * dp[i - j]);
            dp[i] = max(dp[i], tmp);
            // cout << dp[i] << endl;
        }
    return dp[n];
}

int main(){
    integerBreak(10);
    return 0;
}