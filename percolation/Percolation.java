public class Percolation {
    private int[] mas; private int n;
    private int[] sz;
    private int opn;
    private final int f = 0; private int l;

    private int el(int row, int col) {
        return row * n + col + 1;
    }

    public Percolation(int n) {
        if(n <= 0) throw new IllegalArgumentException();
        this.n = n;
        l = n * n + 1;
        mas = new int[n * n + 2];
        sz = new int[n * n + 2];
        for(int i = f; i <= l; i++) { mas[i] = -1; sz[i] = 0; }
        mas[f] = f; mas[l] = l;
        opn = 0;
    }

    private int root(int i) {
        int j = mas[i];
        mas[i] = mas[j];
        if(j == i) return i;
        return root(j);
    }

    private void union(int el1, int el2) {
        int r1 = root(el1);
        int r2 = root(el2);
        if(sz[r1] > sz[r2]) { mas[r2] = r1; sz[r1] += sz[r2]; }
        else { mas[r1] = r2; sz[r2] += sz[r1]; }
    }

    private boolean find(int el1, int el2) {
        if(mas[el1] == -1 || mas[el2] == -1) return false;
        return root(el1) == root(el2);
    }

    public void open(int row, int col) {
        if(!((1 <= row && row <= n) && (1 <= col && col <= n))) throw new IllegalArgumentException();
        row--; col--;
        if(mas[el(row, col)] != -1) return;
        opn++;
        mas[el(row, col)] = el(row, col);
        sz[el(row, col)] = 1;
        if(row == 0) union(el(row, col), f);
        if(row == n - 1) union(el(row, col), l);

        if(row > 0) if(mas[el(row - 1, col)] != -1) union(el(row, col), el(row - 1, col));
        if(row < n - 1) if(mas[el(row + 1, col)] != -1) union(el(row, col), el(row + 1, col));
        if(col > 0) if(mas[el(row, col - 1)] != -1) union(el(row, col), el(row, col - 1));
        if(col < n - 1) if(mas[el(row, col + 1)] != -1) union(el(row, col), el(row, col + 1));
    }

    public boolean isOpen(int row, int col) {
        if(!((1 <= row && row <= n) && (1 <= col && col <= n))) throw new IllegalArgumentException();
        row--; col--;
        return mas[el(row, col)] != -1;
    }

    public boolean isFull(int row, int col) {
        if(!((1 <= row && row <= n) && (1 <= col && col <= n))) throw new IllegalArgumentException();
        row--; col--;
        return find(el(row, col), 0);
    }

    public int numberOfOpenSites() {
        return opn;
    }

    public boolean percolates() {
        return find(f, l);
    }
}
