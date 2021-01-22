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
import com.revolve44.emojipasswordmanager.ui.screens.ViewModelTrashbox
import com.revolve44.emojipasswordmanager.utils.listOfColor

import com.revolve44.emojipasswordmanager.utils.randomName
import timber.log.Timber
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel
    lateinit var viewModelTrashbox: ViewModelTrashbox

    // create a daemon thread (i mean no ui thread if false)
    private val timer = Timer("schedule",false)
    private lateinit var popup : PopupMenu

    lateinit var toolbar: Toolbar
    lateinit var toolbar_menu: ImageView
    lateinit var navController: NavController
    lateinit var actionbarTitle: TextView
    lateinit var repository: PassRepository
    val mainScreenfragment : MainScreenFragment = MainScreenFragment()

    @SuppressLint("BinaryOperationInTimber")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar_menu = findViewById(R.id.toolbar_menu)
        actionbarTitle = findViewById(R.id.actionbarTitle)

        repository = PassRepository(application)

        blinkATextView(actionbarTitle,Color.WHITE,Color.BLACK, listOfColor(PreferenceMaestro.pickedColorofToolbarTitle),1000)


        // viewmodel factory
        val viewModelProviderFactory = ViewModelProviderFactory(application)

        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MainViewModel::class.java)

        sessionRouter()

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



    }


    private fun sessionRouter(){
        navController = findNavController(R.id.nav_host_fragment) //Initialising navController
        PreferenceMaestro.appLaunchCount = PreferenceMaestro.appLaunchCount + 1


        if(PreferenceMaestro.shouldShowFirstrun){
            navController.navigate(R.id.action_MainScreenFragment_to_firstRunFragment)
        }
        toolbarMenuButton()

    }

    private fun toolbarMenuButton(){
        toolbar_menu.setOnClickListener {
            showMenu()

        }

        if(PreferenceMaestro.shouldShowFirstrun){

            toolbar_menu.setColorFilter(Color.BLACK)
            timer.schedule(7000){
                toolbar_menu.setColorFilter(Color.WHITE)
            }
        }else{
            toolbar_menu.setColorFilter(Color.WHITE)

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
                R.id.helper ->{
                    //dummy plug
                    try {
                        navController.navigate(R.id.action_MainScreenFragment_to_firstRunFragment)
                    }catch (e: Exception){
                        Timber.e("Error in action bar menu: $e")
                        //java.lang.IllegalArgumentException: Navigation action/destination com.revolve44.emojipasswordmanager:id/action_MainScreenFragment_to_trashboxFragment cannot be found from the current destination Destination(com.revolve44.emojipasswordmanager:id/trashboxFragment) label=fragment_result class=com.revolve44.emojipasswordmanager.ui.screens.TrashboxFragment
                    }

                }
                R.id.trashbox ->{
                    //dummy plug
                    try {
                        navController.navigate(R.id.action_MainScreenFragment_to_trashboxFragment)
                    }catch (e: Exception){
                        //java.lang.IllegalArgumentException: Navigation action/destination com.revolve44.emojipasswordmanager:id/action_MainScreenFragment_to_trashboxFragment cannot be found from the current destination Destination(com.revolve44.emojipasswordmanager:id/trashboxFragment) label=fragment_result class=com.revolve44.emojipasswordmanager.ui.screens.TrashboxFragment
                    }

                }
                R.id.menu_about -> showDialog()
                R.id.change_color -> {
                    goToUrl("https://discord.gg/gSDaQ4xN")
                    // changeColor(mainScreenLayout,PreferenceMaestro.pickedColorofMainScreen, listOfColor(howmanyclickedChangeColorinMenu),1500)

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






