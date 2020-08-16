package com.paru.bookapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.paru.bookapp.*
import com.paru.bookapp.fragment.AboutAppFragment
import com.paru.bookapp.fragment.DashboardFargment
import com.paru.bookapp.fragment.FavouritesFragment
import com.paru.bookapp.fragment.ProfileFragment

lateinit var drawerLayout: DrawerLayout
lateinit var coordinateLayout: CoordinatorLayout
lateinit var toolbar: Toolbar
lateinit var frameLayout: FrameLayout
lateinit var navigationView: NavigationView

var previousMenuItem:MenuItem?=null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout =findViewById(R.id.drawerLayout)
        coordinateLayout =findViewById(
            R.id.coordinateLayout
        )
        toolbar =findViewById(R.id.toolbar)
        frameLayout =findViewById(R.id.frameLayout)
        navigationView =findViewById(R.id.navigationView)

        setUpToolbar()
        openDashboard()

        val actionBarDrawerToggle=ActionBarDrawerToggle(this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem !=null)
            {
                previousMenuItem?.isChecked=false
            }

            it.isCheckable=true
            it.isChecked=true
            previousMenuItem =it

            when(it.itemId)
            {
                R.id.dashboard ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        DashboardFargment()
                    ).commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Dashboard"
                }

                R.id.favourites ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FavouritesFragment()
                    ).commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Favourites"
                }

                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        ProfileFragment()
                    ).commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Profile"
                }

                R.id.aboutApp ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        AboutAppFragment()
                    ).commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="About App"
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun openDashboard() {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayout,
            DashboardFargment()
        ).commit()
        supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(
            R.id.dashboard
        )
    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="BookHub"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLayout)

        when (frag) {

            !is DashboardFargment ->openDashboard()
            else->super.onBackPressed()
        }
    }
}
