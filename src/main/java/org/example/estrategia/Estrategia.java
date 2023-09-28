package org.example.estrategia;

import org.example.combustible.Combustible;
import org.example.combustible.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.estrategia.exceptions.KilometrosARecorrerNegativoException;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
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
        if (kilometrosARecorrer < 0f) throw new KilometrosARecorrerNegativoException();
        Combustible.esCombustibleConsumidoPorKmRecorridoNegativo(combustibleConsumidoPorKmRecorrido);
        Neumatico.esPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido);

        this.combustible = combustible;
        this.combustibleConsumidoPorKmRecorrido = combustibleConsumidoPorKmRecorrido;
        this.neumaticos = neumaticos;
        this.porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido = porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido;
        this.kilometrosARecorrer = kilometrosARecorrer;
    }

    public boolean esViable() {
        float combustibleNecesario = combustibleConsumidoPorKmRecorrido * kilometrosARecorrer;
        float neumaticoDesgaste = porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido * kilometrosARecorrer;

        return combustible.esValido(combustibleNecesario) && neumaticos.sonValidos(neumaticoDesgaste);
    }

}
