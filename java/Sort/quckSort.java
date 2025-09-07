// 快排的思想就是分治。每次选择一个base，将大于base和小于base的数放在base的左右两边，然后递归处理左右两边。
// 每次扫描后j的位置的元素就已经归位了。（可以举例子看下）但是base并没有在该在的位置，快排有两种划分，下面的写法都是同一种划分，这种划分base会交换到哪里我们并不关心，但是每次扫描后j的位置是有序了。（j所在的位置就是元素的最终位置）

// 有点类似于开区间的写法。
private void quickSort(int[] nums, int l, int r) {
    if (l == r)
        return;
    int x = nums[(l + r) >>> 1];// 比起使用左端点或者右端点，稍微优化了一点
    int i = l - 1, j = r + 1;// 这里
    while (i < j) {// 这里
        do
            i++;
        while (nums[i] < x);// 这里，都是精髓哇，背吧
        do
            j--;
        while (nums[j] > x);
        if (i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }
    quickSort(nums, l, j);
    quickSort(nums, j + 1, r);
}

// 下面是波儿给的快排模板，和上面的写法相比应该算是闭区间写法？感觉更容易理解，记这个吧
private void quickSort(int[] nums, int l, int r) {
    if (l == r)
        return;
    int x = nums[(l + r) >>> 1];
    int i = l, j = r;
    while (i <= j) {
        while (i <= j && nums[i] < x)
            i++;
        while (i <= j && nums[j] > x)
            j--;
        if (i >= j) {// 如果写的是if(i<j)进行交换和指针移动，i==j和nums[i]==x的时候会死循环
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

//下次试试这个，没有break感觉逻辑更流畅
private void quickSort(int[] nums, int l, int r) {
    if (l == r)
        return;
    int x = nums[(l + r) >>> 1];
    int i = l, j = r;
    while (i <= j) {
        while (i <= j && nums[i] < x)
            i++;
        while (i <= j && nums[j] > x)
            j--;
        if (i <= j) {// 如果写的是if(i<j)进行交换和指针移动，i==j和nums[i]==x的时候会死循环
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }
    quickSort(nums, l, j);
    quickSort(nums, i, r);
}


// 您记忆中的 “j的位置是正确的了，所以递归时要用j-1” 这个逻辑是存在的，并且是完全正确的！

// 只不过，这个逻辑适用于另一种非常著名的快速排序分区方案，叫做 “Lomuto分区方案”。而您代码中实现的，是另一种同样经典的分区方案，叫做 “Hoare分区方案”。

// 它们在分区后的状态和递归方式上有所不同。这正是您困惑的根源：您用 Hoare 方案实现了分区，却试图套用 Lomuto 方案的递归逻辑。

// 两种分区方案的对比
// 1. Hoare 分区方案 (您代码中正在使用的)
// 这是最早版本的快速排序分区方法。

// 工作方式：

// 选择一个枢轴（pivot）。

// 设置 i、j 两个指针，分别从区间的左右两端向中间移动。

// i 找到第一个大于等于枢轴的数，j 找到第一个小于等于枢轴的数，然后交换它们。

// 直到 i 和 j 交错而过（j < i）。

// 分区后的状态：

// 循环结束后，数组被分为 [l...j] 和 [i...r] 两个部分。

// 左边部分的所有数都小于等于枢轴，右边部分的所有数都大于等于枢轴。

// 关键点：枢轴没有被放置在它最终排好序的位置上，它只是被用来“分堆”了，它本身可能还在左边或右边的部分里。

// 递归调用：

// 因为 j 所在的位置以及它左边的所有元素都需要继续排序，所以递归调用是 quickSort(nums, l, j)。

// 同理，右边是 quickSort(nums, i, r)。