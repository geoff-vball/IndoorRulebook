package com.gstuart.rulebook

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.gstuart.rulebook.documentModels.RulebookModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gstuart.rulebook.utils.setupWithNavController

class MainActivity : AppCompatActivity() {

    // This has to be initialized lazily because
    private val rulebookModel : RulebookModel by lazy {
        applicationContext.jsonToClass(R.raw.rulebook)
    }

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.search_toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupBottomNavigationBar()
    }



    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val navGraphIds = listOf(R.navigation.rulebook_nav, R.navigation.guidelines_nav, R.navigation.casebook_nav)

        // Setup the bottom navigation view with a guidelines_nav of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.fragment_container,
                intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}