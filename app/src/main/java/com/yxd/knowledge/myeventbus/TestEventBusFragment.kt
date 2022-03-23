package com.yxd.knowledge.myeventbus

import android.os.Bundle
import android.os.Message
import android.view.View
import com.yxd.devlib.base.TestFragment
/*
  @Subscribe(threadMode = ThreadMode.MAIN)
    fun handle(msg: Message){
        Toast.makeText(this, "接受到Message类型消息：${msg.obj}", Toast.LENGTH_SHORT).show()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handle1(msg:String){
        Toast.makeText(this, "接受到String类型消息：${msg}", Toast.LENGTH_SHORT).show()
    }
 */
class TestEventBusFragment: TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        addButton("发送Message消息"){
            MyEventBus.getDefault().post(Message.obtain().apply { obj = "太强了吧" })
        }
        addButton("发送String消息"){
            MyEventBus.getDefault().post(Message.obtain().apply { what = 123 })
        }
    }
}