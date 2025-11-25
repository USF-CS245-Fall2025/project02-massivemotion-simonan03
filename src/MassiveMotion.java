import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * MassiveMotion is this celestial body simulator controller and viewer.
 * It reads the configuration values from the provided property file, initializes a
 * List implementation selected by the user, spawns the initial star (the main celestial body),
 * periodically generates new celestial bodies, updates their positions, removes bodies that leave
 * borders of the canvas, and renders all bodies on the screen
 *
 * It uses a swing timer as the simulation clock and paintComponent to draw simulation state
 */

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;


    private List<body> bodies;
    public static int[] canvas_dimension={640,480};

    //main star variables
    public int star_size=2;
    public int star_vel_x=0;
    public int star_vel_y=0;
    public double star_mass=1E21;
    public int[] star_postions={512,384};


    //other celestial bodies variables
    public double gen_x=0.06;
    public double gen_y=0.06;
    public int body_size=2;
    public double body_mass=2E29;
    public double body_velocity=3;


    /**
     * Constructor method
     * Will assign the appropriate values to features/variables based on what it reads from given property file
     * @param file name of property file
     */
    public MassiveMotion(String file) {

        Properties props= new Properties();

        try(InputStream input = new FileInputStream(file))
        {
            props.load(input);
        }catch(Exception e)
        {
            System.out.println(e);
        }
        String list_type=props.getProperty("list").toLowerCase();

        if (list_type.equalsIgnoreCase("arraylist")) {
            bodies = new ArrayList<body>();
        } else if (list_type.equalsIgnoreCase("single")) {
            bodies = new LinkedList<body>();
        } else if (list_type.equalsIgnoreCase("double")) {
            bodies = new DoublyLinkedList<body>();
        } else if (list_type.equalsIgnoreCase("dummyhead")) {
            bodies = new DummyHeadLinkedList<body>();
        } else {
            bodies = new ArrayList<body>();
        }

        //assigns values to variables relating to the star and canvas
        tm = new Timer(Integer.parseInt(props.getProperty("timer_delay","75")), this); // TODO: Replace the first argument with delay with value from config file.

        star_postions[0] = Integer.parseInt(props.getProperty("star_position_x"));
        star_postions[1] = Integer.parseInt(props.getProperty("star_position_y"));
        canvas_dimension[0]=Integer.parseInt(props.getProperty("window_size_x"));
        canvas_dimension[1]=Integer.parseInt(props.getProperty("window_size_y"));
        star_vel_x=Integer.parseInt(props.getProperty("star_velocity_x"));
        star_vel_y=Integer.parseInt(props.getProperty("star_velocity_y"));
        star_size=Integer.parseInt(props.getProperty("star_size"));
        star_mass= Double.parseDouble(props.getProperty("star_mass"));
        body star=new body(star_postions[0],star_postions[1],star_vel_x,star_vel_y,star_size,star_mass,Color.RED);
        bodies.add(star);

        //assigns values relating to new celestial bodies that should be created
        gen_x=Double.parseDouble(props.getProperty("gen_x"));
        gen_y=Double.parseDouble(props.getProperty("gen_y"));
        body_size=Integer.parseInt(props.getProperty("body_size"));
        body_mass= Double.parseDouble(props.getProperty("body_mass"));
        body_velocity=Double.parseDouble(props.getProperty("body_velocity"));

        tm.start();
    }

    /**
     * actually paints images of celestial bodies to the canvas based on provided data
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawRect(0,0,canvas_dimension[0]-1,canvas_dimension[1]-1 );

        //updates every celestial bodies position
        for(int i=0;i<bodies.size();i++)
        {
            body b=(body)bodies.get(i);
            g.setColor(b.color);
            g.fillOval((int)b.x, (int)b.y, b.size, b.size);
        }


    }

    /**
     * will randomly generate a celestial body at a random location based on values of gen_x and gen_y
     */
    public void body_generator() {

        Random random = new Random();
        float chance = random.nextFloat(0, 1);

        //randomly determines if celestial body will randomly be on the x axis
        if (chance < gen_x){
            double y = random.nextDouble() * canvas_dimension[1];
            double x;
            double vx;
            double vy;

            if(random.nextBoolean())
            {
                //should move to the right
                x=0;
                vx=body_velocity;
            } else
            {
                //should move to the left
                x=canvas_dimension[0]-body_size;
                vx=-body_velocity;
            }
            //makes it more random in choosing direction and speed of celestial body
            vy=(random.nextDouble(0,1)-0.5)*body_velocity;


            bodies.add(new body(x,y,vx,vy,body_size,body_mass,Color.black));

        }
        chance = random.nextFloat(0, 1);
        //randomly determines if celestial body will randomly be on the x axis
        if (chance < gen_y){
            double x = random.nextDouble() * canvas_dimension[0];
            double y;
            double vx;
            double vy;

            if(random.nextBoolean())
            {
                //should move up
                y=0;
                vy=body_velocity;
            } else
            {
                //should move down
                y=canvas_dimension[1]-body_size;
                vy=-body_velocity;
            }
            //makes it more random in choosing direction and speed of celestial body
            vx=(random.nextDouble(0,1)-0.5)*body_velocity;


            bodies.add(new body(x,y,vx,vy,body_size,body_mass,Color.black));

        }

    }

    /**
     * updates location of all celestial bodies depending on boundary and velocity
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        body_generator();

        for(int i=0;i<bodies.size();i++)
        {
            body b=(body) bodies.get(i);
            b.update_position();
            //removes celestial body once it crosses the boundary
            boolean off_right=b.x>canvas_dimension[0];
            boolean off_left=b.x+b.size<0;
            boolean off_bottom=b.y>canvas_dimension[1];
            boolean off_top=b.y+body_size<0;

            if(off_right||off_left||off_bottom||off_top)
            {
                bodies.remove(i);
            }
        }
        repaint();
    }

    public static void main(String[] args) {

        System.out.println("Massive Motion starting...");
        MassiveMotion mm = new MassiveMotion(args[0]);


        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(canvas_dimension[0], canvas_dimension[1]);
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}


