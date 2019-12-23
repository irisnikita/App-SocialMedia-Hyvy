package com.example.hyvysocialapp.Adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hyvysocialapp.Model.ModelComment;
import com.example.hyvysocialapp.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterComments extends RecyclerView.Adapter<AdapterComments.MyHolder>{

    Context context;
    List<ModelComment> commentList;

    public AdapterComments(Context context, List<ModelComment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Liên kết layout row_comments.xml
        View view = LayoutInflater.from(context).inflate(R.layout.row_comments,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //Lấy dữ liệu
        String uid = commentList.get(position).getUid();
        String name = commentList.get(position).getuName();
        String email = commentList.get(position).getuEmail();
        String cid = commentList.get(position).getcId();
        String comment = commentList.get(position).getComment();
        String timestamp = commentList.get(position).getTimestamp();
        String image = commentList.get(position).getuDp();

        //ép kiểu ngay giờ cho timeStamp
        Calendar cal =Calendar.getInstance(Locale.CHINA);
        cal.setTimeInMillis(Long.parseLong(timestamp));
        String datetime = DateFormat.format("dd/MM/yyyy hh:mm aa",cal).toString();

        //Truyền dữ liệu vào view
        holder.nameTv.setText(name);
        holder.commentTv.setText(comment);
        holder.timeTv.setText(datetime);

        //đặt ảnh cho image
        try {
            Picasso.get().load(image).placeholder(R.drawable.ic_useravatar).into(holder.avatarIv);
        }
        catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    //tạo viewholder
    class MyHolder extends RecyclerView.ViewHolder {

        //khởi tạo views từ row_comments.xml
        ImageView avatarIv;
        TextView nameTv,commentTv,timeTv;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            avatarIv = itemView.findViewById(R.id.avatarIv);
            nameTv = itemView.findViewById(R.id.nameTv);
            commentTv = itemView.findViewById(R.id.commentTv);
            timeTv = itemView.findViewById(R.id.timeTv);

        }
    }
}
