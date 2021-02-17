package com.gstuart.rulebook.documentModels

data class RulebookModel (
    val year : String,
    val federation : String,
    val sections : List<Section>
)

data class Section (
    val number : String,
    val title : String,
    val rules : List<Rule>
)

data class Rule (
    val number : String,
    val title : String,
    val text : String,
    val sub_rules : List<SubRule>
)

data class SubRule (
    val number : String,
    val text : String,
    val sub_sub_rules : List<SubSubRule>
)

data class SubSubRule (
    val number : String,
    val text : String
)

