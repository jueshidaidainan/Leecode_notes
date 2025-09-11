//题目要求在O(n)的时间复杂度完成
//思路：利用set，将数组中的元素放入set中，然后从set中取出一个元素，如果这个元素前面有元素，则跳过（前面的元素存在，则说明肯定会有更长的连续序列）
//如果没有，则开始向右寻找，直到没有元素为止，求出最长的连续序列的长度
//注意我们是对set进行遍历，而不是nums，这样更快，而且遍历nums也有可能会出现超时的情况
//小优化：如果当前序列的长度已经超过了数组长度的一半，则直接返回，因为最长子序列的长度不会超过set长度的一半
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for(int num : nums){
            st.add(num);
        }

        int m = st.size();
        int ans = 0;
        for(int x : st){
            if(st.contains(x - 1)){
                continue;
            }
            int y = x + 1;
            while(st.contains(y)){
                y++;
            }
            ans = Math.max(ans, y - x);//结束的时候是y-1，长度应该是y - 1 - x + 1
            if(ans > m / 2) break;
        }
        return ans;
    }
}