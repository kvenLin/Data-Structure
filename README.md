# Data-Structure
数据结构系统学习和实现

## 数组基础

数组的最大的有点:**快速查询**.  数组最好应用于"索引有语意"的情况  但并非所有语意的索引都适用于数组:例如索引长度过长

* 制作自己的数组
    * 索引没有语意,如何标识没有元素?
    * 如何添加元素? 
    * 如何删除元素?
    * 基于Java的数组,二次等装属于我们自己的数组类
    
![数组图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_023.png)

* 使用泛型
    * 数据结构可以放置"任何"数据类型
    * 不可以是基本的数据类型,只能是类对象.基本数据类型: boolean,byte,char,short,int,long,float,double
    * 每个基本数据类型都有对应的包装类:Boolean,Byte,Character,Short,Integer,Long,Float,Double
* 自动扩容
    * 在添加元素时判断是空间已填满
    * 若填满则按照原由的data.length()的两倍进行扩容
    * 扩容原理:
        * 生成新的data数组
        * 将原由的data数组赋值到新的的扩容数组中(遍历赋值存在效率问题)
        * data引用新的数组
        * 原由的数组将不再被引用,JVM进行垃圾回收

### 时间复杂度分析
* O(1),O(n),O(lgn),O(nlogn),O(n^2):大O描述的是算法的运行时间和输入数据之间的关系
* 添加操作O(n),resize O(n),通常以最坏的情况为基准:
    * addLast(e)    O(1)
    * addFirst(e)   O(n)
    * add(index,e)  O(n/2)=O(n) 可以理解成平均需要挪n/2个元素
* 删除操作O(n),resize O(n):
    * removeLast(e)         O(1)
    * removeFirst(e)        O(n)
    * removeIndex(index,e)  O(n/2)=O(n)
* 修改操作O(1):
    * set(index,e)  O(1)
* 查询操作O(n):
    * get(index)    O(1)
    * contains(e)   O(n)
    * find(e)       O(n)

## 栈 Stack (last in first out)

* 栈也是一种线性结构
* 相比数组,栈对应的操作是数组的子集
* 只能从一端添加元素,也只能从另一端取元素
* 取元素这一端称为栈顶


### 栈的应用


* 无处不再的Undo操作(编辑器撤销)
* 程序调用的系统栈:用于记录程序中断的地方,当子函数执行完后中断记录进行出栈,回到之前中断的地方继续执行

![栈图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_024.png)

* 括号匹配 - 编译器检查代码时的应用
    * 思路:栈顶元素反映了在嵌套的层次关系中,最近的需要匹配的元素

[括号匹配算法:Solution](https://github.com/kvenLin/Data-Structure/tree/master/Stack/src/Solution.java)

### 栈的实现

实现ArrayStack\<E>
* void push(E)
* E pop
* E peek()
* int getSize()
* boolean isEmpty()

## 队列 Queue
* 队列也是一种线性结构
* 相比数组,队列对应的操作是数组的子集
* 只能从另一端(队尾)添加元素,只能从另一点(队首)取出元素

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_026.png)

### 队列的实现

实现Queue\<E>
* void enqueue(E)
* E dequeue()
* E getFront()
* int getSize()
* boolean isEmpty()

### 数组队列的问题
* 删除队首元素时间复杂度为O(n)

### 循环队列

* front == tail 队列为空
* (tail + 1)%capacity == front 队列为满

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_027.png)

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_028.png)

## 链表
* 简单的动态的数据结构
* 更加深入的理解引用(或者指针)
* 更深入的理解递归

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_029.png)

优点:真正的动态,不需要处理固定容量的问题

缺点:丧失了随机访问的能力(即不能更具给定的索引来立即取出元素)

1.在链表头添加元素
* node.next = head
* head = node

2.在链表中间添加元素:
* 需要一个prev指针,进行遍历
* 生成一个新的node
* node.next = prev.next
* prev.next = node
* *关键*:
    * 找到要添加的节点的前一个节点
    * 两步操作不能调换

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_030.png)

