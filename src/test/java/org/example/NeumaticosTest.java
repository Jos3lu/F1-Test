package org.example;

import org.example.neumaticos.Neumaticos;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NeumaticosTest {

    private static Neumatico neumaticoPirelli;

    @BeforeAll
    static void inicalizarNeumaticos() throws PorcentajeDeVidaNoValidoException {
        neumaticoPirelli = new Neumatico("Pirelli", 75f);
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
    }

}
