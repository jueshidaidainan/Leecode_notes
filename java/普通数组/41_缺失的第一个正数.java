//要找给定数组中缺失的最小的正数
//思路：将数组中的正数放到对应位置，然后从1开始遍历数组，找到第一个没有对应位置的数，返回该数。
// 将nums[i]看作是学号，要是i与nums[i]相等，则证明这个元素已经归位了。反之，则进行交换，将i位置的nums[i]与nums[i]的位置交换。若是为负数则忽略。
// 但是有个问题就是，如果要交换的nums[i]位置与i位置元素相等的话，比如[1,1,2]就会死循环。所以在while循环里可以使用nums[i]与nums[nums[i] - 1]是否想等来判断。
// 为了和数组的下标索引对应，要对nums[i] - 1 。
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            while(1 <= nums[i] && nums[i] <= n && nums[i] != nums[nums[i] - 1]){//学号在1~n之间的进行处理，nums[i] - 1是为了和数组索引对齐。套两层壳为了避免死循环
                int j = nums[i] - 1;
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
            }
        }

        for(int i = 0; i < n; i++){//找到第一个学号和座位不符的
            if(nums[i] != i + 1){
                return i + 1;
            }
        }

        return n + 1;//要是都符合，就返回n+1
    }
}