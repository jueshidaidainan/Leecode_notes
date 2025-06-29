#include<iostream>
#include<stdio.h>
#include<vector>
using namespace std;

int main(){
    //斐波那契数（细节处理没有做，比如n为0或者1的时候）
    vector<int>dp[n];
    dp[0] = dp[1] = 1;
    for (int i = 2; i <= n; i++ ){
        dp[n] = dp[n - 1] + dp[n - 2];
    }
    return dp[n];
    //发现我们每次只需关注三个数字，所以我们可以只维护三个数即可
    dp[0] = dp[1] = 1;
    sum = dp[0] + dp[1];
    dp[0] = dp[1];
    dp[1] = sum;
}
