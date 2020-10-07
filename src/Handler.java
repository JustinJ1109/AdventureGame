import java.awt.*;

public class Handler {

    private int hp;

    public Handler() {
        hp = 3;
    }

    public Handler(int hp) {
        this.hp = hp;
    }

    public int getHP() {
        return hp;
    }

    public void setHp(int amt) {
        hp = amt;
    }

    public void removeHealth(int amt) {

        if (hp - amt >= 0) {
            hp -= amt;
            System.out.println("Health: "+ hp);
        }
    }

    public void render(Graphics g) {

    }

    public void tick() {

    }
}
