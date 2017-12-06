package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;



public class MainActivity extends ActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public FragmentManager fragmentManager;
    @InjectView(R.id.slider)
    SliderLayout sliderShow;
    @InjectView(R.id.btn_career)
    AppCompatButton btn_career;
    @InjectView(R.id.btn_director)
    AppCompatButton btn_director;
    @InjectView(R.id.imageClassroom)
    ImageView imageClassroom;
    Menu menu1, navbar_menu;
    @InjectView(R.id.btn_Staff)
    AppCompatButton btnStaff;
    @InjectView(R.id.btn_success)
    AppCompatButton btnsuccess;
    @InjectView(R.id.btn_contactus)
    AppCompatButton btnContactUs;
    @InjectView(R.id.btn_aboutus)
    AppCompatButton btnAboutUs;
    @InjectView(R.id.btn_achiever)
    AppCompatButton btnAchiever;
    SharedPreferences sp;
    String username, id, class1;
    MenuItem menuItem;
    String itemname;
    Intent intent;
    Animation animation;
    private Toast toast;
    private long back_pressed = 0;
    private CareerGuidenceFragment fragment;

    private static void setViewAndChildrenEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                setViewAndChildrenEnabled(child, enabled);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        fragmentManager = getSupportFragmentManager();
        for (int i = 1; i <= 5; i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image("http://yashodeepacademy.co.in/slider/" + i + ".jpg");
            //  textSliderView.image("http://orientalbirdimages.org/images/data/striated_laughingthrush_0001.jpg");
            sliderShow.addSlider(textSliderView);
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable(getResources().getDrawable(R.drawable.shareicon));
        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton actionButton = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView itemIcon1 = new ImageView(this);
        ImageView itemIcon2 = new ImageView(this);
        ImageView itemIcon3 = new ImageView(this);
        ImageView itemIcon4 = new ImageView(this);
        int subActionButtonSize = 120;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(subActionButtonSize, subActionButtonSize);
        itemIcon1.setImageDrawable(getResources().getDrawable(R.drawable.facebook));
        itemIcon2.setImageDrawable(getResources().getDrawable(R.drawable.twitter));
        itemIcon3.setImageDrawable(getResources().getDrawable(R.drawable.google));
        itemIcon4.setImageDrawable(getResources().getDrawable(R.drawable.linkedin));
        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).setLayoutParams(params).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).setLayoutParams(params).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).setLayoutParams(params).build();
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).setLayoutParams(params).build();

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#ffffff"));
        gd.setShape(GradientDrawable.OVAL);
        button1.setBackground(gd);
        button2.setBackground(gd);
        button3.setBackground(gd);
        button4.setBackground(gd);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .setRadius(250)
                .attachTo(actionButton)
                .build();
        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {

                AlphaAnimation alpha = new AlphaAnimation(0.2F, 0.2F);
                alpha.setDuration(0); // Make animation instant
                alpha.setFillAfter(true);
                findViewById(R.id.container).startAnimation(alpha);
                LinearLayout layout = (LinearLayout) findViewById(R.id.container1);
                setViewAndChildrenEnabled(layout, false);
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                AlphaAnimation alpha = new AlphaAnimation(1.0F, 1.0F);
                alpha.setDuration(0); // Make animation instant
                alpha.setFillAfter(true);
                findViewById(R.id.container).startAnimation(alpha);
                LinearLayout layout = (LinearLayout) findViewById(R.id.container1);
                setViewAndChildrenEnabled(layout, true);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/yashodeepacademy/"));
                startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://twitter.com/Yashodeep2017"));
                startActivity(intent);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://plus.google.com/discover"));
                startActivity(intent);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.linkedin.com/in/yashodeep-academy-b61232153/"));
                startActivity(intent);

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        actionBarSetup();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

            }
        });
       /* rippleView1.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                rippleView1.setRippleDuration(80);
                intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });*/
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (fragment != null) {
            fragment = null;
            fragmentManager.popBackStack();
        } else if (back_pressed + 2000 > System.currentTimeMillis()) {
            // need to cancel the toast here
            toast.cancel();
            // code for exit
            intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            // ask user to press back button one more time to close app
            toast = Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT);
            toast.show();
        }
        back_pressed = System.currentTimeMillis();

    }


    @OnClick({R.id.imageClassroom, R.id.btn_career, R.id.btn_Staff, R.id.btn_success, R.id.btn_contactus, R.id.btn_aboutus, R.id.btn_achiever, R.id.btn_director})

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageClassroom:

                sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                menuItem = menu1.findItem(R.id.action_login);
                if (menuItem.getTitle().equals("Login")) {
                    Log.d("am i null", "onClick: " + (menuItem == null ? "am null" : "not null"));
                    onOptionsItemSelected(menuItem);
                } else if (sp.getString("CLASS", null).equals("10")) {
                    startActivity(new Intent(this, TenthActivity.class));
                    //onOptionsItemSelected(menuItem);
                } else
                    startActivity(new Intent(this, TabActivity.class));
                break;
            case R.id.btn_career:
                //  btn_career.startAnimation(animation);
                intent = new Intent(MainActivity.this, CareerGuidance.class);
                startActivity(intent);


                break;
            case R.id.btn_Staff:
