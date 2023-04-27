fun names(namesList: List<String>): List<String> {
    var count = 0
    val nameCount = mutableListOf<String>()
    val nameSet = namesList.toSet()
    for (name in nameSet) {
        count = 0
        for (i in namesList.indices) {
            if (namesList[i] == name) {
                count += 1
            }
        }
        nameCount.add("The name $name is repeated $count times")
    }
    return nameCount
}
