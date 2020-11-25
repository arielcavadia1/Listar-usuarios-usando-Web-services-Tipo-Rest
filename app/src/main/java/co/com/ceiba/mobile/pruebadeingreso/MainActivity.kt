package co.com.ceiba.mobile.pruebadeingreso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.ListUsersAdapter
import co.com.ceiba.mobile.pruebadeingreso.view.loading.ProgressLoading
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_view.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val mainActivityViewModel : MainActivityViewModel by inject ()
    val listUserAdapter : ListUsersAdapter by inject()
    var progress : ProgressLoading?= null
    var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        setUI()
        setViewModel()
        setClickListener()
    }

    /**
     * actualiza los elementos del view
     */
    private fun setUI(){
        progress = ProgressLoading(this)
       // listUserAdapter.list = mainActivityViewModel.listUsers.value ?: arrayListOf()
        recyclerViewSearchResults.adapter = listUserAdapter
    }

    /**
     * configura el viewmodel
     */
    private fun setViewModel(){


        mainActivityViewModel.listUsers.observe(this,{
            listUserAdapter.update(it)
        })

        mainActivityViewModel.filterUsers.observe(this,{
            listUserAdapter.update(it)
        })

        mainActivityViewModel.emptyList.observe(this, {
            if (it) empty_text.visibility = View.VISIBLE else empty_text.visibility = View.INVISIBLE
        })

        mainActivityViewModel.error.observe(this, {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

        mainActivityViewModel.isLoading.observe(this, {
            if(it) progress?.showProgress() else progress?.hideProgress()
        })

        mainActivityViewModel.getListUsers()
    }

    /**
     * configura los elementos clickleables
     */
    private fun setClickListener(){

        listUserAdapter.clickListener = object : ListUsersAdapter.OnClick{
            override fun onClick(user: UserEntity) {
                super.onClick(user)
                val intent = Intent(this@MainActivity, PostActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("user",user)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        editTextSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    mainActivityViewModel.getSearch(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}