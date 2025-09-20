// 本来写的是双重for循环，在每个窗口中，找到当前窗口的最大值，然后把最大值加入结果数组中。对于窗口为n/2的情况，时间复杂度是O(n^2)。
// 但是有十几个用例会超时，那怎么优化呢？遍历一次数组肯定没错，但是每次都遍历窗口内所有值，肯定是有一部分操作是浪费行为。
// 灵神的题解是维护了一个双端队列，队尾入队，队首出队。只有大于当前队尾的值，才入队。所以使用while将小于当前值的全部删除。
// 由于要判断队首元素是否在窗口内，所以队列中存存索引，而不是值。
// 移除队首元素的时候使用if而不是while，是因为窗口右侧的移动每次移动一格，左侧当然也是一格了。（while也能过就是慢）
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>(k);

        for(int i = 0; i < n; i++){
            while(!q.isEmpty() && nums[i] >= nums[q.peekLast()]){
                q.removeLast();
            }
            q.offerLast(i);

            int left = i - k + 1;//窗口左侧
            if(left > q.getFirst()){//要是大于队列首部的位置
                q.removeFirst();
            }
            if(left >= 0){
                ans[left] = nums[q.peekFirst()];
            }
        }
        return ans;
    }
}