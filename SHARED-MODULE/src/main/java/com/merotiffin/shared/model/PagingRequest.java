package com.merotiffin.shared.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class PagingRequest implements Serializable {

    private Map<String, Object> searchField;
    private int page;
    private int limit;

    public PagingRequest() {
        this.page = 1;
        this.limit = 10;
    }


}
