//这道题虽然是在动态规划这里，但是动态规划的逻辑稍显复杂，这里主要讲一下栈的思路。如果动态规划求解思路成系统之后，或许可以做。
//用栈来判断有效括号的长度，然后不断维护。
// 先将-1压入栈，作为边界条件。栈底一直存的是最后一个右括号的索引，其余的栈内元素是左括号的索引。
// 这样后续的处理就和栈处理括号问题一样了，遇见左括号就入栈，右括号就出栈，栈为空时，将当前右括号索引入栈。
// 这样遇到右括号但是栈不为空的时候，栈顶的索引和当前右括号索引之差就是有效括号的长度。
// 遇到右括号但是栈为空的时候，就将其索引压入
class Solution {
    public int longestValidParentheses(String s) {
        char[] S = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        stack.push(-1);//方便边界处理，让栈的逻辑和我们定义的一样
        for(int i = 0; i < S.length; i++){
            if(S[i] == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
}

// 至于动态规划的写法，有个精选题解写的挺好的