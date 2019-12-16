package com.example.deanaagirl.NursingDiary.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deanaagirl.NursingDiary.Model.Comment;
import com.example.deanaagirl.NursingDiary.R;

import java.util.List;

public class CommentAdapter extends BaseAdapter {

    private Context mCommentContext;
    private List<Comment> mCommentList;

    public CommentAdapter(Context mCommentContext, List<Comment> mCommentList) {
        this.mCommentContext = mCommentContext;
        this.mCommentList = mCommentList;
    }

    public int getCount() {
        return mCommentList.size();
    }

    public Object getItem(int position) {
    return mCommentList.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position ,View convertView, ViewGroup parent ) {
        View v = View.inflate(mCommentContext, R.layout.item_comment,null);
        TextView tvTname = v.findViewById(R.id.textView_comment_Tname);
        TextView tvContent = v.findViewById(R.id.textView_comment_content);
        TextView tvDate = v.findViewById(R.id.textView_comment_date);

        tvTname.setText(mCommentList.get(position).getTeacherName());
        tvContent.setText(mCommentList.get(position).getCommentContent());
        tvDate.setText(mCommentList.get(position).getDateComment());

        //Save product id to tag
        v.setTag(mCommentList.get(position).getClass());
        return v;
    }
}
