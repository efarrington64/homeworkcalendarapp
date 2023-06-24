package com.homecalapp.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.homecalapp.PREFS_EMAIL
import com.homecalapp.PREFS_PASSWORD
import com.homecalapp.PREFS_PHONE
import com.homecalapp.app.R
import com.homecalapp.defaultSharedPreferences
import com.homecalapp.main.MainActivity

class SignUpFragment : Fragment() {
    lateinit var emailText: EditText
    lateinit var passwordText: EditText
    lateinit var passwordText2: EditText
    lateinit var phoneText: EditText
    lateinit var primaryButton: Button
    lateinit var secondaryButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login_signup, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailText = view.findViewById(R.id.emailText)
        passwordText = view.findViewById(R.id.passwordText)
        passwordText2 = view.findViewById(R.id.passwordText2)
        phoneText = view.findViewById(R.id.phoneText)
        primaryButton = view.findViewById(R.id.primaryButton)
        secondaryButton = view.findViewById(R.id.secondaryButton)

        val watcher = SignupWatcher()
        emailText.addTextChangedListener(watcher)
        passwordText.addTextChangedListener(watcher)
        passwordText2.addTextChangedListener(watcher)
        phoneText.addTextChangedListener(watcher)

        primaryButton.isEnabled = false
        primaryButton.setOnClickListener {
            requireContext().defaultSharedPreferences.edit {
                putString(PREFS_EMAIL, emailText.text.toString())
                putString(PREFS_PASSWORD, passwordText.text.toString())
                putString(PREFS_PHONE, phoneText.text.toString())
            }
            AlertDialog.Builder(requireContext())
                .setTitle("Account created")
                .setMessage("Let's create your first homework!")
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    requireContext()
                        .startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }
                .create()
                .show()
        }
        secondaryButton.setOnClickListener {
            (requireActivity() as LoginActivity).pager.currentItem = 0
        }
    }

    inner class SignupWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            primaryButton.isEnabled = emailText.text.isNotBlank() &&
                passwordText.text.isNotBlank() &&
                passwordText2.text.isNotBlank() &&
                passwordText.text.toString() == passwordText2.text.toString() &&
                phoneText.text.isNotBlank()
        }
    }
}
