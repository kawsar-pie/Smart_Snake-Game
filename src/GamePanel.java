import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.nio.charset.Charset;
import java.util.Random;
public class GamePanel extends JPanel implements ActionListener{

    static final int SCREEN_WIDTH = 1300;
    static final int SCREEN_HEIGHT = 750;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    //  static final
    int DELAY = 100;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int score = 0;
    int applesEaten;
    int appleX=100;
    int appleY=100;
    int y_diff;
    int x_diff;
    int cnt=0;
    int mn=50;
    int win=0;
    int dr[] = {-1,1,0,0};
    int dc[] = {0,0,-1,1};
    int visit[] [] = new int [250][250];
    int n = SCREEN_HEIGHT/UNIT_SIZE;
    int m = SCREEN_WIDTH/UNIT_SIZE;
    byte[] emoji=new byte[]{(byte)0x9F,(byte)0x98,(byte)0x81};
    String apple=new String(emoji,Charset.forName("UTF-8"));
    String preferred_move;
    //List
    //Vector<String>  preferred_moves =  new Vector<>() ;
    char direction = 'R';
    boolean running = false;
    Timer timer,timer2;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY,this);

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if(running) {

            for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
                //g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                //g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }

            g.setColor(Color.red);
            //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for(int i = 0; i< bodyParts;i++) {
                if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(45,180,0));
                    //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+(score), (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        }

        else {
            if(win==0)
                gameOver(g);
            else
            {
                g.setColor(Color.green);
                g.setFont( new Font("Ink Free",Font.BOLD, 40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Congratulations You Win", (SCREEN_WIDTH/2-4*UNIT_SIZE),SCREEN_HEIGHT/2);
            }
            timer.stop();
        }


    }
    public void newApple(){
        appleX = 500;
        appleY = 500;
     //   appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        //appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    int valid(int i,int j)
    {
        if(i>=0&&j>=0&&i<1300&&j<750)
        {
            for(int k=bodyParts;k>=0;k--)
            {
                if(x[k]==i&&y[k]==j) return 0;
            }
            return 1;
        }

        else return 0;
    }
    public void Applemove(){
        switch(direction) {
            case 'U':
                if(valid(appleX,appleY-UNIT_SIZE)==1)
                    appleY = appleY - UNIT_SIZE;
                break;
            case 'D':
                if(valid(appleX,appleY+UNIT_SIZE)==1)
                    appleY = appleY+ UNIT_SIZE;
                break;
            case 'L':
                if(valid(appleX-UNIT_SIZE,appleY)==1)
                    appleX = appleX - UNIT_SIZE;
                break;
            case 'R':
                if(valid(appleX+UNIT_SIZE,appleY)==1)
                    appleX = appleX + UNIT_SIZE;
                break;
        }

    }

    public void Snakemove(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        int diffX = (appleX-x[0]);
        int diffY = (appleY-y[0]);
        char move='N';
        if (Math.abs(diffY) > Math.abs(diffX))
        {if(diffY<=0&&valid(x[0]+dr[2],y[0]+dc[2])==1)
            move='U';
        else if(valid(x[0]+dr[3],y[0]+dc[3])==1)
            move ='D';
        }
        else{
            if (diffX >=0&&valid(x[0]+dr[1],y[0]+dc[1])==1)
                move = 'R';
            else if(valid(x[0]+dr[0],y[0]+dc[0])==1)
                move = 'L';
        }
        if(move=='N')
        {
            for(int i=0;i<4;i++)
            {
                if(valid(x[0]+dr[i],y[0]+dc[i])==1)
                {
                    if(i==0) direction='L';
                    else  if(i==1) direction='R';
                    else  if(i==2) direction='U';
                    else  if(i==3) direction='D';
                    break;
                }
            }
        }
        else direction=move;
       /*
         if(direction=='U'&& move=='D')
         {
            if(valid(x[0]+dr[0],y[0]+dc[0])==1)
                direction='L';
            else if(valid(x[0]+dr[1],y[0]+dc[1])==1)
                direction='R';
            else direction='U';
         }
         else if(direction=='D'&& move=='U')
         {
             if(valid(x[0]+dr[0],y[0]+dc[0])==1)
                direction='L';
            else if(valid(x[0]+dr[1],y[0]+dc[1])==1)
                direction='R';
            else direction='D';
         }
         else if(direction=='R'&& move=='L')
         {
             if(valid(x[0]+dr[2],y[0]+dc[2])==1)
                direction='U';
            else if(valid(x[0]+dr[3],y[0]+dc[3])==1)
                direction='D';
            else direction='R';
         }
         else if(direction=='L'&& move=='R')
         {
             if(valid(x[0]+dr[2],y[0]+dc[2])==1)
                direction='U';
            else if(valid(x[0]+dr[3],y[0]+dc[3])==1)
                direction='D';
            else direction='L';
         }
         else direction=move;*/
        switch(direction) {
            case 'U':

                y[0] = y[0] - UNIT_SIZE ;
                break;
            case 'D':

                y[0] = y[0] + UNIT_SIZE ;
                break;
            case 'L':

                x[0] = x[0] - UNIT_SIZE ;
                break;
            case 'R':

                x[0] = x[0] + UNIT_SIZE ;
                break;       }


    }
    public int checkApple() {
        if((x[0] == appleX) && (y[0] == appleY))
            return 1;
        else return 0;

    }

    public int checkCollisions() {
        //checks if head collides with body
        for(int i = bodyParts;i>0;i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //check if head touches left border
        if(x[0] < 0) {
            running = false;
        }
        //check if head touches right border
        if(x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //check if head touches top border
        if(y[0] < 0) {
            running = false;
        }
        //check if head touches bottom border
        if(y[0] > SCREEN_HEIGHT) {
            running = false;
        }


        if(running==false) return 1;
        else return 0;



    }
    public void gameOver(Graphics g) {
        //Score
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+score, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {

            cnt=(cnt+1)%5;
            if(cnt==0) bodyParts++;
            score++;

            if(checkApple()==1) {running=false;return;}
            Snakemove();
            int l=checkCollisions();
            if(l==1)
            {  //if(checkApple()==0)
                timer.stop();
                win=1;

            }




        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            //bodyParts++;
            switch(e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    direction = 'L';
                    Applemove();
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = 'R';
                    Applemove();
                    break;
                case KeyEvent.VK_UP:
                    direction = 'U';Applemove();
                    break;
                case KeyEvent.VK_DOWN:
                    direction = 'D';Applemove();

                    break;
            }
        }
    }
}