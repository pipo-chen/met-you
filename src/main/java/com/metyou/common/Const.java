package com.metyou.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String WECHAT = "wechat";

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }
    public interface  Cart {
        int CHECKED = 1; //购物车选中状态
        int UN_CHECKED = 0; //购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL"; //限制失败
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS"; //限制成功

    }

    public interface Role {
        int ROLE_CUSTOMER = 0; // 学员
        int ROLE_ADMIN = 1; //管理员
        int ROLE_SUPER_ADMIN = 2;
    }
    public interface OrderStatus {
        int ORDER_STATUS_NEED_BEGIN = 1;
        int ORDER_STATUS_ACTIVE = 2;
        int ORDER_STATUS_FINISHED = 3;
        int ORDER_STATUS_PAYED = 4;
        //订单取消
        int ORDER_STATUS_CANCLE = 5;
    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在线");

        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}

