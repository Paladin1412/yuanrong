package com.yuanrong.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class LogicUtil {
    public static boolean anyOr(boolean...booleans){
        boolean flag = false;
        for(int i = 0;i<booleans.length;i++){
            if(booleans[i] == true){
                flag = true;
            }
        }
        return flag;
    }
    /**
    * 全部是true的话返回true
    * @author      ShiLinghuai
    * @param
    * @return      boolean
    * @exception
    * @date        2018/7/14 15:26
    */
    public static boolean allAnd(boolean...booleans){
        boolean flag = true;
        for(int i = 0;i<booleans.length;i++){
            if(booleans[i] == false){
                flag = false;
            }
        }
        return flag;
    }
    public static int size(Collection collection) {
        return isEmpty(collection) ? 0 : collection.size();
    }

    public static int size(Object[] object) {
        return isEmpty(object) ? 0 : object.length;
    }

    public static boolean isEmpty(Object[] object) {
        return object == null || object.length == 0;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isAllHasValue(Object...objects){
        boolean flag = true;
        for(int i= 0;i<objects.length;i++){
            if( objects[i] instanceof String) {
                if(StringUtils.isBlank((String)objects[i])){
                    flag = false;
                }
            }else {
                if(objects[i] ==null){
                    flag = false;
                }
            }
        }
        return flag;
    }
    public static void main(String[] args){

    }
}
