###  Service和Thread的区别

1. Service是安卓的 系统组件 ，Thread是CPU资源调度的 最小单位 。
2. Service可以运行在指定的进程中，Thread只能运行在当前进程中。
3. Service不会自行销毁，Thread在运行完run方法后会自行销毁。
4. Service可以多次启动，Thread只能启动一次。
