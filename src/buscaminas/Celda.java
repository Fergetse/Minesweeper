package buscaminas;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Celda extends javax.swing.JPanel {

    private int x, y;
    Color bg;
    boolean bomba, mapa[][];
    Celda tab[][];
    int caso = 0;
    boolean run = false;
    boolean reveled = false, rc = false;

    public Celda() {
        initComponents();
    }

    public Celda(int coordenadas[], Color bg, boolean mapa[][], Celda tab[][]) {
        initComponents();
        setBackground(bg);
        this.bg = bg;
        x = coordenadas[0];
        y = coordenadas[1];
        this.mapa = mapa;
        this.bomba = mapa[x][y];
        if (bomba) {
            //txt.setText("X");
        }
        this.tab = tab;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51)));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        txt.setFont(new java.awt.Font("Comfortaa", 1, 12)); // NOI18N
        txt.setForeground(new java.awt.Color(255, 255, 255));
        txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (reveled) {
            return;
        }

        System.out.println("Coordenadas: " + x + ", " + y);

        if (SwingUtilities.isRightMouseButton(evt)) {
            if (rc) {
                if (bomba) {
                    Tablero.flagsinrealbombs--;
                } else {
                    Tablero.flags--;
                }
                this.setBackground(bg);
                rc = !rc;
                return;
            }

            if (bomba) {
                Tablero.flagsinrealbombs++;
            } else {
                Tablero.flags++;
            }
            this.setBackground(Color.RED);
            rc = true;
            if (Tablero.flagsinrealbombs == Tablero.bombas && Tablero.flags == 0) {
                JOptionPane.showMessageDialog(null, "HAZ GANADO");
            }
            return;
        }

        reveled = true;
        if (bomba) {
            for (Celda[] tab1 : tab) {
                for (int j = 0; j < tab[0].length; j++) {
                    if (tab1[j].bomba) {
                        tab1[j].setBackground(Color.MAGENTA);
                        tab1[j].txt.setText("X");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Perdiste");
            System.exit(0);
            return;
        }

        start();
    }//GEN-LAST:event_formMouseClicked

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if (!reveled && !rc)
            setBackground(Color.CYAN);
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        if (!reveled && !rc)
            setBackground(bg);
    }//GEN-LAST:event_formMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel txt;
    // End of variables declaration//GEN-END:variables

    public void start() {
        if (run) {
            return;
        }

        run = true;

        //boolean dat[][] = {{},{}};//check();
        int cont = 0;
        /*for (boolean data[] : dat) {
            for (boolean data2 : data) {
                if (data2) {
                    cont++;
                }
            }
        }*/
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                try {
                    if (i == x && j == y) {
                        continue;
                    }
                    if (mapa[i][j]) {
                        cont++;
                    }
                } catch (Exception e) {
                }
            }
        }

        if (cont == 0) {
            this.setBackground(Color.GRAY);
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    try {
                        if (i == x && j == y) {
                            continue;
                        }
                        tab[i][j].start();
                    } catch (Exception e) {
                    }
                }
            }
        }
        //System.out.println("caso: " + caso);
        txt.setForeground(Color.WHITE);
        if (cont != 0) {
            this.setBackground(Color.DARK_GRAY);
            txt.setText(cont + "");
        }
        this.reveled = true;
    }

    /*public void repeat() {
        switch (caso) {
            case 1 -> {
                tab[x - 1][y - 1].start();
                tab[x - 1][y].start();
                tab[x - 1][y + 1].start();
                tab[x][y - 1].start();
                tab[x][y + 1].start();
            }
            case 2 -> {
                tab[x][y - 1].start();
                tab[x][y + 1].start();
                tab[x + 1][y - 1].start();
                tab[x + 1][y].start();
                tab[x + 1][y + 1].start();
            }
            case 3 -> {
                tab[x - 1][y - 1].start();
                tab[x - 1][y].start();
                tab[x][y - 1].start();
                tab[x + 1][y - 1].start();
                tab[x + 1][y].start();
            }
            case 4 -> {
                tab[x - 1][y].start();
                tab[x - 1][y + 1].start();
                tab[x][y + 1].start();
                tab[x + 1][y].start();
                tab[x + 1][y + 1].start();
            }
            case 5 -> {
                tab[x][y + 1].start();
                tab[x + 1][y].start();
                tab[x + 1][y + 1].start();
            }
            case 6 -> {
                tab[x - 1][y - 1].start();
                tab[x - 1][y].start();
                tab[x][y - 1].start();
            }
            case 7 -> {
                tab[x - 1][y].start();
                tab[x - 1][y + 1].start();
                tab[x][y + 1].start();
            }
            case 8 -> {
                tab[x][y - 1].start();
                tab[x + 1][y - 1].start();
                tab[x + 1][y].start();
            }
            case 9 -> {
                tab[x - 1][y - 1].start();
                tab[x - 1][y].start();
                tab[x - 1][y + 1].start();
                tab[x][y - 1].start();
                tab[x][y + 1].start();
                tab[x + 1][y - 1].start();
                tab[x + 1][y].start();
                tab[x + 1][y + 1].start();
                //run = false;
            }
        }
    }*/

 /*public boolean[][] check() {
        if (x - 1 < 0 && y - 1 < 0) {
            caso = 5;
            boolean round[][] = {
                {bomba, mapa[x][y + 1]},
                {mapa[x + 1][y], mapa[x + 1][y + 1]}
            };
            return round;
        } else if (x + 1 > mapa[0].length - 1 && y + 1 > mapa.length - 1) {
            caso = 6;
            boolean round[][] = {
                {mapa[x - 1][y - 1], mapa[x - 1][y]},
                {mapa[x][y - 1], bomba},};
            return round;
        } else if (x + 1 > mapa[0].length - 1 && y - 1 < 0) {
            caso = 7;
            boolean round[][] = {
                {mapa[x - 1][y], mapa[x - 1][y + 1]},
                {bomba, mapa[x][y + 1]}
            };
            return round;
        } else if (x - 1 < 0 && y + 1 > mapa.length - 1) {
            caso = 8;
            boolean round[][] = {
                {mapa[x][y - 1], bomba},
                {mapa[x + 1][y - 1], mapa[x + 1][y]}
            };
            return round;
        } else if (x + 1 > mapa[0].length - 1) {
            caso = 1;
            boolean round[][] = {
                {mapa[x - 1][y - 1], mapa[x - 1][y], mapa[x - 1][y + 1]},
                {mapa[x][y - 1], bomba, mapa[x][y + 1]}
            };
            return round;
        } else if (x - 1 < 0) {
            caso = 2;
            boolean round[][] = {
                {mapa[x][y - 1], bomba, mapa[x][y + 1]},
                {mapa[x + 1][y - 1], mapa[x + 1][y], mapa[x + 1][y + 1]}
            };
            return round;
        } else if (y + 1 > mapa.length - 1) {
            caso = 3;
            boolean round[][] = {
                {mapa[x - 1][y - 1], mapa[x - 1][y]},
                {mapa[x][y - 1], bomba},
                {mapa[x + 1][y - 1], mapa[x + 1][y]}
            };
            return round;
        } else if (y - 1 < 0) {
            caso = 4;
            boolean round[][] = {
                {mapa[x - 1][y], mapa[x - 1][y + 1]},
                {bomba, mapa[x][y + 1]},
                {mapa[x + 1][y], mapa[x + 1][y + 1]}
            };
            return round;
        } else {
            caso = 9;
            boolean round[][] = {
                {mapa[x - 1][y - 1], mapa[x - 1][y], mapa[x - 1][y + 1]},
                {mapa[x][y - 1], bomba, mapa[x][y + 1]},
                {mapa[x + 1][y - 1], mapa[x + 1][y], mapa[x + 1][y + 1]}
            };
            return round;
        }
    }*/
}
