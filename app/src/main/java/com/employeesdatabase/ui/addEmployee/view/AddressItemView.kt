package com.employeesdatabase.ui.addEmployee.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.employeesdatabase.R
import com.employeesdatabase.models.AddressItem
import kotlinx.android.synthetic.main.item_address_view.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AddressItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_address_view, this)
    }

    @ModelProp
    fun setAddressModel(addressModel: AddressItem?){
        streetTextView.text = addressModel?.street
        cityTextView.text = addressModel?.city
        zipTextView.text = addressModel?.zip
        countryTextView.text = addressModel?.country
    }
}