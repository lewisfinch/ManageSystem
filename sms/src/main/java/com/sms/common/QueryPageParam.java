package com.sms.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    private static int PAGE_SIZE = 20;
    private static int PAGE_NUM = 1;

    private int PageSize = PAGE_SIZE;
    private int PageNum = PAGE_NUM;

    private HashMap param = new HashMap();
}
