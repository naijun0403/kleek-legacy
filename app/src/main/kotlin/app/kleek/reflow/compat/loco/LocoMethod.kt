package app.kleek.reflow.compat.loco

data class LocoMethod(
    val bytes: ByteArray,
    val name: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocoMethod

        if (!bytes.contentEquals(other.bytes)) return false
        return name == other.name
    }

    override fun hashCode(): Int {
        var result = bytes.contentHashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
