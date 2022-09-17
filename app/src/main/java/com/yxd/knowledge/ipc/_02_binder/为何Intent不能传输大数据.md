# 为何Intent不能传输大数据

因为ProcessState.cpp中定义了常量BINDER_VM_SIZE(1M-8kb),

限制了执行mmap操作的数据大小。

> 📢：BINDER_VM_SIZE是数据包(数据+包装)的大小，不是纯数据的大小。  