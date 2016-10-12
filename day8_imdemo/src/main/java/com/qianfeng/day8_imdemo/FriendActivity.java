package com.qianfeng.day8_imdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends AppCompatActivity {

    private ListView mFriendLv;
    private List<String> mFriendNames;
    private ArrayAdapter<String> mAdapter;
    private EditText mFriendNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        initViews();
        loadFriends();
    }

    private void loadFriends() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<String> usernames =
                            EMClient.getInstance().contactManager().getAllContactsFromServer();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mFriendNames.addAll(usernames);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initViews() {
        mFriendLv = (ListView)findViewById(R.id.friend_list_lv);
        mFriendNames = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mFriendNames);
        mFriendLv.setAdapter(mAdapter);

        mFriendNameEt = (EditText)findViewById(R.id.friend_name_et);

        mFriendLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FriendActivity.this,ChatActivity.class);
                intent.putExtra("friend_name",mFriendNames.get(position));
                startActivity(intent);
            }
        });
    }

    public void onAddFriend(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //参数为要添加的好友的username和添加理由
                try {
                    EMClient.getInstance().contactManager().addContact(
                            mFriendNameEt.getText().toString(),
                            "没有理由");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FriendActivity.this, "Add friend successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FriendActivity.this, "Add friend failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();

    }
}
