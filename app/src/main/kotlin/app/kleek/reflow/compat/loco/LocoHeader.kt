package app.kleek.reflow.compat.loco

data class LocoHeader(
    val packetId: Int,
    val status: Short,
    val method: LocoMethod,
)
