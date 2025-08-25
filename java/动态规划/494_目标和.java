//这里灵神将题目转化为了0-1背包问题，所有的数的和为sum的时候，如果取的正数的绝对值是p，负数的绝对值是q，就可以简单推导出p和q与target和sum的关系。（可以看灵神的题解）
// https://leetcode.cn/problems/target-sum/solutions/2119041/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-s1cx/

//其余的就是01背包需要注意的问题了：
//初始化的二维矩阵的大小是多少；初始值是多少；双层for循环的的遍历边界和顺序；递推公式的写法；返回值是哪一个
//使用for循环来写的迭代写法
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int x : nums) sum += x;

        sum -= Math.abs(target);
        if(sum < 0 || sum % 2 == 1){
            return 0;
        }
        
        int m = sum / 2;//背包容量
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];//n + 1是因为我们将递推公式等价替换为了i+1和i的关系，m+1是因为容量可能会等于m，如果数组大小为m，则会少一个可能
        f[0][0] = 1;//这个是循环写法的初始条件，其实也就是递归思考时候的递归出口

        for(int i = 0; i < n; i++){
            int x = nums[i];
            for(int c = 0; c < m + 1; c++){
                if(c < x) {
                    f[i + 1][c] = f[i][c];//只能不选
                }else{
                    f[i + 1][c] = f[i][c] + f[i][c - x];//不选 + 选
                }
            }
        }
        return f[n][m];
    }
}






//滚动数组的解法
//还可以使用一个数组倒序来写，现阶段没有优化到这个地步
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int x : nums) sum += x;

        sum -= Math.abs(target);
        if(sum < 0 || sum % 2 == 1){
            return 0;
        }
        
        int m = sum / 2;//背包容量
        int n = nums.length;
        int[][] f = new int[2][m + 1];//我们发现递推公式，只会使用两行数组，通过循环中的行号%2,刚好可以让数组滚动起来
        f[0][0] = 1;//这个是循环写法的初始条件，其实也就是递归思考时候的递归出口

        for(int i = 0; i < n; i++){
            int x = nums[i];
            for(int c = 0; c < m + 1; c++){
                if(c < x) {
                    f[(i + 1) % 2][c] = f[i % 2][c];//只能不选
                }else{
                    f[(i + 1) % 2][c] = f[i % 2][c] + f[i % 2][c - x];//不选 + 选
                }
            }
        }
        return f[n % 2][m];//最后的值在哪一行也需要%2
    }
}