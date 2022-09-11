import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.yxd.R
import com.yxd.devlib.base.BaseFragment
import com.yxd.devlib.utils.UiUtils
import com.yxd.devlib.view.rv.RVUtils
import com.yxd.devlib.view.rv.RvEx
import com.yxd.devlib.view.vp.Vp2Ex
import com.yxd.knowledge.view.code.RecyclerViewAtViewPager2

/**
 * 解决ViewPager嵌套Rv的滑动冲突
 */
class TestVpWrapRvFragment : BaseFragment(), Vp2Ex, RvEx {

    override fun getLayoutId(): Int {
        return R.layout.frag_vp_wrap_rv
    }

    override fun init(view: View) {
        view.findViewById<ViewPager2>(R.id.vp).generate((1..10).toList(),
            {
                h, i, item ->
                val rv = h.getView<RecyclerView>(R.id.rv);
                RVUtils(rv).generate((1..30).toList(),
                    {
                        h, i, item ->
                        h.tv(R.id.tv).setBackgroundColor(UiUtils.getRandomColor())
                    },
                    null,
                    R.layout.item_rv_simple)
            },
            {
                0
            },
            R.layout.item_test_vp_wrap_def_rv //使用默认RV布局
//            R.layout.item_test_vp_wrap_rv // 使用自定义RV
        )
    }

}