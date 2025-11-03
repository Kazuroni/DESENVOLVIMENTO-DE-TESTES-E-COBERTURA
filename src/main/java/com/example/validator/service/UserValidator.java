package com.example.validator.service;

import com.example.validator.dto.UserRegistrationDTO;
import com.example.validator.model.ValidationResult;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

    public ValidationResult validate(UserRegistrationDTO dto) {
        ValidationResult result = new ValidationResult();

        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
            result.addError("Username não pode ser nulo ou vazio.");
        } else if (dto.getUsername().length() < 3 || dto.getUsername().length() > 20) {
            result.addError("Username deve ter entre 3 e 20 caracteres.");
        }

        if (dto.getEmail() == null || !EMAIL_PATTERN.matcher(dto.getEmail()).matches()) {
            result.addError("Email inválido.");
        }

        if (dto.getPassword() == null || !PASSWORD_PATTERN.matcher(dto.getPassword()).matches()) {
            result.addError("Senha deve ter pelo menos 8 caracteres, contendo uma letra maiúscula, uma minúscula e um número.");
        }

        try {
            LocalDate birthDate = LocalDate.parse(dto.getBirthDate());
            LocalDate now = LocalDate.now();
            if (Period.between(birthDate, now).getYears() < 18) {
                result.addError("Usuário deve ter pelo menos 18 anos.");
            }
        } catch (DateTimeParseException | NullPointerException e) {
            result.addError("Data de nascimento inválida.");
        }

        return result;
    }
}
