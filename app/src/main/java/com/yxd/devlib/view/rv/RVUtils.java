package com.yxd.devlib.view.rv;

import android.content.Context;
import android.graphics.Canvas;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import org.jetbrains.annotations.NotNull;
import java.util.List;

/**
 * RecyclerView工具类
 *
 * Created by Administrator on 2018/1/19 0019.
 */
public class RVUtils {

    public RecyclerView rv;
    public Context context;
    public List<?> dataList;
    public int gridSpanCount = 1;
    public boolean needHeader = false;
    public boolean needFooter = false;
    @NotNull
    public PagerSnapHelper pagerHelper;

    public <T extends RecyclerView> RVUtils(T recyclerView) {
        rv = recyclerView;
        context = recyclerView.getContext();
    }

    /**
     * 是否禁止嵌套滑动
     * @param bool
     * @return
     */
    public RVUtils banNestedScroll(Boolean bool){
        rv.setNestedScrollingEnabled(!bool);
        return this;
    }

    /**
     * 设置网格列表
     * @param spanCount  列数
     * @param isVertical 是否垂直
     * @return
     */
    public RVUtils gridManager(int spanCount, boolean isVertical) {
        gridSpanCount = spanCount;
        if(isVertical){
            rv.setLayoutManager(new GridLayoutManager(context, spanCount));
        }else {
            rv.setLayoutManager(new GridLayoutManager(context, spanCount, LinearLayoutManager.HORIZONTAL, false));
        }
        return this;
    }

    public RVUtils gridManager(int spanCount) {
        gridSpanCount = spanCount;
        rv.setLayoutManager(new GridLayoutManager(context, spanCount));
        return this;
    }

    /**
     * 设置瀑布流列表
     */
    public RVUtils staggerManager(int spanCount, boolean isVertical){
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spanCount,
                isVertical?StaggeredGridLayoutManager.VERTICAL:StaggeredGridLayoutManager.HORIZONTAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rv.setLayoutManager(manager);
        return this;
    }

    /**
     * 设置布局管理器
     */
    public RVUtils manager(RecyclerView.LayoutManager manager) {
        if (manager == null) {
            rv.setLayoutManager(new LinearLayoutManager(context));
        } else {
            rv.setLayoutManager(manager);
        }
        return this;
    }

    /**
     * 设置为横向布局
     */
    public RVUtils managerHorizontal(){
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rv.setLayoutManager(manager);
        return this;
    }

    /**
     * 设置是否固定大小列表项
     */
    public RVUtils fixed(boolean b) {
        rv.setHasFixedSize(b);
        return this;
    }

    /**
     * 滑动到RV的指定位置
     */
    public void scrollToPosition(int position, List list) {
        if (position >= 0 && position <= list.size() - 1) {
            int firstItem = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();
            int lastItem = ((LinearLayoutManager) rv.getLayoutManager()).findLastVisibleItemPosition();
            if (position <= firstItem) {
                rv.scrollToPosition(position);
            } else if (position <= lastItem) {
                int top = rv.getChildAt(position - firstItem).getTop();
                rv.scrollBy(0, top);
            } else {
                rv.scrollToPosition(position);
            }
        }
    }

    /**
     * 获取滑动方向Y轴的距离，如果分割线是独立的，不要采用此方法
     * @return
     */
    public int getScrollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        android.view.View firstVisibleChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisibleChildView.getHeight();
        return (position) * itemHeight - firstVisibleChildView.getTop();
    }

    /**
     * 获取滑动方向X轴的距离，如果分割线是独立的，不要采用此方法
     * @return
     */
    public int getScrollXDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        android.view.View firstVisibleChildView = layoutManager.findViewByPosition(position);
        int itemWidth = firstVisibleChildView.getWidth();
        return (position) * itemWidth - firstVisibleChildView.getLeft();
    }

}