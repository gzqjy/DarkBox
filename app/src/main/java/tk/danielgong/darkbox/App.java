package tk.danielgong.darkbox;

/**
 * Created by gongzhq on 2017/4/10.
 */

public class App {
    private String name;
    private int imageId;
    private int background;
    private boolean check;  //该属性主要标志CheckBox是否选中

    public App(String name, int imageId, int background) {
        this.name = name;
        this.imageId = imageId;
        this.background = background;
        this.check = false;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getCheck() {
        return check;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getBackground() {
        return background;
    }
}
