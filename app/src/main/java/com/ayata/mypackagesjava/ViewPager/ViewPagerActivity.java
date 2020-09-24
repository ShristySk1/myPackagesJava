package com.ayata.mypackagesjava.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayata.mypackagesjava.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    LinearLayout layout_dot;
    TextView[] dot;
    // tab titles
    private String[] titles = new String[]{"Chats", "Users"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager2 = findViewById(R.id.viewPager);

        //recyclerview
//        viewPager2.setAdapter(new ViewPagerAdapter3());
        //below line can be used to change the orientation to vertical i.e. vertical swipe
        //view_pager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        //fragments
//        ViewPagerAdapter2 viewPagerAdapter2 = new ViewPagerAdapter2(getSupportFragmentManager(), getLifecycle());
//        viewPager2.setAdapter(viewPagerAdapter2);
//        TabLayout tabLayout = findViewById(R.id.tabLayout);
//        new TabLayoutMediator(tabLayout, viewPager2,
//                (tab, position) -> tab.setText(titles[position])
//        ).attach();

        //recyclerview with dot
//        layout_dot = findViewById(R.id.linear_dot);
//        viewPager2.setAdapter(new ViewPagerAdapter3());
//        addDot(0);
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                addDot(position);
//                super.onPageSelected(position);
//            }
//        });

        //recyclerview with tab layout dot
        viewPager2.setAdapter(new ViewPagerAdapter3());
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) ->{}).attach();
    }

    public void addDot(int page_position) {
        Log.d("called dot", "addDot: ");
        dot = new TextView[4];
        layout_dot.removeAllViews();

        for (int i = 0; i < dot.length; i++) {
            dot[i] = new TextView(this);
            dot[i].setText(Html.fromHtml("&#9673;"));
            dot[i].setTextSize(35);
            dot[i].setTextColor(getResources().getColor(R.color.colorAccent));
            layout_dot.addView(dot[i]);
            Log.d("called", "addDot: view created"+i);
        }
        //active dot
        if (dot.length > 0) {
            dot[page_position].setTextColor(getResources().getColor(R.color.colorBlue));
    }}

}