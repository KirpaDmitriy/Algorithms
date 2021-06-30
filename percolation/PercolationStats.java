import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] srs;
    public PercolationStats(int n, int trials) {
        srs = new double[trials];
        for(int i=0; i<trials; i++) {
            Percolation ob = new Percolation(n);
            while(!ob.percolates()) {
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                while (ob.isOpen(x, y)) {
                    x = StdRandom.uniform(n) + 1;
                    y = StdRandom.uniform(n) + 1;
                }
                ob.open(x, y);
            }
            srs[i] = ((double) (ob.numberOfOpenSites())) / ((double) (n * n));
        }

    }

    public double mean() {
        return StdStats.mean(srs);
    }

    public double stddev() {
        return StdStats.stddev(srs);
    }

    public double confidenceLo() {
        double x = this.mean();
        double s = this.stddev();
        double t = this.srs.length;
        return x - 1.96 * s / Math.sqrt(t);
    }

    public double confidenceHi() {
        double x = this.mean();
        double s = this.stddev();
        double t = this.srs.length;
        return x + 1.96 * s / Math.sqrt(t);
    }

    public static void main(String[] args) {
        PercolationStats answ = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + Double.toString(answ.mean()));
        System.out.println("stddev                  = " + Double.toString(answ.stddev()));
        System.out.println("95% confidence interval = [" + Double.toString(answ.confidenceLo()) + ", " + Double.toString(answ.confidenceHi()) + ']');
    }
}
