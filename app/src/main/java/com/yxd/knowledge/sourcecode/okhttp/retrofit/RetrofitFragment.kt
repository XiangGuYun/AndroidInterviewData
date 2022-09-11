package com.yxd.knowledge.sourcecode.okhttp.retrofit

import android.os.Bundle
import android.view.View
import com.yxd.devlib.base.TestFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * ========================================================
 * 简介
 *  Retrofit是基于RESTFUL风格、针对OKHTTP的网络框架封装
 *  API设计简洁、注解化配置高度解耦、支持多种解析器、支持RXJAVA
 * ========================================================
 * 常用参数注解
 *  @GET、@POST：确定请求方式
 *  @Path：请求URL的字符替代
 *  @Query：要传递的参数
 *  @QueryMap：包含多个@Query注解参数
 *  @Body：添加实体类对象(JSON对象)
 *  @FormUrlEncoded：URL编码
 *
 * 示例
 *  @Get("/xxxx/login/")
 *  public Call<LoginModel> login(@Query("username")String username, @Query("password")String password)
 *
 * ========================================================
 * 创建Retrofit对象
 *   val retrofit = Retrofit.Builder()
 *   .baseUrl("")
 *   // 设置数据解析器，除GSON外，还支持Jackson、XML等格式
 *   .addConverterFactory(GsonConverterFactory.create())
 *   .build()
 * ========================================================
 * 生成接口对象
 *  val service = retrofit.create(XXXService::class.java)
 * ========================================================
 * 调用接口方法返回Call对象
 *  Call<LoginModel> call = service.login("admin", "123456)
 * ========================================================
 * 发送请求
 *  同步：调用Call对象的execute()，返回结果的ResponseBody
 *  异步：调用Call对象的enqueue()，返回一个回调
 */
class RetrofitFragment : TestFragment() {

    override fun init(view: View, savedInstanceState: Bundle?) {

        // 创建Retrofit对象
        val retrofit = initRetrofitObject()
        // 生成接口对象
        val service = retrofit.create(TestService::class.java)
        // 请求1
        doReqByParams(service)
        // 请求2
        doReqByBody(service)
        // 请求3
        doReqByPath(service)
    }

    private fun doReqByPath(service: TestService) {
        val loginModelCall = service.login2("admin", "123456")
        loginModelCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let {
                    println(it.code)
                    println(it.message)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {}
        })
    }

    private fun doReqByBody(service: TestService) {
        val loginModel = LoginModel("admin", "123456")
        service.login1(loginModel).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let {
                    println(it.code)
                    println(it.message)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {}
        })
    }

    private fun doReqByParams(service: TestService) {
        val loginModelCall = service.login("admin", "123456")
        loginModelCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let {
                    println(it.code)
                    println(it.message)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {}
        })
    }

    private fun initRetrofitObject(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("testBaseUrl")
            // 设置数据解析器，除GSON外，还支持Jackson、XML等格式
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    data class LoginModel(var username: String, var password: String)

    data class LoginResponse(var code: Int, var message: String)

    interface TestService {
        @GET("test/login")
        fun login(
            @Query("username") username: String,
            @Query("password") password: String
        ): Call<LoginResponse>

        @POST("test/login")
        @Headers("Content-Type:application/json", "Accept:application/json")
        fun login1(@Body body: LoginModel): Call<LoginResponse>

        @GET("test/login/username={username}&password={password}")
        fun login2(
            @Path("username") username: String,
            @Path("password") password: String
        ): Call<LoginResponse>

        @GET("test/login/username={username}&password={password}")
        suspend fun login3(
            @Path("username") username: String,
            @Path("password") password: String
        ): Call<LoginResponse>
    }
}