/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day06trianglefractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author ewuzhou
 */
public class TriangleFractalPanel extends javax.swing.JPanel {

    private Color [] colorArray = {
        new Color(255, 90, 90),
        new Color(0, 200, 0),
        new Color(90, 90, 255),
        new Color(10, 10, 84),
        new Color(22, 21, 61),
        new Color(21, 98, 69),
        new Color(217, 146, 54),
        new Color(63, 121, 186),
        new Color(131, 121, 11)
    };
    
    /**
     * Creates new form TriangleFractalPanel
     */
    public TriangleFractalPanel() {
        //initComponents();
    }

    @Override
    protected void paintComponent(Graphics g){   //canibas similar
        super.paintComponent(g);  // components of graphics
        Graphics2D g2d = (Graphics2D)g.create();  
        int x1 = 5;  // 5pixel from the left of the screen
        int y1 = getHeight() -5;
        int x2 = getWidth() -5;
        int y2 = getHeight() -5;  // bottom line of triangle,so has the same height with left point
        int x3 = (x1 + x2)/2;  // top middle point of triangle
        int y3 = 5;    // 5pixel from the top of the screen
        
        drawTriangleFractal(g2d, x1, y1, x2, y2, x3, y3, 0);
    }
    
    private void drawTriangle(Graphics2D g2d, int x1, int y1, int x2, int y2, int x3, int y3) {
        double dist = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
        
        if(dist < 5) return;
        g2d.drawLine(x1, y1, x2, y2);
        g2d.drawLine(x1, y1, x3, x3);
        g2d.drawLine(x3, y3, x2, y2);
        
        drawTriangleFractal(g2d, x1, y1, x2, y2, x3, y3, 0);
    }
    
    private void drawTriangleFractal(Graphics2D g2d, int x1, int y1, int x2, int y2, int x3, int y3, int level) {
        
        double dist = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        
        if(dist < 5) return;
        //g2d.drawLine(x1, y1, x2, y2);
        //g2d.drawLine(x2, y3, x3, y3);
        //g2d.drawLine(x1, y1, x2, y2);
        
        int x12m = (x1 + x2)/2;
        int y12m = (y1 + y2)/2;
        int x13m = (x1 + x3)/2;
        int y13m = (y1 + y3)/2;
        int x23m = (x2 + x3)/2;
        int y23m = (y2 + y3)/2;
        
        g2d.setColor(colorArray[level++]);
        
        g2d.drawLine(x12m, y12m, x23m, y23m);
        g2d.drawLine(x12m, y12m, x13m, y13m);
        g2d.drawLine(x13m, y13m, x23m, y23m);
        
        drawTriangleFractal(g2d, x1, y1, x12m, y12m, x13m, y13m, level);
        drawTriangleFractal(g2d, x12m, y12m, x2, y2, x23m, y23m, level);
        drawTriangleFractal(g2d, x13m, y13m, x23m, y23m, x3, y3, level);
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