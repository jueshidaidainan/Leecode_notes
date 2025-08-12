// 快排的思想就是分治。每次选择一个base，将大于base和小于base的数放在base的左右两边，然后递归处理左右两边。
// 每次扫描后j的位置的元素就已经归位了。（可以举例子看下）但是base并没有在该在的位置，快排有两种划分，下面的写法都是一种划分，这种划分base会交换到哪里我们并不关心，但是每次扫描后j的位置是有序了。
// 有点类似于开区间的写法。


private void quickSort(int [] nums, int l, int r){
    if(l == r) return;
    int x = nums[(l + r) >>> 1];//比起使用左端点或者右端点，稍微优化了一点
    int i = l - 1, j = r + 1;//这里
    while(i < j){//这里
        do i++; while(nums[i] < x);//这里，都是精髓哇，背吧
        do j--; while(nums[j] > x);
        if(i < j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }
    quickSort(nums, l, j);
    quickSort(nums, j + 1, r);
}

//下面是波儿给的快排模板，和上面的写法相比应该算是闭区间写法？感觉更容易理解，记这个吧
private void quickSort(int[] nums, int l, int r) {
    if (l == r)
        return;
    int x = nums[(l + r) >>> 1];
    int i = l, j = r;
    while (i <= j) {
        while (i <= j && nums[i] < x) i++;
        while (i <= j && nums[j] > x) j--;
        if (i >= j) {//如果写的是if(i<j)进行交换和指针移动，i==j和nums[i]==x的时候会死循环
            break;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        i++;
        j--;
    }
    quickSort(nums, l, j);
    quickSort(nums, j + 1, r);
}
