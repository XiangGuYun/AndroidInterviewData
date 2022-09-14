# 演示WebView的loadUrl()

点击按钮，调用src/main/assets/文件名.html中的方法callJS()。

## 步骤1：将JS代码以.html格式放到src/main/assets文件夹中

> 为方便演示调用本地JS代码；实际开发中更多的是调用远程JS代码，将加载的JS代码路径改成url即可。

```html
// 文件名：javascript.html
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>Carson_Ho</title>
      
      // JS代码
     <script>
       // Android需要调用的方法
       function callJS(){
          alert("Android调用了JS的callJS方法");
       }
    </script>

   </head>

</html>
```

## 步骤2：通过WebView设置调用JS代码
```java
 public class MainActivity extends AppCompatActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...

        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/javascript.html");

        button.setOnClickListener({
            mWebView.post({
               // 注意调用的JS方法名要对应上
               // 调用javascript的callJS()方法
               mWebView.loadUrl("javascript:callJS()");
            });
        });

        // ...

    }
}
```
