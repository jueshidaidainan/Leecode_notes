//这道题采用双指针的思路：很容易想到交换两个指针的元素，但是先入为主想到的是一头一尾两个指针，但是其实两个指针都是从头开始遍历。
//一个指针指向第一个零，另一个指针指向从左到右的第一个非零元素，然后交换两个指针的元素，并维护指针。
//非0指针遇到0会继续前进寻找非0值，而0指针比较慢。因为，如果刚开始就是非0值，那么会元素自己交换行，等于没动，这样两个指针都移动，总会遇到0，然后非0指针超过了0指针。进入交换正轨。
class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;//指向从左向右的第一个0
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                //交换0和非零元素
                int tmp = nums[i];
                nums[i] = nums[index];
                nums[index] = tmp;
                index++;//交换后的指0指针要移动
            }
        }
    }
}


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

