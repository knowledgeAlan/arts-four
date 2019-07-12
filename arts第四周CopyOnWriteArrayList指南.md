#java中CopyOnWriteArrayList使用

## 1.介绍

这篇文章中，我们查看java并发包中 *CopyOnWriteArrayList* ，这是非常实用结构在多线程中，当我们想线程安全迭代*CopyOnWriteArrayList* 中元素不需要使用*synchronization*



## 2.CopyOnWriteArrayList 方法



*CopyOnWriteArrayList* 设计使用一个很好技术确保线程安全不需要使用*synchronization* ，当我们使用任何修改方法，例如 *add()*  或者 *remove()* ,*CopyOnWriteArrayList* 中数组内容是拷贝新增内部对象在操作。

因为这个事实，所以迭代list是线程安全，即使在并发修改，当我们调用*CopyOnWriteArrayList* *iterator()* ,我们得到是*CopyOnWriteArrayList* 一个不可变的快照，当*iterator* 创建时候，*CopyOnWriteArrayList* 中数据复制成内部*ArrayList*。即使同时其他线程也增肌或删除元素，修改时候还会产生新的复制对象，后来查询会使用新对象。

当迭代时候 *CopyOnWriteArrayList* 中数据结构更好查找比修改时候，如果增加元素，*CopyOnWriteArrayList* 不是很好选择，因为增加复制次数导致性能低下

##3.插入时候迭代*CopyOnWriteArrayList*

看下以下例子,创建一个存储In特格尔类型CopyOnWriteArrayList

> CopyOnWriteArrayList<Integer> numbers 
>   = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});

接下来,我们想迭代数组,因此创建迭代实例

> Iterator<Integer> iterator = numbers.iterator();



迭代器创建之后,增加新的元素

> numbers.add(10);



记住这一点,创建*CopyOnWriteArrayList* 迭代器时候,得到是不可变量快照数据

因为这点，数组元素迭代时候,元素10不会再这次迭代中

>  List<Integer> result = new LinkedList<>();
> iterator.forEachRemaining(result::add);
>
> assertThat(result).containsOnly(1, 3, 5, 8);

在以后迭代器中使用新创建迭代器会返回元素10

> Iterator<Integer> iterator2 = numbers.iterator();
> List<Integer> result2 = new LinkedList<>();
> iterator2.forEachRemaining(result2::add);
>
> assertThat(result2).containsOnly(1, 3, 5, 8, 10);

## 4.迭代时候不允许删除

*CopyOnWriteArrayList* 线程安全创建迭代器当数组元素被修改，因为负责新对象，调用删除方法

 *remove()*   返回迭代器不被允许会掏出*UnsupportedOperationException* 

```java
@Test(expected = UnsupportedOperationException.class)
public void whenIterateOverItAndTryToRemoveElement_thenShouldThrowException() {
     
    CopyOnWriteArrayList<Integer> numbers
      = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});
 
    Iterator<Integer> iterator = numbers.iterator();
    while (iterator.hasNext()) {
        iterator.remove();
    }
}
```

## 5.总结

在上面介绍中，我们知道*CopyOnWriteArrayList* 实现在*java.util.concurrent* 包中

我们知道数组中是如何线程安全迭代，当多线程或删除元素