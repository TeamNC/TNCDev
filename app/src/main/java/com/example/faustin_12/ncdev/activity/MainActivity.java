package com.example.faustin_12.ncdev.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.faustin_12.ncdev.R;
import com.example.faustin_12.ncdev.activity.fragment.AccueilFragment;
import com.example.faustin_12.ncdev.activity.fragment.AccueilFragment2;
import com.example.faustin_12.ncdev.activity.fragment.AideFragment;
import com.example.faustin_12.ncdev.activity.fragment.BoiteFragment;
import com.example.faustin_12.ncdev.activity.fragment.CategoriesFragment;
import com.example.faustin_12.ncdev.activity.fragment.ClassementFragment;
import com.example.faustin_12.ncdev.activity.fragment.FavorisFragment;
import com.example.faustin_12.ncdev.activity.fragment.OffreSpecialeFragment;
import com.example.faustin_12.ncdev.activity.fragment.ReglageFragment;
import com.example.faustin_12.ncdev.activity.fragment.SnackFragment;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    public ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;
    private int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         *Setup the DrawerLayout , TabLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */
      //  AccueilFragment accueilFragment = new AccueilFragment();
        //accueilFragment.setTabId(0);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new AccueilFragment()).commit();

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                toolbar.setVisibility(View.VISIBLE);
                mDrawerLayout.closeDrawers();
                AccueilFragment accueilFragment = new AccueilFragment();

                if (menuItem.getItemId() == R.id.nav_item_actualite) {
                    accueilFragment.setTabId(0);
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, accueilFragment).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_evenement) {
                    accueilFragment.setTabId(1);
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, accueilFragment).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_live) {
                    accueilFragment.setTabId(2);
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, accueilFragment).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_boites) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new BoiteFragment()).addToBackStack(null).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_snacks) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new SnackFragment()).addToBackStack(null).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_offresspeciales) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new AccueilFragment2()).addToBackStack(null).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_classement) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new ClassementFragment()).addToBackStack(null).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_favoris) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new FavorisFragment()).addToBackStack(null).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_reglages) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new ReglageFragment()).addToBackStack(null).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_aide) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new AideFragment()).addToBackStack(null).commit();
                }

                return true;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        /*toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                //return super.onOptionsItemSelected(item);
                return true;
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mNavigationView))
        {
            mDrawerLayout.closeDrawer(mNavigationView);
        }
        else if (getFragmentManager().getBackStackEntryCount()>0)
        {
            getFragmentManager().popBackStack();
            getFragmentManager().executePendingTransactions();
            mDrawerToggle.setDrawerIndicatorEnabled(true);
        }
        else
        {
            super.onBackPressed();
            toolbar.setVisibility(View.VISIBLE);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
        }
    }

}