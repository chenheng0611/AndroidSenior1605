package com.qianfeng.day8_imdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView mChatLv;
    private ArrayList<String> mChatList;
    private ArrayAdapter<String> mAdapter;
    private EditText mMessageEt;
    private String mFriendName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mFriendName = getIntent().getStringExtra("friend_name");

        initViews();
        receiveMessage();
    }

    public void onSendMessage(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(mMessageEt.getText().toString(),
                        mFriendName);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
            }
        }).start();

    }

    private void receiveMessage() {
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    private void initViews() {
        mChatLv = (ListView)findViewById(R.id.chat_list_lv);
        mChatList = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mChatList);
        mChatLv.setAdapter(mAdapter);

        mMessageEt = (EditText)findViewById(R.id.chat_message_et);
    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(final List<EMMessage> messages) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EMMessage msg = messages.get(0);
                    //收到消息
                    mChatList.add(msg.getUserName()+" : "+msg.getBody());
                    mAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> message) {
            //收到已送达回执
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在不需要的时候移除listener，如在activity的onDestroy()时
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}
