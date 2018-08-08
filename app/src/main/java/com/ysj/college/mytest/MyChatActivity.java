package com.ysj.college.mytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import adapter.MsgAdapter;
import bean.Msg;
import util.NetUtils;

/**
 * Created by wang on 2018/1/10.
 * 使用RecyclerView完成聊天界面
 *
 * 远程地址：https://github.com/wangsunny/simpleRecyclerviewChat.git
 *
 */

public class MyChatActivity extends BaseActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView recyclerView;
    private MsgAdapter adapter;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mychat);
        //初始化消息数据
        initMsgs();
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //创建默认的线性LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(adapter);
        type = Msg.TYPE_SENT;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,type);
                    msgList.add(msg);
                    //当有新消息时，刷新RecyclerView中的显示
                    adapter.notifyItemInserted(msgList.size()-1);
                    //将RecyclerView定位到最后一行
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    //清空输入框中的内容
                    inputText.setText("");
                    // 切换对话收发状态，形成对聊形式
                    type=type==Msg.TYPE_RECEIVED?Msg.TYPE_SENT:Msg.TYPE_RECEIVED;
                }
            }
        });
        //通过浏览器跳转到app
        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();
        if(Intent.ACTION_VIEW.equals(action)){
            Uri uri = i_getvalue.getData();
            if(uri != null){
                String name = uri.getQueryParameter("name");
                String age = uri.getQueryParameter("age");
            }
        }
    }

    @Override
    public void reConnect() {
        super.reConnect();
        //重新发起网络请求
        Log.i("tag","------MyChatActivity/NetUtils.isConnected(mContext)="+ NetUtils.isConnected(mContext));
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy",Msg.TYPE_RECEIVED);
        Msg msg2 = new Msg("Hello.Who is that?",Msg.TYPE_SENT);
        Msg msg3 = new Msg("I am Jack.Nice talking to you.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        msgList.add(msg2);
        msgList.add(msg3);
    }
}
