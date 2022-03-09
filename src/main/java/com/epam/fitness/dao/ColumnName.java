package com.epam.fitness.dao;

public final class ColumnName {
    //Users table
    public static final String USER_ID = "users.id";
    public static final String LOGIN = "users.login";
    public static final String EMAIL = "users.email";
    public static final String PASSWORD = "users.password";
    public static final String USER_NAME = "users.name";
    public static final String USER_SURNAME = "users.surname";
    public static final String USER_STATUS = "users.status";
    public static final String USER_ROLE = "users.role";

    //Orders table
    public static final String ORDER_ID = "orders.id";
    public static final String ORDER_NAME = "orders.order_name";
    public static final String ORDER_DATE = "orders.order_date";
    public static final String ORDER_STATUS = "orders.status";
    public static final String ORDER_SUMMARY_PRICE = "orders.summary_price";
    public static final String ORDER_USER_ID = "orders.user_id";
    public static final String ORDER_TATTOO_ID = "orders.tattoo_id";

    //Tattoos table
    public static final String TATTOO_ID = "tattoos.id";
    public static final String TATTOO_NAME = "tattoos.name";
    public static final String TATTOO_PRICE = "tattoos.price";
    public static final String TATTOO_IMAGE = "tattoos.image";

    private ColumnName() {

    }
}
