package com.example.arbaz.sparshdemo.Model;

import java.io.Serializable;

/**
 * Created by arbaz on 15/10/16.
 */

public class DetailFields implements Serializable {
    public int m_id;
    public String faliya_name;
    public String head_household;
    public String occupation_household;
    public String age_household;
    public String gender_household;
    public String names_familymembers;

    public DetailFields() {

    }

    public DetailFields(int m_id, String faliya_name, String head_household, String occupation_household, String age_household, String gender_household, String names_familymembers) {
        this.m_id = m_id;
        this.faliya_name = faliya_name;
        this.head_household = head_household;
        this.occupation_household = occupation_household;
        this.age_household = age_household;
        this.gender_household = gender_household;
        this.names_familymembers = names_familymembers;

    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getFaliya_name() {
        return faliya_name;
    }

    public void setFaliya_name(String faliya_name) {
        this.faliya_name = faliya_name;
    }

    public String getHead_household() {
        return head_household;
    }

    public void setHead_household(String head_household) {
        this.head_household = head_household;
    }

    public String getOccupation_household() {
        return occupation_household;
    }

    public void setOccupation_household(String occupation_household) {
        this.occupation_household = occupation_household;
    }

    public String getAge_household() {
        return age_household;
    }

    public void setAge_household(String age_household) {
        this.age_household = age_household;
    }

    public String getGender_household() {
        return gender_household;
    }

    public void setGender_household(String gender_household) {
        this.gender_household = gender_household;
    }

    public String getNames_familymembers() {
        return names_familymembers;
    }

    public void setNames_familymembers(String names_familymembers) {
        this.names_familymembers = names_familymembers;
    }
}