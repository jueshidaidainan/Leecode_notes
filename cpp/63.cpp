#include<iostream>
#include<stdio.h>
#include<vector>
using namespace std;
/*
有障碍物的路径数问题：
注意在初始化的时候，最左和最上方的格子，一但遇到障碍物，后面的就都是0了
其次，在递推关系这里，障碍物是不进行计算的
*/
int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
    int m = obstacleGrid.size();
    int n = obstacleGrid[0].size();
    if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0; 
    
    vector<vector<int>> dp(m, vector<int>(n, 0));
    for(int i = 0; i < m && obstacleGrid[i][0] == 0; i++) dp[i][0] = 1;
    for(int j = 0; j < n && obstacleGrid[0][j] == 0; j++) dp[0][j] = 1;
    for(int i = 1; i < m; i++)
        for(int j = 1; j < n; j++)
            if(obstacleGrid[i][j] == 0) dp[i][j] = dp[i - 1][j] +  dp[i][j - 1];
    return dp[m - 1][n - 1];    
}

int main(){
    vector<vector<int>> obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
    uniquePathsWithObstacles(obstacleGrid);
    return 0;
}