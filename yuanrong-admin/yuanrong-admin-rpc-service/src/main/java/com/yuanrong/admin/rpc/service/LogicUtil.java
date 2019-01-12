package com.yuanrong.admin.rpc.service;

import com.yuanrong.admin.rpc.api.system.AdminUserServicesI;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
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
    /**
     * 把代理类的字节码写到硬盘上
     * @param path 保存路径
     */
    public static void writeProxyClassToHardDisk(String path) {
        // 第一种方法，这种方式在刚才分析ProxyGenerator时已经知道了
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);

        // 第二种方法

        // 获取代理类的字节码
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy106", Executor.class.getInterfaces());

        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        LogicUtil.writeProxyClassToHardDisk("D:/$Proxy.class");
    }
}
