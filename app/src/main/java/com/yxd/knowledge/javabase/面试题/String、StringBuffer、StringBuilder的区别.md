#### String、StringBuffer、StringBuilder的区别

> String：字符串常量，使用字符串拼接时是不同的两个空间。
>
> StringBuffer：字符串变量，线程安全，字符串拼接直接在字符串后追加。
>
> StringBuilder：字符串变量，非线程安全，字符串拼接直接在字符串后追加。