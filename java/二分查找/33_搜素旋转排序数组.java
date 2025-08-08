// 借用了153的解法，找到最小值，然后和n-1比较，如果target大于最后一个值，则target只会在第一段，闭区间0～i-1，否则target在第二段
// 因为按照题目的意思，数组是被分为了两半，数组肯定存在最小值，只是这个最小值的位置是不定的，可能在两个边界，也可能在中间。但是我们按照中间来处理，特殊情况区间不合法自会处理。
class Solution {
    public int search(int[] nums, int target) {
        //与n-1比较之后，不管i在0还是n-1，区间不合法自会解决一切
        int n = nums.length;
        int i = findMin(nums);
        int ans = -2;
        if(target > nums[n - 1]){//大于最后一个值的时候，target只会在第一段，闭区间0～i-1
            ans = bSearch(nums,  target, 0, i - 1);
        }else{
            ans = bSearch(nums, target, i, n - 1);//否则target在第二段
        }
        return ans;
    }

    private int bSearch(int[] arr, int target, int l , int r){
        while(l <= r){
            int mid = (l + r) >>> 1;
            if(arr[mid] < target){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return arr[l] == target ? l : -1;
    }

    private int findMin(int[] nums){
        int n = nums.length;
        int l = 0;
        int r = n - 2;
        
        while(l <= r){
            int mid = (l + r) >>> 1;
            if(nums[mid] < nums[n - 1]){
                r = mid - 1; 
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
}