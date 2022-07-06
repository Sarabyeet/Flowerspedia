package com.sarabyeet.flowerspedia.arch

import android.content.Context
import com.sarabyeet.flowerspedia.R

class FlowersRepository {
    fun parseFlowersList(context: Context): List<String> {
        return context.resources.getStringArray(R.array.flowers).toList()
    }
}