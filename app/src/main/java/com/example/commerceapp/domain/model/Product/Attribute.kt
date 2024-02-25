package com.example.commerceapp.domain.model.Product

import java.lang.StackWalker.Option

data class Attribute(
    val id:String,
    val name:String,
    val values:List<Option>
)
