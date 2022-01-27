package com.zuluft.navigation.entry_point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class GlobalEntryPoint : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        findNavController().navigate(GlobalEntryPointDirections.actionGlobalEntryPointToFeatureIntro())
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}