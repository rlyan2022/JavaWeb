package com.sanqing.service;

import java.util.Date;

public interface DianjiliangService {
    //判断是否点击过
    public boolean isVistor(int AId, String IP, Date time);
}
