package com.hannamsm.shop.global.error;

public enum ErrorCode {
	// Common
    INVALID_INPUT_VALUE(400, "CM001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "CM002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "CM003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "CM004", "Server Error"),
    INVALID_TYPE_VALUE(400, "CM005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "CM006", "Access is Denied"),


    // Address
    ADDRESS_BILLING_NOT_FOUND_ERROR(400, "AB001", "Billing address could not be found!"),

    // Member
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
    PASSWORD_NOT_MATCH(410, "M003", "Password is not matched"),
    ID_NOT_FOUND(410, "M004", "ID NOT FOUND"),

    // Cart
    CART_ITEM_ALREADY_USE(400, "CA001", "CartItem was already used!"),
    CART_ITEM_DUPLICATION(400, "CA002", "CartItem is Duplication!"),
    CART_ITEM_WRONG_PRICES(400, "CA003", "MaxPrics is wrong!"),
    CART_ITEM_END_EVENT_DATE_TIME(400, "CA004", "EndEventDateTime is wrong!"),
    //COUPON_EXPIRE(400, "CO004", "Coupon was already expired")

    // EVENT
    EVENT_WRONG_PRICES_ERROR(400, "EV001", "MaxPrics is wrong!"),
    EVENT_END_EVENT_DATE_TIME_ERROR(400, "EV002", "EndEventDateTime is wrong!"),

    // FILE
    FILE_STORAGE_ERROR(400, "FI001", "Could not store file!"),
    FILE_NOT_FOUND_ERROR(400, "FI002", "Could not found file!"),

    // PICKUP
    PICKUP_NO_SLOT_TIME_ERROR(400, "PI001", "There is no slot time!"),
    PICKUP_NO_DEFAULT_SLOT_TIME_ERROR(400, "PI001", "There is no default slot time!"),

    // ORDER
    ORDER_NOT_FOUND_ERROR(400, "OR001", "Order is not found!"),
    ORDER_STATUS_WRONG_PRICES(400, "OR002", "Order status is wrong!"),

    // PAYMENT
    PAYMENT_NOT_FOUND_ERROR(400, "PI001", "Could not found payment!"),
    PAYMENT_DECLINED_ERROR(400, "PI002", "Transaction declined!"),

    // Credit Card
    CREDIT_CARD_NUMBER_INVALID(400, "CC5000", "Credit Card Number Invalid!"),

    //999
    ETC_ERROR(500, "ZZ999", "Etc is Error!")
    ;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
