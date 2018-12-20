package com.ll.horsebean.demo.database.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ll.horsebean.C;
import com.ll.horsebean.R;
import com.ll.horsebean.common.DemoBaseActivity;
import com.ll.horsebean.common.view.dialog.DialogCreator;
import com.ll.horsebean.db.baseAccess.BookDaoBase;
import com.ll.horsebean.db.framework.Book;
import com.ll.horsebean.demo.database.model.DatabaseDataAdapter;
import com.ll.horsebean.demo.database.model.DatabaseModel;
import com.ll.services.helper.FStatisticAgent;
import com.ll.services.util.FTimeUtil;
import com.ll.services.view.recyclerview.onFRecyclerItemTouchListener;
import com.ll.services.view.titlebar.IFTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Liujc on 2016/8/17.
 * Email: liujiangchuan@hotmail.com
 */
public class DatabaseActivity extends DemoBaseActivity
{
    //view
    @BindView(R.id.et_database_input) EditText mEtDatabaseInput;
    @BindView(R.id.rv_database_data)
    RecyclerView mRvDatabaseData;

    private Dialog mDeleteDialog;
    //model
    private DatabaseModel mDatabaseModel;
    //adapter
    private DatabaseDataAdapter mDatabaseDataAdapter;

    @Override protected int getLayoutResource()
    {
        return R.layout.activity_database;
    }

    @Override protected void initTitlebar(IFTitlebar titlebar)
    {
        titlebar.setTitleText(getClass().getName());
    }

    @Override protected void onInit(Bundle savedInstanceState)
    {
        mDatabaseModel = new DatabaseModel();
        mDatabaseDataAdapter = new DatabaseDataAdapter(mDatabaseModel.mBooks);

        mRvDatabaseData.setAdapter(mDatabaseDataAdapter);
        mRvDatabaseData.addOnItemTouchListener(new onFRecyclerItemTouchListener(mRvDatabaseData,
                new onFRecyclerItemTouchListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, final int position)
                    {
                        String message = String.format("Sure to delete '%s' ?",
                                mDatabaseDataAdapter.getItem(position).getName());
                        mDeleteDialog = DialogCreator
                                .createConfirmDialog(DatabaseActivity.this, "Warning", message,
                                        "OK", "CANCEL", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {
                                                Book book = mDatabaseDataAdapter.getItem(position);
                                                BookDaoBase.getInstance()
                                                        .deleteByKey(book.getContent_id());
                                                mDatabaseDataAdapter.removeItem(book);
                                                mDeleteDialog.dismiss();
                                                FStatisticAgent
                                                        .onEvent(C.statistic.DEMO_DATABASE_DELETE);
                                            }
                                        }, new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {
                                                mDeleteDialog.dismiss();
                                            }
                                        });
                        mDeleteDialog.show();
                    }

                    @Override public void onItemLongClick(View view, int position)
                    {

                    }
                }));
        mRvDatabaseData.setLayoutManager(
                new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
    }

    @Override protected void loadData()
    {
        mDatabaseModel.reloadData();
        mDatabaseDataAdapter.notifyDataSetChanged(mDatabaseModel.mBooks);
    }

    @OnClick(R.id.et_database_save) public void onClick()
    {
        String text = mEtDatabaseInput.getText().toString();
        if (!TextUtils.isEmpty(text))
        {
            //create object
            Book book = new Book();
            book.setContent_id(FTimeUtil.getCurrentTime());
            book.setName(mEtDatabaseInput.getText().toString());
            //insert database
            BookDaoBase.getInstance().insert(book);
            //update UI
            mEtDatabaseInput.setText("");
            mDatabaseDataAdapter.proposeItem(book);
        }
        FStatisticAgent.onEvent(C.statistic.DEMO_DATABASE_ADD);
    }
}
