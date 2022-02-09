package com.example.todo.constants;

/**
 * Class containing the constants used in the application.
 */
public class ApplicationConstants {

    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    /**
     * Common numbers.
     */
    public static final int ZERO = 0;

    /**
     * Class containing the error codes.
     */
    public static class ErrorCode {
        public static final int BAD_REQUEST_ERROR_CODE = 400;
        public static final int FORBIDDEN_ERROR_CODE = 403;
        public static final int NOT_FOUND_ERROR_CODE = 404;
        public static final int INTERNAL_ERROR_CODE = 500;
    }

    /**
     * Class containing the error descriptions.
     */
    public static class ErrorMessage {
        public static final String PERSON_DOES_NOT_EXIST_IN_DATABASE = "No such person exist with id: %s";
        public static final String TASK_DOES_NOT_EXIST_IN_DATABASE = "No such task exist with id: %s";
        public static final String GET_PERSON_FAILED = "Unable to fetch person details for id: %s with error %s";
        public static final String ADD_PERSON_FAILED = "Unable to add person details: %s with error: %s";
        public static final String UPDATE_PERSON_FAILED = "Unable to update person details for id: %s with error: %s";
        public static final String GET_TASK_FAILED = "Unable to fetch task details for id: %s with error %s";
        public static final String ADD_TASK_FAILED = "Unable to add task details: %s with error: %s";
        public static final String UPDATE_TASK_FAILED = "Unable to update task details for id: %s with error: %s";

    }

}
