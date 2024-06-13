import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 8) {
            System.out.println("\nEl parqueadero presenta las siguientes opciones:");
            System.out.println("1. Ingresar un carro al parqueadero");
            System.out.println("2. Dar salida a un carro del parqueadero");
            System.out.println("3. Informar los ingresos del parqueadero");
            System.out.println("4. Consultar la cantidad de puestos disponibles");
            System.out.println("5. Avanzar el reloj del parqueadero");
            System.out.println("6. Cambiar la tarifa del parqueadero");
            System.out.println("7. Métodos nuevos implementados");
            System.out.println("8. Salir");
            System.out.println("Ingrese la opción que desea: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la placa del carro: ");
                    String placaEntrada = scanner.next();
                    int puestoAsignado = parqueadero.entrarCarro(placaEntrada);
                    if (puestoAsignado == Parqueadero.NO_HAY_PUESTO) {
                        System.out.println("No hay puestos disponibles.");
                    } else if (puestoAsignado == Parqueadero.PARQUEADERO_CERRADO) {
                        System.out.println("El parqueadero está cerrado.");
                    } else if (puestoAsignado == Parqueadero.CARRO_YA_EXISTE) {
                        System.out.println("Ya hay un carro con esa placa en el parqueadero.");
                    } else {
                        System.out.println("Carro ingresado en el puesto: " + (puestoAsignado + 1));
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la placa del carro: ");
                    String placaSalida = scanner.next();
                    int valorPagar = parqueadero.sacarCarro(placaSalida);
                    if (valorPagar == Parqueadero.CARRO_NO_EXISTE) {
                        System.out.println("El carro no está en el parqueadero.");
                    } else if (valorPagar == Parqueadero.PARQUEADERO_CERRADO) {
                        System.out.println("El parqueadero está cerrado.");
                    } else {
                        System.out.println("El valor a pagar es: $" + valorPagar);
                    }
                    break;

                case 3:
                    System.out.println("Los ingresos del parqueadero son: $" + parqueadero.darMontoCaja());
                    break;

                case 4:
                    int puestosLibres = parqueadero.calcularPuestosLibres();
                    System.out.println("La cantidad de puestos disponibles es: " + puestosLibres);
                    break;

                case 5:
                    parqueadero.avanzarHora();
                    System.out.println("La hora actual es: " + parqueadero.darHoraActual());
                    if (!parqueadero.estaAbierto()) {
                        System.out.println("El parqueadero está cerrado.");
                    }
                    break;

                case 6:
                    System.out.print("Ingrese la nueva tarifa: ");
                    int nuevaTarifa = scanner.nextInt();
                    parqueadero.cambiarTarifa(nuevaTarifa);
                    System.out.println("La nueva tarifa es: $" + parqueadero.darTarifa());
                    break;

                case 7:
                    // Métodos nuevos implementados
                    System.out.println("Tiempo promedio de los carros en el parqueadero: " + parqueadero.darTiempoPromedio());

                    Carro carroMasHoras = parqueadero.darCarroMasHorasParqueado();
                    if (carroMasHoras != null) {
                        System.out.println("Carro con más horas en el parqueadero: " + carroMasHoras.darPlaca());
                    } else {
                        System.out.println("No hay carros en el parqueadero.");
                    }

                    System.out.println("¿Hay un carro parqueado por más de 8 horas?: " + (parqueadero.hayCarroMasDeOchoHoras() ? "Sí" : "No"));

                    ArrayList<Carro> carrosMasDeTresHorasParqueados = parqueadero.darCarrosMasDeTresHorasParqueados();
                    if (!carrosMasDeTresHorasParqueados.isEmpty()) {
                        System.out.println("Carros que llevan más de tres horas parqueados:");
                        for (Carro carro : carrosMasDeTresHorasParqueados) {
                            System.out.println(carro.darPlaca());
                        }
                    } else {
                        System.out.println("No hay carros que lleven más de tres horas parqueados.");
                    }

                    System.out.println("¿Hay dos carros con la misma placa?: " + (parqueadero.hayCarrosPlacaIgual() ? "Sí" : "No"));

                    System.out.println(parqueadero.metodo1());
                    System.out.println(parqueadero.metodo2());
                    break;

                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}