3.虚拟头节点
* 有意的设置一个为null的dummyHead
* 即浪费一个空间为后续编写逻辑的方便

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_031.png)

4.删除某一链表元素
* 找到要删除的前一个元素的位置prev
* prev.next = delNode.next
* 释放将删除的指针与链表断开联系使得JVM能够进行垃圾回收: delNode.next = null

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_032.png)

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_033.png)

### 链表时间复杂度分析
* 增删改查的复杂度都是O(n)
* 如果只是对链表头进行操作:O(1)

### 使用链表实现栈
[链表栈的实现示例](https://github.com/kvenLin/Data-Structure/tree/master/Stack/src/LinkedListStack.java)

[数组栈和链表栈的性能测试](https://github.com/kvenLin/Data-Structure/tree/master/Stack/src/Main.java)

两种栈的耗时点:
* 数组栈主要在缩容和扩容的地方需要对元素进行遍历赋值
* 链表需要对每个添加的元素进行new操作来添加新的空间
* 相对优异的比较需要综合考量,差异不是很大

### 使用链表实现队列
[链表队列的实现示例](https://github.com/kvenLin/Data-Structure/tree/master/Queue/src/LinkedListQueue.java)
* 队列需要在队尾添加元素,在队首删除元素
* 链表需要两个指针一个head,一个tail
* head端为队首,tail端为队尾

![数组队列图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_034.png)

### 链表算法
[LeetCode203题链表移除指定元素:Solution2](https://github.com/kvenLin/Data-Structure/tree/master/LinkedList/src/Solution2.java)

### 链表和递归
#### 递归
* 本质上,将原来的问题,转换为更小的问题
* 列举:数组求和
    * Sum(arr[0...n-1]) = arr[0] + Sum(arr[1...n-1])
    * Sum(arr[1...n-1]) = arr[1] + Sum(arr[2...n-1])
    * ...
* 注意递归函数的"宏观"语意
* 递归函数就是一个函数,完成一个功能
#### 链表天然的递归性
* 解决链表中删除元素的问题

![链表递归进行删除元素图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_035.png)

[解决函数方法](https://github.com/kvenLin/Data-Structure/tree/master/LinkedList/src/Solution3.java)

## 树结构

主要数结构:
* 二分搜索树(Binary Search Tree)
* 平衡二叉树: AVL;红黑树
* 堆;并查集
* 线段树;Trie(字典树,前缀树):主要用于处理字符串数据

### 二叉树

![二叉树结构图示](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_036.png)

* 二叉树具备天然递归结构
* 每个节点的左孩子树也是二叉树
* 每个节点的右孩子树也是二叉树
* 二叉树不一定是"满"的

#### 二分搜索树
* 二分搜索树是二叉树
* 二分搜索树的每一个节点的值:
    * 大于其左子树的所有节点的值
    * 小于其右子树的所有节点的值
* 每一棵子树也是二分搜索树
* 存储的元素具有可比较性

##### 二分搜索树的遍历
* 遍历操作就是把所有节点都访问一遍
* 访问的原因和业务有关
    * 例如: 当发现某个题答案出错了,需要对每个学生的成绩都加上几分,这时就需要对每个元素进行遍历
* 在线性结构下,遍历是极其容易的
* 在树结构下,也没有那么难

遍历方式:
* 深度优先遍历:
    * 前序遍历: 根->左->右
    * 中序遍历: 左->根->右
    * 后序遍历: 左->右->根
        * 后序遍历的应用: 手动释放内存,先处理孩子节点
* 广度优先遍历:
    * 层序遍历
    * 广度优先的意义:
        * 更快的找到对应的元素
        * 更多应用于搜索策略上
        * 常用于算法设计-最短路径
        
##### 删除任意节点
* 删除节点的情况:
    1. 删除的节点左子树为null
    2. 删除的节点右子树为null
    3. 删除节点的左右子树都不为null(两种思路:1.删除当前节点后找前驱节点,2.删除当前节点后找后继节点)
![删除二分搜索树任意节点](https://raw.githubusercontent.com/kvenLin/Data-Structure/master/images/选区_040.png)

