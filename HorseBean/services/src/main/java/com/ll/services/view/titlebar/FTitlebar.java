package com.ll.services.view.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ll.services.R;
import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FTitlebar extends RelativeLayout implements IFTitlebar
{
    private TextView mFTitlebarText;
    private FTitlebarBtn mFTitlebarLeft1;
    private FTitlebarBtn mFTitlebarLeft2;
    private FTitlebarBtn mFTitlebarRight1;
    private FTitlebarBtn mFTitlebarRight2;

    private onFTitlebarClickListener mClickListener;

    public FTitlebar(Context context)
    {
        this(context, null);
    }

    public FTitlebar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.f_titlebar_inner, this, true);
        findViews();
        initClick();
    }

    private void findViews()
    {
        mFTitlebarText = (TextView) findViewById(R.id.f_titlebar_text);
        mFTitlebarLeft1 = (FTitlebarBtn) findViewById(R.id.f_titlebar_left1);
        mFTitlebarLeft2 = (FTitlebarBtn) findViewById(R.id.f_titlebar_left2);
        mFTitlebarRight1 = (FTitlebarBtn) findViewById(R.id.f_titlebar_right1);
        mFTitlebarRight2 = (FTitlebarBtn) findViewById(R.id.f_titlebar_right2);
    }

    private void initClick()
    {
        mFTitlebarLeft1.setOnClickListener(new OnClickListener()
        {
            @Override public void onClick(View v)
            {
                if (null != mClickListener)
                {
                    mClickListener.onLeft1Click(v);
                }
                else
                {
                    FLog.e("mClickListener is null!");
                }
            }
        });

        mFTitlebarLeft2.setOnClickListener(new OnClickListener()
        {
            @Override public void onClick(View v)
            {
                if (null != mClickListener)
                {
                    mClickListener.onLeft2Click(v);
                }
                else
                {
                    FLog.e("mClickListener is null!");
                }
            }
        });

        mFTitlebarRight1.setOnClickListener(new OnClickListener()
        {
            @Override public void onClick(View v)
            {
                if (null != mClickListener)
                {
                    mClickListener.onRight1Click(v);
                }
                else
                {
                    FLog.e("mClickListener is null!");
                }
            }
        });

        mFTitlebarRight2.setOnClickListener(new OnClickListener()
        {
            @Override public void onClick(View v)
            {
                if (null != mClickListener)
                {
                    mClickListener.onRight2Click(v);
                }
                else
                {
                    FLog.e("mClickListener is null!");
                }
            }
        });
    }

    public void setOnTitlebarClickListener(onFTitlebarClickListener listener)
    {
        mClickListener = listener;
    }

    @Override public void setTitleText(int resId)
    {
        if (null != mFTitlebarText)
        {
            mFTitlebarText.setText(resId);
        }
        else
        {
            FLog.e("mFTitlebarText is null!");
        }
    }

    @Override public void setTitleText(String text)
    {
        if (null != mFTitlebarText)
        {
            mFTitlebarText.setText(text);
        }
        else
        {
            FLog.e("mFTitlebarText is null!");
        }
    }

    @Override public IFTitlebarBtn getLeft1()
    {
        return mFTitlebarLeft1;
    }

    //    @Override public IFTitlebarBtn getLeft2()
    //    {
    //        return mFTitlebarLeft2;
    //    }

    @Override public IFTitlebarBtn getRight1()
    {
        return mFTitlebarRight1;
    }

    @Override public IFTitlebarBtn getRight2()
    {
        return mFTitlebarRight2;
    }
}
