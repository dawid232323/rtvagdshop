package com.amadon.rtvagdshop.shared.service.validator;


public final class ValidationMessages
{
    public static class Common {
        public static final String fieldRequired = "Please provide value for field";
        public static final String fieldMaxLength = "Please provide shorter value";
        public static final String fieldMinLength = "Please provide shorter value";
    }

    public static class Name {
        public static final String firstNameMaxLength = "Please provide shorter first name";
        public static final String lastNameMaxLength = "Please provide shorter last name";
    }

    public static class Email {
        public static final String emailRequired = "Please provide email address";
        public static final String invalidEmail = "Please provide valid email address";
    }

    public static class PrimaryKey {
        public static final String validValue = "Please provide valid number starting from one";
    }

    public static class List {
        public static final String emptyNotNull = "List cannot be null. " +
                "If you don't want to put any items in the list, just provide the empty one";
    }
}
