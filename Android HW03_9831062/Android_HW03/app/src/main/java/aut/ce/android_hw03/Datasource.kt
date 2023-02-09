package values

import aut.ce.android_hw03.BookInfo
import aut.ce.android_hw03.R

class Datasource {
    fun loadBookInfo(): List<BookInfo> {
        return listOf<BookInfo>(
            BookInfo(R.string.title1, R.string.author1, R.string.summary1, R.string.date1, R.drawable.jeremy),
            BookInfo(R.string.title2, R.string.author2, R.string.summary2, R.string.date2, R.drawable.davinci_code),
            BookInfo(R.string.title3, R.string.author3, R.string.summary3, R.string.date3, R.drawable.explorers_on_the_moon),
            BookInfo(R.string.title4, R.string.author4, R.string.summary4, R.string.date4, R.drawable.no_longer_human),
            BookInfo(R.string.title5, R.string.author5, R.string.summary5, R.string.date5, R.drawable.os),
            BookInfo(R.string.title6, R.string.author6, R.string.summary6, R.string.date6, R.drawable.prisoners_of_the_sun),
            BookInfo(R.string.title7, R.string.author7, R.string.summary7, R.string.date7, R.drawable.uncommon_type)
        )
    }
}