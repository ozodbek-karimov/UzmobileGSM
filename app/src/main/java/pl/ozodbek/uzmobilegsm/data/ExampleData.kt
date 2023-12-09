import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExampleData(
    var package_name: String,
    var package_payment: String,
    var package_about: String,
): Parcelable
