package com.mayandro.cleanarchbaseproject.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel>(clazz: KClass<VM>) : Fragment(){

    @LayoutRes
    protected abstract fun layoutId(): Int

    lateinit var binding: B

    val viewModel: VM by viewModel(clazz)

    val alertDialog: DialogUtils by lazy {
        DialogUtils()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            layoutId(),
            container,
            false
        )
        return binding.root
    }

    fun handleLoadingDialog(flag: Boolean) {

    }
}