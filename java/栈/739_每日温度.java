//这是一道单调栈的题目，第一个是自己的写法。遍历数组，每次和栈顶元素比较，比栈顶大就弹出，直到比栈顶小，然后入栈。比栈顶小就直接入栈。
//答案就是当前遍历的j减去弹出元素在数组本来的索引。但是这是额外信息，本来想用HasMap存储，发现有重复元素，报错了，最后使用了双栈。
//灵神的解法可以看作优化，思路其实都一样（只是栈里直接存储的是index就不用额外的空间了）
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int len = temperatures.length;
        int[] ans = new int[len];
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Integer> indexStack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>(len);

        numStack.push(temperatures[0]);
        indexStack.push(0);
        // for (int i = 0; i < len; i++) {
        //     map.put(temperatures[i], i);
        // }哈西不行，元素有重复的

        for (int j = 1; j < len; j++) {
            while (!numStack.isEmpty() && temperatures[j] > numStack.peek()) {//当前元素大于栈顶元素
                int index = indexStack.pop();
                ans[index] = j - index;
                numStack.pop();
            }
            numStack.push(temperatures[j]);
            indexStack.push(j);
        }
        

        while (!numStack.isEmpty()) {
            numStack.pop();
            int index = indexStack.pop();
            ans[index] = 0;
        }

        return ans;
    }
}

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque <Integer> stack = new ArrayDeque<>();
        int len = temperatures.length;
        int[] ans = new int[len];
        for(int i = 0; i < len; i++){
            int x = temperatures[i];
            while(!stack.isEmpty() && x > temperatures[stack.peek()]){
                int j = stack.pop();
                ans[j] = i - j;
            }
            stack.push(i);
        }

        return ans;
    }
}