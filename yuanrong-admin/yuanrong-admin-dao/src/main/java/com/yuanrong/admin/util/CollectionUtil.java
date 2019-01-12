package com.yuanrong.admin.util;

import java.util.*;

/**
 * Created by zhonghang on 2018/4/11.
 */
public class CollectionUtil {
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

    public static List getList(Set set) {
        List v = new ArrayList();
        if (size((Collection)set) > 0) {
            Iterator iterator = set.iterator();

            while(iterator.hasNext()) {
                v.add(iterator.next());
            }
        }
        return v;
    }
}
