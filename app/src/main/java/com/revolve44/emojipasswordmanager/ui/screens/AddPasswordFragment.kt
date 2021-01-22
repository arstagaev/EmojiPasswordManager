package com.revolve44.emojipasswordmanager.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.revolve44.emojipasswordmanager.MainActivity
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.adapters.SuggestionsAdapter1
import com.revolve44.emojipasswordmanager.adapters.SuggestionsAdapter2
import com.revolve44.emojipasswordmanager.adapters.SuggestionsAdapter3
import com.revolve44.emojipasswordmanager.adapters.SuggestionsAdapter4
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.models.SuggestPair
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import com.revolve44.emojipasswordmanager.utils.randomPasswordFirstPart
import com.revolve44.emojipasswordmanager.utils.randomPasswordSecondPart
import timber.log.Timber



// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment(R.layout.fragment_setpassword) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel : MainViewModel
    lateinit var confirmButton : Button
    lateinit var randomButton: Button

    lateinit var inputServiceName : EditText
    lateinit var inputPassword : EditText

    lateinit var recyclerviewSuggestions : RecyclerView
    lateinit var adapterSuggestions : SuggestionsAdapter1

    lateinit var recyclerviewSuggestions2 : RecyclerView
    lateinit var adapterSuggestions2 : SuggestionsAdapter2

    lateinit var recyclerviewSuggestions3 : RecyclerView
    lateinit var adapterSuggestions3 : SuggestionsAdapter3

    lateinit var recyclerviewSuggestions4 : RecyclerView
    lateinit var adapterSuggestions4 : SuggestionsAdapter4

    //var oldPairNameAndPassword : PairNameandPassword = PairNameandPassword()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("LogNotTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as Context
        viewModel =(activity as MainActivity).viewModel
        confirmButton = view.findViewById(R.id.confirm)
        randomButton = view.findViewById(R.id.generate_random)



        inputServiceName = view.findViewById(R.id.input_name_of_service)
        inputPassword = view.findViewById(R.id.input_password)
        Timber.i("well " + viewModel.pairPasswordAndName.value.toString())

        if (viewModel.pairPasswordAndName.value!=null){
            inputServiceName.setText(viewModel.pairPasswordAndName.value!!.nameCompany + "")
            inputPassword.setText(viewModel.pairPasswordAndName.value!!.password + "")
            confirmButton.text = "confirm changes"


        }
        if (inputServiceName.text.toString() == ""){

        }


        initFirstRecyclerView(view)
        initSecondRecyclerView(view)
        initThirdRecyclerView(view)
        initFourthRecyclerView(view)

        initButtons()

    }

    private fun initButtons() {
        confirmButton.setOnClickListener {
            var oldPairNameAndPassword : PairNameandPassword =
                    PairNameandPassword(
                            inputServiceName.text.toString(),
                            inputPassword.text.toString()
                    )


//            val pairNameandPassword : PairNameandPassword = PairNameandPassword("VK","qwerty")
//            viewModel.newPassword.value =  pairNameandPassword

            //viewModel.deletePassword(oldPairNameAndPassword)
            viewModel.addPassword("${inputServiceName.text}", "${inputPassword.text}")

            //go to another fragment
            NavHostFragment.findNavController(this).navigate(R.id.action_setPasswordFragment_to_MainScreenFragment)
        }
        randomButton.setOnClickListener {
            inputPassword.append(randomPasswordFirstPart().toString()+ randomPasswordSecondPart().toString())
        }
    }

    private fun initFourthRecyclerView(view: View) {
        var arrayList : ArrayList<SuggestPair> = ArrayList()

        recyclerviewSuggestions4 = view.findViewById(R.id.fourthPasswordSuggestingList)
        //var suggestPair: SuggestPair = SuggestPair("Facebook", "\uD83C\uDFCA")

        arrayList.add(SuggestPair("name of shop", "\uD83C\uDFEC\uD83D\uDED2"))
        arrayList.add(SuggestPair("birthday date", "\uD83C\uDF82"))
        arrayList.add(SuggestPair("home address", "\uD83C\uDFE0"))
        arrayList.add(SuggestPair("country", "\uD83D\uDDFE"))

        arrayList.add(SuggestPair("work adress", "\uD83C\uDFEC"))
        //arrayList.add(SuggestPair("my profession", "\uD83D\uDEE3️"))
//        arrayList.add(SuggestPair("favorite game", "\uD83C\uDFAE"))

        adapterSuggestions4 = SuggestionsAdapter4(arrayList, requireContext(), viewModel)

        recyclerviewSuggestions4.adapter = adapterSuggestions4
        recyclerviewSuggestions4.layoutManager =
                LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recyclerviewSuggestions4.setHasFixedSize(false)

        adapterSuggestions4.onItemClick = { result ->
            Log.d("vvv",""+result)
            inputPassword.append(result.emojiPasswordSuggest.toString())
        }

    }

    private fun initThirdRecyclerView(view: View) {
        var arrayList : ArrayList<SuggestPair> = ArrayList()

        recyclerviewSuggestions3 = view.findViewById(R.id.thirdPasswordSuggestingList)
        //var suggestPair: SuggestPair = SuggestPair("Facebook", "\uD83C\uDFCA")

        arrayList.add(SuggestPair("1", "1️⃣"))
        arrayList.add(SuggestPair("2", "2️⃣"))
        arrayList.add(SuggestPair("3", "3️⃣"))
        arrayList.add(SuggestPair("4", "4️⃣"))
        arrayList.add(SuggestPair("5", "5️⃣"))
        arrayList.add(SuggestPair("6", "6️⃣"))
        arrayList.add(SuggestPair("7", "7️⃣"))
        arrayList.add(SuggestPair("8", "8️⃣"))
        arrayList.add(SuggestPair("9", "9️⃣"))
        arrayList.add(SuggestPair("0", "0️⃣"))


        //arrayList.add(SuggestPair("my profession", "\uD83D\uDEE3️"))
//        arrayList.add(SuggestPair("favorite game", "\uD83C\uDFAE"))

        adapterSuggestions3 = SuggestionsAdapter3(arrayList, requireContext(), viewModel)

        recyclerviewSuggestions3.adapter = adapterSuggestions3
        recyclerviewSuggestions3.layoutManager =
                LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recyclerviewSuggestions3.setHasFixedSize(false)

        adapterSuggestions3.onItemClick = { result ->
            Log.d("vvv",""+result)
            inputPassword.append(result.emojiPasswordSuggest.toString())
        }

    }

    private fun initSecondRecyclerView(view: View) {
        var arrayList : ArrayList<SuggestPair> = ArrayList()

        recyclerviewSuggestions2 = view.findViewById(R.id.secondPasswordSuggestingList)
        //var suggestPair: SuggestPair = SuggestPair("Facebook", "\uD83C\uDFCA")

        arrayList.add(SuggestPair("phone number", "\uD83D\uDCF1"))
        arrayList.add(SuggestPair("home phone", "☎️\uD83C\uDFE0"))
        arrayList.add(SuggestPair("work phone", "\uD83D\uDCF1\uD83D\uDCBC"))
        arrayList.add(SuggestPair("car mark", "\uD83D\uDE98"))

        arrayList.add(SuggestPair("car number", "#️⃣\uD83D\uDE98"))
        arrayList.add(SuggestPair("favorite street", "\uD83D\uDEE3️"))
        arrayList.add(SuggestPair("favorite game", "\uD83C\uDFAE"))

        adapterSuggestions2 = SuggestionsAdapter2(arrayList, requireContext(), viewModel)

        recyclerviewSuggestions2.adapter = adapterSuggestions2
        recyclerviewSuggestions2.layoutManager =
                LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recyclerviewSuggestions2.setHasFixedSize(false)

        adapterSuggestions2.onItemClick = { result ->
            Log.d("vvv",""+result)
            inputPassword.append(result.emojiPasswordSuggest.toString())
        }


    }

    private fun initFirstRecyclerView(view: View) {
        var arrayList : ArrayList<SuggestPair> = ArrayList()
        recyclerviewSuggestions = view.findViewById(R.id.firstPasswordSuggestingList)

        arrayList.add(SuggestPair("dog's nickname", "\uD83D\uDC36\uD83D\uDD24"))
        arrayList.add(SuggestPair("cat's nickname", "\uD83D\uDC31\uD83D\uDD24"))
        arrayList.add(SuggestPair("favorite number", "\uD83D\uDD22❤️"))
        arrayList.add(SuggestPair("favorite color", "\uD83C\uDF08❤️"))

        arrayList.add(SuggestPair("favorite book", "\uD83D\uDCD6♥️"))
        arrayList.add(SuggestPair("favorite food", "\uD83E\uDD63♥️"))
        arrayList.add(SuggestPair("favorite city", "\uD83C\uDFD9️♥️"))

        adapterSuggestions = SuggestionsAdapter1(arrayList, requireContext(), viewModel)

        recyclerviewSuggestions.adapter = adapterSuggestions
        recyclerviewSuggestions.layoutManager =
            LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recyclerviewSuggestions.setHasFixedSize(false)

        adapterSuggestions.onItemClick = { result ->
            Log.d("vvv",""+result)
            inputPassword.append(result.emojiPasswordSuggest.toString())
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.pairPasswordAndName.value=null
        inputServiceName.setText("")
        inputPassword.setText("")
        Timber.i("lifec onpause")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("lifec onresume")
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ResultFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}


