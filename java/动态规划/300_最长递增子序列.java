//f[i]表示以nums[i]为结尾的最长递增子序列的长度。所以数组的大小也不需要是n+1了。
//然后使用两层for循环来模拟递归。外层循环遍历nums数组，内层循环遍历i之前的元素，如果nums[j] < nums[i]，则f[i] = Math.max(f[i], f[j]);
//注意内层循环外的++f[i]是必要的，因为nums[i]其本身也是为1的最长递增子序列。
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int [] f = new int[n];
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            ans = Math.max(ans, ++f[i]);//无论内层for循环的if语句执行与否，这里+1都是必要的
        }
        return ans;
    }

}