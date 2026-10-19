public class IntList {
    private static final int CONST_NEW = 10;
    private int cWidth = 2; //
    private int[] array = new int[CONST_NEW];
    private int[] pos = new int[CONST_NEW];
    private int size = 0;

    public void add(int fig) {
        if (array.length == size) {
            int[] newArray = new int[cWidth * array.length];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size] = fig;
        size++;
    }

    public void addMod(int fig, int p) {
        if (array.length == size) {
            int[] newArray = new int[cWidth * array.length];
            int[] newPos = new int[cWidth * pos.length];
            System.arraycopy(array, 0, newArray, 0, array.length);
            System.arraycopy(pos, 0, newPos, 0, pos.length);
            array = newArray;
            pos = newPos;
        }
        array[size] = fig;
        pos[size] = p;
        size++;
    }

    public int getLine(int x) {
        if (x < 0 || x >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс");
        }
        return array[x];
    }

    public int getPos(int x) {
        if (x < 0 || x >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс");
        }
        return pos[x];
    }

    public int size() {
        return size;
    }

    public int get(int x) {
        if (x < 0 || x >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс");
        }
        return array[x];
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                str.append(" ");
            }
            str.append(array[i]);
        }
        return str.toString();
    }
}
