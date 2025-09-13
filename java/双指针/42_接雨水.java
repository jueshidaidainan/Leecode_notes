// 双指针:没个柱子能装多少水是由柱子两边的最高柱子高度的最小值和自身高度决定

// 刚开始自己写的时候，打算找到没一个凹陷的区域，然后找到区域内的边界最小值作为水位，同时计算出容量。
// 但是忽视了水位其实是由前缀最大值和后缀最大值中的最小值来决定的（也就是说视野域太小了）。
// 所以使用两个指针，一个从左往右，一个从右往左，每次找前缀最大值和后缀最大值的最小值作为水位，然后计算容量。
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int preMax = 0;
        int sufMax = 0;

        while(left < right){//因为短的那边才会被移动，所以最后相遇的地方一定是最高的柱子，是不会装雨水的
            preMax = Math.max(height[left], preMax);//前缀最大值
            sufMax = Math.max(height[right], sufMax);//后缀最大值
            if(preMax < sufMax){
                ans += preMax - height[left];
                left++;
            }else{
                ans += sufMax - height[right];
                right--;
            }
        }
        return ans;
    }
}
// 当 height[left] < height[right] 时，我们选择处理 left 指针。为什么这是绝对安全的？

// 我们要计算什么？ 我们想知道 left 指针所在位置上方能装多少水。这个水量由 min(它左边的最高墙, 它右边的最高墙) - height[left] 决定。

// 我们已知什么？

// 关键洞察：既然我们知道在右边（right 指针或更远的地方）至少存在一个比 leftMax 更高的墙（即 rightMax），那么对于 left 指针来说，决定它命运的“木桶短板”就一定是它左边的 leftMax。

// min(leftMax, 它右边的真实最高墙) 的结果必然是 leftMax。我们根本不需要知道右边那堵最高的墙到底有多高，只需要知道它比 leftMax 高就足够了！

// 做出决策

// 基于这个保证，我们可以放心地使用 leftMax 来计算 left 位置的储水量：ans += leftMax - height[left]。

// 计算完毕后，left 这个位置的任务就完成了，我们可以安全地将 left 指针向右移动一位，去处理下一个位置。

// 右指针 right 的处理逻辑完全对称。