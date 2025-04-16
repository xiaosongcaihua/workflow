package com.emos.workflow.exception;

public class EmosException extends RuntimeException {
    private int code = 500;  // HTTP-like error code
    private String message;  // Error message

    // Constructor with message, code, and cause
    public EmosException(String message, int code, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = code;
    }

    // Constructor with message and cause
    public EmosException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    // Constructor with message and code
    public EmosException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    // Constructor with message only
    public EmosException(String message) {
        super(message);
        this.message = message;
    }

    // Getter for error code
    public int getCode() {
        return code;
    }

    // Setter for error code
    public void setCode(int code) {
        this.code = code;
    }

    // Getter for error message
    public String getMsg() {
        return message;
    }

    // Setter for error message
    public void setMsg(String message) {
        this.message = message;
    }

    // Override equals method for comparing exceptions
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmosException)) {
            return false;
        }
        EmosException other = (EmosException) obj;
        return this.code == other.code && this.message.equals(other.message);
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + code;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    // Override toString method to return a custom string representation
    @Override
    public String toString() {
        return "EmosException { " +
                "code=" + code +
                ", message='" + message + '\'' +
                " }";
    }

    // Override canEqual to ensure equality checks between instances of the same class
    protected boolean canEqual(Object obj) {
        return obj instanceof EmosException;
    }
}
