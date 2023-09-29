package org.example.estrategia;

import org.example.combustible.Combustible;
import org.example.estrategia.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.estrategia.exceptions.KilometrosARecorrerNegativoException;
import org.example.estrategia.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.Neumaticos;

public class Estrategia {
    private final Combustible combustible;
    private final float combustibleConsumidoPorKmRecorrido;
    private final Neumaticos neumaticos;
    private final float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido;
    private final float kilometrosARecorrer;

    public Estrategia(Combustible combustible, float combustibleConsumidoPorKmRecorrido, Neumaticos neumaticos,
                      float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido, float kilometrosARecorrer) throws
            CombustibleConsumidoPorKmRecorridoNegativoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException {

        // Throw Exceptions if negative values
        esKilometrosARecorrerNegativo(kilometrosARecorrer);
        esCombustibleConsumidoPorKmRecorridoNegativo(combustibleConsumidoPorKmRecorrido);
        esPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido);

        this.combustible = combustible;
        this.combustibleConsumidoPorKmRecorrido = combustibleConsumidoPorKmRecorrido;
        this.neumaticos = neumaticos;
        this.porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido = porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido;
        this.kilometrosARecorrer = kilometrosARecorrer;
    }

    private void esCombustibleConsumidoPorKmRecorridoNegativo(float combustibleConsumidoPorKmRecorrido)
            throws CombustibleConsumidoPorKmRecorridoNegativoException {
        if (combustibleConsumidoPorKmRecorrido < 0f)
            throw new CombustibleConsumidoPorKmRecorridoNegativoException();
    }

    private void esPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido)
            throws PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException {
        if (porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido < 0f ||
                porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido > 100f)
            throw new PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException();
    }

    private void esKilometrosARecorrerNegativo(float kilometrosARecorrer) throws KilometrosARecorrerNegativoException {
        if (kilometrosARecorrer < 0f) throw new KilometrosARecorrerNegativoException();
    }

    public boolean esViable() {
        float combustibleNecesario = combustibleConsumidoPorKmRecorrido * kilometrosARecorrer;
        float neumaticoDesgaste = porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido * kilometrosARecorrer;

        return combustible.esValido(combustibleNecesario) && neumaticos.sonValidos(neumaticoDesgaste);
    }

}
