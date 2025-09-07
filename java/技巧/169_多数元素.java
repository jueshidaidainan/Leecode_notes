//这道题用投票算法，时间复杂度O(n)，空间复杂度O(1)
//可以理解为打擂台，hp代表血量，每当遇见相同的数就将血量+1，遇见和擂主不同的数就将血量-1，最后剩下的肯定是个数最多的那个。
//如果刚开始守擂的数不是众数，那更好了，相当于其他数在内战。众数的胜率又增大了。
class Solution {
    public int majorityElement(int[] nums) {
        int ans = 0;
        int hp = 0;
        for(int x : nums){
            if(hp == 0){//换擂主了，上一个擂主死了
                ans = x;
                hp = 1;
            }else{
                hp += ans == x ? 1 : -1; //和擂主相同就血量+1，否则-1
            }
        }
        return ans;
    }
}