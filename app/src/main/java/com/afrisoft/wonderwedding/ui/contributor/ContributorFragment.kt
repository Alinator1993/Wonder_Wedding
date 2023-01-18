package com.afrisoft.wonderwedding.ui.contributor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.afrisoft.wonderwedding.databinding.FragmentContributorBinding
import com.google.firebase.database.FirebaseDatabase

class ContributorFragment : Fragment() {
    private var _binding:  FragmentContributorBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContributorBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener{
            savePerson()
        }
    }
    private fun savePerson() {
        val name = binding.editTextName.text.toString().trim()

        if (name.isEmpty()){
            binding.editTextName.error = "Please enter a name"
            return
        }
        // Write a message to the database
        val ref = FirebaseDatabase.getInstance().getReference("Contributors")
        val personId = ref.push().key
        val donor = Contributor(name,binding.ratingBar.rating.toInt())
        ref.child(personId!!).setValue(donor).addOnCompleteListener{
            Toast.makeText(context,"Rating saved successfully",Toast.LENGTH_LONG).show()
        }
    }

//    public fun saveAddress(){
//        // Save donor address
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
