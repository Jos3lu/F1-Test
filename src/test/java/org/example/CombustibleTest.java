package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.combustible.Combustible;
import org.example.combustible.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativosException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CombustibleTest {
	
	private static final String TIPO = "Gasolina";
	private static final float LITROS = 50f;

    @Test
    void combustibleLitrosNegativo() {
        // Given
        float litros = -1f;

        // When & Then
        assertThrows(LitrosNegativosException.class, () -> new Combustible(TIPO, litros));
    }
    
    @Test
    void combustibleConsumidoPorKmRecorridoNegativo() {
    	// Given 
    	float combustibleConsumidoPorKmRecorrido = -1f;
    	
    	// When & Then
    	assertThrows(CombustibleConsumidoPorKmRecorridoNegativoException.class, 
    			() -> Combustible.esCombustibleConsumidoPorKmRecorridoNegativo(combustibleConsumidoPorKmRecorrido));
    }
    
    @ParameterizedTest
    @CsvSource({
            // Given
            "Gas",
            "Butano",
            "Acohol"
    })
    void combustibleTipoNoValido(String tipo) throws LitrosNegativosException {
    	// When
    	Combustible combustible = new Combustible(tipo, LITROS);
    	
    	// Then
    	assertFalse(combustible.esTipoValido());
    }
    
    @ParameterizedTest
    @CsvSource({
            // Given
            "51",
            "55",
            "70"
    })
    void noHaycombustibleNecesario(float combustibleNecesario) throws LitrosNegativosException {
    	
    	// When
    	Combustible combustible = new Combustible(TIPO, LITROS);
    	
    	// Then
    	assertFalse(combustible.hayCombustibleNecesario(combustibleNecesario));
    }

    @ParameterizedTest
    @CsvSource({
            // Given
            "Diésel",
            "Gasolina"
    })
    void combustibleTipoValido(String tipo) throws LitrosNegativosException {
        // When
        Combustible combustible = new Combustible(tipo, LITROS);

        // Then
        assertTrue(combustible.esTipoValido());
    }

    @ParameterizedTest
    @CsvSource({
            // Given
            "10",
            "25",
            "49",
            "50"
    })
    void hayCombustibleNecesario(float combustibleNecesario) throws LitrosNegativosException {
        // When
        Combustible combustible = new Combustible(TIPO, LITROS);

        // Then
        assertTrue(combustible.hayCombustibleNecesario(combustibleNecesario));
    }

    @Test
    void combustibleValido() throws LitrosNegativosException {
        // Given
        float combustibleNecesario = 45f;

        // When
    	Combustible combustible = new Combustible(TIPO, LITROS);
    	
    	// Then
    	assertTrue(combustible.esValido(combustibleNecesario));
    }

}
