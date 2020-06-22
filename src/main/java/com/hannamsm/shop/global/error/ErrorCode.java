package com.hannamsm.shop.global.error;

public enum ErrorCode {
	// Common
    INVALID_INPUT_VALUE(400, "CM001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "CM002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "CM003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "CM004", "Server Error"),
    INVALID_TYPE_VALUE(400, "CM005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "CM006", "Access is Denied"),


    // Member
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),

    // Cart
    CART_ITEM_ALREADY_USE(400, "CA001", "CartItem was already used!"),
    CART_ITEM_DUPLICATION(400, "CA002", "CartItem is Duplication!"),
    CART_ITEM_WRONG_PRICES(400, "CA003", "MaxPrics is wrong!"),
    CART_ITEM_END_EVENT_DATE_TIME(400, "CA004", "EndEventDateTime is wrong!"),
    //COUPON_EXPIRE(400, "CO004", "Coupon was already expired")

    // EVENT
    EVENT_WRONG_PRICES(400, "EV001", "MaxPrics is wrong!"),
    EVENT_END_EVENT_DATE_TIME(400, "EV002", "EndEventDateTime is wrong!"),

    // FILE
    FILE_STORAGE_ERROR(400, "FI001", "Could not store file!"),
    FILE_NOT_FOUND_ERROR(400, "FI002", "Could not found file!"),

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