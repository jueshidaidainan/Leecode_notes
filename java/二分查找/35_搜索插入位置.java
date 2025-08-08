// 二分查找
// 注意：1.区间开闭
// 2.mid 避免溢出的写法
// 3. 返回值写什么

class Solution { 
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;//闭区间
        
        while(l <= r){//区间不为空的条件和 上面l r的定义match
            int mid = l + (r - l) / 2;// 化简后是(l+r)/2,且可以理解为l向右移动来区间长度的一半，符合mid定义。并且避免了溢出
            if(nums[mid] == target ) return mid;// 由于mid是向下取整的，所以按照灵神的写法，可以不用等于判断，但是还是加上吧，这样省脑子
            
            if(nums[mid] > target){//因为是闭区间，所以l和r都是指向下一位，避免while循环进入死循环
                r = mid - 1;
            }else{
                l = mid + 1 ;
            }
            
        }
        return l;//返回值写l
    }
}