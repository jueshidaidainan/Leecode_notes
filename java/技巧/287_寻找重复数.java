//n+1个整数，范围[1,n]
//n+1个数的下标刚好在0-n之间，所以我们可以把index和nums[index]看作是一个链表。
//这样这道题就变成链表环入口问题了，有重复的数，就表示有不同的索引会指向同一个值（节点），会成环。
//由于题目本身的限制，也不像链表那样涉及指向null的情况，边界条件也不用判断了。
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];//快慢指针初始化，避免直接跳出while循环
        fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int res = 0;//当快慢指针相遇的时候，再初始化一个指针，和慢指针一起移动，直到相遇，相遇的点就是环入口点
        while(res != slow){
            res = nums[res];
            slow = nums[slow];
        }

        return res;
    }
}