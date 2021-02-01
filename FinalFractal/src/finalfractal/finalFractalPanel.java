/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfractal;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author ewuzhou
 */
public class finalFractalPanel extends javax.swing.JPanel {

    /**
     * Creates new form finalFractalPanel
     */
    public finalFractalPanel() {
        //initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g){   //canibas similar
        super.paintComponent(g);  // components of graphics
        Graphics2D g2d = (Graphics2D)g.create();  
        
        int x1 = 5; 
        int y1 = getHeight() -5;
        int x3 = getWidth()-5;
        int y3 = getHeight() -5;
        
        int x2 = 5;
        int y2 = 5;
        int x4 = getWidth()-5;
        int y4 = 5;
        
        g2d.drawLine(x1, y1, x2, y2);
        g2d.drawLine(x1, y1, x3, y3);
        g2d.drawLine(x2, y2, x4, y4);
        g2d.drawLine(x3, y3, x4, y4);
        
        drawFinalFractal(g2d, x1, y1, x2, y2, x3, y3, x4, y4);
    }
    
    private void drawFinalFractal(Graphics2D g2d, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        
        double dist = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
        if(dist < 10) return;
        
        int x12m = (x1+x2)/2;
        int y12m = (y1+y2)/2;
        
        int x13m = (x1+x3)/2;
        int y13m = (y1+y3)/2;
        
        int x24m = (x2+x4)/2;
        int y24m = (y2+y4)/2;
        
        int x34m = (x3+x4)/2;
        int y34m = (y3+y4)/2;
        
        g2d.drawLine(x12m, y12m, x13m, y13m);
        g2d.drawLine(x12m, y12m, x24m, y24m);
        g2d.drawLine(x13m, y13m, x34m, y34m);
        g2d.drawLine(x34m, y34m, x24m, y24m);
        
        drawTriangleFractal(g2d, x1, y1, x12m, y12m, x13m, y13m);
        drawTriangleFractal(g2d, x2, y2, x24m, y24m, x12m, y12m);
        drawTriangleFractal(g2d, x3, y3, x13m, y13m, x34m, y34m);
        drawTriangleFractal(g2d, x4, y4, x34m, y34m, x24m, y24m);

    }

    private void drawTriangleFractal(Graphics2D g2d, int x1, int y1, int x2, int y2, int x3, int y3){
        
        double dist = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
            if(dist < 10) return;
        
        int x12m = (x1+x2)/2;
        int y12m = (y1+y2)/2;
        
        int x13m = (x1+x3)/2;
        int y13m = (y1+y3)/2;
        
        int x23m = (x2+x3)/2;
        int y23m = (y2+y3)/2;   
           
        g2d.drawLine(x12m, y12m, x23m, y23m);
        g2d.drawLine(x13m, y13m, x23m, y23m);   
        
        drawFinalFractal(g2d, x1, y1, x12m, y12m, x13m, y13m, x23m, y23m);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
