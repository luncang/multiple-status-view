package com.charles.multiple_status_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Author: Charles_lun
 * Email:clun@gdeng.cn
 * Time:2016/9/12 11:47
 * Package_name:com.charles.multiple_status_view
 * Description:
 */
public class MultipleStatusView extends RelativeLayout {

    private final static byte STATUS_CONTENT_VIEW = 0x01;
    private final static byte STATUS_EMPTY_VIEW = 0x02;
    private final static byte STATUS_ERROR_DATA_VIEW = 0x03;
    private final static byte STATUS_ERROR_NETWORK_VIEW = 0x04;
    private final static byte STATUS_LOADING_VIEW = 0x05;

    private final static byte STATUS_DEFAULT_VIEW_ID = -1;

    private byte status = STATUS_CONTENT_VIEW;

    private View contentView;
    private View emptyView;
    private View errorDataView;
    private View errorNetworkView;
    private View loadingView;

    private View emptyRetryView;
    private View errorDataRetryView;
    private View errorNetworkRetryView;

    private int contentViewId;
    private int emptyViewId;
    private int errorDataViewId;
    private int errorNetworkViewId;
    private int loadingViewId;

    private LayoutInflater inflater;


    private ViewGroup.LayoutParams layoutParams;

    private OnClickListener onRetryClickListener;


    public MultipleStatusView(Context context) {
        this(context, null);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView);
        contentViewId = a.getResourceId(R.styleable.MultipleStatusView_content_View, STATUS_DEFAULT_VIEW_ID);
        emptyViewId = a.getResourceId(R.styleable.MultipleStatusView_empty_View, R.layout.empty_view);
        errorDataViewId = a.getResourceId(R.styleable.MultipleStatusView_error_View, R.layout.error_view);
        errorNetworkViewId = a.getResourceId(R.styleable.MultipleStatusView_noNetwork_View, R.layout.no_network_view);
        loadingViewId = a.getResourceId(R.styleable.MultipleStatusView_loading_View, R.layout.loading_view);
        a.recycle();

        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflater = LayoutInflater.from(getContext());
        showContentView();
    }

    public void showContentView() {
        status = STATUS_CONTENT_VIEW;
        if (contentView == null) {
            if (contentViewId != STATUS_DEFAULT_VIEW_ID) {
                contentView = inflater.inflate(contentViewId, null);
                if (contentView != null) {
                    addView(contentView, 0, layoutParams);
                }
            } else {
                contentView = findViewById(R.id.content_view);
            }
        }
        showViewByStatus(status);
    }

    public void showEmptyView() {
        status = STATUS_EMPTY_VIEW;
        if (emptyView == null) {
            emptyView = inflater.inflate(emptyViewId, null);
            emptyRetryView = emptyView.findViewById(R.id.empty_retry_view);
            if (emptyRetryView != null && onRetryClickListener != null) {
                emptyRetryView.setOnClickListener(onRetryClickListener);
            }
            addView(emptyView, 0, layoutParams);
        }
        showViewByStatus(status);
    }

    public void showErrorDataView() {
        status = STATUS_ERROR_DATA_VIEW;
        if (errorDataView == null) {
            errorDataView = inflater.inflate(errorDataViewId, null);
            errorDataRetryView = errorDataView.findViewById(R.id.error_retry_view);
            if (errorDataRetryView != null && onRetryClickListener != null) {
                errorDataRetryView.setOnClickListener(onRetryClickListener);
            }
            addView(errorDataView, 0, layoutParams);
        }
        showViewByStatus(status);
    }

    public void showErrorNetworkView() {
        status = STATUS_ERROR_NETWORK_VIEW;
        if (errorNetworkView == null) {
            errorNetworkView = inflater.inflate(errorNetworkViewId, null);
            errorNetworkRetryView = errorNetworkView.findViewById(R.id.no_network_retry_view);
            if (errorNetworkRetryView != null && onRetryClickListener != null) {
                errorNetworkRetryView.setOnClickListener(onRetryClickListener);
            }
            addView(errorNetworkView, 0, layoutParams);
        }
        showViewByStatus(status);
    }


    public void showLoading() {
        status = STATUS_LOADING_VIEW;
        if (loadingView == null) {
            loadingView = inflater.inflate(loadingViewId, null);
            addView(loadingView, 0, layoutParams);
        }
        showViewByStatus(status);
    }

    /**
     * show the view by status
     */
    private void showViewByStatus(byte status) {
        if (null != loadingView) {
            loadingView.setVisibility(status == STATUS_LOADING_VIEW ? View.VISIBLE : View.GONE);
        }
        if (null != emptyView) {
            emptyView.setVisibility(status == STATUS_EMPTY_VIEW ? View.VISIBLE : View.GONE);
        }
        if (null != errorDataView) {
            errorDataView.setVisibility(status == STATUS_ERROR_DATA_VIEW ? View.VISIBLE : View.GONE);
        }
        if (null != errorNetworkView) {
            errorNetworkView.setVisibility(status == STATUS_ERROR_NETWORK_VIEW ? View.VISIBLE : View.GONE);
        }
        if (null != contentView) {
            contentView.setVisibility(status == STATUS_CONTENT_VIEW ? View.VISIBLE : View.GONE);
        }
        Log.e("mess","status:"+status);
    }


    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }


}
