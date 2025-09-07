// 颜色分类,要使用O(1)空间复杂度和O(n)的时间复杂度
// 思路：[0, p0) 是0的区间，(p1, n - 1]是2的区间，[p0, i)是1的区间，[i, p1]是未知的区间
// 遍历的时候遇见了0，则和p0交换，p0++，i++；  交换后的nums[i]是1，所以i++；
// 遇见了2，则和p1交换，p1--；因为不确定交换后的值，所以i不变
// 其他情况也就是1，i++
class Solution {
    public void sortColors(int[] nums) {
        int p0 = 0;
        int p1 = nums.length - 1;

        int i = 0;//遍历指针   
        while (i <= p1) {//只需要处理到p1这段即可，后面的是2
            if (nums[i] == 0) {
                swap(nums, p0, i);
                p0++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, p1);
                p1--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}