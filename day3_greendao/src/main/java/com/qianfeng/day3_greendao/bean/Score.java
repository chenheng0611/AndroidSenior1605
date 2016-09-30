package com.qianfeng.day3_greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xray on 16/9/28.
 */
@Entity
public class Score {
    @Id(autoincrement = true)
    private Long id;

    private Long studentId;
    private int score;
    private String subject;
    @Generated(hash = 712834684)
    public Score(Long id, Long studentId, int score, String subject) {
        this.id = id;
        this.studentId = studentId;
        this.score = score;
        this.subject = subject;
    }

    public Score(Long studentId, int score, String subject) {
        this.studentId = studentId;
        this.score = score;
        this.subject = subject;
    }

    @Generated(hash = 226049941)
    public Score() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", score=" + score +
                ", subject='" + subject + '\'' +
                '}';
    }
}
