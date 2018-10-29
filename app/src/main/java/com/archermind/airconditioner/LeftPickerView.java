package com.archermind.airconditioner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;


public class LeftPickerView extends ScrollView {

    private Context context;
    /**
     * 数据源
     */
    private List<String> mData;
    /**
     * 两条线的画笔
     */
    private Paint mLinePaint;
    /**
     * 宽,高,还有显示的TextView的高度（注意:这里只是TextView的高度,不算margin）
     */
    private int mWidth, mHeight, mTextHeight;
    /**
     * TextView的父容器
     */
    private LinearLayout mTextGroup;
    /**
     * 获取屏幕的密度
     */
    private float mDensity;
    /**
     * 字体的大小
     */
    private float mTextSize;
    /**
     * 根据字体大小来控制整个控件的高度
     */
    private float mWrapContentHeight;
    /**
     * 当前选中的position
     */
    public static int position, tempPosition = -1;
    private int scrollY;

    public LeftPickerView(Context context) {
        this(context, null);
    }

    public LeftPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        /**
         * 这句话设置ScrollView滑动到极限的时候不显示提示（就是那个阴影）
         */
        setOverScrollMode(OVER_SCROLL_NEVER);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LeftPickerView, defStyleAttr, 0);
        try {
            /**
             * 获取到用户设置的字体大小,默认25
             */
            mTextSize = a.getDimension(R.styleable.LeftPickerView_textSize, 25);
            /**
             * 获取到屏幕的密度来设置TextView的高度
             */
            mDensity = getResources().getDisplayMetrics().density;
            mTextHeight = (int) ((mDensity + 0.5) * mTextSize);
            /**
             * 整个控件的高度为TextView的高度
             */
            mWrapContentHeight = mTextHeight;
        } finally {
            a.recycle();
        }
    }


    private void init() {

        /**
         * 初始化数据,首先添加Group
         */
        mTextGroup = new LinearLayout(context);
        mTextGroup.setOrientation(LinearLayout.VERTICAL);
        mTextGroup.setGravity(Gravity.CENTER);
        addView(mTextGroup);

        for (int i = 0; i < mData.size(); i++) {
            mTextGroup.addView(createTextView("" + mData.get(i)));
        }

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(1);
        mLinePaint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        setPosition(0);
    }

    /**
     * 动态创建TextView
     */
    private TextView createTextView(String s) {
        TextView tv = new TextView(context);
        tv.setText(s);
        tv.setTextColor(getResources().getColor(android.R.color.white));
        tv.setTextSize(mTextSize);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mTextHeight);
        tv.setLayoutParams(params);
        return tv;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 更改测量方法
         * 指定高度为mWrapContentHeight
         */
        mWidth = measureWidth(widthMeasureSpec);
        mHeight = (int) mWrapContentHeight;
        setMeasuredDimension(mWidth, (int) mWrapContentHeight);
    }


    private int measureWidth(int size) {
        int mode = MeasureSpec.getMode(size);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                return (int) mWrapContentHeight;
            case MeasureSpec.EXACTLY:
            default:
                return MeasureSpec.getSize(size);
        }
    }

    /**
     * ScrollView的滑动事件监听
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        /**
         * 计算出当前在两条线里的position
         */
        position = t  / mTextHeight ;
        Log.d("hct","position = "+position);
        /**
         * 因为此方法会在滑动的时候不停的调用,所以这里设置一个临时的变量来控制
         */
        if (tempPosition != position) {
            int size = mTextGroup.getChildCount();
            for (int i = 0; i < size; i++) {
                if (position  == i) {
                    Log.d("hct","position  == i"+position);
                    /**
                     * 这里写空调温度的逻辑
                     */
                } else {

                }
            }
        }
        tempPosition = position;
        Log.d("hct","tempPosition = "+tempPosition);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        /**
         * 因为ScrollView没有停止滑动的监听,所以这里取巧
         * 在手指离开屏幕的30ms后判断是否和原来的scrollY一样
         * 如果一样则进入,如果不一样则设置为一样
         */
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            scrollY = getScrollY();
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (scrollY == getScrollY()) {
                        /**
                         * 获得每次松手后scrollY相对于TextView高度的偏移量
                         */
                        int offset = scrollY % (mTextHeight);

                        /**
                         * 如果偏移量大于TextView高度的一半
                         * 则进入到下一个
                         */
                        if (offset > mTextHeight/2) {
                            smoothScrollTo(0, scrollY - offset + mTextHeight );
                        } else {
                            smoothScrollTo(0, scrollY - offset);
                        }
                    } else {
                        scrollY = getScrollY();
                        post(this);
                    }
                }
            }, 30);
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 设置fling的速度为原来的1/3
     */
    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 3);
    }

    /**
     * 设置数据源
     */
    public void setData(List<String> data) {
        mData = data;
        init();
    }

    /**
     * 设置position
     */
    public void setPosition(int position) {
        this.position = position;
        if (position == 0) {
            post(new Runnable() {
                @Override
                public void run() {
                    scrollTo(0, 1);
                }
            });
            return;
        }
        post(new Runnable() {
            @Override
            public void run() {
                Log.d("hct","289position = "+ LeftPickerView.this.position * mTextHeight);
                smoothScrollTo(0, LeftPickerView.this.position * mTextHeight);
            }
        });
    }

    /**
     * 获取position
     * @return
     */
    public int getPosition() {
        return position;
    }

}
