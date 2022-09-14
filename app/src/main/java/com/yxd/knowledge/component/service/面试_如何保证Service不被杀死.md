# 如何保证Service不被杀死

1. 将Service设置为前台进程
2. 双进程保护
3. 使用JobService来实现应用退出后重启Service
