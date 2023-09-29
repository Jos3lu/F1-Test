package org.example.neumaticos.neumatico;

import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;

public class Neumatico {
    private final String marca;
    private final float porcentajeDeVida;

    public Neumatico(String marca, float porcentajeDeVida) throws PorcentajeDeVidaNoValidoException {
        esPorcentajeDeVidaNoValido(porcentajeDeVida);

        this.marca = marca;
        this.porcentajeDeVida = porcentajeDeVida;
    }

    private void esPorcentajeDeVidaNoValido(float porcentajeDeVida) throws PorcentajeDeVidaNoValidoException {
        if (porcentajeDeVida < 0f || porcentajeDeVida > 100f) throw new PorcentajeDeVidaNoValidoException();
    }

    public float getPorcentajeDeVida() {
        return porcentajeDeVida;
    }

    public String getMarca() {
        return marca;
    }

    public boolean esValido(float neumaticoDesgaste) {
        return esMarcaValida() && noSeDesgasta(neumaticoDesgaste);
    }

    private boolean esMarcaValida() {
        return marca.equalsIgnoreCase("Pirelli") || marca.equalsIgnoreCase("Bridgeston");
    }

    private boolean noSeDesgasta(float neumaticoDesgaste) {
        return neumaticoDesgaste <= porcentajeDeVida;
    }

}
