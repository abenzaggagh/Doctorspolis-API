package com.doctorspolis.backend.commun;

import lombok.experimental.Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

@Helper
public abstract class AbstractHelper {

    public void update(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] propertyDescriptors;
        propertyDescriptors = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Object srcValue = src.getPropertyValue(propertyDescriptor.getName());
            if (srcValue == null) emptyNames.add(propertyDescriptor.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
