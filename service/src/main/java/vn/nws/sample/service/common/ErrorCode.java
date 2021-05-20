package vn.nws.sample.service.common;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorCode {
	public static final BusinessErrorCode INTERNAL_SERVER_ERROR = new BusinessErrorCode(
            500_000, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final BusinessErrorCode INVALID_PARAMETERS = new BusinessErrorCode(
            400_000, "Invalid parameters", HttpStatus.BAD_REQUEST);
    public static BusinessErrorCode VALIDATE_FAILED(List<FieldViolation> errors) {
                return new BusinessErrorCode(
                        400_001, "Invalid parameters", HttpStatus.BAD_REQUEST, errors);
            }
    public static final BusinessErrorCode EMAIL_NOT_FOUND = new BusinessErrorCode(
            400_002, "Email not found", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode INVALID_TOKEN = new BusinessErrorCode(
            400_003, "Invalid token", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode GROUP_EXIST = new BusinessErrorCode(
            400_004, "Group does not exist", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode CATEGORY_MATCH = new BusinessErrorCode(
            400_0041, "Category does not match", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode TOKEN_EXPIRED = new BusinessErrorCode(
            400_005, "Token expired", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode ADMIN_NOT_FOUND = new BusinessErrorCode(
            400_006, "Admin not found", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode RESET_PASS_TOKEN_NOT_FOUND = new BusinessErrorCode(
            400_007, "Reset pass Token not found", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode EMAIL_EXISTED = new BusinessErrorCode(
            400_008, "Email existed", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode PHONE_NUMBER_EXISTED = new BusinessErrorCode(
            400_0081, "Phone number existed", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode EMAIL_SENT = new BusinessErrorCode(
            400_009, "Email sent", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode EMAIL_REPRESENTATIVE_NOT_FOUND = new BusinessErrorCode(
            400_010, "Email representative not found", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode USER_NOT_FOUND = new BusinessErrorCode(
            400_010, "User not found", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode EMAIL_ADMINISTRATOR_NOT_FOUND = new BusinessErrorCode(
            400_011, "Email administrator not found", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode LINE_CANNOT_GET_BOT_INFO = new BusinessErrorCode(
            400_12, "Cannot get Bot Info", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode DUPLICATED_STUDENT_NUMBER = new BusinessErrorCode(
            400_013, "The student number is duplicated", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode DUPLICATED_EMAIL = new BusinessErrorCode(
            400_014, "The email is duplicated", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode LINE_BOT_ACCESS_TOKEN_NOT_FOUND = new BusinessErrorCode(
            400_015, "The line bot access token is duplicated", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode DELETE_USER_FAILED = new BusinessErrorCode(
    		400_016, "æ¨©é™�ã‚’å¤–ã�—ã�¦ã�‹ã‚‰å‰Šé™¤ã‚’è¡Œã�£ã�¦ã��ã� ã�•ã�„", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode FILE_BLANK = new BusinessErrorCode(
            400_017, "The file format is invalid", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode FILE_EMPTY = new BusinessErrorCode(
            400_018, "The file format is empty", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode NUMBER_OF_COLUMNS_NOT_MATCH = new BusinessErrorCode(
            400_019, "The number of columns of csv file is not match", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode ROLE_NOT_SELECTED = new BusinessErrorCode(
    		400_020, "é…�ä¿¡å¯¾è±¡ã�¯å¿…é ˆã�§ã�™ã€‚", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode GROUP_INACTIVE = new BusinessErrorCode(
    		400_021, "Group stop use", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode GROUP_ACCOUNT_EXIST = new BusinessErrorCode(
    		400_022, "Group does not exist", HttpStatus.BAD_REQUEST);
    public static final BusinessErrorCode OUT_OF_PROCESS_TIME = new BusinessErrorCode(
    		400_023, "é…�ä¿¡å�¯èƒ½æ™‚é–“å†…ã�«ãƒ¡ãƒ¼ãƒ«é€�ä¿¡æ™‚é–“ã‚’æŒ‡å®šã�—ã�¦ã��ã� ã�•ã�„", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode DELIVERY_BOX_NOT_FOUND = new BusinessErrorCode(
    		400_024, "Delivery box not found!", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode DELIVERY_BOX_SENT = new BusinessErrorCode(
    		400_025, "Delivery box has been sent!", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode SURVEY_NOT_FOUND = new BusinessErrorCode(
    		400_026, "Survey not found!", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode PHONE_NUMBER_REQUIRED = new BusinessErrorCode(
    		400_027, "Phone number is required", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode LINE_BOT_ACCESS_TOKEN_EXIST = new BusinessErrorCode(
    		400_028, "Line bot access token existed", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode PHONE_NOT_MATCH = new BusinessErrorCode(
    		400_029, "Phone number not match with Admin's Phone", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode OTP_VERIFY_ERROR = new BusinessErrorCode(
    		400_030, "Cannot Verify OTP", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode OTP_VERIFY_MAX_ERROR = new BusinessErrorCode(
    		400_031, "OTP Verify Error 5 times", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode PHONE_VERIFY_MAX_ERROR = new BusinessErrorCode(
    		400_032, "Phone number verify error 5 times", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode PHONE_SEND_OTP_ERROR = new BusinessErrorCode(
    		400_033, "Cannot send OTP to this phone number", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode SSO_TOKEN_NOT_FOUND = new BusinessErrorCode(
    		400_034, "SSO Token not found.", HttpStatus.BAD_REQUEST);
	public static final BusinessErrorCode SSO_GROUP_NOT_FOUND = new BusinessErrorCode(
    		400_035, "SSO Group not found.", HttpStatus.BAD_REQUEST);

    public static final BusinessErrorCode UNAUTHORIZED = new BusinessErrorCode(
            401_000, "Unauthorized", HttpStatus.UNAUTHORIZED);
    public static final BusinessErrorCode AUTHEN_FAIL = new BusinessErrorCode(
            401_001, "Password & username mismatch.", HttpStatus.UNAUTHORIZED);
    public static final BusinessErrorCode ACCOUNT_LOCKED = new BusinessErrorCode(
            401_002, "Account locked.", HttpStatus.UNAUTHORIZED);
    public static final BusinessErrorCode PASSWORD_CPPASSWORD = new BusinessErrorCode(
            401_003, "Password & ConfirmPassword mismatch.", HttpStatus.UNAUTHORIZED);
    public static final BusinessErrorCode ACCOUNT_DISABLED = new BusinessErrorCode(
            401_004, "Account Disabled.", HttpStatus.UNAUTHORIZED);

    public static final BusinessErrorCode FORBIDDEN = new BusinessErrorCode(
            403_000, "Forbidden", HttpStatus.FORBIDDEN);
    public static final BusinessErrorCode FORBIDDEN_ROLE_NULL = new BusinessErrorCode(
            403_002, "Role is empty or null. Contact admin for support", HttpStatus.FORBIDDEN);
    public static final BusinessErrorCode FORBIDDEN_PERMISSION_NULL = new BusinessErrorCode(
            403_003, "Permission is empty or null. Contact admin for support", HttpStatus.FORBIDDEN);
    
}
