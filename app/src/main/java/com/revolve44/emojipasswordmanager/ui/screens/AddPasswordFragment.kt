package com.revolve44.emojipasswordmanager.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.revolve44.emojipasswordmanager.MainActivity
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.adapters.MassiveAdapter
import com.revolve44.emojipasswordmanager.adapters.SuggestionsAdapter
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.models.SuggestPair
import com.revolve44.emojipasswordmanager.ui.MainViewModel
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
    lateinit var inputServiceName : EditText
    lateinit var inputPassword : EditText

    lateinit var recyclerviewSuggestions : RecyclerView
    lateinit var adapterSuggestions : SuggestionsAdapter

    lateinit var oldPairNameAndPassword : PairNameandPassword

    var arrayList : ArrayList<SuggestPair> = ArrayList()

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

        recyclerviewSuggestions = view.findViewById(R.id.passwordSuggestingList)

        inputServiceName = view.findViewById(R.id.input_name_of_service)
        inputPassword = view.findViewById(R.id.input_password)
        Timber.i("well " + viewModel.pairPasswordAndName.value.toString())

        if (viewModel.pairPasswordAndName.value!=null){
            inputServiceName.setText(viewModel.pairPasswordAndName.value!!.nameCompany + "")
            inputPassword.setText(viewModel.pairPasswordAndName.value!!.password + "")
            confirmButton.text = "confirm changes"

            oldPairNameAndPassword =
                PairNameandPassword(
                    inputServiceName.text.toString(),
                    inputPassword.text.toString()
                )
        }
        if (inputServiceName.text.toString() == ""){

        }

        confirmButton.setOnClickListener {



//            val pairNameandPassword : PairNameandPassword = PairNameandPassword("VK","qwerty")
//            viewModel.newPassword.value =  pairNameandPassword

            viewModel.deletePassword(oldPairNameAndPassword)
            viewModel.addPassword("${inputServiceName.text}", "${inputPassword.text}")

            //go to another fragment
            NavHostFragment.findNavController(this).navigate(R.id.action_setPasswordFragment_to_MainScreenFragment)
        }
        initRecyclerView()
        adapterSuggestions.onItemClick = { result ->
            Log.d("vvv",""+result)
            inputPassword.append(result.emojiPasswordSuggest.toString())
        }



    }

    private fun initRecyclerView() {
        var suggestPair: SuggestPair = SuggestPair("Facebook", "\uD83C\uDFCA")

        arrayList.add(SuggestPair("my dog's nickname", "\uD83D\uDC36\uD83D\uDD24"))
        arrayList.add(SuggestPair("my cat's nickname", "\uD83D\uDC31\uD83D\uDD24"))
        arrayList.add(SuggestPair("my favorite number", "\uD83D\uDD22❤️"))
        arrayList.add(SuggestPair("my favorite color", "\uD83C\uDF08❤️"))

        adapterSuggestions = SuggestionsAdapter(arrayList, requireContext(), viewModel)

        recyclerviewSuggestions.adapter = adapterSuggestions
        recyclerviewSuggestions.layoutManager =
            LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recyclerviewSuggestions.setHasFixedSize(false)
    }

    override fun onPause() {
        super.onPause()
        viewModel.pairPasswordAndName.value=null
        inputServiceName.setText("")
        inputPassword.setText("")
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


