package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by college on 2018/1/8.
 */

public class ExpandView extends View{

    float percent;
    float maxRadius = 15;
    float maxDist = 60;
    Paint mPaint;
    public ExpandView(Context context, @Nullable AttributeSet attrs ) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.CYAN);
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth()/2;
        float centerY = getHeight()/2;
        if(percent <= 0.5f){//只画一个圆
            mPaint.setAlpha(255);
            float radius = percent * 2 * maxRadius;
            canvas.drawCircle(centerX,centerY,radius,mPaint);
        }else {//画三个圆
            float afterPercent = (percent - 0.5f)/0.5f;
            float radius = maxRadius - maxRadius / 2 * afterPercent;
            canvas.drawCircle(centerX, centerY, radius, mPaint);
            canvas.drawCircle(centerX - afterPercent * maxDist, centerY, maxRadius / 2,mPaint );
            canvas.drawCircle(centerX + afterPercent * maxDist, centerY, maxRadius / 2,mPaint);
        }
    }
}
