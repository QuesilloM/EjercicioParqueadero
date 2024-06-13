import java.util.ArrayList;

public class Parqueadero {
    // Atributos
    private static final int CAPACIDAD = 40;
    public static final int NO_HAY_PUESTO = -1;
    public static final int PARQUEADERO_CERRADO = -2;
    public static final int CARRO_YA_EXISTE = -3;
    public static final int CARRO_NO_EXISTE = -4;

    private Puesto[] puestos;
    private int horaActual;
    private int tarifa;
    private int montoCaja;
    private int cantidadCarrosSacados;

    // Constructor
    public Parqueadero() {
        puestos = new Puesto[CAPACIDAD];
        for (int i = 0; i < CAPACIDAD; i++) {
            puestos[i] = new Puesto(i);
        }
        horaActual = 6;
        tarifa = 0;
        montoCaja = 0;
        cantidadCarrosSacados = 0;
    }

    // Métodos
    public int entrarCarro(String placa) {
        if (horaActual < 6 || horaActual > 20) {
            return PARQUEADERO_CERRADO;
        }
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darPlaca().equals(placa)) {
                return CARRO_YA_EXISTE;
            }
        }
        for (Puesto puesto : puestos) {
            if (!puesto.estaOcupado()) {
                Carro carro = new Carro(placa, horaActual);
                puesto.parquearCarro(carro);
                return puesto.darNumeroPuesto();
            }
        }
        return NO_HAY_PUESTO;
    }

    public int sacarCarro(String placa) {
        if (horaActual < 6 || horaActual > 20) {
            return PARQUEADERO_CERRADO;
        }
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darPlaca().equals(placa)) {
                Carro carro = puesto.darCarro();
                int tiempoEnParqueadero = carro.darTiempoEnParqueadero(horaActual);
                int valorPagar = tiempoEnParqueadero * tarifa;
                montoCaja += valorPagar;
                puesto.sacarCarro();
                cantidadCarrosSacados++; // Incrementa el contador de carros sacados
                return valorPagar;
            }
        }
        return CARRO_NO_EXISTE;
    }

    public double darTiempoPromedio() {
        int sumaTiempos = 0;
        int cantidadCarros = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                sumaTiempos += horaActual - puesto.darCarro().darHoraLlegada();
                cantidadCarros++;
            }
        }
        if (cantidadCarros == 0) {
            return 0;
        }
        return (double) sumaTiempos / cantidadCarros;
    }

    public Carro darCarroMasHorasParqueado() {
        Carro carroMasHoras = null;
        int maxHoras = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                Carro carro = puesto.darCarro();
                int horasParqueado = horaActual - carro.darHoraLlegada();
                if (horasParqueado > maxHoras) {
                    maxHoras = horasParqueado;
                    carroMasHoras = carro;
                }
            }
        }
        return carroMasHoras;
    }

    public boolean hayCarroMasDeOchoHoras() {
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                int horasParqueado = horaActual - puesto.darCarro().darHoraLlegada();
                if (horasParqueado > 8) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Carro> darCarrosMasDeTresHorasParqueados() {
        ArrayList<Carro> carros = new ArrayList<>();
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                int horasParqueado = horaActual - puesto.darCarro().darHoraLlegada();
                if (horasParqueado > 3) {
                    carros.add(puesto.darCarro());
                }
            }
        }
        return carros;
    }

    public boolean hayCarrosPlacaIgual() {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = i + 1; j < puestos.length; j++) {
                if (puestos[i].estaOcupado() && puestos[j].estaOcupado()) {
                    if (puestos[i].darCarro().darPlaca().equals(puestos[j].darCarro().darPlaca())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int contarCarrosQueComienzanConPlacaPB() {
        int contador = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado() && puesto.darCarro().darPlaca().startsWith("PB")) {
                contador++;
            }
        }
        return contador;
    }

    public boolean hayCarroCon24Horas() {
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                int horasParqueado = horaActual - puesto.darCarro().darHoraLlegada();
                if (horasParqueado >= 24) {
                    return true;
                }
            }
        }
        return false;
    }

    public String metodo1() {
        int cantidadPB = contarCarrosQueComienzanConPlacaPB();
        boolean hayCarro24Horas = hayCarroCon24Horas();
        return "Cantidad de carros con placa PB: " + cantidadPB + " – Hay carro parqueado por 24 o más horas: " + (hayCarro24Horas ? "Sí" : "No");
    }

    public int desocuparParqueadero() {
        int carrosSacadosEnDesocupar = 0;
        for (Puesto puesto : puestos) {
            if (puesto.estaOcupado()) {
                puesto.sacarCarro();
                carrosSacadosEnDesocupar++;
            }
        }
        cantidadCarrosSacados += carrosSacadosEnDesocupar; // Incrementa el contador de carros sacados
        return carrosSacadosEnDesocupar;
    }

    public String metodo2() {
        return "Cantidad de carros sacados: " + cantidadCarrosSacados;
    }

    public void avanzarHora() {
        horaActual++;
    }

    public int darHoraActual() {
        return horaActual;
    }

    public void cambiarTarifa(int nuevaTarifa) {
        tarifa = nuevaTarifa;
    }

    public int darTarifa() {
        return tarifa;
    }

    public int darMontoCaja() {
        return montoCaja;
    }

    public int calcularPuestosLibres() {
        int puestosLibres = 0;
        for (Puesto puesto : puestos) {
            if (!puesto.estaOcupado()) {
                puestosLibres++;
            }
        }
        return puestosLibres;
    }

    public boolean estaAbierto() {
        return horaActual >= 6 && horaActual <= 20;
    }
}
