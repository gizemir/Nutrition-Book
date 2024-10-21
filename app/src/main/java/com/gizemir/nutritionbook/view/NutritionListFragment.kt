package com.gizemir.nutritionbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gizemir.nutritionbook.adapter.NutritionRecyclerAdapter
import com.gizemir.nutritionbook.databinding.FragmentNutritionListBinding
import com.gizemir.nutritionbook.service.NutritionAPI
import com.gizemir.nutritionbook.viewmodel.NutritionListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NutritionListFragment : Fragment() {
    private var _binding: FragmentNutritionListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NutritionListViewModel
    private val nutritionRecyclerAdapter = NutritionRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NutritionListViewModel::class.java]
        viewModel.refreshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = nutritionRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.textViewErrorMessage.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refreshDataFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.nutritions.observe(viewLifecycleOwner){
            nutritionRecyclerAdapter.updateList(it)
            binding.recyclerView.visibility = View.VISIBLE
        }
        viewModel.nutritionsErrorMessage.observe(viewLifecycleOwner){
            if(it){
                binding.textViewErrorMessage.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }else{
                binding.textViewErrorMessage.visibility = View.GONE
            }
        }
        viewModel.nutritionsUploading.observe(viewLifecycleOwner){
            if(it){
                binding.textViewErrorMessage.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}