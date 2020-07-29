package com.mayandro.cleanarchbaseproject.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel>(clazz : KClass<VM>): AppCompatActivity(){

    @LayoutRes
    protected abstract fun layoutId(): Int

    private lateinit var binding: B

    val viewModel : VM by viewModel(clazz)

    val alertDialog: DialogUtils by lazy {
        DialogUtils()
    }

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        bindContentView(layoutId())
    }

    private fun bindContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}