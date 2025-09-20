//这个写法会超时
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int resMax = Integer.MIN_VALUE; 
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = i; j < n; j++){
                sum += nums[j];
                resMax = Math.max(resMax, sum);
            }
        }
        return resMax;
    }
}   

//想用前缀和来求解，求出最大前缀和与最小前缀和的差值，来降低复杂度，思路没问题，
//但是这个解法若是数组全为负值，最大前缀和反而是代表较少元素的和，最小前缀和代表较多元素的和，与前缀和差表示子数组的和这一概念相违背
//而且resMin的初始化不应该是int最大值，而应该是0，因为若数组全部为正，做差就没必要了，数组和即可，初值为0就很妙
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int resMax = Integer.MIN_VALUE;
        // int resMin = Integer.MAX_VALUE;
        int resMin = 0;
        int preSum [] = new int [n + 1]; 
        if(n == 1) return nums[0];
        for(int i = 0; i < n; i++){
            preSum[i + 1] = preSum[i] + nums[i];
            System.out.print(preSum[i + 1]);
            resMax = Math.max(resMax, preSum[i + 1]);
            resMin = Math.min(resMin, preSum[i + 1]);
        }

        return resMax - resMin;
    }
}

// 这就是前缀和的正确使用，含有顺序，每次都用前缀和减去目前的最小前缀和，在这个过程中更新维护最小前缀和与要求的最大值
// 因为子数组的和就可以表示为两个前缀和的差
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int resMax = Integer.MIN_VALUE;
        int preMin = 0;
        int preSum = 0;

        for(int x : nums){
            preSum += x;
            resMax = Math.max(resMax, preSum - preMin);
            preMin = Math.min(preMin, preSum);
        }
        return resMax;
    }
}

//动态规划的写法
//快刷完one hot之后发现这个写法，也很好理解。子数组因为存在负数，而且要是连续的，所以每一段的和加上当前的x，若是小于了pre，说明还不如从当前位置开始重新计算；反之，则加上当前位置的数。
//同时类似贪心的思路，维护答案即可。
class Solution {
    public int maxSubArray(int[] nums) {
        // 构造前缀和
        int max = nums[0];
        int pre = 0; // 记录可能出现的最大值
        for(int x : nums){
            pre = Math.max(x, pre + x);
            max = Math.max(max, pre);
        }
        return max;
    }
}