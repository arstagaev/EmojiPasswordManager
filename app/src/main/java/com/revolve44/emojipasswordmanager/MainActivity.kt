package com.revolve44.emojipasswordmanager



import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController


import com.revolve44.emojipasswordmanager.repository.PassRepository
import com.revolve44.emojipasswordmanager.storage.PreferenceMaestro
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import com.revolve44.emojipasswordmanager.ui.ViewModelProviderFactory
import com.revolve44.emojipasswordmanager.ui.blinkATextView
//import com.revolve44.emojipasswordmanager.ui.changeColor
import com.revolve44.emojipasswordmanager.ui.screens.MainScreenFragment
import com.revolve44.emojipasswordmanager.utils.listOfColor

import com.revolve44.emojipasswordmanager.utils.randomName
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel
    // create a daemon thread (i mean no ui thread if false)
    private val timer = Timer("schedule",false)
    private lateinit var popup : PopupMenu

    lateinit var toolbar: Toolbar
    lateinit var toolbar_menu: ImageView
    lateinit var navController: NavController
    lateinit var actionbarTitle: TextView
    val mainScreenfragment : MainScreenFragment = MainScreenFragment()

    @SuppressLint("BinaryOperationInTimber")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar = findViewById(R.id.toolbar)
        //mainScreenLayout = findViewById(R.id.main_content)
        toolbar_menu = findViewById(R.id.toolbar_menu)
        actionbarTitle = findViewById(R.id.actionbarTitle)




        val repository = PassRepository(application)

        blinkATextView(actionbarTitle,Color.WHITE,Color.BLACK, listOfColor(PreferenceMaestro.pickedColorofToolbarTitle),1000)



        val viewModelProviderFactory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

//        toolbar.inflateMenu(R.menu.menu_on_mainscreen)
//        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { false })
        navController = findNavController(R.id.nav_host_fragment) //Initialising navController
        var a = 0
        actionbarTitle.setOnClickListener {
            a++

            if (a>6){
                actionbarTitle.text = randomName()
                timer.schedule(4000){
                    actionbarTitle.text = "Emoji Password Manager"
                    a=0

                }
            }
            if (a<7){
                blinkATextView(actionbarTitle, PreferenceMaestro.pickedColorofToolbarTitle,Color.BLACK, listOfColor(a),1000)
                PreferenceMaestro.pickedColorofToolbarTitle = a

            }

        }




        toolbar_menu.setOnClickListener {
            showMenu()
        }

    }

    private fun showMenu(){
        popup = PopupMenu(this@MainActivity, toolbar_menu)
        popup.inflate(R.menu.menu_on_mainscreen)
        popup.show()
        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener {menux ->

//                Timber.i("ddd ${menux.itemId}")
//                Log.d("sss",""+menux.itemId)

            when(menux.itemId){
                //R.id.menu_settings -> navController.navigate(R.id.action_gameFragment_to_settingsFragment)
                R.id.menu_about -> showDialog()
                R.id.change_color -> {
                    goToUrl("https://discord.gg/gSDaQ4xN")
                    // changeColor(mainScreenLayout,PreferenceMaestro.pickedColorofMainScreen, listOfColor(howmanyclickedChangeColorinMenu),1500)
//                    howmanyclickedChangeColorinMenu++
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun showDialog(){
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Emoji Password Manager \n(v. 0.1)")
        alertDialog.setMessage("This method of storing passwords is itself a high degree of encryption." +
                " Only you know what these or those symbols mean, only you understand what such a sequence of emoji means."+
                "\n          " +
        "\n future features: \n" +
                "- password to emoji converter, its will be fun!(Coming Soon!) \uD83D\uDE02 \n" +
                "- personal account for synchronizing passwords on various devices (IF WE GET 1000 users)\uD83D\uDDA5️ \n" +
                "- iOS version (IF WE GET 3000 users)\uD83D\uDE09 \n"+
                "- desktop version (IF WE GET 5000 users) \uD83D\uDE0D")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "our Discord Channel",
            DialogInterface.OnClickListener { dialog, which -> goToUrl("https://discord.gg/gSDaQ4xN") })
//        alertDialog.setButton(
//            AlertDialog.BUTTON_POSITIVE, "Don't show again",
//            DialogInterface.OnClickListener { dialog, which ->
//                editor.putBoolean("showagain", false)
//                editor.apply()
//                dialog.dismiss()https://discord.gg/gSDaQ4xN
//            })
        alertDialog.show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_on_mainscreen, menu);
        return true;

    }

    private fun goToUrl(url: String) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }
}






