package com.qianfeng.day3_greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qianfeng.day3_greendao.bean.Score;
import com.qianfeng.day3_greendao.bean.Student;
import com.qianfeng.day3_greendao.db.DaoMaster;
import com.qianfeng.day3_greendao.db.DaoSession;
import com.qianfeng.day3_greendao.db.ScoreDao;
import com.qianfeng.day3_greendao.db.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "1605";
    private StudentDao mStudentDao;
    private ScoreDao mScoreDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDao();
    }

    private void initDao() {
        DaoMaster.DevOpenHelper helper =
                new DaoMaster.DevOpenHelper(this,"students.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mStudentDao = daoSession.getStudentDao();
        mScoreDao = daoSession.getScoreDao();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_student_btn:
                insertStudent();
                break;
            case R.id.query_student_btn:
                queryStudent();
                break;
            case R.id.delete_student_btn:
                deleteStudent();
                break;
            case R.id.update_student_btn:
                updateStudent();
                break;
            case R.id.insert_score_btn:
                insertScores();
                break;
            case R.id.query_score_btn:
                queryStudentWithScores();
                break;
        }
    }

    private void insertStudent(){
        mStudentDao.insert(new Student("zhangsan",30,"wuhan"));
    }

    private void deleteStudent(){
        mStudentDao.deleteByKey(1L);
    }

    private void updateStudent(){
        mStudentDao.update(new Student(3L,"lisi",44,"shanghai"));
    }

    private void queryStudent(){
//        List<Student> list = mStudentDao.queryBuilder()
//        .where(StudentDao.Properties.Id.eq(2L)).build().list();
        List<Student> list = mStudentDao.queryBuilder().build().list();
        for(Student stu : list){
            Log.i(TAG, "queryStudent: " +stu);
        }
    }

    private void insertScores(){
        List<Score> scores = new ArrayList<>();
        scores.add(new Score(3L,88,"Math"));
        scores.add(new Score(3L,77,"English"));
        scores.add(new Score(3L,66,"Chinese"));
        mScoreDao.insertInTx(scores);
    }

    private void queryStudentWithScores(){
        List<Student> list = mStudentDao.queryBuilder()
        .where(StudentDao.Properties.Id.eq(3L)).build().list();
        for(Student stu : list){
            Log.i(TAG, "queryStudent: " +stu);
            List<Score> scores = stu.getScores();
            for(Score score : scores){
                Log.i(TAG, "score: " +score);
            }
        }
    }
}
