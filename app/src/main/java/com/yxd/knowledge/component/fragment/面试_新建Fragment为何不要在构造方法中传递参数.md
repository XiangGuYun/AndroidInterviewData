# 新建Fragment为何不要在构造方法中传递参数

根据Android文档说明，当一个Fragment重新创建时，系统会再次调用Fragment中的默认构造函数。

>当你创建了一个带有参数的Fragment的之后，一旦由于什么原因（例如横竖屏切换）导致Fragment重新创建。
>
>那么之前传递的参数都不见了，因为reCreate你的Fragment的时候，调用的是默认构造函数。

因此，官方推荐使用Fragment.setArguments(Bundle bundle)这种方式来传递参数，而不推荐通过构造方法直接来传递参数。
