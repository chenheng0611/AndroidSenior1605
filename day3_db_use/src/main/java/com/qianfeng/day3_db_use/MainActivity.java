package com.qianfeng.day3_db_use;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.day3_db_use.db.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEt;
    private EditText mAgeEt;
    private EditText mAddressEt;
    private RecyclerView mStudetList;
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;
    private List<Student> mStudents;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();

        initViews();


    }

    private void initDatabase() {
        mHelper = new SQLiteHelper(this);
        mDatabase = mHelper.getWritableDatabase();
        mStudents = getStudents();
    }

    private void initViews() {
        mNameEt = (EditText)findViewById(R.id.student_name_et);
        mAgeEt = (EditText)findViewById(R.id.student_age_et);
        mAddressEt = (EditText)findViewById(R.id.student_address_et);
        mStudetList = (RecyclerView)findViewById(R.id.recycler_view);
        mAdapter = new MyAdapter();
        mStudetList.setAdapter(mAdapter);
        mStudetList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onSubmit(View view) {
        insertStudent();
    }

    /**
     * 插入学生
     */
    private void insertStudent(){
        if(validateInput()){
            mDatabase.execSQL(
                    "insert into student_tb(name,age,address) values(?,?,?)",
                    new Object[]{mNameEt.getText().toString(),
                    mAgeEt.getText().toString(),
                    mAddressEt.getText().toString()});
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            mAdapter.refresh();
        }
    }

    /**
     * 查询所有学生
     * @return
     */
    private List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from student_tb", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            students.add(new Student(id,name,age,address));
        }
        cursor.close();
        return students;
    }

    /**
     * 输入验证
     * @return
     */
    private boolean validateInput(){
        String name = mNameEt.getText().toString();
        String age = mAgeEt.getText().toString();
        String address = mAddressEt.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please input your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(age)){
            Toast.makeText(this, "Please input your age", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(address)){
            Toast.makeText(this, "Please input your address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //1.继承ViewHodler
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mIdTv;
        TextView mNameTv;
        TextView mAgeTv;
        TextView mAddressTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            mIdTv = (TextView)itemView.findViewById(R.id.student_id_tv);
            mNameTv = (TextView)itemView.findViewById(R.id.student_name_tv);
            mAgeTv = (TextView)itemView.findViewById(R.id.student_age_tv);
            mAddressTv = (TextView)itemView.findViewById(R.id.student_address_tv);
        }
    }

    //2.继承Adapter
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        public void refresh(){
            mStudents = getStudents();
            notifyDataSetChanged();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.student_item
                    , parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Student student = mStudents.get(position);
            holder.mIdTv.setText(""+student.getId());
            holder.mNameTv.setText(student.getName());
            holder.mAgeTv.setText(""+student.getAge());
            holder.mAddressTv.setText(student.getAddress());
        }

        @Override
        public int getItemCount() {
            return mStudents.size();
        }
    }
}
