# Data-Structure
数据结构系统学习和实现

## 数组基础

数组的最大的有点:**快速查询**.  数组最好应用于"索引有语意"的情况  但并非所有语意的索引都适用于数组:例如索引长度过长

* 制作自己的数组
    * 索引没有语意,如何标识没有元素?
    * 如何添加元素? 
    * 如何删除元素?
    * 基于Java的数组,二次等装属于我们自己的数组类
    
![数组图示](https://github.com/kvenLin/Data-Structure/tree/master/images/选区_023.png)

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

![栈图示](https://github.com/kvenLin/Data-Structure/tree/master/images/选区_024.png)

### 栈的应用

![栈图示](https://github.com/kvenLin/Data-Structure/tree/master/images/选区_025.png)

* 无处不再的Undo操作(编辑器撤销)
* 程序调用的系统栈:用于记录程序中断的地方,当子函数执行完后中断记录进行出栈,回到之前中断的地方继续执行
* 括号匹配 - 编译器检查代码时的应用
    * 思路:栈顶元素反映了在嵌套的层次关系中,最近的需要匹配的元素

[括号匹配算法:Solution](https://github.com/kvenLin/Data-Structure/tree/master/Stack/Solution.java)

### 栈的实现

实现ArrayStack\<E>
* void push(E)
* E pop
* E peek()
* int getSize()
* boolean isEmpty()
