package aqua.activity

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aqua.enums.RegistrationStage
import aqua.enums.RegistrationType
import aqua.extensions.*
import aqua.fragments.AquaEmailRegistrationFragment
import aqua.markers.AquaPasswordLoginButton
import assignViewModel


open class AquaLoginActivity : AppCompatActivity(), LifecycleOwner {


    val viewModel = assignViewModel<LoginActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewModel.stage.postValue(RegistrationStage.PROVIDER_CHOICE)

        viewModel.stage.observe(this, Observer { currentStage ->
            currentStage.toString() log "STAGE_FROM_"+this.javaClass.simpleName.toString()
            when(currentStage){
                RegistrationStage.PROVIDER_CHOICE -> removeAllFragments()
                RegistrationStage.EMAIL_AND_PASSWORD_INPUT -> addFragmentToRoot(AquaEmailRegistrationFragment())
                RegistrationStage.EMAIL_REGISTRATION_FAILED -> showMessage("Success")
                RegistrationStage.EMAIL_REGISTRATION_SUCCESS -> onEmailRegistrationSuccess()
                else -> showError("IllegalStageException")
            }
        })


        viewModel.user.observe(this, Observer { user ->
            showInfo("Login Successfull")
        })

        // Do after 2 seconds {viewModel.stage.postValue(RegistrationStage.EMAIL_AND_PASSWORD_INPUT)}
    }

    override fun setContentView(layoutResID: Int) {
        // super removed, layout inflated manually
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflatedLayout = inflater.inflate(layoutResID, null) as ViewGroup
        getRegistrationTypeList(inflatedLayout)
        setContentView(inflatedLayout)
    }

    /** Parses an inflated layout  to find Aqua-Named widgets in order to bind the correct viewModel
     * call with [View.isMarkedAs].
     * Usually called inside [setContentView] after inflating the layout manually
     */
    private fun getRegistrationTypeList(vg : ViewGroup){
        for(index in 0 until vg.childCount){
            val currentView = vg.getChildAt(index)
            when (currentView){
                is AquaPasswordLoginButton -> currentView.setOnClickListener { viewModel.startEmailRegistrationFlow() }
            }
        }
    }

    /** Based on different types @OnClick starts a different registration flow. Generally called
     * after [getRegistrationTypeList]
     */
    private infix fun View.isMarkedAs(type : RegistrationType){
        when(type){
            RegistrationType.PASSWORD_EMAIL -> this.setOnClickListener { viewModel.startEmailRegistrationFlow() }
        }
    }

    private fun onEmailRegistrationSuccess(){
        removeAllFragments()
    }


    override fun onBackPressed() {
        when(viewModel.stage.value){
            RegistrationStage.PROVIDER_CHOICE -> super.onBackPressed()
            RegistrationStage.EMAIL_AND_PASSWORD_INPUT -> removeFragmentsOfType<AquaEmailRegistrationFragment>()
        }
    }
}
