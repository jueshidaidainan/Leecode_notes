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