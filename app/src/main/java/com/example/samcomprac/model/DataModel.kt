package com.example.samcomprac.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "DataModel")
data class DataModel(

    @PrimaryKey(autoGenerate = true) var localId: Int,
    @SerializedName("Id") var Id: Int? = null,
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("ShortDescription") var ShortDescription: String? = null,
    @SerializedName("FullDescription") var FullDescription: String? = null,
    @SerializedName("Sku") var Sku: String? = null,
    @SerializedName("Gtin") var Gtin: String? = null,
    @SerializedName("IsGiftCard") var IsGiftCard: Boolean? = null,
    @SerializedName("TaxRate") var TaxRate: String? = null,
    @SerializedName("ManageInventoryMethodId") var ManageInventoryMethodId: String? = null,
    @SerializedName("DisplayStockAvailability") var DisplayStockAvailability: Boolean? = null,
    @SerializedName("DisplayStockQuantity") var DisplayStockQuantity: Boolean? = null,
    @SerializedName("StockQuantity") var StockQuantity: String? = null,
    @SerializedName("Price") var Price: String? = null,
    @SerializedName("CustomerEntersPrice") var CustomerEntersPrice: Boolean? = null,
    @SerializedName("MinimumCustomerEnteredPrice") var MinimumCustomerEnteredPrice: String? = null,
    @SerializedName("MaximumCustomerEnteredPrice") var MaximumCustomerEnteredPrice: String? = null,
   @SerializedName("ProductCategories") var ProductCategories: String? = null,
    @SerializedName("Published") var Published: Boolean? = null,
    @SerializedName("DisplayOrder") var DisplayOrder: String? = null,
    @SerializedName("ShowOnWebsite") var ShowOnWebsite: Boolean? = null,
    @SerializedName("ShowOnKiosk") var ShowOnKiosk: Boolean? = null,
    @SerializedName("ShowOnPosWeb") var ShowOnPosWeb: Boolean? = null,
    @SerializedName("ShowOnPosMobile") var ShowOnPosMobile: Boolean? = null,
    @SerializedName("DiscountAmount") var DiscountAmount: String? = null,
    @SerializedName("DiscountPercentage") var DiscountPercentage: String? = null,
    @SerializedName("UsePercentage") var UsePercentage: Boolean? = null
) : Serializable
