/*
KMP算法：进行模式串匹配
思想：利用模式串的next数组进行匹配，当匹配出错时，使用next数组选择模式串下次的匹配位置
next数组：存储的是模式串每个字母所对应的最大相等前后缀长度
比如：          a a b a a f
next数组：      0 1 0 1 2 0

难点：next数组的生成
使用双指针i，j
i:指向后缀的末尾字符  j：指向前缀的末尾字符，同时j也表示截至到i所指的这些字符的最大匹配前后缀长度
思路：使用i对模式串进行遍历，对next数组进行填充（初始化为了0）


*/
// 其实感觉也是一次前缀和后缀匹配的过程
#include<String>
using namespace std;
void next(string s){
    int next [s.size()] = {0};
    int j = 0;//最小前缀就是0嘛
    for(int i = 1; i < s.size(); i++){
        while(j > 0 && s[i] != s[j]) j = next[j - 1]; // 不相等的时候便一直回溯直到满足j表示的是i前面最长匹配的前后缀长度是j，只有这样下面j++的逻辑才可以通顺
        if(s[i] == s[j]) j++;
        next[i] = j;
    }
}