//要求log(m+n)的时间复杂度，让这道题麻烦起来了
//看的灵神题解，比官方的感觉好理解一点，但是没感觉到这个思路和二分有什么关系，后续可以用二分来优化查找i的过程
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            int [] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int [] a = new int[m + 2];
        int [] b = new int[n + 2];
        a[0] = b[0] = Integer.MIN_VALUE;
        a[m + 1] = b[n + 1] = Integer.MAX_VALUE;
        System.arraycopy(nums1, 0, a, 1, m);
        System.arraycopy(nums2, 0, b, 1, n);

        int i = 0;
        int j = (m + n + 1) >>> 1;
        while(true){
            if(a[i] <= b[j + 1] && a[i + 1] >= b[j] ){
                int max1 = Math.max(a[i], b[j]);
                int min2 = Math.min(a[i + 1], b[j + 1]);
                return (m + n) % 2 > 0 ? max1 : (max1 + min2) / 2.0;
            }
            i++;
            j--;
        }

    }
}