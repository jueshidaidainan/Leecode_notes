//题目要求子数组的乘积是最大的，但是给定的数组中可能包含0和负数
// 思路：用两个数组，一个存储当前位置的乘积最大值，一个存储当前位置的乘积最小值，这样可以简化考虑两个数的符号问题了。然后返回fmax的最大值即可
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] fMax = new int[n];
        int[] fMin = new int[n];
        int ans = nums[0];
        fMax[0] = fMin[0] = nums[0];

        for(int i = 1; i < n; i++){
            int x = nums[i];
            fMax[i] = Math.max(Math.max(fMax[i - 1] * x, fMin[i - 1] * x), x);
            fMin[i] = Math.min(Math.min(fMax[i - 1] * x, fMin[i - 1] * x), x);
            ans = Math.max(ans, fMax[i]);
        }
        // return Arrays.stream(fMax).max().getAsInt();
        return ans;
    }
}