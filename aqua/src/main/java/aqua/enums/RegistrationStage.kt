package aqua.enums

enum class RegistrationStage{
    PROVIDER_CHOICE,


    EMAIL_AND_PASSWORD_INPUT,
    ILLEGAL_EMAIL_FIELD,
    ILLEGAL_PASSWORD_FIELD,
    WAITING_FOR_RESPONSE,
    OK_EMAIL_AND_PASS,

    EMAIL_REGISTRATION_SUCCESS,
    EMAIL_REGISTRATION_FAILED,
}