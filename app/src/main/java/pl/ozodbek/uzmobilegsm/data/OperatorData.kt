package pl.ozodbek.uzmobilegsm.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OperatorData(val region: String, val phoneNumber: String) : Parcelable
