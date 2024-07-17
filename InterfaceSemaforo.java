package Semaforo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfaceSemaforo extends JFrame {
    private Cruzamento cruzamento;
    private SemaforoPanel painelSemaforos;

    public InterfaceSemaforo() {
        cruzamento = new Cruzamento();
        painelSemaforos = new SemaforoPanel(cruzamento);

        // Adiciona dois semáforos digitais
        Semaforo semaforo1 = new Semaforo("digital", 10, 10, 2);
        Semaforo semaforo2 = new Semaforo("digital", 10, 10, 2);
        cruzamento.adicionaSemaforo(semaforo1);
        cruzamento.adicionaSemaforo(semaforo2);

        setTitle("Controle de Semáforos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JButton btnControla = new JButton("Controlar Semáforos");
        JButton btnMudaTipo = new JButton("Mudar Tipo");

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(btnControla);
        panel.add(btnMudaTipo);
        panel.setBackground(new Color(200, 200, 200));

        add(panel, BorderLayout.NORTH);
        add(painelSemaforos, BorderLayout.CENTER);

        btnControla.setBackground(new Color(34, 139, 34));
        btnControla.setForeground(Color.WHITE);
        btnControla.setFont(new Font("Arial", Font.BOLD, 14));

        btnMudaTipo.setBackground(new Color(70, 130, 180));
        btnMudaTipo.setForeground(Color.WHITE);
        btnMudaTipo.setFont(new Font("Arial", Font.BOLD, 14));

        btnControla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cruzamento.controlaSemaforos();
                new Thread(() -> {
                    while (true) {
                        painelSemaforos.repaint();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }).start();
                JOptionPane.showMessageDialog(null, "Controle de semáforos iniciado!");
            }
        });

        btnMudaTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Semaforo semaforo : cruzamento.getSemaforos()) {
                    String novoTipo = semaforo.getTipo().equals("tradicional") ? "digital" : "tradicional";
                    semaforo.setTipo(novoTipo);
                }
                painelSemaforos.repaint();
                JOptionPane.showMessageDialog(null, "Tipos de semáforos alterados!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceSemaforo().setVisible(true);
            }
        });
    }
}

class SemaforoPanel extends JPanel {
    private Cruzamento cruzamento;

    public SemaforoPanel(Cruzamento cruzamento) {
        this.cruzamento = cruzamento;
        setBackground(new Color(240, 240, 240));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Semaforo> semaforos = cruzamento.getSemaforos();

        int x = 100;
        int y = 50;
        int spacing = 200;

        for (Semaforo semaforo : semaforos) {
            semaforo.desenhaSemaforo(g, x, y);
            if (semaforo.getTipo().equals("digital")) {
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.setColor(Color.BLACK);
                g.drawString("Tempo restante: " + semaforo.getTempoRestante(), x + 100, y + 100);
            }
            x += spacing;
        }
    }
}
