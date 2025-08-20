//下面是灵神的解法，我们可以维护一个变量，来表示从之前位置的，最远可以跳到的位置max。
//在遍历的过程中维护max，如果i > max，则表示从之前的位置无法到达i，返回false。

//但是刚开始我的递归思路虽然超时了，但是可以帮助我们理解递归。（第二个解法）
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > max){//无法到达i
                return false;
            }
            max = Math.max(max, i + nums[i]);//从i最右可以到达i + nums[i]
        }
        return true;
    }
}

//这是我写的，gemini纠正过的递归写法，测试的可以过一半，剩下的会超时，但是可以更加了解递归
//1. 这道题要去从起点开始，所以主函数只需要dfs(nums, 0)
//2. dfs里递归出口，刚开始我写的是==nums.length - 1，其实>==nums.length - 1才是正确的，超过可以到达终点，我的写法范围就缩减了，会错
//3. dfs里for循环里，j不能写>=0，当j=0的时候，递归就是原地踏步，一直dfs(0)自己无限递归自己。
//4. dfs里for循环里，要判断递归的分支是否正确，如果正确就返回true，不正确就返回false。如果不写，即使递归分支正确了，返回值也丢了。
// 递归的出口，递归要滚动逼近出口，不能原地踏步。这两个核心点都写错了。
class Solution {
    public boolean canJump(int[] nums) {
        
        return dfs(nums, 0);
    }

    private boolean dfs(int[] nums, int i) {
        if (i >= nums.length - 1) {//大于等于就可以到达了，之前写==，算是写错了
            return true;
        }

        for (int j = nums[i]; j > 0; j--) {//j不能==0，当j=0的时候，递归就是原地踏步，一直dfs(0)自己无限递归自己
            if(dfs(nums, i + j)){
                return true;//这里也需要返回值，不然即使这个分支是对的，for循环还是会继续往下循环，而不是返回
            }
        }
        
        return false;//无法到达
    }
}