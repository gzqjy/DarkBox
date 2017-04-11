package tk.danielgong.darkbox;

/**
 * Created by gongzhq on 2017/4/11.
 */

public class Box {
    private String name;
    private int imageId;
    private int background;
    public Box(String name, int imageId, int background) {
        this.name = name;
        this.imageId = imageId;
        this.background = background;
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
