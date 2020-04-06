package com.holl.wechat.model;

import com.holl.wechat.model.config.StudentMultiKeysClass;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "students")
@IdClass(StudentMultiKeysClass.class)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, length = 30)
    private String userId;

    @Id
    @Column(nullable = false, length = 30)
    private String schoolId;

    public Student() {}

    public Student(String userId, String schoolId) {
        this.userId = userId;
        this.schoolId = schoolId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
