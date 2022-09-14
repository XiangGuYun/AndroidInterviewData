# shouldOverrideUrlLoadingl拦截

## 步骤1：在JS中约定所需要的Url协议

#### javascript.html

```html
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>Carson_Ho</title>
      
      <script>
         function callAndroid(){
            /*约定的url协议为：js://webview?arg1=111&arg2=222*/
            document.location = "js://webview?arg1=111&arg2=222";
         }
      </script>
   </head>

   <!-- 点击按钮则调用callAndroid（）方法  -->
   <body>
     <button type="button" id="button1" onclick="callAndroid()">点击调用Android代码</button>
   </body>
</html>
```

## 重写WebViewClient的shouldOverrideUrlLoading()

```java
public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        
        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 步骤1：加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/javascript.html");


        // 复写WebViewClient类的shouldOverrideUrlLoading方法
        mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                    // 步骤2：根据协议的参数，判断是否是所需要的url
                    // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                    //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                    Uri uri = Uri.parse(url);                                 
                    // 如果url的协议 = 预先约定的 js 协议
                    // 就解析往下解析参数
                    if ( uri.getScheme().equals("js")) {

                        // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                        // 所以拦截url,下面JS开始调用Android需要的方法
                        if (uri.getAuthority().equals("webview")) {

                            //  步骤3：
                            // 执行JS所需要调用的逻辑
                            System.out.println("js调用了Android的方法");
                            // 可以在协议上带有参数并传递到Android上
                            HashMap<String, String> params = new HashMap<>();
                            Set<String> collection = uri.getQueryParameterNames();

                        }

                        return true;
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }
            }
        );
   }
}
```
