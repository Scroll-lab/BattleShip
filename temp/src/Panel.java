import javax.imageio.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;


class Panel extends JPanel {
    private Timer timerDraw;
    private Image background, ship, dead, injure, bomb, end1, end2;
    private JButton btn1, btn2;  
    private Logic myGame;
    private int mX, mY;

    public class myMouse1 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            if ((e.getButton()==1) && (e.getClickCount()==1)) {
                mX = e.getX();
                mY = e.getY();
                if ((mX>100) && (mY>100) && (mX<400) && (mY<400)) {
                    if ((myGame.endGame==0) && (myGame.botTurn == false)) {
                        int i = (mY-100)/30; 
                        int j = (mX-100)/30; 
                        if (myGame.arrayBot[i][j]<=4) myGame.shootPlay(i,j);
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

    }

    public class myMouse2 implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            mX = e.getX();
            mY = e.getY();
            if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
    
    public Panel() {
        addMouseListener(new myMouse1());
        addMouseMotionListener(new myMouse2());
        setFocusable(true);
        myGame = new Logic();
        myGame.start();
        try {
            background = ImageIO.read(new File("C:\\Users\\Kmkt_intel\\Desktop\\temp\\img\\background.jpg"));
            ship = ImageIO.read(new File("C:\\Users\\Kmkt_intel\\Desktop\\temp\\img\\ship.jpg"));
            dead = ImageIO.read(new File("C:\\Users\\Kmkt_intel\\Desktop\\temp\\img\\dead.jpg"));
            injure = ImageIO.read(new File("C:\\Users\\Kmkt_intel\\Desktop\\temp\\img\\injure.jpg"));
            end1 = ImageIO.read(new File(""));
            end2 = ImageIO.read(new File(""));
            bomb = ImageIO.read(new File(""));
        } catch (IOException ex) {
            
        }
        timerDraw = new Timer(50, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               repaint();
                
            }

        });
        timerDraw.start();

        setLayout(null);

        btn1 = new JButton();
        btn1.setText("Новая игра");
        btn1.setBackground(Color.LIGHT_GRAY);
        btn1.setFont(new Font("serif", 0, 30));
        btn1.setBounds(130, 450, 200, 80);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                myGame.start();
            }
        });
        add(btn1);

        btn2 = new JButton();
        btn2.setText("Выход");
        btn2.setBackground(Color.LIGHT_GRAY);
        btn2.setFont(new Font("serif", 0, 30));
        btn2.setBounds(530, 450, 200, 80);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        add(btn2);
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(background, 0, 0, 900, 600, null);
        gr.setFont(new Font("serif", 3, 40));
        gr.setColor(Color.BLACK);
        gr.drawString("Компьютер", 150, 50);
        gr.drawString("Игрок", 590, 50);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
               // if((myGame.arrayPlayer[i][j]>=1) && (myGame.arrayPlayer[i][j]<=4)) {
                 //   gr.drawImage(ship, 500+j*30, 100+i*30, 30,30,null);
                //}
                if (myGame.arrayBot[i][j]!=0) {
                    if ((myGame.arrayBot[i][j]>=8) && (myGame.arrayBot[i][j]<=11)) {
                        gr.drawImage(injure,100+j*30,100+i*30,30,30,null);
                    } 
                    else if (myGame.arrayBot[i][j]>=15) {
                        gr.drawImage(dead,100+j*30,100+i*30,30,30,null);
                    }
                    if (myGame.arrayBot[i][j]>=5) {
                        gr.drawImage(bomb,100+j*30,100+i*30,30,30,null);
                    }
                }
                if (myGame.arrayPlayer[i][j]!=0) {
                    if ((myGame.arrayPlayer[i][j]>=1) && (myGame.arrayPlayer[i][j]<=4)) {
                        gr.drawImage(ship,500+j*30,100+i*30,30,30,null);
                    }
                    else if ((myGame.arrayPlayer[i][j]>=8) && (myGame.arrayPlayer[i][j]<=11)) {
                            gr.drawImage(injure,500+j*30,100+i*30,30,30,null);
                    } 
                    else if (myGame.arrayPlayer[i][j]>=15) {
                            gr.drawImage(dead,500+j*30,100+i*30,30,30,null);
                    }
                    if (myGame.arrayPlayer[i][j]>=5) {
                            gr.drawImage(bomb,500+j*30,100+i*30,30,30,null);
                    
                    }
                }
            }
        }

        gr.setColor(Color.RED);
        if ((mX>100) && (mY>100) && (mX<400) && (mY<400)) {
            if((myGame.endGame==0) && (myGame.botTurn == false)) {
                int i = (mY-100)/30;
                int j = (mX-100)/30;
                if (myGame.arrayBot[i][j]<=4) {
                    gr.fillRect(100+j*30, 100+i*30, 30, 30);
                }
            }
        }

        gr.setColor(Color.BLACK);
        for (int i = 0; i < 11; i++) {
            gr.drawLine(100+i*30,100,100+i*30,400);
            gr.drawLine(100,100+i*30,400,100+i*30);
            gr.drawLine(500+i*30,100,500+i*30,400);
            gr.drawLine(500,100+i*30,800,100+i*30);
        }

        gr.setFont(new Font("serif",0,20));
        gr.setColor(Color.DARK_GRAY);
        for (int i = 1; i <=10; i++) {
            gr.drawString(""+i,73,93+i*30);
            gr.drawString(""+i,473,93+i*30);
            gr.drawString(""+(char)('A'+i-1),78+i*30,93);
            gr.drawString(""+(char)('A'+i-1),478+i*30,93);
        }

        if (myGame.endGame==1) {
            gr.drawImage(end1, 300, 200, 300, 100, null);
        } else if (myGame.endGame==2) {
            gr.drawImage(end2,300,200,300,100, null);
        }
    }
}