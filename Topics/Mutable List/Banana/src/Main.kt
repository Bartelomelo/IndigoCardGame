fun solution(strings: MutableList<String>, str: String): MutableList<String> {
    // put your code here
    for (i in strings.indices) {
        if (strings[i] == str) {
            strings[i] = "Banana"
        }
    }
    return strings
}