package tk.danielgong.darkbox;

import android.content.Context;
import android.content.SharedPreferences;

import com.solidfire.gson.Gson;
import com.solidfire.gson.JsonArray;
import com.solidfire.gson.JsonElement;
import com.solidfire.gson.JsonParser;
import com.solidfire.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongzhq on 2017/4/10.
 */

public class ListDataSaver {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ListDataSaver(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0) {
            editor.remove(tag);
            editor.commit();
            return;
        }

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
//        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();
    }

//    /**
//     * 保存List
//     * @param tag
//     * @param datalist
//     */
//    public <T> void setDataList(String tag, List<T> datalist) {
//        if (null == datalist || datalist.size() <= 0) {
//            editor.remove(tag);
//            editor.commit();
//            return;
//        }
//
//        Gson gson = new Gson();
//        //转换成json数据，再保存
//        String strJson = gson.toJson(datalist);
////        editor.clear();
//        editor.putString(tag, strJson);
//        editor.commit();
//    }

    /**
     * 删除List
     * @param tag
     */
    public void removeData(String tag) {
        editor.remove(tag);
        editor.commit();
    }

    /**
     * 获取List
     * @param tag
     * @return
     */
    public <T> List<T> getDataList(String tag, Class<T> cls) {
        List<T> datalist=new ArrayList<T>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        JsonArray arry = new JsonParser().parse(strJson).getAsJsonArray();
        for (JsonElement jsonElement : arry) {
            datalist.add(gson.fromJson(jsonElement, cls));
        }
//        List<T> datalist2 = gson.fromJson(strJson, new TypeToken<List<T>>() {
//        }.getType());
        return datalist;
    }

//    /**
//     * 获取List
//     * @param tag
//     * @return
//     */
//    public <T> List<T> getDataList(String tag, Class<T> cls) {
//        List<T> datalist=new ArrayList<T>();
//        String strJson = preferences.getString(tag, null);
//        if (null == strJson) {
//            return datalist;
//        }
//        Gson gson = new Gson();
//        JsonArray arry = new JsonParser().parse(strJson).getAsJsonArray();
//        for (JsonElement jsonElement : arry) {
//            datalist.add(gson.fromJson(jsonElement, cls));
//        }
//        return datalist;
//
////        T[] list = new Gson().fromJson(strJson, type);
////        List aslist = Arrays.asList(list);
////        return new ArrayList(aslist);
////        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
////        }.getType());
////        return datalist;
//    }
}
