package com.shurrik.codegen.model;

import java.util.Comparator;

/**
 * Created by lip on 2015-12-25.
 */
public class ComparatorClassObject implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        ClassObject co1=(ClassObject)o1;
        ClassObject co2=(ClassObject)o2;

        int flag=co1.getClassName().compareTo(co2.getClassName());
        return flag;
    }
}
