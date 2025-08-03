package model;

public class SudokuCell {

    private int value;
    private final boolean isFixed;

    public SudokuCell(boolean isFixed, int value) {
        this.isFixed = isFixed;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setValue(int value) {
        if (!isFixed) {
            this.value = value;
        }
    }


    public void limparValor() {
        if (!isFixed) {
            this.value = 0;
        }
    }

    public boolean isEmpty() {
        return value == 0;
    }

}
