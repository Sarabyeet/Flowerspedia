package com.sarabyeet.flowerspedia.epoxy

import com.airbnb.epoxy.EpoxyController
import com.sarabyeet.flowerspedia.R
import com.sarabyeet.flowerspedia.databinding.ModelLetterEpoxyBinding

class EpoxyLetterController(
    private val onClickCallback: (String) -> Unit
) : EpoxyController() {
    private val lettersList = ('A'..'Z').toList()

    override fun buildModels() {
        lettersList.forEachIndexed { index, letter ->
            LetterEpoxyModel(letter, onClickCallback).id("$index").addTo(this)
        }
    }

    data class LetterEpoxyModel(
        val letter: Char,
        val onClick: (String) -> Unit
    ) : ViewBindingKotlinModel<ModelLetterEpoxyBinding>(R.layout.model_letter_epoxy) {
        override fun ModelLetterEpoxyBinding.bind() {
            mainButton.text = letter.toString()
            mainButton.setOnClickListener {
                onClick(letter.toString())
            }
        }
    }
}

