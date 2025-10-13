public class extra-713-乘积小于k的子数组 {
    
}

//和209是一个思路的滑动窗口，只是这里因为是要找严格小于k的子数组，所以while循环之后prod就是严格小于k的。而且这里是所有子数组，所以ans是累加，虽然看似像是求区间的元素个数，但是因为子数组的个数恰好和区间元素个数相等。
//注意k取0或者1的时候，要是不写最开始的if语句进行过滤。就需要在while循环里加上left <= right这个判断。避免窗口的left跑太远越界。
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // if(k <= 1) return 0;
        int prod = 1;
        int left = 0;
        int ans = 0;

        for(int right = 0; right < nums.length; right++){
            prod *= nums[right];
            while(left <= right && prod >= k){
                prod /= nums[left];
                left++;
            }
            ans += right - left + 1;
        }
        return ans;
    }
}