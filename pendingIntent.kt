PendingIntent.getActivity(
    context,
    resultCode,
    Intent(Intent.ACTION_SEND).apply {
        data = Uri.parse("smsto:$destinationPhoneNumber")   // - remove this line when sending image files
        putExtra(
            "sms_body",
            "some message"
        )

       // + add this code when sending image files
       putExtra("address", destinationPhoneNumber)
       putExtra(Intent.EXTRA_STREAM, imageUri)
       type = "image/*"
       `package` = "com.samsung.android.messaging"  // package name
    },
    PendingIntent.FLAG_UPDATE_CURRENT
)
