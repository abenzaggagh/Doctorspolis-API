package com.doctorspolis.backend.utility.commun;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractHelper {

    public void update(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public void replace(Object source, Object target) {
        BeanUtils.copyProperties(source, target, new String[0]);
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper sourceBeanWrapper = new BeanWrapperImpl(source);

        PropertyDescriptor[] propertyDescriptors = sourceBeanWrapper.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Object sourceValue = sourceBeanWrapper.getPropertyValue(propertyDescriptor.getName());

            if (sourceValue == null)
                emptyNames.add(propertyDescriptor.getName());
        }

        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }

}
