package tk.danielgong.darkbox;

import java.util.ArrayList;

/**
 * Created by gongzhq on 2017/4/11.
 */

public class Box {
    private ArrayList<App> apps;
    private String name;
    private int imageId;
    private int background;
    private boolean check;  //该属性主要标志CheckBox是否选中
    public Box(String name, int imageId, int background, ArrayList<App> apps) {
        this.name = name;
        this.imageId = imageId;
        this.background = background;
        this.check = false;
        this.apps = apps;
    }

    public void setApps(ArrayList<App> apps) {
        this.apps = apps;
    }

    public ArrayList<App> getApps() {
        return apps;
    }

    public boolean getCheck () {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
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
