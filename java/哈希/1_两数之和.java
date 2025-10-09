//这是onehot的第道题，也可以看作是梦开始的地方。
//暴力解法可以两次遍历来数组来找和为target的情况，但是时间复杂度太高了。
//所以使用hashmap,在遍历的过程中，找出target - nums[i]的情况，如果存在，则返回结果。如果不存在将nums[i]和i加入map中。
//注意对于两数之和的这种情况，我们使用nums[i] 和target - nums[i]来进行一个小小的转换，简化问题。
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2]; 
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i], i);
        }
        return ans;
    }
}