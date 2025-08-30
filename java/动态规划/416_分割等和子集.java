//将数组的和求出，然后问题就转化为了在nums里选出和为target的子集。就转化为了一个0-1背包问题。
//表明0-1背包和完全背包是很重要的基础问题。
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for(int x : nums) sum += x;
        if(sum % 2 == 1 || n < 2) return false;//能被分割的话，数组大小至少为2，且数组的和要为偶数

        int target = sum / 2;

        boolean[][] f = new boolean [n + 1][target + 1];
        f[0][0] = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= target; j++){
                if(j < nums[i]){
                    f[i + 1][j] = f[i][j];
                }else{
                    f[i + 1][j] = f[i][j - nums[i]] || f[i][j];
                }
            }
        }
        return f[n][target];

    }
}