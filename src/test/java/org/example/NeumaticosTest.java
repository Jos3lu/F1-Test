package org.example;

import org.example.neumaticos.Neumaticos;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.estrategia.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NeumaticosTest {

    private final static float NEUMATICO_DESGASTE = 50f;

    private static Neumatico neumaticoPirelli;
    private static Neumatico neumaticoBridgeston;
    private static Neumatico neumaticoBridgeston50PorcentajeDeVida;

    @BeforeAll
    static void inicializarNeumaticos() throws PorcentajeDeVidaNoValidoException {
        neumaticoPirelli = new Neumatico("Pirelli", 75f);
        neumaticoBridgeston = new Neumatico("Bridgeston", 75f);
        neumaticoBridgeston50PorcentajeDeVida = new Neumatico("Bridgeston", 50f);
    }

    @Test
    void neumaticosNoTiene4Neumaticos() {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoPirelli, neumaticoPirelli, neumaticoPirelli);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertFalse(neumaticos.sonValidos(NEUMATICO_DESGASTE));
    }

    @Test
    void neumaticosTiene4Neumaticos() {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoPirelli, neumaticoPirelli,
                neumaticoPirelli, neumaticoPirelli);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertTrue(neumaticos.sonValidos(NEUMATICO_DESGASTE));
    }

    @Test
    void neumaticosNoTienenMismaMarca() {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoPirelli, neumaticoPirelli,
                neumaticoBridgeston, neumaticoBridgeston);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertFalse(neumaticos.sonValidos(NEUMATICO_DESGASTE));
    }

    @Test
    void neumaticosTienenMismaMarca() {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoBridgeston, neumaticoBridgeston,
                neumaticoBridgeston, neumaticoBridgeston);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertTrue(neumaticos.sonValidos(NEUMATICO_DESGASTE));
    }

    @Test
    void neumaticosNoTienenMismoPorcentajeDeVida() {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoBridgeston, neumaticoBridgeston,
                neumaticoBridgeston50PorcentajeDeVida, neumaticoBridgeston50PorcentajeDeVida);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertFalse(neumaticos.sonValidos(NEUMATICO_DESGASTE));
    }

    @Test
    void neumaticosTienenMismoPorcentajeDeVida() {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoBridgeston, neumaticoBridgeston,
                neumaticoBridgeston, neumaticoBridgeston);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertTrue(neumaticos.sonValidos(NEUMATICO_DESGASTE));
    }

}
