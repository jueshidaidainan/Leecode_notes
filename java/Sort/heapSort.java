//堆排序
//和快排一样，都是O(nlogn)的时间复杂度，都是在原数组上进行排序
//需要先建立堆，然后从第一个非叶子节点开始维护堆的有序性。heapSize / 2 - 1
//然后将堆顶元素与最后一个元素交换，然后维护堆的序。维护的时候堆的大小会减一。
public class heapSort {
    public static void buildMaxHeap(int[] a, int heapSize){
        for(int i = heapSize / 2  - 1; i >= 0; i-- ){//第一个叶子节点是heapSize / 2,非叶子节点就是heapSize / 2 -1了
            maxHeaify(a, i, heapSize);
        }
    }

    public static void maxHeaify(int[] a, int i, int heapSize){//维护堆
        int l = i * 2 + 1;//数组中左子节点的表示
        int r = i * 2 + 2;
        int largest = i;
        if(l < heapSize && a[l] > a[largest]) largest = l;
        if(r < heapSize && a[r] > a[largest]) largest = r;
        if(largest != i){
            swap(a, i, largest);//局部堆顶元素与子节点中较大的元素交换
            maxHeaify(a, largest, heapSize);//递归维护子节点,交换后largest节点就需要去维护了
        }
    }
    public static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int [] nums = new int[]{ 9,5,6,3,5,3,1,0,96,66 };
        int heapSize = nums.length;
        
        buildMaxHeap(nums, heapSize);
        for(int i = nums.length - 1; i >= 0; i--){
            swap(nums, 0, heapSize - 1);
            heapSize--;
            maxHeaify(nums, 0, heapSize);//将堆顶元素与最后一个元素交换后，从头开始维护堆
        }
        for(int x : nums){
            System.out.println(x);
        }
    }
}
