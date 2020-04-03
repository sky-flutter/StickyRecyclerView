package com.example.stickyrecyclerview.model

import androidx.lifecycle.MutableLiveData

class Product {
    var strName = "Index 1"
    var isSelected = MutableLiveData<Boolean>()
}