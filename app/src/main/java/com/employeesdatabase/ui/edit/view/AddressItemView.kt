package com.employeesdatabase.ui.edit.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.employeesdatabase.R
import com.employeesdatabase.models.AddressItem
import kotlinx.android.synthetic.main.fragment_edit_address_item.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AddressItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.fragment_edit_address_item, this)
    }

    @ModelProp
    fun setAddressModel(addressModel: AddressItem?){
        streetTextView.text = addressModel?.street
        cityTextView.text = addressModel?.city
        zipTextView.text = addressModel?.zip
        countryTextView.text = addressModel?.country
    }

    @CallbackProp
    fun setOnDiscardButtonCallback(onDiscardButtonListener: OnClickListener?) {
        removeAddressButton.setOnClickListener(onDiscardButtonListener)
    }

    @CallbackProp
    fun setOnEditButtonCallback(onEditButtonListener: OnClickListener?) {
        editAddressButton.setOnClickListener(onEditButtonListener)
    }
}