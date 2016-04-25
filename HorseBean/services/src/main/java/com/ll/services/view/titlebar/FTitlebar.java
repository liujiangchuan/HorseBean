package com.ll.services.view.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ll.services.R;
import com.ll.services.helper.FLog;

/**
 * Created by Liujc on 2016/4/25.
 * Email liujiangchuan@hotmail.com
 */
public class FTitlebar extends LinearLayout implements IFTitlebar
{
    private TextView mFTitlebarText;
    private ImageView mFTitlebarLeft1;
    private TextView mFTitlebarRight1;

    private onTitlebarClickListener mClickListener;

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
        mFTitlebarLeft1 = (ImageView) findViewById(R.id.f_titlebar_left1);
        mFTitlebarRight1 = (TextView) findViewById(R.id.f_titlebar_right1);
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
    }

    public void setOnTitlebarClickListener(onTitlebarClickListener listener)
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

    @Override public void setLeft1Visible()
    {
        if (null != mFTitlebarLeft1)
        {
            mFTitlebarLeft1.setVisibility(View.VISIBLE);
        }
        else
        {
            FLog.e("mFTitlebarLeft1 is null!");
        }
    }

    @Override public void setLeft1Invisible()
    {
        if (null != mFTitlebarLeft1)
        {
            mFTitlebarLeft1.setVisibility(View.INVISIBLE);
        }
        else
        {
            FLog.e("mFTitlebarLeft1 is null!");
        }
    }

    @Override public void setLeft1Resource(int resId)
    {
        if (null != mFTitlebarLeft1)
        {
            mFTitlebarLeft1.setImageResource(resId);
        }
        else
        {
            FLog.e("mFTitlebarLeft1 is null!");
        }
    }

    @Override public void setRight1Visible()
    {
        if (null != mFTitlebarRight1)
        {
            mFTitlebarRight1.setVisibility(View.VISIBLE);
        }
        else
        {
            FLog.e("mFTitlebarRight1 is null!");
        }
    }

    @Override public void setRight1Invisible()
    {
        if (null != mFTitlebarRight1)
        {
            mFTitlebarRight1.setVisibility(View.INVISIBLE);
        }
        else
        {
            FLog.e("mFTitlebarRight1 is null!");
        }
    }

    @Override public void setRight1Text(int resId)
    {
        if (null != mFTitlebarRight1)
        {
            mFTitlebarRight1.setText(resId);
        }
        else
        {
            FLog.e("mFTitlebarRight1 is null!");
        }
    }
}
