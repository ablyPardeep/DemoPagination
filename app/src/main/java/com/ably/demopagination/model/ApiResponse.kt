package com.ably.demopagination.model

class ApiResponse : ArrayList<ApiResponseItem>()

data class ApiResponseItem(
    val allow_forking: Boolean,
    val full_name: String,
    val id: Int,
    val name: String,

)



