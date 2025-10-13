public class 209_长度最小的子数组 {
    
}

//这道题的暴力解法可能会想到两层for循环，但是这样会超时。
//使用滑动窗口双指针的方法，虽然看似是for循环里面有while循环，但是实际上还是O(n)的复杂度。因为窗口只会遍历数组一次，考虑算法真正的运行情况的话就可以理解了。
//思路：枚举滑动窗口的右边界，先加入sum，然后如果sum >= target，则移动左边界，注意while循环里是提前判断要是左边界的这次右移会使sum<target，while循环就会退出。所以实际上退出while循环之后，sum还是大于target的。
//注意更新答案的时候也要判断sum >= target，因为sum刚开始可能是小于target的，这种情况建议加上，礼多人不怪嘛。
        int left = 0;
        int sum = 0;
        int ans = n + 1;//答案不可能为n+1

        for(int right = 0; right < n; right++){
            sum += nums[right];
            while(sum - nums[left] >= target){//当left == right的时候这个条件不成立，所以不需要left < right这个判断条件了
                sum -= nums[left];
                left++;
            }
            if(sum >= target){
                ans = Math.min(ans, right - left + 1);
            }
        }

        return ans <= n  ? ans :  0;
    }
}