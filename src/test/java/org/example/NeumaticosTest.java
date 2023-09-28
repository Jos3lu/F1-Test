package org.example;

import org.example.neumaticos.Neumaticos;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NeumaticosTest {

    private static Neumatico neumaticoPirelli;
    private static Neumatico neumaticoBridgeston;

    @BeforeAll
    static void inicializarNeumaticos() throws PorcentajeDeVidaNoValidoException {
        neumaticoPirelli = new Neumatico("Pirelli", 75f);
        neumaticoBridgeston = new Neumatico("Bridgeston", 75f);
    }

    @Test
    void neumaticosNoTiene4Neumaticos() {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoPirelli, neumaticoPirelli, neumaticoPirelli);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertFalse(neumaticos.tiene4Neumaticos());
    }

    @Test
    void neumaticosTiene4Neumaticos() {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoPirelli, neumaticoPirelli,
                neumaticoPirelli, neumaticoPirelli);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertTrue(neumaticos.tiene4Neumaticos());
    }

    @Test
    void neumaticosNoTienenMismaMarca() {
        // Given
        String marca = neumaticoPirelli.getMarca();
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoPirelli, neumaticoPirelli,
                neumaticoBridgeston, neumaticoBridgeston);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertFalse(neumaticos.tienenMismaMarca(marca));
    }

    @Test
    void neumaticosTienenMismaMarca() {
        // Given
        String marca = neumaticoBridgeston.getMarca();
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoBridgeston, neumaticoBridgeston,
                neumaticoBridgeston, neumaticoBridgeston);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertTrue(neumaticos.tienenMismaMarca(marca));
    }

    @Test
    void neumaticosNoTienenMismoPorcentajeDeVida() throws PorcentajeDeVidaNoValidoException {
        // Given
        float porcentajeDeVida = neumaticoBridgeston.getPorcentajeDeVida();
        Neumatico neumaticoBridgeston50PorcentajeDeVida = new Neumatico("Bridgeston", 50f);
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoBridgeston, neumaticoBridgeston,
                neumaticoBridgeston50PorcentajeDeVida, neumaticoBridgeston50PorcentajeDeVida);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertFalse(neumaticos.tienenMismoPorcentajeDeVida(porcentajeDeVida));
    }

    @Test
    void neumaticosTienenMismoPorcentajeDeVida() {
        // Given
        float porcentajeDeVida = neumaticoBridgeston.getPorcentajeDeVida();
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoBridgeston, neumaticoBridgeston,
                neumaticoBridgeston, neumaticoBridgeston);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertTrue(neumaticos.tienenMismoPorcentajeDeVida(porcentajeDeVida));
    }

    @ParameterizedTest
    @CsvSource({
            // Given
            "76",
            "80",
            "90",
            "100"
    })
    void NeumaticosNoSonValidos(float neumaticoDesgaste) {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoPirelli, neumaticoPirelli,
                neumaticoPirelli, neumaticoPirelli);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertFalse(neumaticos.sonValidos(neumaticoDesgaste));
    }

    @ParameterizedTest
    @CsvSource({
            // Given
            "75",
            "70",
            "50",
            "25"
    })
    void NeumaticosSonValidos(float neumaticoDesgaste) {
        // Given
        List<Neumatico> neumaticoList = Arrays.asList(neumaticoPirelli, neumaticoPirelli,
                neumaticoPirelli, neumaticoPirelli);

        // When
        Neumaticos neumaticos = new Neumaticos(neumaticoList);

        // Then
        assertTrue(neumaticos.sonValidos(neumaticoDesgaste));
    }

}
