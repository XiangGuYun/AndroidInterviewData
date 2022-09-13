# MVP设计

## 场景

假如界面有一个按钮，点击后请求数据，然后成功后将返回的数据设置到一个Textview中。


## 不使用MVP的代码

``` java
public class MainActivity extends AppCompatActivity {

    @FieldView(R.id.tv_text)
    private TextView textView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFind.bind(this);
    }
    
    // 按钮点击事件
    public void request(View view) {
        clickRequest("101010100");
    }
    
    // 发起请求
    public void clickRequest(final String cityId) {
        //请求接口
        Retrofit retrofit = new Retrofit.Builder()
            //代表root地址
            .baseUrl("http://www.weather.com.cn/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        
        ApiService apiService =
            retrofit.create(ApiService.class);
            
        //请求
        Call<WeatherBean> weatherBeanCall =
            apiService.requestWeather(cityId);
            weatherBeanCall.enqueue(new Callback<WeatherBean>() {
                @Override
                public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                    //请求成功
                    textView.setText(response.body().getWeatherinfo().toString());
                }
                @Override
                public void onFailure(Call<WeatherBean> call, Throwable t) {
                    //请求失败
                }
        });
    }
}
```
***
## 最简单的MVP实现

### 思路

1. 定义一个<font color=#dea32c>**接口**</font>，规定View需要的操作。
2. 让Activity<font color=#dea32c>**实现**</font>这个接口中的方法<font color=#dea32c>**（V层）**</font>。
3. 创建一个类，封装<font color=#dea32c>**网络请求过程（M层）**</font>。
4. 创建一个类，处理<font color=#dea32c>**M层和V层之间的通信（P层）**</font>。

### 定义接口
``` java
public interface RequestView1 {
    // 请求时展示加载
    void requestLoading();
    // 请求成功
    void resultSuccess(WeatherBean result);
    // 请求失败
    void resultFailure(String result);
}
```

### 实现接口
``` java
/**
 * 最简单的mvp模式,
 * 1.Activity需要实现v层接口
 * 2.Persenter需要持有v层引用和m层引用
 * 3.在实现类view中创建persenter
 */
public class MainActivity extends AppCompatActivity
    implements RequestView1 
{
    @FieldView(R.id.tv_text)
    private TextView textView;
    
    private RequestPresenter1 presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFind.bind(this);
        //创建Presenter
        presenter = new RequestPresenter1(this);
    }
    
    //点击事件
    public void request(View view) {
        presenter.clickRequest("101010100");
    }
    
    //请求时加载
    @Override
    public void requestLoading() {
        textView.setText("请求中,请稍后...");
    }
    
    //请求成功
    @Override
    public void resultSuccess(WeatherBean result) {
        //成功
        textView.setText(result.getWeatherinfo().toString());
    }
    
    //请求失败
    @Override
    public void resultFailure(String result) {
        //失败
        textView.setText(result);
    }
}
```

### 网络请求过程（M层）

``` java
public class RequestMode1 {
    private static final String BASE_URL = "http://www.weather.com.cn/";
    
    public void request(String detailId, Callback<WeatherBean> callback){
        //请求接口
        Retrofit retrofit = new Retrofit.Builder()
            //代表root地址
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
            
        ApiService apiService =
            retrofit.create(ApiService.class);
            
        //请求
        Call<WeatherBean> weatherBeanCall =
            apiService.requestWeather(detailId);
            
        weatherBeanCall.enqueue(callback);
    }
}
```

### M层和V层之间的通信（P层）

``` java
/**
* @description P层
* 特点:需要持有M层和V层
*/
public class RequestPresenter1 {

    private final RequestView1 mRequestView;
    
    private final RequestMode1 mRequestMode;
    
    // 如果在网络请求的过程中Activity就关闭了，
    // Presenter还持有了V层的引用(MainActivity)，就会内存泄露。
    public RequestPresenter1(RequestView1 requestView) {
        this.mRequestView = requestView;
        this.mRequestMode = new RequestMode1();
    }
    
    public void clickRequest(final String cityId){
    
        // 请求时显示加载
        mRequestView.requestLoading();
        
        // 模拟耗时，可以展示出loading
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRequestMode.request(cityId, new Callback<WeatherBean>() {
                
                @Override
                public void
                onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                    mRequestView.resultSuccess(response.body());
                }
                
                @Override
                public void
                onFailure(Call<WeatherBean> call, Throwable t) {
                    mRequestView.resultFailure(Log.getStackTraceString(t));
                }
                });
            }
        },1000);
    }
}
```

### 解决MVP内存泄露

