package org.example.neumaticos;

import org.example.neumaticos.neumatico.Neumatico;

import java.util.List;

public class Neumaticos {
    private final List<Neumatico> neumaticos;

    public Neumaticos(List<Neumatico> neumaticos) {
        this.neumaticos = neumaticos;
    }

    public boolean sonValidos(float neumaticosDesgaste) {
    	Neumatico neumatico = neumaticos.get(0);
        final String marca = neumatico.getMarca();
        final float porcentajeDeVida = neumatico.getPorcentajeDeVida();

        return tiene4Neumaticos() && tienenMismaMarcaYPorcentajeDeVida(marca, porcentajeDeVida)
                && neumatico.esMarcaValida()
                && neumatico.noSeDesgasta(neumaticosDesgaste);
    }

    public boolean tiene4Neumaticos() {
        return neumaticos.size() == 4;
    }

    public boolean tienenMismaMarcaYPorcentajeDeVida(String marca, float porcentajeDeVida) {
        return neumaticos.stream().allMatch(neumatico -> neumatico.getMarca().equalsIgnoreCase(marca)
                && neumatico.getPorcentajeDeVida() == porcentajeDeVida);
    }

}
