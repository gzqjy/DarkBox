package tk.danielgong.darkbox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Fragment darkbox_fragment;//进行切换的Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_data();
    }

    //提供一个方法用来切换Fragment。
    private void addFragmentToStack(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    private void init_data() {
        if (darkbox_fragment == null) {
            darkbox_fragment = new DarkBoxFragment();
        }
        addFragmentToStack(darkbox_fragment);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btnLeft:
//                if (view_fragment==null){
//                    view_fragment=new DarkBoxViewFragment();
//                }
//                addFragmentToStack(view_fragment);
//                break;
//            case R.id.btnRight:
//                if (add_fragment==null){
//                    add_fragment=new DarkBoxAddFragment();
//                }
//                addFragmentToStack(add_fragment);
//                break;
//        }
//    }
}
