//这道题使用回溯递归的方法很好理解，有点类似于八皇后。翻译为递推需要注意的是，初始化的处理。（见下面注释）
class Solution {
    public int uniquePaths(int m, int n) {
        int [][] f = new int [m + 1][n + 1];
        f[0][1] = 1;//本来翻译自递归应该是f[1][1]=1,但是循环里会覆盖掉使其变为0，反正第一行和第一列是代表边界（不会用），那起始点对应的边界就要为起始点服务
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                f[i + 1][j + 1] = f[i][j + 1] + f[i + 1][j];
            }
        }
        return f[m][n];
    }
}