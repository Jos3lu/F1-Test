package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        float litros = -3f;

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
    
    @Test
    void combustibleTipoNoValido() throws LitrosNegativosException {
    	// Given
    	String tipo = new String("GAS NATURAL");
    	
    	// When
    	Combustible combustible = new Combustible(tipo, LITROS);
    	
    	// Then
    	assertFalse(combustible.esTipoValido());
    }
    
    @Test
    void noHaycombustibleNecesario() throws LitrosNegativosException {
    	// Given
    	float combustibleNecesario = 70f;
    	
    	// When
    	Combustible combustible = new Combustible(TIPO, LITROS);
    	
    	// Then
    	assertFalse(combustible.hayCombustibleNecesario(combustibleNecesario));
    }
    
    @ParameterizedTest
    @CsvSource({
    	"10", "25", "40"
    })
    void combustibleValido(float combustibleEntrada) throws LitrosNegativosException {
    	// Given
    	float combustibleNecesario = combustibleEntrada;
    	
    	// When
    	Combustible combustible = new Combustible(TIPO, LITROS);
    	
    	// Then
    	assertTrue(combustible.esValido(combustibleNecesario));
    }

}
