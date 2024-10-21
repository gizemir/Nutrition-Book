package com.gizemir.nutritionbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gizemir.nutritionbook.databinding.FragmentNutritionDetailBinding
import com.gizemir.nutritionbook.util.downloadImage
import com.gizemir.nutritionbook.util.placeHolderMake
import com.gizemir.nutritionbook.viewmodel.NutritionDetailViewModel
import com.gizemir.nutritionbook.viewmodel.NutritionListViewModel


class NutritionDetailFragment : Fragment() {

    private var _binding: FragmentNutritionDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NutritionDetailViewModel
    var nutritionId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NutritionDetailViewModel::class.java]
        arguments?.let {
            nutritionId = NutritionDetailFragmentArgs.fromBundle(it).id
        }
        viewModel.getRoomData(nutritionId)

        observeLiveData()

    }
    private fun observeLiveData(){
        viewModel.nutritionLiveData.observe(viewLifecycleOwner){
            binding.nutritionName.text = it.nutritionName
            binding.nutritionCalori.text = it.nutritionCalori
            binding.nutritionProtein.text = it.nutritionProtein
            binding.nutritionCarbohydrate.text = it.nutritionCarbohydrate
            binding.nutritionFat.text = it.nutritionFat
            binding.nutritionImage.downloadImage(it.nutritionImage, placeHolderMake(requireContext()))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}