package com.jokerpeng.demo.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by PengXiaoJie on 2017/5/31.11 01..
 */

public class IndicatorView extends View {
    private Context mContext;
    private Paint paint;
    private int count;
    private int middleWidth;
    private int middleHeight;
    private final float unSelectedWidth = 20;
    private final float selectedWidth = 30;
    private final float height = 5;
    private int selectPosition;
    private float start;
    private final float itemSpace = 10;
//    private List<Float> mList_xPosition;
    public IndicatorView(Context context) {
        super(context);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData(context);
        postInvalidate();
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setWidthHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void initData(Context context) {
        this.mContext = context;
//        mList_xPosition = new ArrayList<>();
        setCount(4);
        initPaint();
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setSelectPosition(int position){
        if(position >= count){
            new ExceptionInInitializerError("选择的position大于总数");
        }else{
            this.selectPosition = position;
        }
        postInvalidate();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mContext.getResources().getColor(android.R.color.black));
        paint.setStrokeWidth(2);
    }

    private void setWidthHeight(){
        middleWidth = getMeasuredWidth()/2;
        middleHeight = getMeasuredHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setSelectPosition(2);
        setStartCoordinate();
        Paint p = new Paint();
        p.setColor(mContext.getResources().getColor(R.color.colorAccent));
        p.setStrokeWidth(3);
        canvas.drawLine(middleWidth,getMeasuredHeight(),middleWidth,getMeasuredHeight()-300,p);
        drawIndicator(canvas);
    }

    private void setStartCoordinate() {
        float total = (unSelectedWidth  + itemSpace) * (count - 1) + selectedWidth;
        start = middleWidth - total/2;
    }

    private void drawIndicator(Canvas canvas) {
        float left = start;
        float right = start + unSelectedWidth;
        for(int i = 0;i<count;i++){
            if(i == 0){
                left = start;
            }else{
                left = right + itemSpace;
            }
            if(selectPosition == i){
                right = left + selectedWidth;
            }else{
                right = left + unSelectedWidth;
            }
            float top = middleHeight - height;
            float bottom = middleHeight + height;
            RectF oval3 = new RectF(left, top,right, bottom);
            canvas.drawRoundRect(oval3, 100, 100, paint);
//            canvas.drawCircle(itemWidth*i+100,100,10,paint);
        }
    }

}
