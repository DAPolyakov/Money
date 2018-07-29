package yandexschool.dmpolyakov.money.ui.tracker.account.operations

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import yandexschool.dmpolyakov.money.OperationCategory


class CategoryArrayAdapter(context: Context, private val items: List<OperationCategory>)
    : ArrayAdapter<OperationCategory>(context, android.R.layout.simple_spinner_dropdown_item, items) {

    private fun getCustomView(position: Int, convertView: View?): View? {
        var view = convertView
        if (view == null)
            view = View.inflate(context, android.R.layout.simple_spinner_dropdown_item, null)

        (view as TextView).text = context.resources.getString(items[position].title)
        return view
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        return getCustomView(position, convertView)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        return getCustomView(position, convertView)
    }
}