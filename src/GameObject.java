public abstract class GameObject {

    protected int x, y, width, height;
    protected String itemID;

    public GameObject(int x, int y, int width, int height, String itemID) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.itemID = itemID;

    }

    public abstract void setX();

    public abstract void setY();

    public abstract void setWidth();

    public abstract void setHeight();

    public abstract void setItemID();

    public abstract int getX();

    public abstract int getY();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract String getItemID();

}
