class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        int tmp = 0;
        // System.out.println(j);
        if(nums.length <= 1) return;
        while( j <=  nums.length - 1){
            while(nums[i] != 0 && i < nums.length - 1) i++;//指向0元素, 
            while(nums[j] == 0 && j <  nums.length - 1 ) j++;//指向非0元素
            if(i < j){
                tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j++;
            }else{
                j++;
            }

        }
    }
}


class Solution {//C++的
public:
    void moveZeroes(vector<int>& nums) {
        int n = nums.size(), left = 0, right = 0;
        while (right < n) { //题解这里从头开始，将所有的非0元素都交换到前面，可能会有原地交换的嫌疑，但比起我的代码胜在优雅
            if (nums[right]) {
                swap(nums[left], nums[right]);
                left++;
            }
            right++;
        }
    }
};