//                btnStaff.startAnimation(animation);
                intent = new Intent(MainActivity.this, StaffActivity.class);
                startActivity(intent);

                break;
            case R.id.btn_success:
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                onNavigationItemSelected(menu.getItem(3));
                break;
            case R.id.btn_contactus:
//                btnContactUs.startAnimation(animation);
                intent = new Intent(MainActivity.this, ContactUs.class);
                startActivity(intent);
                break;
            case R.id.btn_aboutus:
//                btnAboutUs.startAnimation(animation);
                intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);

                break;
            case R.id.btn_achiever:
//                btnAchiever.startAnimation(animation);
                intent = new Intent(MainActivity.this, OurAchievers.class);
                startActivity(intent);
                break;
            case R.id.btn_director:
                intent = new Intent(MainActivity.this, DirectorInfo.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        menu1 = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        menuItem = item;
        if (id == R.id.action_login) {
            itemname = item.getTitle().toString();
            Log.d("ItemName", itemname);
            if (itemname.equals("Login")) {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 100);
            } else {
                menuItem.setTitle("Login");
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                menu.findItem(R.id.nav_login).setTitle("Login");
                SharedPreferences sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USERNAME", null);
                editor.putString("ID", null);
                editor.putString("CLASS", null);
                editor.commit();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item1) {
        // Handle navigation view item clicks here.
        int id = item1.getItemId();
        if (id == R.id.nav_login) {
            MenuItem item = menu1.findItem(R.id.action_login);
            onOptionsItemSelected(item);
        } else if (id == R.id.nav_classroom) {
            (findViewById(R.id.imageClassroom)).performClick();
        } else if (id == R.id.nav_Stat) {
            sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
            if (sp.getString("ID1", null) == null)
                Toast.makeText(getBaseContext(), "Please Login once to Activate", Toast.LENGTH_SHORT).show();
            else {
                intent = new Intent(MainActivity.this, Performance.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_staff) {
            (findViewById(R.id.btn_Staff)).performClick();
        } else if (id == R.id.nav_careerguidance) {
            (findViewById(R.id.btn_career)).performClick();
        } else if (id == R.id.nav_contactus) {
            (findViewById(R.id.btn_contactus)).performClick();
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=com.xoxytech.ostello");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Home");

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data.getBooleanExtra("data", false)) {
                menuItem.setTitle("Logout");
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                SharedPreferences sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                ((TextView) (navigationView.getHeaderView(0).findViewById(R.id.username))).setText(sp.getString("USERNAME", null));
                ((TextView) (navigationView.getHeaderView(0).findViewById(R.id.standard))).setText(sp.getString("CLASS", null));
                Menu menu = navigationView.getMenu();
                menu.getItem(0).setTitle("Logout");
                Log.d("****", "Item**** ");
            }
        }
    }
}