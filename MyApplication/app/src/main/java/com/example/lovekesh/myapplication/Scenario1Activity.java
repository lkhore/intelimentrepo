package com.example.lovekesh.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lovekesh.myapplication.viewpagerindicator.CirclePageIndicator;

public class Scenario1Activity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private int[] tutorialImg;
    private Context mContext;
    private CirclePageIndicator pageIndicator;
    private static String TAG = Scenario1Activity.class.getSimpleName();
    private SectionsPagerAdapter mPagerAdapter;
    private Button redBtn;
    private Button blueBtn;
    private Button greenBtn;
    private LinearLayout color_linear;
    private TextView dynamicTextView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        redBtn = (Button) findViewById(R.id.redBtn);
        redBtn.setOnClickListener(this);
        blueBtn = (Button) findViewById(R.id.blueBtn);
        blueBtn.setOnClickListener(this);
        greenBtn = (Button) findViewById(R.id.greenBtn);
        greenBtn.setOnClickListener(this);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        dynamicTextView = (TextView) findViewById(R.id.dynamicTextView);
        color_linear = (LinearLayout) findViewById(R.id.color_linear);
        mContext = this;
        mPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        pageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        pageIndicator.setViewPager(viewPager);


    }

    @Override
    public void onClick(View v) {

        if (v == redBtn) {
            color_linear.setBackgroundColor(Color.parseColor("#ff0000"));
            return;
        } else if (v == greenBtn) {
            color_linear.setBackgroundColor(Color.parseColor("#00ff00"));
            return;
        } else if (v == blueBtn) {
            color_linear.setBackgroundColor(Color.parseColor("#0000ff"));
            return;
        } else if (v == button1) {
            dynamicTextView.setText("Item 1");
            return;
        } else if (v == button2) {
            dynamicTextView.setText("Item 2");
            return;
        } else if (v == button3) {
            dynamicTextView.setText("Item 3");
            return;
        } else if (v == button4) {
            dynamicTextView.setText("Item 4");
            return;
        } else if (v == button5) {
            dynamicTextView.setText("Item 5");
            return;
        }

    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            Log.d(TAG, "newInstance() called with: " + "sectionNumber = [" + sectionNumber + "]");
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView() called with: " + "inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
            View rootView = inflater.inflate(R.layout.placeholder_fragment, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            int value = getArguments().getInt(ARG_SECTION_NUMBER, 0);
            textView.setText("Item " + value);
//            textView.setText("1");
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "getItem() called with: " + "position = [" + position + "]");
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }

}
