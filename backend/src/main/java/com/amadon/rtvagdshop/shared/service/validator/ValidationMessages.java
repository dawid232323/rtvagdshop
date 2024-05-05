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
}
