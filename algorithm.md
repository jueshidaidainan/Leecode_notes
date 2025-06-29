# algorithm

## 数组

- 都在另一个本子上

## 链表

1. 203 移除链表元素
2. 707 设计链表
3. 19 删除链表的倒数第n个节点
4. 142 环形链表Ⅱ

## DP(动态规划) 

- 视频资料（代码随想录）

https://www.bilibili.com/video/BV1ve4y1x7Eu/

- 对应网站
- programmercarl.com

![image-20231114111240464](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231114111240464.png)

1. 斐波那契序列 509
2. 爬楼梯 70

 ![image-20231114164017395](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231114164017395.png)

3. 最小代价爬楼梯 746

爬每阶楼梯是有代价的，只是可以选择一次爬一阶或者两阶楼梯。爬一阶和两阶是同一个代价。

所以，归根到底还是斐波那契的这种递推关系，只是dp数组的含义应该不一样。

4. 整数划分：343

   ![image-20231117110448920](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231117110448920.png)

5. 不同的二叉搜索树 96

![image-20231117163152383](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231117163152383.png)

### **01背包问题**

- 愈发感觉遍历顺序和初始化是和递推公式强相关的

![image-20231120104355872](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231120104355872.png)

- 一维滚动数组优化

![image-20231120111939605](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231120111939605.png)

关键思考点：

应该从最核心最原始的二维矩阵出发去考虑，并结合递推公式。

如何初始化：dp[0]=0，非0下标由于max函数的特点，也初始化为0就行

先遍历背包：如果在二位矩阵相当于按列遍历，对于一维数组，我们保留的是一行的信息，所以是不行的

先遍历物品：如果在二位矩阵相当于按行遍历，对于一维数组，我们保留的是一行的信息，所以应该先遍历物品

顺序遍历or倒序遍历：由于dp数组使用的是前面的数据，所以应该从后往前遍历，若是从前往后遍历，会产生新旧数据冲突

似乎有说 顺序遍历背包是完全背包问题（可以取多次）而倒序遍历是01背包问题（只可以取一次）。

6. 分割等和子集 416

![image-20231120165702108](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231120165702108.png)

7. 最后一块石头的重量 1049

8. 目标和 494

![image-20231123171426633](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231123171426633.png)

8. 一和零 474

![image-20231122215727442](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231122215727442.png)

### 完全背包问题

![image-20231123155753899](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231123155753899.png)

遍历顺序：先物品先背包都是可以的，因为从二维数组来看的话，递推公式所需要的值都是已经计算得出的了（虽然图中似乎只有01背包的递推公式），且似乎纯完全背包是这样的，其它情况可能会不一样

正序还是倒序遍历：需要正序遍历，因为完全背包需要使用到当前层的数据。

先遍历物品再遍历背包所得是一种组合情况（因为物品的出现在背包中是有序的，只有一种情况，比如：1 2这种），先背包再物品所得是一种排列。

零钱兑换 Ⅱ 518

377 组合总和

## BST（二叉搜索树）

- 二叉搜索树中的搜索 700
- 下图一种是递归写法 另一种是迭代写法（在此题中显得更简单）

![image-20231117154337110](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231117154337110.png)

<img src="C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231117160233064.png" alt="image-20231117160233064" style="zoom:50%;" /><img src="C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20231117160250121.png" alt="image-20231117160250121" style="zoom:50%;" />

这边只是递归出口的条件判断的顺序不一样就报错，我的理解是||的逻辑判断是从左向右进行的，如果一个节点是null，访问val就会报错。