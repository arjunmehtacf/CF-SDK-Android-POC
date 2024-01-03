package com.example.sdk_no_dagger.changebankapi.model.google

import com.example.cf_sdk.changebankapi.parameter.google.PlaceDetailParameters
import com.example.cf_sdk.changebankapi.parameter.member.AddAddressParameters
import com.google.gson.annotations.SerializedName
import java.util.Arrays

/**
 * Created by gunveernatt on 9/7/17.
 *
 * retrieve address selected.
 */
class PlaceDetailSerializer(place: Place?) {
    @SerializedName("result")
    var place: Place? = null

    @SerializedName("status")
    private val status = ""

    init {
        this.place = place
    }

    fun convertToAddAddressParameters(parameters: PlaceDetailParameters): AddAddressParameters {
        val headers = parameters.headers
        val street = parameters.addressLine1
        val unit = parameters.unit
        var zipCode: String? = null
        var city: String? = null
        var state: String? = null
        var country: String? = null
        for (component in place!!.addressComponents) {
            if (Arrays.asList(*component.types).contains("locality")) {
                city = component.long_name
            } else if (Arrays.asList<String>(*component.types)
                    .contains("administrative_area_level_1")
            ) {
                state = component.short_name
            } else if (Arrays.asList<String>(*component.types).contains("country")) {
                country = component.short_name
            } else if (Arrays.asList<String>(*component.types).contains("postal_code")) {
                zipCode = component.long_name
            }
        }
        return AddAddressParameters(headers, street, unit, zipCode, city, state, country, "USA")
    }

    inner class Geometry {
        inner class Location {
            var lat = 0.0
            var lng = 0.0
        }

        var location: Location? = null
    }

    class AddressComponent(
        @field:SerializedName("types") var types: Array<String>,
        shortName: String,
        longName: String
    ) {
        @SerializedName("long_name")
        var long_name = ""

        @SerializedName("short_name")
        var short_name = ""

        init {
            short_name = shortName
            long_name = longName
        }

        companion object {
            fun create(
                types: Array<String>,
                shortName: String,
                longName: String
            ): AddressComponent {
                return AddressComponent(types, shortName, longName)
            }
        }
    }

    inner class Photo {
        var height = 0
        var width = 0
        var photo_reference: String? = null
    }

    class Place(
        @field:SerializedName("place_id") val iD: String,
        @field:SerializedName("name") private val mName: String,
        @field:SerializedName("address_components") var addressComponents: List<AddressComponent>
    ) {

        @SerializedName("formatted_address")
        val address: String? = null

        @SerializedName("international_phone_number")
        private val mPhoneNumber: String? = null

        @SerializedName("photos")
        private val mPhotos: List<Photo>? = null

        @SerializedName("geometry")
        private val mGeometry: Geometry? = null

        @SerializedName("icon")
        private val mIcon: String? = null

        @SerializedName("rating")
        var mRating: Double? = null

        @get:SerializedName("types")
        private val mTypes: Array<String>
            get() {
                TODO()
            }

        @SerializedName("url")
        private val mUrl: String? = null

        @SerializedName("vicinity")
        private val mVicinity: String? = null

        @SerializedName("website")
        private val mWebsite: String? = null
        fun getmName(): String {
            return mName
        }

        override fun toString(): String {
            return "Place{" +
                    "mName='" + mName + '\'' +
                    '}'
        }

        companion object {
            fun create(
                placeId: String,
                name: String,
                addressComponents: List<AddressComponent>
            ): Place {
                return Place(placeId, name, addressComponents)
            }
        }
    }

    companion object {
        fun create(place: Place?): PlaceDetailSerializer {
            return PlaceDetailSerializer(place)
        }
    }
}