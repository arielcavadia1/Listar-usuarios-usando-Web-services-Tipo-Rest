package co.com.ceiba.mobile.pruebadeingreso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.com.ceiba.mobile.pruebadeingreso.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostUserAdapter
import co.com.ceiba.mobile.pruebadeingreso.view.loading.ProgressLoading
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModel
import kotlinx.android.synthetic.main.activity_post.*
import org.koin.android.ext.android.inject

class PostActivity : AppCompatActivity() {
    private var user : UserEntity?= null
    var progress : ProgressLoading?= null
    val listPostAdapter : PostUserAdapter by inject()
    val postActivityViewModel : PostActivityViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        user = this.intent.extras!!.getSerializable("user") as UserEntity
        setUI()
        setViewModel()
    }

    /**
     * Actualiza los elementos del view
     */
    private fun setUI(){
        txtName2.text = user?.username
        txtEmail.text = user?.email
        txtPhone.text = user?.phone
        progress = ProgressLoading(this)
        listPostAdapter.list = postActivityViewModel.listPost.value ?: arrayListOf()
        recyclerViewPostsResults.adapter = listPostAdapter
    }

    /**
     * configura el viewmodel
     * obtiene la lista de publicaciones del usuario
     */
    private fun setViewModel(){
        postActivityViewModel.getListPost(user!!)

        postActivityViewModel.listPost.observe(this,{
            listPostAdapter.update(it)
        })


        postActivityViewModel.isLoading.observe(this,{
            if (it) progress?.showProgress() else progress?.hideProgress()
        })

    }
}