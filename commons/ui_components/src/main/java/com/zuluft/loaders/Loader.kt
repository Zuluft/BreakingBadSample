package com.zuluft.loaders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.zuluft.ui_components.databinding.LayoutLoaderBinding

class Loader : DialogFragment() {

    private lateinit var binding: LayoutLoaderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LayoutLoaderBinding.inflate(inflater, container, false)
            .also {
                binding = it
            }.root
    }

    companion object {
        private const val TAG = "LOADER_TAG"

        fun show(fragmentManager: FragmentManager): Loader {
            val loader = Loader()
            loader.show(fragmentManager, TAG)
            return loader
        }

        fun dismiss(fragmentManager: FragmentManager) {
            val loader = fragmentManager.findFragmentByTag(TAG)
            if (loader != null) {
                fragmentManager.beginTransaction()
                    .detach(loader)
                    .remove(loader)
                    .commitAllowingStateLoss()
            }
        }
    }
}