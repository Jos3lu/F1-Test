package org.example;

import org.example.combustible.Combustible;
import org.example.combustible.Tipo;
import org.example.combustible.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativosException;
import org.example.estrategia.Estrategia;
import org.example.estrategia.KilometrosARecorrerNegativoException;
import org.example.neumaticos.Neumaticos;
import org.example.neumaticos.neumatico.Marca;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;

import java.util.Arrays;

public class F1TestApplication {

    private static final int LITROS = 50;
    private static final Tipo TIPO = Tipo.DIESEL;

    public static void main(String[] args) throws LitrosNegativosException,
            PorcentajeDeVidaNoValidoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        Combustible combustible = new Combustible(TIPO, LITROS);
        float combustibleConsumidoPorKmRecorrido = 2;

        Neumaticos neumaticos = new Neumaticos(Arrays.asList(
                new Neumatico(Marca.BRIDGESTONE, 70f),
                new Neumatico(Marca.BRIDGESTONE, 70f),
                new Neumatico(Marca.BRIDGESTONE, 70f),
                new Neumatico(Marca.BRIDGESTONE, 70f)
        ));

        float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido = 2f;

        float kilometrosARecorrer = 20;

        Estrategia estrategia = new Estrategia(
                combustible,
                combustibleConsumidoPorKmRecorrido,
                neumaticos,
                porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido,
                kilometrosARecorrer
        );

        boolean laEstrategiaEsViable = estrategia.esViable();
        System.out.println(laEstrategiaEsViable);
    }

}
