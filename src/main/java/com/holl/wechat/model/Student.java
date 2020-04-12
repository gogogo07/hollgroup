package com.holl.wechat.model;

public class Student {

    private String userId;

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
