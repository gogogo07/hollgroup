package com.holl.wechat.model.config;

import java.io.Serializable;

public class StudentMultiKeysClass implements Serializable {

    private String userId;
    private String schoolId;

    public StudentMultiKeysClass() { }

    public StudentMultiKeysClass(String userId, String schoolId) {
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

    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((userId == null) ? 0 : userId.hashCode());
        result = PRIME * result + ((schoolId == null) ? 0 : schoolId.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final StudentMultiKeysClass other = (StudentMultiKeysClass) obj;
        if(userId == null){
            if(other.userId != null){
                return false;
            }
        }else if(!userId.equals(other.userId)){
            return false;
        }
        if(schoolId == null){
            if(other.schoolId != null){
                return false;
            }
        }else if(!schoolId.equals(other.schoolId)){
            return false;
        }

        return true;
    }
}
