package tmdtdemo.tmdt.utils;

import tmdtdemo.tmdt.dto.request.UserRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateObject {
    public static Map<String, String> validateRegisterRequest(UserRequest dto){
        Map<String, String> errors = new HashMap<>();

        errors.putAll(ValidateUtils.builder()
                .fieldName("username")
                .value(dto.getUsername())
                .required(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("password")
                .value(dto.getPassword())
                .required(true)
                        .minLength(0)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("email")
                .value(dto.getEmail())
                .required(true)
                .isValidEmail(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("gender")
                .value(dto.getGender())
                .required(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("date")
                .value(dto.getGender())
                .required(true)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("role")
                .value(dto.getGender())
                .required(true)
                .build().validate());

        return errors;
    }
}
