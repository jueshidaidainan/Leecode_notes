//在刚开始看灵神的题解的时候，已经看到了这道题。但是写的时候，把f0和f1的赋值顺序搞反了，f1要给f0，所以不能先给f1赋值。
//思路：状态转移方程：f(i) = max(f(i-1), f(i-2) + nums[i])  也就是每个状态的最大值f(i)，取决于是否取当前位置，要是取那就是i-1（也就是f1），要是不选那就是i-2（也就是f0）加上当前位置的值nums[i]。
//也就是说当你看i位置的时候，只需要关注i-1和i-2这两个状态，所以每时每刻只需要保存这两个状态，这也就是为什么只需要保存两个变量f0和f1，不需要保存一个数组。
class Solution {
    public int rob(int[] nums) {
        int f0 = 0; 
        int f1 = 0;
        // int res = 0;
        for(int i = 0; i < nums.length; i++){
            int res = Math.max(f1, nums[i] + f0);
            f0 = f1;
            f1 = res;            
        }
        return f1;
    }
}