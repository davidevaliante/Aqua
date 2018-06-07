package aqua.contracts

import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.FirebaseUser

interface EmailRegistrationContracts{
    fun onEmailRegistrationSuccess(user: FirebaseUser?, additionalUserInfo: AdditionalUserInfo?)
    fun onEmailRegistrationFailure()
}