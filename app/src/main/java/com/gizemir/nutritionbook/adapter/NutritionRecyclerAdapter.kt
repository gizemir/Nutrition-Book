package com.gizemir.nutritionbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gizemir.nutritionbook.databinding.NutritionRecyclerRowBinding
import com.gizemir.nutritionbook.model.Nutrition
import com.gizemir.nutritionbook.util.downloadImage
import com.gizemir.nutritionbook.util.placeHolderMake
import com.gizemir.nutritionbook.view.NutritionListFragmentDirections

class NutritionRecyclerAdapter(val nutritionList: ArrayList<Nutrition>) : RecyclerView.Adapter<NutritionRecyclerAdapter.NutritionViewHolder>(){
    class NutritionViewHolder(val binding: NutritionRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder {
        val binding = NutritionRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NutritionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nutritionList.size

    }
    fun updateList(newNutritionList: List<Nutrition>){
        nutritionList.clear()
        nutritionList.addAll(newNutritionList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NutritionViewHolder, position: Int) {
        holder.binding.name.text = nutritionList[position].nutritionName
        holder.binding.calori.text = nutritionList[position].nutritionCalori

        holder.itemView.setOnClickListener {
            val action = NutritionListFragmentDirections.actionNutritionListToNutritionDetail(nutritionList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.downloadImage(nutritionList[position].nutritionImage, placeHolderMake(holder.itemView.context ))
    }
}