package minesweeper;

public enum Box {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,

    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image;

    public Box nextNumberBox()
    {
        return Box.values()[this.ordinal() + 1];
    }
    public int getNumber(){
        if (ordinal() >= Box.NUM1.ordinal() && ordinal() <= Box.NUM8.ordinal())
            return ordinal();

        return -1;
    }
}
