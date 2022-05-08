package com.disu.disme;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 07/05/2022 | 10119239 | DEA INESIA SRI UTAMI | IF6
 */

public class GridViewItem extends androidx.appcompat.widget.AppCompatImageView {

    public GridViewItem(Context context) {
        super(context);
    }

    public GridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}
