package com.example.thenewyorktime.datasource.api.exception
import com.google.gson.annotations.SerializedName
import java.lang.Exception


data class ApiException(
    @SerializedName("fault")
    val fault: Fault
): Throwable()

data class Fault(
    @SerializedName("detail")
    val detail: Detail,
    @SerializedName("faultstring")
    val faultString: String
)

data class Detail(
    @SerializedName("errorcode")
    val errorCode: String
)