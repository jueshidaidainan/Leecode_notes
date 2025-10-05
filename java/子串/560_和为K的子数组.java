//这里的子数组类似子串一样，需要是连续的。因为数组的元素是存在负值的，所以最简单的方式就是三次遍历数组，但是时间复杂度太高了。
//由于存在负值，而且要求连续的子数组，我们自然想到用前缀和，注意前缀和多开一个空间。preSum[i]表示0 ~ i-1的和，preSum[0]=0。i~j的和为preSum[j+1] - preSum[i]。
//使用前缀和：[i,j]如果符合条件，说明preSum[j+1]-preSum[i] = k，也就是preSum[i] = preSum[j+1] - k。也就是遍历preSum，看之前的前缀和里值为preSum[j+1] - k的有多少。
//这个查找步骤用map优化比较好，而且注意查找map的时候，肯定是j前面的位置，也就是说可以边赋值前缀和，边负赋值map且查找，完成一次遍历（灵神的这个题解我没写，过于精炼反倒可读性不高了）。
class Solution {
public int subarraySum(int[] nums, int k) {
        int ans = 0;
        int len = nums.length;
        int[] preSum = new int[len + 1];
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + nums[i];//preSum[i]表示0 ~ i-1的和，preSum[0]=0
        } 

        Map<Integer, Integer> cnt = new HashMap<>(len + 1);
        for(int pre : preSum){
            ans += cnt.getOrDefault(pre - k, 0);
            cnt.merge(pre, 1, Integer::sum);//pre在map里不存在就创建并加入，否则旧值加一
        }
        return ans;
    }
}

//题解的暴力优化方法，从左到右遍历数组的每个元素，看其字串是否达到要求，由于下个字串的和可以利用上一个子串的和加上即将遍历的元素求得，因此将三次方的复杂度降低到了二次方
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

//当初自己写的，有错误
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