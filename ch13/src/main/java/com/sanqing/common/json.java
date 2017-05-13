/*
 * json.java
 *
 * Created on 2007骞�6鏈�16鏃�, 涓嬪崍5:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sanqing.common;

import java.util.ArrayList;

/**
 * @author root
 */
public class json {

    /**
     * Creates a new instance of json
     */
    public json() {
    }

    //  对应JSON的singleInfo成员
    public String singleInfo = "";
    protected String _error = "";
    protected boolean _success = true;
    protected ArrayList arrData = new ArrayList();
    protected ArrayList arrDataItem = new ArrayList();

    //  重置，每次新生成一个json对象时必须执行该方法
    public void Reset() {
        _success = true;
        _error = "";
        singleInfo = "";
        arrData.clear();
        arrDataItem.clear();
    }

    ///添加data数组中一个元素（js对象）的一个名值对，例如
    ///对于一个数组元素：{userName:"supNate",userId:"1"}
    ///需执行两次AddItem：
    ///AddItem("userName","supNate");
    ///AddItem("userId","1");
    ///最后执行
    ///ItemOk();
    ///表示数组元素添加完毕，底下的AddItem表示另一个数组元素的开始
    public void AddItem(String name, String _value) {
        arrDataItem.add(name);
        arrDataItem.add(_value);
    }

    //  一个数组元素添加完毕（data数组）
    public void ItemOk() {
        arrData.add(arrDataItem);
        arrDataItem = new ArrayList();
    }

    //序列化JSON对象，得到返回的JSON代码
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("success:" + (_success ? "true" : "false") + ",");
        sb.append("error:\"" + _error.replace("\"", "\\\"") + "\",");
        sb.append("singleInfo:\"" + singleInfo.replace("\"", "\\\"") + "\",");
        sb.append("data:[");

        for (int i = 0; i < arrData.size(); i++) {

            ArrayList arr = (ArrayList) arrData.get(i);
            sb.append("{");
            for (int j = 0; j < arr.size(); j += 2) {
                if (j == arr.size()) break;
                sb.append(arr.get(j));
                sb.append(":");
                sb.append("\"");
                try {
                    sb.append(arr.get(j + 1).toString().replace("\"", "\\\""));
                } catch (Exception eee) {
                }
                sb.append("\"");
                if (j < arr.size() - 2) sb.append(",");
            }
            sb.append("}");
            if (i < arrData.size() - 1) sb.append(",");
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    public void set_error(String error) {

        if (error != "") _success = false;
        this._error = _error;
    }

    public String get_error() {
        return _error;
    }

    public void set_success(boolean success) {
        if (success) _error = "";
        this._success = success;
    }

    public boolean is_success() {
        return _success;
    }
}
