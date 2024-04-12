/**
 * return uri using filePath
 * ex)
 *     val path = "/storage/emulated/0/DCIM/Camera/test.jpg"
 *     val uri = getUriFromPath(context, path)
 */

fun getUriFromPath(context : Context, filePath: String): Uri? {
    val cursor: Cursor = context.contentResolver.query(
        Media.EXTERNAL_CONTENT_URI,
        null, "_data = '$filePath'", null, null
    ) ?: return null

    if(cursor.columnCount == 0 ||
        cursor.count == 0)
        return null

    cursor.moveToNext()
    val id = try {
          cursor.getInt(cursor.getColumnIndex("_id"))
        }catch (e: Exception){
            Timber.e(e)
            null
        }finally {
            cursor.close()
        } ?: return null

    return ContentUris.withAppendedId(
        Media.EXTERNAL_CONTENT_URI,
        id.toLong())
}
