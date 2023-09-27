package org.example;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NeumaticoTest {
	
	private static final String MARCA = "Pirelli";
	private static final float PORCENTAJE_DE_VIDA = 70f;
	
	@ParameterizedTest
	@CsvSource({
		"-20", "-7", "120", "150"
	})
	void porcentajeDeVidaNoValido(float porcentajeDeVidaEntrada) {
		// Given
		float porcentajeDeVida = porcentajeDeVidaEntrada;
		
		// When & Then
		assertThrows(PorcentajeDeVidaNoValidoException.class, () -> new Neumatico(MARCA, porcentajeDeVida));
	}
	
	@ParameterizedTest
	@CsvSource({
		"-20", "-7", "120", "150"
	})
	void porcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(float 
			porcentajeDeVidaDeNeumaticosConsumidoPorRecorridoEntrada) {
		// Given
		float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido = 
				porcentajeDeVidaDeNeumaticosConsumidoPorRecorridoEntrada;
		
		// When & Then
		assertThrows(PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException.class, 
				() -> Neumatico.esPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido));
	}

}
