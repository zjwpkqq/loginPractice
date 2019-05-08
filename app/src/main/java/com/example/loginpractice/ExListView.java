package com.example.loginpractice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class ExListView extends ListView implements AbsListView.OnScrollListener {

    // 是否加载中或已加载所有数据
    private boolean isLoadingOrComplete;
    // 是否所有子项都可见
    private boolean isAllVisible;

    private OnLoadMoreListener onLoadMoreListener;
    private View loadMoreView;
    private View loadCompleteView;

    public ExListView(Context context) {
        super(context);
        init(context);
    }

    public ExListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    // 加载更多回调接口
    public interface OnLoadMoreListener {
        void loadMore();
    }

    // 初始化
    @SuppressLint("InflateParams")
    public void init(Context context) {
        loadMoreView = LayoutInflater.from(context).inflate(R.layout.load_more, null);
        loadCompleteView = LayoutInflater.from(context).inflate(R.layout.load_complete, null);
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // (最后一条可见item == 最后一条item) && (停止滑动) && (!加载数据中) && (!所有子项都可见)
        if (view.getLastVisiblePosition() == getAdapter().getCount() -1 &&
        scrollState == SCROLL_STATE_IDLE && !isLoadingOrComplete && !isAllVisible) {
            if (onLoadMoreListener != null) {
                // 加载更多（延时1秒，防止加载速度过快导致加载更多布局闪现）
                isLoadingOrComplete = true;
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoadMoreListener.loadMore();
                    }
                }, 1500);
            }
        }
        if (getFooterViewsCount() == 0 && !isAllVisible) addFooterView(loadMoreView);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        isAllVisible = totalItemCount == visibleItemCount;
    }

    /*
    加载更多回调

    @param onLoadMoreListener 加载更多回调接口
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    /*
    通过此次加载完成，remove footerView

    @param allComplete 是否已加载全部数据
     */
    public void setLoadCompleted(final boolean allComplete) {
        if (allComplete && getFooterViewsCount() != 0) {
            removeFooterView(loadMoreView);
            removeFooterView(loadCompleteView);
            addFooterView(loadCompleteView);
        } else {
            isLoadingOrComplete = false;
            if (getFooterViewsCount() != 0)
                removeFooterView(loadCompleteView);
        }
    }
}
