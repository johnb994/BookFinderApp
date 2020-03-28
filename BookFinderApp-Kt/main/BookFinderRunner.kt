fun main(args: Array<String>) {
    val bookFinderInstance = BookFinder()
    var input = readLine()

    if (input.isNullOrBlank()) {
        throw IllegalArgumentException("Search query is empty!")
    } else {
        bookFinderInstance.searchQuery = input
    }
}
