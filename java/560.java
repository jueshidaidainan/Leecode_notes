class Solution {
    // 我这个写成了一个针对正数的方法（也是利用到了累计的和，将其保存来移动两个指针，但是元素有负数，这样数组和的单调性无法保证，也就无法适用了），但是题目描写里是会有负数的
    //下面采用前缀和的方法，因此，我们可以猜想，是不是如果元素有负值，使得累计和不是非递减的，就需要考虑前缀和的方法
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        int res = 0;
        for(int i = 0, j  = 0; i < len ; i++){
            if(nums[i] == k){
                count++;
                // i++;
            }
            // if(nums[i] > k){
            //     // i++;
            // }
            if(nums[i] < k){              
                while(res < k && j < len){
                    if(j == 0) j = i;
                    res += nums[j++];
                }
                if(res == k){
                    count++;
                    // i++;
                }//else{
                    // i++;
                // }
                res -= nums[i];
            }
        }
        return count;
    }
}
//题解的暴力优化方法，从左到右遍历数组的每个元素，看其字串是否达到要求，由于下个字串的和可以利用上一个字串的和加上即将遍历的元素求得，因此将三次方的复杂度降低到了二次方
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        for(int i = 0; i < len ; i++){//遍历外层的每个元素
            int sum  = 0;
            for(int j = i; j < len; j++){//遍历字串
                sum += nums[j];
                if(sum == k) count++;
            }
        }
        return count;
    }
}

//前缀和
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        int [] preSum = new int[len + 1];
        for(int i = 0; i < len; i++){//初始化前缀和，注意下标偏移一下
            preSum[i + 1] = preSum[i] + nums[i];
        }
        for(int l  = 0; l < len; l++){//遍历寻找符合条件的
            for(int r = l; r < len; r++){
                if(preSum[r + 1] - preSum[l] == k) count++;//本来寻思这里，既然下标都往后偏移一位，preSum[l]应该是preSum[l - 1]，发现是没正确理解前缀和与这道题目的关系
            }                                              // [i,j]的字串的和表示为前缀和应该是：到j的和 - 到i-1的和，即preSum[j] - preSum[i - 1]（在我们初始化为下标偏移一位的情况下）
        }
        return count;
    }
}