``` java
public class RequestPresenter2 {
    
    // Activity不再作为构造传参传入
    public RequestPresenter2() {
        mMode = new RequestMode2();
    }
    
    // 省略的代码基本等同于RequestPresenter1
    // 调用mView之前需要做判空
    // ...
    
    /**
     * 绑定
     * @param view
     */
    public void attach( RequestView2 view) {
        this.mView = view;
    }
    
    /**
     * 解除绑定
     */
    public void detach() {
        mView = null;
    }
    
    /**
     * 取消网络请求
     */
    public void interruptHttp(){
        mMode.interruptHttp();
    }
}
```

``` java
public class MainActivity extends AppCompatActivity
    implements RequestView2 {
    
    // ...
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFind.bind(this);
        presenter = new RequestPresenter2();
        // 添加引用
        presenter.attach(this);
    }
    
    // ...
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解除引用
        presenter.detach();
        // 中断网络操作
        // 如果网络有点慢，退出Activity后，
        // 请求回来后还是会调用Activity的回调方法
        presenter.interruptHttp();
    }
    
}
```

#### 代码冗余问题
应用中可能有N个模块，每个模块都对应着一个V层和P层，这样造成：
1. 每个Presenter中都要定义绑定和解绑的方法；
2. Activity中对应的也要调用绑定和解绑的方法；

## 解决MVP代码冗余

#### 核心思路
抽取出基类的Presenter和基类的Activity来做绑定和解绑。

``` java
/**
 * 所有View接口都必须实现
 */
public interface IMvpBaseView4 {
}
```

``` java
/**
 * 基类Presenter
 *
 * 1. 规定View泛型，
 * 2. 定义绑定和解绑的方法
 * 3. 对外在提供一个获取View的方法
 */
public abstract class AbstractMvpPersenter4<V extends IMvpBaseView4> 
{
    private V mMvpView;
    
    /**
    * 绑定V层
    * @param view
    */
    public void attachMvpView(V view){
        this.mMvpView = view;
    }
    /**
    * 解除绑定V层
    */
    public void detachMvpView(){
        mMvpView = null;
    }
    /**
    * 获取V层
    * @return
    */
    public V getmMvpView() {
        return mMvpView;
    }
}
```

``` java
/**
 * 基类Activity
 *
 * 指定子类具体的View必须继承自IMvpBaseView4
 * 指定子类具体的Presenter必须继承自AbstractMvpPersenter4
 */
public abstract class AbstractMvpActivity
    <V extends IMvpBaseView4, P extends AbstractMvpPersenter4<V>>
    extends AppCompatActivity implements IMvpBaseView4 
{
    private P presenter;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //创建Presenter
        if (presenter == null) {
            presenter = createPresenter();
        }
        
        //绑定view
        presenter.attachMvpView((V) this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        if (presenter != null) {
            presenter.detachMvpView();
        }
    }
    
    /**
    * 创建Presenter
    * @return 子类自己需要的Presenter
    */
    protected abstract P createPresenter();
    
    /**
    * 获取Presenter
    * @return 返回子类创建的Presenter
    */
    public P getPresenter() {
        return presenter;
    }
}
```

### 修改后的Presenter

``` java
public class RequestPresenter4 extends
    AbstractMvpPersenter4<RequestView4> {
    
    private final RequestMode4 mRequestMode;
    
    public RequestPresenter4() {
        this.mRequestMode = new RequestMode4();
    }
    
    public void clickRequest(final String cityId){
        //获取View
        if(getmMvpView() != null){
            getmMvpView().requestLoading();
        }
        
        //模拟网络延迟，可以显示出加载中
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRequestMode.request(cityId, new Callback<WeatherBean>() {
                
                @Override
                public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                    //判断View是否为空
                    if(getmMvpView() != null){
                        getmMvpView().resultSuccess(response.body());
                    }
                }
            
                @Override
                public void
                onFailure(Call<WeatherBean> call, Throwable t) {
                    if(getmMvpView() != null){
                        getmMvpView().resultFailure(Log.getStackTraceString(t));
                    }
                }
            });
        } },1000);
    }
    
    public void interruptHttp(){
        mRequestMode.interruptHttp();
    }
}
```

### 修改后的Activity

``` java
public class MainActivity extends
    AbstractMvpActivity<RequestView4, RequestPresenter4> implements RequestView4 {

    @FieldView(R.id.tv_text)
    private TextView textView;
    
    private RequestPresenter3 presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFind.bind(this);
    }
    
    @Override
    protected RequestPresenter4 createPresenter() {
        return new RequestPresenter4();
    }
    
    //点击事件
    public void request(View view) {
        getPresenter().clickRequest("101010100");
    }
    
    @Override
    public void requestLoading() {
        textView.setText("请求中,请稍后...");
    }
    
    @Override
    public void resultSuccess(WeatherBean result) {
        //成功
        textView.setText(result.getWeatherinfo().toString());
    }
    
    @Override
    public void resultFailure(String result) {
        //失败
        textView.setText(result);
    }
}

```

## 进一步的优化
***


