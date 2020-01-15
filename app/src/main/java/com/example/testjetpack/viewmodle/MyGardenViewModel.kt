package com.example.testjetpack.viewmodle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testjetpack.data.GardenPlantingRepository
import com.example.testjetpack.data.PlantAndGardenPlantings

class MyGardenViewModel internal constructor(
gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {
    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantedGardens()
}
