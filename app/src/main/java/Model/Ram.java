package Model;

public class Ram {
    private int[] buffer;

    public Ram() {
        buffer = new int[256];
    }

    public int getReg(int pos) {
        return buffer[pos];
    }

    public void setReg(int pos, int val) {

        // status register (pos3) should be mirrored
        // check if pr0 (03h 00100000) is set case: (bank 1 not active)
        if ((getReg(3) & 0b00100000) == 0 || pos == 3) {
            buffer[pos] = val;
        }

        // check if pr0 (03h 00100000) is set case: (bank 1 active)
        if ((getReg(3) & 0b00100000) > 0 || pos == 3) {
            buffer[pos + 128] = val;
        }
    }

    /*
     * public static void main(String[] args) {
     * Ram ram = new Ram();
     * 
     * ram.setReg(10, 10);
     * System.out.println(ram.getReg(10));
     * }
     */

}
