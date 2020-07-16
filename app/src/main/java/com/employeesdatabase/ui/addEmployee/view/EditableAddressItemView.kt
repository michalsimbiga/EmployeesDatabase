package com.employeesdatabase.ui.addEmployee.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.employeesdatabase.R
import com.employeesdatabase.models.AddressItem
import kotlinx.android.synthetic.main.item_address_view.view.*
import kotlinx.android.synthetic.main.item_editable_address.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EditableAddressItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_editable_address, this)
    }

    @CallbackProp
    fun setOnDiscardButtonCallback(onDiscardButtonListener: OnClickListener?) {
        discardAddressButton.setOnClickListener(onDiscardButtonListener)
    }

    @CallbackProp
    fun setOnAddButtonCallback(onAddButtonListener: ((AddressItem) -> Unit)?) {
        acceptAddressButton.setOnClickListener { onAddButtonListener?.invoke(constructAddress()) }
    }

    @ModelProp
    fun setAddressModel(addressModel: AddressItem?) {
        streetEditableEditText.setText(addressModel?.street)
        cityEditableEditText.setText(addressModel?.city)
        zipEditableEditText.setText(addressModel?.zip)
        countryEditableEditText.setText(addressModel?.country)
    }

    private fun constructAddress() = AddressItem(
        street = streetEditableEditText.text.toString().trim(),
        city = cityEditableEditText.text.toString().trim(),
        zip = zipEditableEditText.text.toString().trim(),
        country = countryEditableEditText.text.toString().trim(),
        editable = false
    )

    @OnViewRecycled
    fun onViewRecycled() {
        streetEditableEditText.setText("")
        cityEditableEditText.setText("")
        zipEditableEditText.setText("")
        countryEditableEditText.setText("")
    }
}
