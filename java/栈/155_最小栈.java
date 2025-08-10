
//辅助栈的解法，辅助栈和栈的状态一一对应，维护了每一时刻的栈的最小值。时间复杂度是O(1)的，但是空间复杂度是O(n)的。空间复杂度要是o(1),需要自定义栈。
class MinStack {
    Deque<Integer> minStack;
    Deque<Integer> stack;

    public MinStack() {
        minStack = new ArrayDeque<>();
        stack = new ArrayDeque<>();
        minStack.push(Integer.MAX_VALUE);//最大值，用来处理边界

    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(), val));//辅助栈存储的是当前的最小值
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}



//灵神的解法，和辅助栈思想一样的，只是栈的节点内部维护了当前的最小值。相当于辅助栈内化了罢了。
class MinStack {

    private final Deque<int []> st = new ArrayDeque<>();
    public MinStack() {
        st.push(new int[] {0, Integer.MAX_VALUE});
    }
    
    public void push(int val) {
        st.push(new int [] {val, Math.min(val, st.peek()[1])});
    }
    
    public void pop() {
        st.pop();
    }
    
    public int top() {
        return st.peek()[0];
    }
    
    public int getMin() {
        return st.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */