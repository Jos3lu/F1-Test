package org.example.neumaticos;

import org.example.neumaticos.neumatico.Neumatico;

import java.util.List;

public class Neumaticos {
    private final List<Neumatico> neumaticos;

    public Neumaticos(List<Neumatico> neumaticos) {
        this.neumaticos = neumaticos;
    }

    public boolean sonValidos(float neumaticoDesgaste) {
    	Neumatico neumatico = neumaticos.get(0);
        final String marca = neumatico.getMarca();
        final float porcentajeDeVida = neumatico.getPorcentajeDeVida();

        return tiene4Neumaticos() && tienenMismaMarca(marca) && tienenMismoPorcentajeDeVida(porcentajeDeVida)
                && neumatico.esMarcaValida()
                && neumatico.noSeDesgasta(neumaticoDesgaste);
    }

    public boolean tiene4Neumaticos() {
        return neumaticos.size() == 4;
    }

    public boolean tienenMismaMarca(String marca) {
        return neumaticos.stream().allMatch(neumatico -> neumatico.getMarca().equalsIgnoreCase(marca));
    }

    public boolean tienenMismoPorcentajeDeVida(float porcentajeDeVida) {
        return neumaticos.stream().allMatch(neumatico -> neumatico.getPorcentajeDeVida() == porcentajeDeVida);
    }

}
