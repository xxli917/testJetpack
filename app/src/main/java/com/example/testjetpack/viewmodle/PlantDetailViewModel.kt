package com.example.testjetpack.viewmodle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testjetpack.data.GardenPlantingRepository
import com.example.testjetpack.data.PlantRepository
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModel() {

    val isPlanted = gardenPlantingRepository.isPlanted(plantId)
    val plant = plantRepository.getPlant(plantId)

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }
}
