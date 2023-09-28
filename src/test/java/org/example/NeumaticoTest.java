package org.example;

import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class NeumaticoTest {
	
	private static final String MARCA = "Pirelli";
	private static final float PORCENTAJE_DE_VIDA = 70f;
	
	@ParameterizedTest
	@CsvSource({
		// Given
		"-20",
		"-1",
		"101",
		"125"
	})
	void porcentajeDeVidaNoValido(float porcentajeDeVidaEntrada) {
		// When & Then
		assertThrows(PorcentajeDeVidaNoValidoException.class, () -> new Neumatico(MARCA, porcentajeDeVidaEntrada));
	}
	
	@ParameterizedTest
	@CsvSource({
		// Given
		"-20",
		"-1",
		"101",
		"125"
	})
	void porcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(float 
			porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido) {
		// When & Then
		assertThrows(PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException.class, () ->
				Neumatico.esPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido));
	}

	@Test
	void getPorcentajeDeVida() throws PorcentajeDeVidaNoValidoException {
		// Given
		float porcentajeDeVida = 95f;

		// When
		Neumatico neumatico = new Neumatico(MARCA, porcentajeDeVida);

		// Then
		assertEquals(porcentajeDeVida, neumatico.getPorcentajeDeVida());
	}

	@Test
	void getMarca() throws PorcentajeDeVidaNoValidoException {
		// Given
		String marca = "Bridgeston";

		// When
		Neumatico neumatico = new Neumatico(marca, PORCENTAJE_DE_VIDA);

		// Then
		assertEquals(marca, neumatico.getMarca());
	}

	@ParameterizedTest
	@CsvSource({
			// Given
			"Michelin",
			"Continental",
			"Dunlop"
	})
	void neumaticoMarcaNoValido(String marca) throws PorcentajeDeVidaNoValidoException {
		// When
		Neumatico neumatico = new Neumatico(marca, PORCENTAJE_DE_VIDA);

		// Then
		assertFalse(neumatico.esMarcaValida());
	}

	@ParameterizedTest
	@CsvSource({
			// Given
			"Pirelli",
			"Bridgeston"
	})
	void neumaticoMarcaValido(String marca) throws PorcentajeDeVidaNoValidoException {
		// When
		Neumatico neumatico = new Neumatico(marca, PORCENTAJE_DE_VIDA);

		// Then
		assertTrue(neumatico.esMarcaValida());
	}

	@ParameterizedTest
	@CsvSource({
			// Given
			"71",
			"90",
			"100"
	})
	void neumaticoSeDesgasta(float neumaticoDesgaste) throws PorcentajeDeVidaNoValidoException {
		// When
		Neumatico neumatico = new Neumatico(MARCA, PORCENTAJE_DE_VIDA);

		// Then
		assertFalse(neumatico.noSeDesgasta(neumaticoDesgaste));
	}

	@ParameterizedTest
	@CsvSource({
			// Given
			"70",
			"50"
	})
	void neumaticoNoSeDesgasta(float neumaticoDesgaste) throws PorcentajeDeVidaNoValidoException {
		// When
		Neumatico neumatico = new Neumatico(MARCA, PORCENTAJE_DE_VIDA);

		// The
		assertTrue(neumatico.noSeDesgasta(neumaticoDesgaste));
	}

}
