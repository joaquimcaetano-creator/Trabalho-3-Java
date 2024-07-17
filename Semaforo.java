package Semaforo;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Semaforo {
    private boolean aberto;
    private boolean fechado;
    private boolean atencao;
    private String tipo;
    private int tempoAberto;
    private int tempoFechado;
    private int tempoAtencao;
    private int tempoRestante;

    // Construtor
    public Semaforo(String tipo, int tempoAberto, int tempoFechado, int tempoAtencao) {
        this.tipo = tipo;
        this.tempoAberto = tempoAberto;
        this.tempoFechado = tempoFechado;
        this.tempoAtencao = tempoAtencao;
        this.tempoRestante = tempoFechado;  // Inicialmente fechado
        this.aberto = false;
        this.fechado = true;
        this.atencao = false;
    }

    public void abre() {
        this.aberto = true;
        this.fechado = false;
        this.atencao = false;
        this.tempoRestante = tempoAberto;
    }

    public void fecha() {
        this.aberto = false;
        this.fechado = true;
        this.atencao = false;
        this.tempoRestante = tempoFechado;
    }

    public void atencao() {
        this.aberto = false;
        this.fechado = false;
        this.atencao = true;
        this.tempoRestante = tempoAtencao;
    }

    public void decrementaTempo() {
        if (tempoRestante > 0) {
            tempoRestante--;
        }
    }

    public boolean estaAberto() {
        return aberto;
    }

    public boolean estaFechado() {
        return fechado;
    }

    public boolean estaEmAtencao() {
        return atencao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTempoAberto() {
        return tempoAberto;
    }

    public void setTempoAberto(int tempoAberto) {
        this.tempoAberto = tempoAberto;
    }

    public int getTempoFechado() {
        return tempoFechado;
    }

    public void setTempoFechado(int tempoFechado) {
        this.tempoFechado = tempoFechado;
    }

    public int getTempoAtencao() {
        return tempoAtencao;
    }

    public void setTempoAtencao(int tempoAtencao) {
        this.tempoAtencao = tempoAtencao;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(int tempoRestante) {
        this.tempoRestante = tempoRestante;
    }
    
    public void desenhaSemaforo(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(x, y, 80, 200, 20, 20);

        if (tipo.equals("digital")) {
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.WHITE);
            g.drawString("Digital", x + 10, y + 190);
            g.drawString(String.valueOf(tempoRestante), x + 35, y + 170);
        } else {
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.WHITE);
            g.drawString("Tradicional", x + 5, y + 190);
        }

        if (estaAberto()) {
            g.setColor(Color.GREEN);
            g.fillOval(x + 20, y + 20, 40, 40);
        } else {
            g.setColor(new Color(169, 169, 169));
            g.fillOval(x + 20, y + 20, 40, 40);
        }

        if (estaEmAtencao()) {
            g.setColor(Color.YELLOW);
            g.fillOval(x + 20, y + 80, 40, 40);
        } else {
            g.setColor(new Color(169, 169, 169));
            g.fillOval(x + 20, y + 80, 40, 40);
        }

        if (estaFechado()) {
            g.setColor(Color.RED);
            g.fillOval(x + 20, y + 140, 40, 40);
        } else {
            g.setColor(new Color(169, 169, 169));
            g.fillOval(x + 20, y + 140, 40, 40);
        }
    }
}
