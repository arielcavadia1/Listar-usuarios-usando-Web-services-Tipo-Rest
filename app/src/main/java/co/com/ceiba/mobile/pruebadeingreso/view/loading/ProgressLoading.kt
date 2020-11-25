package co.com.ceiba.mobile.pruebadeingreso.view.loading

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import co.com.ceiba.mobile.pruebadeingreso.R

class ProgressLoading(activity : Activity) {
    private var viewGroup : ViewGroup?=null
    private var view : View?=null
    init {
        view = activity.layoutInflater.inflate(R.layout.progress_bar,null)
        viewGroup = activity.window.decorView.findViewById(android.R.id.content)
    }

    /**
     * muestra un progress
     */
    fun showProgress(){
        viewGroup?.addView(view)
        print("")
    }

    /**
     *  desactiva el progress
     */
    fun hideProgress() {
        if(viewGroup != null && view != null) {
            viewGroup?.removeView(view)
        }
    }
}