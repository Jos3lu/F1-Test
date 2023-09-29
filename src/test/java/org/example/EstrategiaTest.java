package org.example;

import org.example.combustible.Combustible;
import org.example.combustible.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativoException;
import org.example.estrategia.Estrategia;
import org.example.estrategia.exceptions.KilometrosARecorrerNegativoException;
import org.example.neumaticos.Neumaticos;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
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
    static void inicializarEstrategia() throws LitrosNegativoException, PorcentajeDeVidaNoValidoException {
        combustible = new Combustible("Diésel", 50f);
        Neumatico neumatico = new Neumatico("Pirelli", 75f);
        neumaticos = new Neumaticos(Arrays.asList(neumatico, neumatico, neumatico, neumatico));
    }

    @Test
    void estrategiaKilometrosARecorrerNegativo() {
        // Given
        float kilometrosARecorrer = -5f;

        // When & Then
        assertThrows(KilometrosARecorrerNegativoException.class, () -> new Estrategia(combustible,
                COMBUSTIBLE_CONSUMIDO_POR_KILOMETRO_RECORRIDO, neumaticos,
                PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO, kilometrosARecorrer));
    }

    @Test
    void estrategiaCombustibleConsumidoPorKmRecorridoNegativo() {
        // Given
        float combustibleConsumidoPorKmRecorrido = -5f;

        // When & Then
        assertThrows(CombustibleConsumidoPorKmRecorridoNegativoException.class, () -> new Estrategia(combustible,
                combustibleConsumidoPorKmRecorrido, neumaticos, PORCENTAJE_DE_VIDA_DE_NEUMATICOS_CONSUMIDOS_POR_RECORRIDO,
                KILOMETROS_A_RECORRER));
    }

    @Test
    void estrategiaPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido() {
        // Given
        float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido = -5f;

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
        Neumatico neumaticoBridgeston = new Neumatico("Bridgeston", 75f);
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
        Neumatico neumaticoPirelli75PorcentajeDeVida = new Neumatico("Bridgeston", 75f);
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
