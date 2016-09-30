package com.qianfeng.day3_greendao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.qianfeng.day3_greendao.bean.Score;
import com.qianfeng.day3_greendao.bean.Student;

import com.qianfeng.day3_greendao.db.ScoreDao;
import com.qianfeng.day3_greendao.db.StudentDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig scoreDaoConfig;
    private final DaoConfig studentDaoConfig;

    private final ScoreDao scoreDao;
    private final StudentDao studentDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        scoreDaoConfig = daoConfigMap.get(ScoreDao.class).clone();
        scoreDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        scoreDao = new ScoreDao(scoreDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);

        registerDao(Score.class, scoreDao);
        registerDao(Student.class, studentDao);
    }
    
    public void clear() {
        scoreDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
    }

    public ScoreDao getScoreDao() {
        return scoreDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

}