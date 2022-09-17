# put

先将计算key的hash值，然后取模，根据结果来决定放在数据的第几位。

> 可以把HashMap理解成一个书架（按A-Z排列）。
> 1. 计算key的hash值：将书名转换为拼音（例如将《时间简史》转换为shijianjianshi）。
> 2. 取模：取出拼音的首字母（S）。
> 3. 放入数组：将《时间简史》放入S层上。
> 4. 存在hash冲突：S层上已经有了本《史记》，那么将《时间简史》放在《史记》前面，并在档案中的《史记》前面加一条《时间简史》的记录。