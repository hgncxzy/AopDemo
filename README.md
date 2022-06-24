# AopDemo
Android AOP 测试用例(基于 AspectJ 方式)

AspectJ 属于静态AOP，它是在编译时进行增强，会在编译时期将AOP逻辑织入到代码中。由于是在编译器织入，所以它的优点是不影响运行时性能，缺点是不够灵活。

AOP 即面向切面编程，区别于 OOP（面向对象编程）的功能模块化，AOP 主要侧重于解决某一类的问题。

![aop](https://upload-images.jianshu.io/upload_images/12359382-8036eca34aa14074.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/580/format/webp)

从上图可以看出，面向切面编程是在不影响原业务功能的情况下，将我们所需要的功能插入进原业务代码中。

1. [Android AOP 引入AspectJ 配置](https://www.jianshu.com/p/f84a42ac0e16)
2. [写给Android工程师的AOP知识!](https://www.jianshu.com/p/f933706e352b)
3. [Android AOP面向切面编程和Gradle插件编写(二)](https://www.jianshu.com/p/fc7456c65dfd) -- 通过 gradle 插件简化 AspectJ 的配置
4. [Android中AOP的实际运用](https://www.jianshu.com/p/980cfab4c790)
