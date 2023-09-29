package org.example;

import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NeumaticoTest {
	
	private static final String MARCA = "Pirelli";
	private static final float PORCENTAJE_DE_VIDA = 70f;
	private static final float NEUMATICO_DESGASTE = 25f;
	
	@ParameterizedTest
	@ValueSource(floats = {
		// Given
		-20f,
		-1f,
		101f,
		125f
	})
	void neumaticoPorcentajeDeVidaNoValido(float porcentajeDeVidaEntrada) {
		// When & Then
		assertThrows(PorcentajeDeVidaNoValidoException.class, () -> new Neumatico(MARCA, porcentajeDeVidaEntrada));
	}

	@Test
	void neumaticoGetPorcentajeDeVida() throws PorcentajeDeVidaNoValidoException {
		// Given
		float porcentajeDeVida = 95f;

		// When
		Neumatico neumatico = new Neumatico(MARCA, porcentajeDeVida);

		// Then
		assertEquals(porcentajeDeVida, neumatico.getPorcentajeDeVida());
	}

	@Test
	void neumaticoGetMarca() throws PorcentajeDeVidaNoValidoException {
		// Given
		String marca = "Bridgeston";

		// When
		Neumatico neumatico = new Neumatico(marca, PORCENTAJE_DE_VIDA);

		// Then
		assertEquals(marca, neumatico.getMarca());
	}

	@ParameterizedTest
	@ValueSource(strings = {
			// Given
			"Michelin",
			"Continental",
			"Dunlop"
	})
	void neumaticoMarcaNoValido(String marca) throws PorcentajeDeVidaNoValidoException {
		// When
		Neumatico neumatico = new Neumatico(marca, PORCENTAJE_DE_VIDA);

		// Then
		assertFalse(neumatico.esValido(NEUMATICO_DESGASTE));
	}

	@ParameterizedTest
	@ValueSource(strings = {
			// Given
			"Pirelli",
			"Bridgeston"
	})
	void neumaticoMarcaValido(String marca) throws PorcentajeDeVidaNoValidoException {
		// When
		Neumatico neumatico = new Neumatico(marca, PORCENTAJE_DE_VIDA);

		// Then
		assertTrue(neumatico.esValido(NEUMATICO_DESGASTE));
	}

	@ParameterizedTest
	@ValueSource(floats = {
			// Given
			71f,
			90f,
			100f
	})
	void neumaticoSeDesgasta(float neumaticoDesgaste) throws PorcentajeDeVidaNoValidoException {
		// When
		Neumatico neumatico = new Neumatico(MARCA, PORCENTAJE_DE_VIDA);

		// Then
		assertFalse(neumatico.esValido(neumaticoDesgaste));
	}

	@ParameterizedTest
	@ValueSource(floats = {
			// Given
			70f,
			25f
	})
	void neumaticoNoSeDesgasta(float neumaticoDesgaste) throws PorcentajeDeVidaNoValidoException {
		// When
		Neumatico neumatico = new Neumatico(MARCA, PORCENTAJE_DE_VIDA);

		// Then
		assertTrue(neumatico.esValido(neumaticoDesgaste));
	}

}
