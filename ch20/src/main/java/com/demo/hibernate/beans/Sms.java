/*
 * Created Tue Aug 28 18:29:29 CST 2007 by MyEclipse Hibernate Tool.
 */
package com.demo.hibernate.beans;

import java.io.Serializable;

/**
 * A class that represents a row in the 'sms' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class Sms
        extends AbstractSms
        implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3116317566760985263L;

    /**
     * Simple constructor of Sms instances.
     */
    public Sms() {
    }

    /**
     * Constructor of Sms instances given a simple primary key.
     *
     * @param id
     */
    public Sms(Integer id) {
        super(id);
    }

    /* Add customized code below */

}
