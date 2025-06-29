/*
不同的二叉搜索树：二叉搜索树的定义见700
主要思路：利用dp的思想将n个数值按照头节点的值进行分类讨论，考虑左右子树的情况，这下只考虑左右子树的节点的数量
但是左右子树节点的数量可以由头节点的数值来进行推算j-1和i-j的由来，每种情况相乘
各个情况相加即可
*/ 
class Solution {
public:
    int numTrees(int n) {
        vector<int>dp(n + 1);
        dp[0] = 1;
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= i; j++)
                dp[i] += dp[j - 1] * dp[i - j];
        return dp[n];
    }
};