
public class BookFinder constructor()

    var searchQuery: String = ""
        get() = field
        set(value) {
            field = if (!(value.isNullOrBlank())) value else 
        }
}