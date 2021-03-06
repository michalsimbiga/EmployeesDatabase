package com.employeesdatabase.ui.edit.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.employeesdatabase.R
import com.employeesdatabase.common.empty
import com.employeesdatabase.models.AddressItem
import kotlinx.android.synthetic.main.fragment_edit_editable_address_item.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EditableAddressItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.fragment_edit_editable_address_item, this)
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
        streetEditableEditText.setText(String.empty)
        cityEditableEditText.setText(String.empty)
        zipEditableEditText.setText(String.empty)
        countryEditableEditText.setText(String.empty)
    }
}
