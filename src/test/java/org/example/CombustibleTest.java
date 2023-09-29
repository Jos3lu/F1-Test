package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.combustible.Combustible;
import org.example.estrategia.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CombustibleTest {
	
	private static final String TIPO = "Gasolina";
	private static final float LITROS = 50f;
    private static final float COMBUSTIBLE_NECESARIO = 45f;

    @Test
    void combustibleLitrosNegativo() {
        // Given
        float litros = -1f;

        // When & Then
        assertThrows(LitrosNegativoException.class, () -> new Combustible(TIPO, litros));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {
            // Given
            "Gas",
            "Butano",
            "Alcohol"
    })
    void combustibleTipoNoValido(String tipo) throws LitrosNegativoException {
    	// When
    	Combustible combustible = new Combustible(tipo, LITROS);
    	
    	// Then
    	assertFalse(combustible.esValido(COMBUSTIBLE_NECESARIO));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // Given
            "Diésel",
            "Gasolina"
    })
    void combustibleTipoValido(String tipo) throws LitrosNegativoException {
        // When
        Combustible combustible = new Combustible(tipo, LITROS);

        // Then
        assertTrue(combustible.esValido(COMBUSTIBLE_NECESARIO));
    }
    
    @ParameterizedTest
    @ValueSource(floats = {
            // Given
            51f,
            55f,
            70f
    })
    void noHaycombustibleNecesario(float combustibleNecesario) throws LitrosNegativoException {
    	// When
    	Combustible combustible = new Combustible(TIPO, LITROS);
    	
    	// Then
    	assertFalse(combustible.esValido(combustibleNecesario));
    }

    @ParameterizedTest
    @ValueSource(floats = {
            // Given
            50f,
            45f,
            25f
    })
    void hayCombustibleNecesario(float combustibleNecesario) throws LitrosNegativoException {
        // When
        Combustible combustible = new Combustible(TIPO, LITROS);

        // Then
        assertTrue(combustible.esValido(combustibleNecesario));
    }

}
