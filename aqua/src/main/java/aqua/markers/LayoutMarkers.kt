package aqua.markers

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.RelativeLayout
import aqua.auth.R
import aqua.extensions.setPaddingInDpi
import kotlinx.android.synthetic.main.aqua_email_login_button.view.*


class AquaPasswordLoginButton @JvmOverloads constructor(context: Context,
                                                        attrs: AttributeSet? = null,
                                                        defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    init {

        fun Float.toDPI() : Float{
            val dp = 5.0f // This is the dp value from your dimen file
            val displayMetrics = resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
        }

        LayoutInflater.from(context).inflate(R.layout.aqua_email_login_button, this, true)

        attrs?.let {

            val typedArray = context.obtainStyledAttributes(it, R.styleable.email_button, 0, 0)

            // TEXT_VIEW attrs
            emailRegistrationDesc.text = resources.getText(
                    typedArray.getResourceId(R.styleable.email_button_buttonText,R.string.emailRegDesc))
            // se il nome dei colori combacia prende quello dell'app e non della libreria
            emailRegistrationDesc.background = ContextCompat.getDrawable(context,
                    typedArray.getResourceId(R.styleable.email_button_buttonTextColor,R.color.black))

            emailRegistrationDesc.setTextColor(ContextCompat.getColor(context, typedArray.getResourceId(R.styleable.email_button_buttonTextColor,R.color.white)))
//            emailRegistrationDesc.setPadding(resources.getDimension(R.dimen.base_padding).toDPI().toInt(),
//                                                resources.getDimension(R.dimen.base_padding).toDPI().toInt(),
//                                                resources.getDimension(R.dimen.base_padding).toDPI().toInt(),
//                                                resources.getDimension(R.dimen.base_padding).toDPI().toInt())

//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextPaddingTop,)
//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextPaddingBottom,)
//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextPaddingStart,)
//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextPaddingEnd,)
//
//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextMargin,)
//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextMarginTop,)
//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextMarginBottom,)
//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextMarginStart,)
//            emailRegistrationDesc. = typedArray.getResourceId(R.styleable.emailbutton_buttonTextMarginEnd,)

//            val icon = ContextCompat.getDrawable(context,R.drawable.ic_aqua_email_icon)
//                emailRegistrationIcon.background = icon
//
//
//        typedArray.recycle()
        }
    }
}

class AquaEmailField : AppCompatEditText {

    // Clear buttom image
    private lateinit var mClearButtonImage: Drawable

    constructor(context: Context) : super(context) {
        applyDefaultStyle()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyDefaultStyle()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        applyDefaultStyle()
    }

    private fun applyDefaultStyle(){
        this setPaddingInDpi 16
        hint = "Email"
    }
}

class AquaPasswordField : AppCompatEditText {

    // Clear buttom image
    private lateinit var mClearButtonImage: Drawable

    constructor(context: Context) : super(context) {
        applyDefaultStyle()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyDefaultStyle()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        applyDefaultStyle()
    }

    fun applyDefaultStyle(){
        this setPaddingInDpi 16
        hint = "Password"
        inputType=InputType.TYPE_TEXT_VARIATION_PASSWORD+1
    }
}