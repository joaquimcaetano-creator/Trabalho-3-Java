package Semaforo;

import java.util.ArrayList;
import java.util.List;

public class Cruzamento {
    private List<Semaforo> semaforos;

    public Cruzamento() {
        this.semaforos = new ArrayList<>();
    }

    public void adicionaSemaforo(Semaforo semaforo) {
        semaforos.add(semaforo);
    }

    public void limpaSemaforos() {
        semaforos.clear();
    }

    public List<Semaforo> getSemaforos() {
        return semaforos;
    }

    public void controlaSemaforos() {
        if (semaforos.size() >= 2) {
            Semaforo semaforo1 = semaforos.get(0);
            Semaforo semaforo2 = semaforos.get(1);

            new Thread(() -> {
                try {
                    while (true) {
                        // Semaforo 1 abre, Semaforo 2 fecha
                        semaforo1.abre();
                        semaforo2.fecha();
                        for (int i = 0; i < semaforo1.getTempoAberto(); i++) {
                            semaforo1.decrementaTempo();
                            semaforo2.decrementaTempo();
                            Thread.sleep(1000);
                        }

                        // Semaforo 1 em atenção, Semaforo 2 fecha
                        semaforo1.atencao();
                        for (int i = 0; i < semaforo1.getTempoAtencao(); i++) {
                            semaforo1.decrementaTempo();
                            semaforo2.decrementaTempo();
                            Thread.sleep(1000);
                        }

                        // Semaforo 1 fecha, Semaforo 2 abre
                        semaforo1.fecha();
                        semaforo2.abre();
                        for (int i = 0; i < semaforo2.getTempoAberto(); i++) {
                            semaforo2.decrementaTempo();
                            semaforo1.decrementaTempo();
                            Thread.sleep(1000);
                        }

                        // Semaforo 2 em atenção, Semaforo 1 fecha
                        semaforo2.atencao();
                        for (int i = 0; i < semaforo2.getTempoAtencao(); i++) {
                            semaforo2.decrementaTempo();
                            semaforo1.decrementaTempo();
                            Thread.sleep(1000);
                        }

                        // Semaforo 2 fecha
                        semaforo2.fecha();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
