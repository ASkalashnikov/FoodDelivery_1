package com.kalashnikov.tt_fooddelivery_1.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kalashnikov.tt_fooddelivery_1.R
import com.kalashnikov.tt_fooddelivery_1.databinding.FragmentMainBinding
import com.kalashnikov.tt_fooddelivery_1.domain.adapter.MainAdapter
import com.kalashnikov.tt_fooddelivery_1.presentation.mvvm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.InterfaceMain {
    private lateinit var binding: FragmentMainBinding
    private val mvvm: MainViewModel by activityViewModels()
    private val adapter = MainAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Загрузка данных
        mvvm.getCity()
        mvvm.getDate()
        initView()

        mvvm.getMain()
        initRcView()
    }

    private fun initView() = with(binding) {
        mvvm.textDate.observe(viewLifecycleOwner) { text ->
            includeToolbar.textDate.text = text
        }
        mvvm.textCity.observe(viewLifecycleOwner) { text ->
            includeToolbar.textCity.text = text
        }
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity as Context)
        rcView.adapter = adapter
        mvvm.rcViewMainVM.observe(viewLifecycleOwner) { adapter.updateAdapter(it) }
    }

    override fun onClickMain() {
        (requireActivity()).supportFragmentManager.beginTransaction()
            .replace(R.id.fLayout, MenuFragment.newInstance())
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}