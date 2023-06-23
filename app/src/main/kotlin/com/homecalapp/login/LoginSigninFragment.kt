package com.homecalapp.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.homecalapp.app.R
import com.homecalapp.toast

class LoginSigninFragment : Fragment() {
    lateinit var emailText: EditText
    lateinit var passwordText: EditText
    lateinit var primaryButton: Button
    lateinit var secondaryButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login_signin, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailText = view.findViewById(R.id.emailText)
        passwordText = view.findViewById(R.id.passwordText)
        primaryButton = view.findViewById(R.id.primaryButton)
        secondaryButton = view.findViewById(R.id.secondaryButton)

        val watcher = StubWatcher()
        emailText.addTextChangedListener(watcher)
        passwordText.addTextChangedListener(watcher)

        primaryButton.setOnClickListener {
            it.context.toast("Not yet implemented, sign up instead.")
        }
        secondaryButton.setOnClickListener {
            (requireActivity() as LoginActivity).pager.currentItem = 1
        }
    }

    inner class StubWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            requireContext().toast("Not yet implemented, sign up instead.")
        }

        override fun afterTextChanged(s: Editable) {}
    }
}
