/*
 * @(#)Teacher.java Sep 8, 2007
 * Copyright 2007 sanqing organization, Inc. All rights reserved
 */
package com.sanqing.course.model;

import java.util.Set;

/**
 * 教师实体对象
 * @hibernate.class table="Teacher" lazy="false"
 */
public class Teacher {

    private String id;              //主键
    private String name;            //姓名

    private User user;             //级联的用户

    private Set<Course> courses;    //教授的课程

    /**
     * @hibernate.set lazy="true" cascade="none" table="teacher_course"  
     * @hibernate.key column="teacherId"  
     * @hibernate.many-to-many column="courseId" class="com.sanqing.course.model.Course"
     */
    public Set<Course> getCourses() {
        return courses;
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
     * @param courses the courses to set
     */
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
