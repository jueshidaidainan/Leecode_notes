//完全平方数可以重复选取，其实是一个完全背包问题。可以选择的数最大是√n，所以可以定义一个二维数组f[i][j]表示前i个数中，和为j的最小完全平方数个数。
//递推公式、初始化条件、在代码中很清楚。
//这份代码是我为了统一使用i和i+1的状态转移方程（和前面的题目保持一样）而使用gemini修改的，所以需要比较i+1的平方。
class Solution {

    public int numSquares(int n) {
        int N = (int)Math.sqrt(n);
        int[][] f = new int[N + 1][n + 1];

        Arrays.fill(f[0], Integer.MAX_VALUE / 2);//i为0时，不合理的地方都初始化为无穷大
        f[0][0] = 0;//这是合理的出口
        for(int i = 0; i < N; i++){
            int squre = (i + 1) * (i + 1);//虽然当前循环是i，但是递推公式考虑的是第i+1个数
            for(int j = 0; j <= n; j++){
                if(j < squre){
                    f[i + 1][j] = f[i][j];
                }else{
                    f[i + 1][j] = Math.min(f[i][j], f[i + 1][j - squre] + 1);
                }
            }
        }
        return f[N][n];//答案：使用前N个完全平方数，组成和为n
    }
}


class Solution {
    public int numSquares(int n) {
        int N = (int)Math.sqrt(n);
        int[][] f = new int[N + 1][n + 1];

        Arrays.fill(f[0], Integer.MAX_VALUE / 2);//i为0时，不合理的地方都初始化为无穷大
        f[0][0] = 0;//这是合理的出口
        for(int i = 1; i <= N; i++){//状态转移方程i从i-1得到，需要修改外层for循环
            int squre = i * i;
            for(int j = 0; j <= n; j++){
                if(j < squre){
                    f[i][j] = f[i - 1][j];
                }else{
                    f[i][j] = Math.min(f[i - 1][j], f[i][j - squre] + 1);
                }
            }
        }
        return f[N][n];
    }
}