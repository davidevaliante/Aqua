package aqua.fragments


import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import aqua.activity.EmailRegistrationViewModel
import aqua.activity.LoginActivityViewModel
import aqua.auth.R
import aqua.auth.R.id.userMail
import aqua.auth.R.id.userPass
import aqua.enums.RegistrationStage
import aqua.extensions.*
import aqua.markers.AquaEmailField
import aqua.markers.AquaPasswordField
import assignViewModel
import kotlinx.android.synthetic.main.aqua_email_registration_fragment.*
import string


open class AquaEmailRegistrationFragment : Fragment(), LifecycleOwner {

    private val viewModel by lazy { assignViewModel<EmailRegistrationViewModel>() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val vg = inflateInContainer(R.layout.aqua_email_registration_fragment, container)
        getEmailRegistrationFieldsIn(vg)

        viewModel.emailRegistrationStage.observe(this, Observer { currentStage ->
            currentStage.toString() log "STAGE_FROM_"+this.javaClass.simpleName.toString()

            when(currentStage){
                RegistrationStage.ILLEGAL_EMAIL_FIELD -> showError("Insert Email")
                RegistrationStage.ILLEGAL_PASSWORD_FIELD -> showError("Insert pass")
                RegistrationStage.OK_EMAIL_AND_PASS -> askForEmailRegistration()
                RegistrationStage.WAITING_FOR_RESPONSE -> showInfo("Waiting for response")
            }
        })

        return vg
    }

    private fun askForEmailRegistration(){
        activity?.hideKeyboard()
        viewModel.performEmailRegistration()
    }

    private fun getEmailRegistrationFieldsIn(vg : ViewGroup){
        // finds the root of the layout to inflate required fragments
        // finds required registration buttons and assigns the a RegistrationType
        for(index in 0 until vg.childCount){
            val currentView = vg.getChildAt(index)
            when (currentView){
                is AquaEmailField -> "F_PARSER" log currentView.id.toString()+" Is the email field"
                is AquaPasswordField -> "F_PARSER" log currentView.id.toString()+" Is the pass field"
                is Button -> currentView.setOnClickListener { viewModel.submitEmailRegistrationFields(userMail.string(), userPass.string()) }
            }
        }
    }
}
