package org.example.neumaticos;

import org.example.neumaticos.neumatico.Marca;
import org.example.neumaticos.neumatico.Neumatico;

import java.util.List;

public class Neumaticos {
    private final List<Neumatico> neumaticos;

    public Neumaticos(List<Neumatico> neumaticos) {
        this.neumaticos = neumaticos;
    }

    public boolean sonValidos(float neumaticosDesgaste) {
        final Marca marca = neumaticos.get(0).getMarca();
        final float porcentajeDeVida = neumaticos.get(0).getPorcentajeDeVida();

        return tiene4Neumaticos() && tienenMismaMarcaYPorcentajeDeVida(marca, porcentajeDeVida)
                && Neumatico.esMarcaValida(marca)
                && Neumatico.noSeDesgasta(neumaticosDesgaste, porcentajeDeVida);
    }

    private boolean tiene4Neumaticos() {
        return neumaticos.size() == 4;
    }

    private boolean tienenMismaMarcaYPorcentajeDeVida(Marca marca, float porcentajeDeVida) {
        return neumaticos.stream().allMatch(neumatico -> neumatico.getMarca().equals(marca)
                && neumatico.getPorcentajeDeVida() == porcentajeDeVida);
    }

}
