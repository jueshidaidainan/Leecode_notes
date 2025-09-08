//找到给定数组的下一个字典序的排列。其实就是给定这些数字的排列，找到大于当前数字的数字，且字典序最小。
//思路：从右向左，找到第一个小于右侧相邻数字的下标i。然后再i的右侧找到最小的大于nums[i]的下标j，交换i和j。
//这时候，新的序列已经比原来的序列大了，要字典序最小，就要i右侧是递增的。直观上可能觉得一个快排什么的就解决了，但是由于j是大于nums[i]的最小的数，所以交换后，i右侧的递减性质不变。（可以举个例子看一下）
//我们只需要对i右侧的数进行反转即可。
    public void nextPermutation(int[] nums) {
        if (nums.length < 2)
            return;
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {//从右向左，找到第一个小于右侧相邻数字的下标
            i--;
        }

        if (i >= 0) {//如果找到了，从右到i找到大于nums[i]的最小的数的下标j。若是没找到（i<0）,代表这整个数组都是递减的，直接反转即可
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);//交换，之后i右边的数还是递减的
        }
        
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
