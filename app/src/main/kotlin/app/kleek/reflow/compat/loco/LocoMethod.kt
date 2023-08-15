package app.kleek.reflow.compat.loco

data class LocoMethod(
    val methodBytes: ByteArray,
    val methodName: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocoMethod

        if (!methodBytes.contentEquals(other.methodBytes)) return false
        return methodName == other.methodName
    }

    override fun hashCode(): Int {
        var result = methodBytes.contentHashCode()
        result = 31 * result + methodName.hashCode()
        return result
    }
}
