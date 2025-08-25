//这是一道典型的完全背包的问题
//状态转移方程：f[i][c] = Math.min(f[i - 1][c], f[i][c - coins[i]] + 1);
//完全背包就是一个物品可以无限取，也就提现在转移方程里，取了i之后还可选取i
//注意初始化的时候，二位数组的第一行都是最大值，f[0][0]=0表示coin为0，代表这个结果是符合题意的；
//其余i=0的时候，不符合题意就返回一个无穷大数（/2防止溢出），求最小值自然就过滤了
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int [][]  f = new int[n + 1][amount + 1];
        Arrays.fill(f[0], Integer.MAX_VALUE / 2);//除2防止下面 + 1溢出
        f[0][0] = 0;

        for(int i = 0; i < n; i++){
            for(int c = 0; c <= amount; c++){
                if(c < coins[i]){
                    f[i + 1][c] = f[i][c];
                }else{
                    f[i + 1][c] = Math.min(f[i][c], f[i + 1][c - coins[i]] + 1);
                }
            }
        }

        int ans = f[n][amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;

    }
}


//可以对比看一下从i-1转移到i的边界处理，帮助理解。
//其实这两种状态转移方程的目的都是对二维数组进行赋值，只是循环变量的表示方式不一样了，我们需要清楚知道当下赋值的是i还是i+1，所需要的值存在哪个下标里就行了。
class Solution {
    public int numSquares(int n) {
        int N = (int)Math.sqrt(n);
        int[][] f = new int[N + 1][n + 1];

        Arrays.fill(f[0], Integer.MAX_VALUE / 2);//i为0时，不合理的地方都初始化为无穷大
        f[0][0] = 0;//这是合理的出口
        for(int i = 1; i <= N; i++){//状态转移方程i从i-1得到
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