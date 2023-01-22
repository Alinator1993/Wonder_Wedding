package com.afrisoft.wonderwedding.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afrisoft.wonderwedding.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.afrisoft.wonderwedding.MainActivity
import com.afrisoft.wonderwedding.databinding.ActivityRegisterBinding


//  Step 6: Create an empty activity as Register Activity.
/**
 * Register Screen of the application.
 */
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        //setContentView(R.layout.activity_register)
        //  Step 9: Assign the click event to the register button and perform the functionality.
        // START
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_register)

        binding.btnRegister.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.etRegisterEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.etRegisterPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = binding.etRegisterEmail.text.toString().trim { it <= ' ' }
                    val password: String = binding.etRegisterPassword.text.toString().trim { it <= ' ' }

                    // Create an instance and create a register a user with email and password.
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            { task ->

                                // If the registration is successfully done
                                if (task.isSuccessful) {

                                    // Firebase registered user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "You are registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    /**
                                     * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
                                     * and send him to Main Screen with user id and email that user have used for registration.
                                     */

                                    val intent =
                                        Intent(this@RegisterActivity, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    // If the registering is not successful then show error message.
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                }
            }
        }
        // END

        //  Step 14: Assign the click event to the Login text and launch the login screen.
        // START
            binding.tvLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                }
        }
        // END
    }