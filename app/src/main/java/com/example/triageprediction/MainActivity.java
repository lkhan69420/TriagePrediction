package com.example.triageprediction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private InputForm inputForm;
    private WaitingList waitingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        inputForm = new InputForm();
        waitingList = new WaitingList();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(inputForm, "INPUT FORM");
        viewPagerAdapter.addFragment(waitingList, "WAITING LIST");
        viewPager.setAdapter(viewPagerAdapter);

//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_explore_24);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_flight_24);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_card_travel_24);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }

    public static void main(String[] args) {
//        ICUClassifier predictor = new ICUClassifier();
//        String[] st = new String[581];
//        st[0]="22.";
//        st[1]="1.";
//        st[2]="1.";
//        st[3]="98.";
//        st[4]="90.";
//        st[5]="16.";
//        st[6]="102.";
//        st[7]="67.";
//        st[8]="99.";
//        for (int i=9; i<581; i++) {
//            st[i]=("0.");
//        }
//        int prediction = predictor.predict(st);
//        LinearLayout cont = (LinearLayout)findViewById(R.id.cont);
//        MaterialCardView n = new MaterialCardView(this);
//        TextView t1, t2
//        if (prediction == 1) System.out.println("Critical care unit");
//        System.out.println(prediction);
    }

}

