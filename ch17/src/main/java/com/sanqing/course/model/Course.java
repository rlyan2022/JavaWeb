/*
 * @(#)Course.java Sep 8, 2007
 * Copyright 2007 sanqing organization, Inc. All rights reserved
 */
package com.sanqing.course.model;

import java.util.Set;

/**
 * 课程实体对象
 * @hibernate.class table="Course" lazy="false"
 */
public class Course {

    private String id;      //主键
    private String name;    //课程名

    private Set<Teacher> teachers;  //任教老师

    /**
     * @hibernate.set lazy="true" table="teacher_course"  
     * @hibernate.key column="courseId"
     * @hibernate.many-to-many column="teacherId" class="com.sanqing.course.model.Teacher"
     */
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * @hibernate.id
     *      column="id"
     *      generator-class="uuid.hex"
     */
    public String getId() {
        return id;
    }

    /**
     * @hibernate.property
     *      column="name"
     *      not-null="false"
     */
    public String getName() {
        return name;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param teachers the teachers to set
     */
    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

}
