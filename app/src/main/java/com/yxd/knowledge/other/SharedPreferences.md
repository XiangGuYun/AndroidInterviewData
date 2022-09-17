### SharedPreferences
SharedPreferences是Android中<font color=#dea32c>**轻量级**</font>的数据存储方式，适用于保存简单的数据类型。
其内部是以xml结构保存在<font color=#009ad6>**/data/data/{包名}/shared_prefs**</font>文件夹下的。数据以<font color=#dea32c>**键值对**</font>的形式保存，如下所示：
```xml
<map>
    <float name="isFloat" value="1.5" />
    <string name="isString">Android</string>
    <int name="isInt" value="1" />
    <long name="isLong" value="1000" />
    <boolean name="isBoolean" value="true" />
    <set name="isStringSet">
        <string>element 1</string>
        <string>element 2</string>
        <string>element 3</string>
    </set>
</map>
```

### 简单使用
```java
// 通过Context获取SharedPreferencesImpl实例
SharedPreferences sp = Context.getSharedPreferences("my_sp", Context.MODE_PRIVATE);
// 通过SP获取EditorImpl实例
SharedPreferences.Editor edit = sp.edit();
// 存入key为value，值为text的数据。
edit.putString("value","test");
// 提交
edit.apply(); // 或者edit.commit();
// 从SP中读取value对应的值
sp.getString("value","");
```

### SP与其它方案比较

![](img/36a284ad.png)