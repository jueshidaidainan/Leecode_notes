public class 15_三数之和 {
    
}

// 这道题的可以算是两数之和的进阶版本。
// 使用双向双指针来解决。外侧一个指针来遍历数组，两个双指针一个指向局部数组开头i+1，一个指向数组末尾n-1。
// 采用二分的思想，三数之和小于0的，则L++；大于0，则R--。和等于0，则加入结果集。
// 注意题目要求去重，所以在添加结果之后要进行去重。同时，第一个数也要进行去重操作。
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);//先排序
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {//我们本能的以为后面要访问i+2的位置，所以循环到n-2。但其实不是，是因为我们的代码逻辑L是i+1，R是n-1来利用双指针来移动。所以当i=n-1的时候，本质上就是最后一种情况了。（已经在leecode测试运行过的）
            if (i > 0 && nums[i] == nums[i - 1])//外层的去重
                continue;
            
            //下面这两行if是优化的代码，可以不写，
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0)//如果排序后的前三个元素和大于0，则后面不可能有和为0的数，可以提前结束。剪枝
                break;
            if (nums[i] + nums[n - 1] + nums[n - 2] < 0)//如果排序后的当前元素加上后面最大的两个元素<0,那么当前的i指针++。后面元素还会变大。
                continue;
            int L = i + 1;
            int R = n - 1;
            while (L < R) {
                int s = nums[i] + nums[L] + nums[R];
                if (s == 0) {
                    ans.add(List.of(nums[i], nums[L], nums[R]));//List.of()创建一个不可变list
                    L++;
                    R--;
                    while (L < R && nums[L] == nums[L - 1])//注意去重的逻辑不能写在三个if判断分支的外面。因为，if的三个分支是为了模拟类似二分的思想，s<0就L++；s>0,R--。只有在s==0的时候，才去重，为了避免得到重复的答案。
                        L++;                               //若是去重的while循环放在最外面，即使没有找到到满足条件的三元组，也会去重。就会导致一些元素本来会是结果里的一部分，但是被跳过了，破坏了我们二分的思想。
                    while (L < R && nums[R] == nums[R + 1])
                        R--;
                } else if (s < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return ans;
    }
}