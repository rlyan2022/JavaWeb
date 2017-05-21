/*
 * Created Tue Aug 28 18:29:26 CST 2007 by MyEclipse Hibernate Tool.
 */
package com.demo.hibernate.beans;

import java.io.Serializable;

/**
 * A class that represents a row in the 'meeting' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class Meeting
        extends AbstractMeeting
        implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3215681451729420622L;

    /**
     * Simple constructor of Meeting instances.
     */
    public Meeting() {
    }

    /**
     * Constructor of Meeting instances given a simple primary key.
     *
     * @param id
     */
    public Meeting(Integer id) {
        super(id);
    }

    /* Add customized code below */

}
