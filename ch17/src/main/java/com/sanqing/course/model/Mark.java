/*
 * @(#)Mark.java Sep 8, 2007
 * Copyright 2007 sanqing organization, Inc. All rights reserved
 */
package com.sanqing.course.model;

/**
 * 成绩实体对象
 * @hibernate.class table="Mark" lazy="false"
 */
public class Mark {

    private String id;          //主键
    private Student student;    //学生
    private Course course;      //课程
    private Float score;        //成绩

    /**
     * @hibernate.many-to-one column="studentId" class="com.sanqing.course.model.Student" 
     */
    public Student getStudent() {
        return student;
    }
    /**
     * @hibernate.many-to-one column="courseId" class="com.sanqing.course.model.Course" 
     */
    public Course getCourse() {
        return course;
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
     *      column="score"
     *      not-null="false"
     */
    public Float getScore() {
        return score;
    }
    /**
     * @param course the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @param score the score to set
     */
    public void setScore(Float score) {
        this.score = score;
    }
    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

}
