//借用了35题的解法：可以找到>=x的第一个位置，所以第二个位置就是>=x+1 的位置-1
//要有第一个位置，则肯定有第二个位置；若第一个位置都没有，则第二个位置也不用判断了

class Solution {
    int[] ans = new int[] { -1, -1 };
    public int[] searchRange(int[] nums, int target) {
        // if (nums.length == 0)
        //     return ans;
        int start = searchIndex(nums, target);
        int end = searchIndex(nums, target + 1) - 1;
        if (start == nums.length || nums[start] != target) {
            return ans;
        }
        // System.out.println(start);

        ans[0] = start;
        ans[1] = end;
        return ans;
    }

    private int searchIndex(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // if(nums[mid] == target){ 直接返回的话，对于有多个targt的情况下，找到的不一定是最左边的target，唯一target才可以
            //     return mid;
            // }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {// 相等的时候，右边界左移，mid也会减小，所以会逐渐找到相同target的左边界。要是判断大于target的逻辑，则会找到相同target的右边界
                r = mid - 1;
            }
        }
        return l;
    }
}