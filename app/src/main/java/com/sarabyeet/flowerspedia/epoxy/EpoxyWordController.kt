package com.sarabyeet.flowerspedia.epoxy

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.sarabyeet.flowerspedia.R
import com.sarabyeet.flowerspedia.databinding.ModelWordEpoxyBinding

class EpoxyWordController(
    private val flowersList: List<String>,
    private val letterId: String,
    private val context: Context ) : EpoxyController() {

    lateinit var onClickCallBack: (String) -> Unit

    override fun buildModels() {
        val words = flowersList
            .filter { it.startsWith(letterId, ignoreCase = true)}
            .shuffled()
            .take(6)
            .sorted()

        words.forEachIndexed { index, flowerName ->
            EpoxyWordModel(flowerName, context,onClickCallBack).id("$index").addTo(this)
        }
    }
}

data class EpoxyWordModel(
    val flower: String,
    val context: Context,
    val onClick: (String) -> Unit
) : ViewBindingKotlinModel<ModelWordEpoxyBinding>(R.layout.model_word_epoxy) {
    override fun ModelWordEpoxyBinding.bind() {
        mainButton.text = flower
        mainButton.setOnClickListener {
            onClick.invoke(flower)
        }
    }
}