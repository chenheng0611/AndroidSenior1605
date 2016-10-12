package com.qianfeng.day8_pack_apk;

import java.io.Serializable;

/**
 * Created by xray on 16/10/10.
 */

public class Teacher  implements Serializable {

    private String name;
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
