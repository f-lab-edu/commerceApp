package com.example.commerceapp.domain.model.payment

import com.example.commerceapp.data.remote.model.payment.BankData
import com.example.commerceapp.data.remote.model.payment.CardData
import com.example.commerceapp.data.remote.model.payment.Metadata
import com.example.commerceapp.data.remote.model.payment.PhoneData
import com.example.commerceapp.data.remote.model.payment.VbankData

data class PaymentData(
    val bankData: BankData,
    val cancelledAt: String,
    val cancelledPrice: Int,
    val cancelledTaxFree: Int,
    val cardData: CardData,
    val companyName: String,
    val currency: String,
    val escrowStatus: Int,
    val escrowStatusLocale: String,
    val gatewayUrl: String,
    val metadata: Metadata,
    val method: String,
    val methodOrigin: String,
    val methodOriginSymbol: String,
    val methodSymbol: String,
    val orderId: String,
    val orderName: String,
    val pg: String,
    val phoneData: PhoneData,
    val price: Int,
    val purchasedAt: String,
    val receiptId: String,
    val receiptUrl: String,
    val requestedAt: String,
    val sandbox: Boolean,
    val status: Int,
    val statusLocale: String,
    val taxFree: Int,
    val vbankData: VbankData
)