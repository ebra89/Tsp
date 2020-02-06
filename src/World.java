import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class World extends JPanel {

    private final AtomicInteger generation;
    private final TSPPopulation population;
     static final int WIDTH = 800;
     static final int HEIGHT = 600;

    public World(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.black);
        this.population = new TSPPopulation(TSPUtils.CITIES, 10);
        this.generation = new AtomicInteger(0);
        final Timer timer = new Timer(5, (ActionEvent e) ->{
            this.population.update();
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(final Graphics graphics){
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.CYAN);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("gen "+ this.generation.incrementAndGet(),350, 15);
        g.drawString("pop size: "+this.population.getPopulation().size(), 150, 15 );
        g.drawString("strada piu corta : " +String.format("%.2f", this.population.getAlpha().calculateDistance()), 500, 15);
        drawBestChromosome(g);
    }

    private void drawBestChromosome(final Graphics2D g) {
        final java.util.List<TSPGene> chromosome = this.population.getAlpha().getChromosome();
        g.setColor(Color.white);
        for(int i = 0 ; i < chromosome.size()-1; i++){
            TSPGene gene = chromosome.get(i);
            TSPGene neighbor = chromosome.get(i + 1);
            g.drawLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }
        g.setColor(Color.red);
        for(final TSPGene gene : chromosome){
            g.fillOval(gene.getX(), gene.getY(), 5,5);
        }
    }

    public static void main(String [] args){

        SwingUtilities.invokeLater(()->{
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Genetic Algorithm");
            frame.setResizable(false);
            frame.add(new World(), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
