package com.revolve44.emojipasswordmanager



import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController


import com.revolve44.emojipasswordmanager.repository.PassRepository
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import com.revolve44.emojipasswordmanager.ui.ViewModelProviderFactory
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel

    lateinit var toolbar: Toolbar
    lateinit var toolbar_menu: Button
    lateinit var navController: NavController

    @SuppressLint("BinaryOperationInTimber")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar = findViewById(R.id.toolbar)
        toolbar_menu = findViewById(R.id.toolbar_menu)

        val repository = PassRepository(application) // really? wtf

        val viewModelProviderFactory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

//        toolbar.inflateMenu(R.menu.menu_on_mainscreen)
//        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { false })
        navController = findNavController(R.id.nav_host_fragment) //Initialising navController

        toolbar_menu.setOnClickListener {
            //Creating the instance of PopupMenu

            //Creating the instance of PopupMenu
//            val menu = PopupMenu(view.context, view)
//            menu.inflate(R.menu.article_item_menu)

            val popup = PopupMenu(this@MainActivity, toolbar_menu)
            //Inflating the Popup using xml file
            //Inflating the Popup using xml file
            //popup.getMenuInflater().inflate(R.menu., popup.getMenu())
            popup.inflate(R.menu.menu_on_mainscreen)

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener {menux ->
//                Timber.i("ddd ${menux.itemId}")
//                Log.d("sss",""+menux.itemId)

                when(menux.itemId){
                    R.id.menu_settings -> navController.navigate(R.id.action_gameFragment_to_settingsFragment)
                    R.id.menu_about -> showPopupDialog()
                }
                return@setOnMenuItemClickListener true
            }

//            popup.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener() {
//                override fun onMenuItemClick(item: MenuItem): Boolean {
//                    Toast.makeText(this@MainActivity, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show()
//                    return true
//                }
//            })

            popup.show() //showing popup menu


        }

    }

    private fun showPopupDialog() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_on_mainscreen, menu);
        return true;

    }
}