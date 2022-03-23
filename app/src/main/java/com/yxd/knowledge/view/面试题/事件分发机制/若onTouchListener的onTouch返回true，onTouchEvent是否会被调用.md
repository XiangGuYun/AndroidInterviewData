#### 如果onTouchListener的onTouch方法返回true，那么onTouchEvent等之后的方法是否会被调用？

<details>
<summary>查看答案</summary>
<pre>
不会，常用来防止点击穿透。
btnTest.setOnTouchListener { v, event ->
    <font color=#dea32c>true</font>
}
btnTest.setOnClickListener {
    // <font color=#dea32c>这里不会被调用</font>
    Toast.makeText(this, "666", Toast.LENGTH_SHORT).show()
}
</pre>
</details>