package 普通数组;

//这个是一道技巧性比较强题。使用三次翻转即可。
//但是要注意三次翻转的顺序，不同的顺序对应的结果也不一样。
//  (反转局部 -> 反转局部 -> 反转整体) 实现的是向左旋转。
// 标准的向右旋转问题的解法顺序是 (反转整体 -> 反转局部 -> 反转局部)。
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;//k次轮转相当于不变
        reverse(nums, 0, len - 1);//翻转整个数组
        reverse(nums, 0,  k - 1);//翻转前k个
        reverse(nums, k, len - 1);//翻转后len-k个
    }

    private void reverse(int[] nums, int l, int r){
        int tmp = 0;
        while(l < r){
            tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }
}