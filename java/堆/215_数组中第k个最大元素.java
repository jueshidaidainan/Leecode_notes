// 主要是用的是快排的思路，返回第k大其实就是排序后的第n-k个
// 快排优化后的时间复杂度就是O(n)了
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
//官方题解的快速选择排序，就是对快排进行剪枝，只是将答案所在的那部分排序就可。因为每次扫描之后j的位置就确定了。
class Solution {
    int quickselect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) return quickselect(nums, l, j, k);
        else return quickselect(nums, j + 1, r, k);
    }
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect(_nums, 0, n - 1, n - k);
    }
}

//这个思路很巧妙，就是需要额外的空间，C++版本
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        return quickSelect(nums, k);
    }
    int quickSelect(vector<int>& nums, int k) {
        // 基于快排的快速选择
        // 随机选择基准数字
        int p = nums[rand() % nums.size()];
        // 将大于等于小于的元素分别放入三个数组
        vector<int> big, equal, small;
        for (int a : nums) {
            if (a < p) small.push_back(a);
            else if (a == p) equal.push_back(a);
            else big.push_back(a);
        }
        // 第k大元素在big中, 递归划分
        if (k <= big.size()) {
            return quickSelect(big, k);
        }
        // 第k大元素在small中, 递归划分
        if (big.size() + equal.size() < k) {
            return quickSelect(small, k - (big.size() + equal.size()));
        }
        // 第k大元素在equal中, 返回p
        return p;
    }
};

//这是官方给的堆排序的解法，重点得会堆排序哇。快排和堆排的区别是，堆可以动态扩充调整。但是选择快排的复杂度是O(n)，堆排的复杂度是O(nlogk)。虽然都能过
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for(int i = nums.length - 1; i > nums.length - k; i--){
            swap(nums, 0, heapSize - 1);
            heapSize--;
            maxHeaify(nums, 0, heapSize);//将堆顶元素与最后一个元素交换后，从头开始维护堆
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize){
        for(int i = heapSize / 2  - 1; i >= 0; i-- ){//第一个叶子节点是heapSize / 2,非叶子节点就是heapSize / 2 -1了
            maxHeaify(a, i, heapSize);
        }
    }

    public void maxHeaify(int[] a, int i, int heapSize){//维护堆
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        int largest = i;
        if(l < heapSize && a[l] > a[largest]) largest = l;
        if(r < heapSize && a[r] > a[largest]) largest = r;
        if(largest != i){
            swap(a, i, largest);
            maxHeaify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}