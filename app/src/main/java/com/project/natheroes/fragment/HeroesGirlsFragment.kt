package com.project.natheroes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.natheroes.adapter.HeroesGirlsAdapter
import com.project.natheroes.databinding.FragmentHeroesGirlsBinding

class HeroesGirlsFragment : Fragment() {

    private var _binding: FragmentHeroesGirlsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroesGirlsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val heroesGirls = HeroesGirlsAdapter()
       // heroesGirls.setDatas(newList = )
    }

}