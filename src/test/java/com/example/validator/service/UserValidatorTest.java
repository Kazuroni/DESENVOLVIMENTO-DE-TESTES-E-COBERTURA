package com.example.validator.service;

import com.example.validator.dto.UserRegistrationDTO;
import com.example.validator.model.ValidationResult;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    private final UserValidator validator = new UserValidator();

    @Test
    void deveValidarUsuarioCorretamente_CaminhoFeliz() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Kazu",
                "teste@email.com",
                "Senha123",
                LocalDate.now().minusYears(20).toString()
        );

        ValidationResult result = validator.validate(dto);
        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
    }

    @Test
    void deveInvalidarUsernameCurto() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Ka",
                "teste@email.com",
                "Senha123",
                LocalDate.now().minusYears(20).toString()
        );

        ValidationResult result = validator.validate(dto);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Username deve ter entre 3 e 20 caracteres."));
    }

    @Test
    void deveInvalidarEmailInvalido() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Kazu",
                "email_invalido",
                "Senha123",
                LocalDate.now().minusYears(20).toString()
        );

        ValidationResult result = validator.validate(dto);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Email inválido."));
    }

    @Test
    void deveInvalidarSenhaFraca() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Kazu",
                "teste@email.com",
                "senha",
                LocalDate.now().minusYears(20).toString()
        );

        ValidationResult result = validator.validate(dto);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().get(0).contains("Senha deve ter"));
    }

    @Test
    void deveInvalidarMenorDeIdade() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Kazu",
                "teste@email.com",
                "Senha123",
                LocalDate.now().minusYears(17).toString()
        );

        ValidationResult result = validator.validate(dto);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Usuário deve ter pelo menos 18 anos."));
    }

    @Test
    void deveAcumularMultiplosErros() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Ka",
                "email_invalido",
                "senha",
                LocalDate.now().minusYears(15).toString()
        );

        ValidationResult result = validator.validate(dto);
        assertFalse(result.isValid());
        assertEquals(4, result.getErrors().size());
    }

    @Test
    void deveAceitarUsuarioCom18AnosHoje() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Kazu",
                "teste@email.com",
                "Senha123",
                LocalDate.now().minusYears(18).toString()
        );

        ValidationResult result = validator.validate(dto);
        assertTrue(result.isValid());
    }

    @Test
    void deveAceitarUsernameCom3Caracteres() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Kaz",
                "teste@email.com",
                "Senha123",
                LocalDate.now().minusYears(25).toString()
        );

        ValidationResult result = validator.validate(dto);
        assertTrue(result.isValid());
    }
}
