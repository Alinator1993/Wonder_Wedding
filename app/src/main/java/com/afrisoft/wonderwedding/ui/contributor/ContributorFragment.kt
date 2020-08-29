package com.afrisoft.wonderwedding.ui.contributor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.afrisoft.wonderwedding.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_contributor.*

class ContributorFragment : Fragment() {

    lateinit var editTextContributor : EditText
    lateinit var ratingBarContributor: RatingBar
    lateinit var buttonSaveContributor: Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_contributor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextContributor = editTextName
        ratingBarContributor = ratingBar
        buttonSaveContributor = buttonSave

        buttonSaveContributor.setOnClickListener{
            savePerson()
        }

    }


    private fun savePerson() {
        val name = editTextContributor.text.toString().trim()

        if (name.isEmpty()){
            editTextContributor.error = "Please enter a name"
            return
        }
        // Write a message to the database
        val ref = FirebaseDatabase.getInstance().getReference("Contributors")
        val personId = ref.push().key
        val donator = Contributor(name,ratingBarContributor.rating.toInt())
        ref.child(personId!!).setValue(donator).addOnCompleteListener{
            Toast.makeText(context,"Rating saved successfully",Toast.LENGTH_LONG).show()
        }

    }

    public fun saveAddress(){
        // Save donar address
    }
}
