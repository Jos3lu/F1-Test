package org.example;

import org.example.combustible.Combustible;
import org.example.combustible.exceptions.LitrosNegativosException;
import org.example.estrategia.Estrategia;
import org.example.estrategia.exceptions.KilometrosARecorrerNegativoException;
import org.example.neumaticos.Neumaticos;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class EstrategiaTest {

    private static final float COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO = 2f;
    private static final float PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO = 5f;
    private static final float KILOMETROS_A_RECORRER = 10f;
    private static Combustible combustible;
    private static Neumaticos neumaticos;

    @BeforeAll
    static void inicializarEstrategia() throws LitrosNegativosException, PorcentajeDeVidaNoValidoException {
        combustible = new Combustible("Diésel", 50f);
        Neumatico neumatico = new Neumatico("Pirelli", 75f);
        neumaticos = new Neumaticos(Arrays.asList(neumatico, neumatico, neumatico, neumatico));
    }

    @Test
    void kilometrosARecorrerEsNegativo() {
        // Given
        float kilometrosARecorrer = -5f;

        // When & Then
        assertThrows(KilometrosARecorrerNegativoException.class, () -> new Estrategia(combustible,
                COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, kilometrosARecorrer));

    }

    // Hacer los otros dos exceptions

    // Fallar el método esViable por todos los motivos posibles (replicar casos de los otros test hechos)

}
