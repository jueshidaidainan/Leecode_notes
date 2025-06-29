#include <iostream>
using namespace std;

int main()
{
    int n = 2;
    int dp[3];
    dp[0] = 0, dp[1] = 1;
    int sum = 0;
    // 使用动态规划的思路本来应该开数组的，但是考虑到我们只需要维护3个变量的值，所以使用滚动数组
    // 由于递推关系的前两个数是必须要给的所以从第2个开始使用递推公式，注意这里存在第0个数
    if (n == 1)
        return 1;
    if (n == 0)
        return 0;

    // 第n个数总共需要n-1次滚动
    else
    {
        n -= 1;
        while (n--)
        {
            sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }
        return sum;
    }
}