package com.vhuthu.until

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FarmerResponse(
    @SerialName("data") val data: Data,
    @SerialName("message") val message: String,
    @SerialName("status") val status: Int
)

@Serializable
data class Data(
    @SerialName("id") val id: Int,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("email") val email: String,
    @SerialName("cellNumber") val cellNumber: String,
    @SerialName("idNumber") val idNumber: String,
    @SerialName("farms") val farms: List<Farm>
)

@Serializable
data class Farm(
    @SerialName("id") val id: Int,
    @SerialName("numberOfEmployees") val numberOfEmployees: Int,
    @SerialName("farmName") val farmName: String,
    @SerialName("farmAddress") val farmAddress: String,
    @SerialName("yearsActive") val yearsActive: Int,
    @SerialName("address") val address: String,
    @SerialName("farmingReason") val farmingReason: String,
    @SerialName("crops") val crops: List<Any>, // You can replace "Any" with a specific type
    @SerialName("plots") val plots: List<Any>, // You can replace "Any" with a specific type
    @SerialName("assets") val assets: List<Any>, // You can replace "Any" with a specific type
    @SerialName("incomeStatements") val incomeStatements: List<IncomeStatement> // You can replace "Any" with a specific type
)

data class IncomeStatement(
    val id: Int,
    val incomeStatementItems: List<IncomeStatementItem>,
    val netIncome: Double,
    val statementDate: String,
    val totalExpenses: Double,
    val totalIncome: Double
)

data class IncomeStatementItem(
    val amount: Double,
    val category: String,
    val date: String,
    val description: String,
    val id: Int,
    val proofOfReceipt: String
)