package org.example;

import org.example.combustible.Combustible;
import org.example.estrategia.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativoException;
import org.example.estrategia.Estrategia;
import org.example.estrategia.exceptions.KilometrosARecorrerNegativoException;
import org.example.neumaticos.Neumaticos;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.estrategia.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class EstrategiaTest {

    private static final float COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO = 2f;
    private static final float PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO = 5f;
    private static final float KILOMETROS_A_RECORRER = 10f;
    private static Combustible combustible;
    private static Neumaticos neumaticos;

    @BeforeAll
    static void inicializarEstrategia() throws LitrosNegativoException, PorcentajeDeVidaNoValidoException {
        combustible = new Combustible("Diésel", 50f);
        Neumatico neumatico = new Neumatico("Pirelli", 75f);
        neumaticos = new Neumaticos(Arrays.asList(neumatico, neumatico, neumatico, neumatico));
    }

    @ParameterizedTest
    @ValueSource(floats = {
            // Given
            -.1f,
            -5f
    })
    void estrategiaKilometrosARecorrerNegativo(float kilometrosARecorrer) {
        // When & Then
        assertThrows(KilometrosARecorrerNegativoException.class, () -> new Estrategia(combustible,
                COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, kilometrosARecorrer));
    }

    @ParameterizedTest
    @ValueSource(floats = {
            // Given
            -.1f,
            -5f
    })
    void estrategiaCombustibleConsumidoPorKmRecorridoNegativo(float combustibleConsumidoPorKmRecorrido) {
        // When & Then
        assertThrows(CombustibleConsumidoPorKmRecorridoNegativoException.class, () -> new Estrategia(combustible,
                combustibleConsumidoPorKmRecorrido, neumaticos, PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO,
                KILOMETROS_A_RECORRER));
    }

    @ParameterizedTest
    @ValueSource(floats = {
            // Given
            -.1f,
            -5f,
            100.1f,
            125f
    })
    void estrategiaPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido) {
        // When & Then
        assertThrows(PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException.class, () -> new Estrategia(combustible,
                COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos, porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido,
                KILOMETROS_A_RECORRER));
    }

    @Test
    void estrategiaCombustibleTipoNoValido() throws LitrosNegativoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        // Given
        Combustible combustible = new Combustible("Gas", 50f);

        // When
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, KILOMETROS_A_RECORRER);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaCombustibleNoHayCombustibleNecesario() throws LitrosNegativoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        // Given
        Combustible combustible = new Combustible("Diésel", 19f);

        // When
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, KILOMETROS_A_RECORRER);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNeumaticoMarcaNoValida() throws PorcentajeDeVidaNoValidoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        // Given
        Neumatico neumatico = new Neumatico("Continental", 75f);
        Neumaticos neumaticos = new Neumaticos(Arrays.asList(neumatico, neumatico, neumatico, neumatico));

        // When
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, KILOMETROS_A_RECORRER);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNeumaticoSeDesgasta() throws PorcentajeDeVidaNoValidoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        // Given
        Neumatico neumatico = new Neumatico("Pirelli", 49f);
        Neumaticos neumaticos = new Neumaticos(Arrays.asList(neumatico, neumatico, neumatico, neumatico));

        // When
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, KILOMETROS_A_RECORRER);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNeumaticosNoTiene4Neumaticos() throws PorcentajeDeVidaNoValidoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        // Given
        Neumatico neumatico = new Neumatico("Pirelli", 75f);
        Neumaticos neumaticos = new Neumaticos(Arrays.asList(neumatico, neumatico, neumatico, neumatico, neumatico));

        // When
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, KILOMETROS_A_RECORRER);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNeumaticosNoTienenMismaMarca() throws PorcentajeDeVidaNoValidoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        // Given
        Neumatico neumaticoPirelli = new Neumatico("Pirelli", 75f);
        Neumatico neumaticoBridgeston = new Neumatico("Bridgestone", 75f);
        Neumaticos neumaticos = new Neumaticos(Arrays.asList(neumaticoPirelli, neumaticoBridgeston, neumaticoPirelli, neumaticoBridgeston));

        // When
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, KILOMETROS_A_RECORRER);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNeumaticosNoTienenMismoPorcentajeDeVida() throws PorcentajeDeVidaNoValidoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        // Given
        Neumatico neumaticoPirelli90PorcentajeDeVida = new Neumatico("Pirelli", 90f);
        Neumatico neumaticoPirelli75PorcentajeDeVida = new Neumatico("Bridgestone", 75f);
        Neumaticos neumaticos = new Neumaticos(Arrays.asList(neumaticoPirelli75PorcentajeDeVida, neumaticoPirelli90PorcentajeDeVida,
                neumaticoPirelli75PorcentajeDeVida, neumaticoPirelli90PorcentajeDeVida));

        // When
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, KILOMETROS_A_RECORRER);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaValida() throws PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException, KilometrosARecorrerNegativoException,
            CombustibleConsumidoPorKmRecorridoNegativoException {
        // Given & When
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, KILOMETROS_A_RECORRER);

        // Then
        assertTrue(estrategia.esViable());
    }

}
