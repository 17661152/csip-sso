package com.expect.csip.iam.domain;

import com.expect.common.domain.BaseDomain;

/**
 * @author jie
 * @date 2018-12-10
 */
public class Redis extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private String key;

    private Object value;

    public Redis() {
    }

    public Redis(String key) {
        this.key = key;
    }

    public Redis(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
