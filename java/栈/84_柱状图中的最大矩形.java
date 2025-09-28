//枚举heights中的每一个柱子，求以该元素为高度的矩形的面积。
//对于每个柱子，高知道了，只要找出离其最近的小于等于heights[i]的元素，就是左、右边界，right - left - 1就是宽度，乘以高度就是面积。
//三次遍历，分别求出左边界和右边界，然后求出面积。记得清空栈。
//在第三次遍历过程中，维护最大值即可。栈使用ArrayDeque，而不是stack，效率更高一点。
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        Deque<Integer> st = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {//左边界赋值
            int h = heights[i];
            while (!st.isEmpty() && heights[st.peek()] >= h) {
                st.pop();
            }
            left[i] = st.isEmpty() ? -1 : st.peek();//栈为空，代表左边的所有元素都大于h，所以左边界为-1
            st.push(i);
        }

        int[] right = new int[n];
        st.clear();//清空栈
        for (int i = n - 1; i >= 0; i--) {//右边界赋值
            int h = heights[i];
            while (!st.isEmpty() && heights[st.peek()] >= h) {//栈为空，代表右边的所有元素都大于h，所以右边界为n
                st.pop();
            }
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}