package com.example.cf_sdk.changebankapi.model.google

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PlaceAutocompleteSerializer(predictions: List<Prediction>?) {
    @SerializedName("status")
    @Expose
    var status = ""

    @SerializedName("predictions")
    @Expose
    var predictions: List<Prediction>? = null

    init {
        this.predictions = predictions
    }

    inner class MatchedSubstring {
        @SerializedName("length")
        @Expose
        var length = 0

        @SerializedName("offset")
        @Expose
        var offset = 0
    }

    class Prediction(
        placeId: String,
        description: String,
        structuredFormatting: StructuredFormatting?
    ) {
        @SerializedName("description")
        @Expose
        var description = ""

        @SerializedName("map_id")
        @Expose
        var id = ""

        @SerializedName("matched_substrings")
        @Expose
        var matchedSubstrings: List<MatchedSubstring>? = null

        @SerializedName("place_id")
        @Expose
        var placeId = ""

        @SerializedName("reference")
        @Expose
        var reference = ""

        @SerializedName("terms")
        @Expose
        var terms: List<Term>? = null

        @SerializedName("types")
        @Expose
        var types: List<String>? = null

        @SerializedName("structured_formatting")
        @Expose
        var structuredFormatting: StructuredFormatting? = null

        init {
            this.placeId = placeId
            this.description = description
            this.structuredFormatting = structuredFormatting
        }

        companion object {
            fun create(
                placeId: String,
                description: String,
                structuredFormatting: StructuredFormatting?
            ): Prediction {
                return Prediction(placeId, description, structuredFormatting)
            }
        }
    }

    inner class Term {
        @SerializedName("offset")
        @Expose
        var offset = 0

        @SerializedName("value")
        @Expose
        var value = ""
    }

    class StructuredFormatting private constructor(addressLine1: String) {
        @SerializedName("main_text")
        @Expose
        var addressLine1 = ""

        init {
            this.addressLine1 = addressLine1
        }

        companion object {
            fun create(addressLine1: String): StructuredFormatting {
                return StructuredFormatting(addressLine1)
            }
        }
    }

    companion object {
        fun create(predictions: List<Prediction>?): PlaceAutocompleteSerializer {
            return PlaceAutocompleteSerializer(predictions)
        }
    }
}