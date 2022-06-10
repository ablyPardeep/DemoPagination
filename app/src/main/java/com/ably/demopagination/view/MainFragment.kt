package com.ably.demopagination.view

import com.ably.demopagination.network.ApiService
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ably.demopagination.R
import com.ably.demopagination.adapter.ItemAdapter
import com.ably.demopagination.adapter.MyLoadStateAdapter
import com.ably.demopagination.viewmodel.DataViewModel
import com.ably.demopagination.viewmodel.DataViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment: Fragment() {

    private lateinit var viewModel: DataViewModel

    /**
     * @author Pardeep Sharma
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = DataViewModelFactory(ApiService())
        /********************initialze the viewmodel here*/
        viewModel = ViewModelProvider(this, factory).get(DataViewModel::class.java)

        val passengersAdapter = ItemAdapter()
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.setHasFixedSize(true)

        /************ Add retry and loader layout with recycler view*/
        recycler_view.adapter = passengersAdapter.withLoadStateHeaderAndFooter(
            header = MyLoadStateAdapter{passengersAdapter.retry()}
            , footer = MyLoadStateAdapter{passengersAdapter.retry()}
        )

        lifecycleScope.launch {
            viewModel.passengers.collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
            }
        }
    }
}
