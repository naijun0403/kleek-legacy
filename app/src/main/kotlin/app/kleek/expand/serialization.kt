package app.kleek.expand

import com.akuleshov7.ktoml.Toml
import com.akuleshov7.ktoml.TomlIndentation
import com.akuleshov7.ktoml.TomlInputConfig
import com.akuleshov7.ktoml.TomlOutputConfig
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val DefaultJson = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    explicitNulls = false
}

val DefaultToml = Toml(
    inputConfig = TomlInputConfig(
        ignoreUnknownNames = false,
        allowEmptyValues = true,
        allowNullValues = true,
        allowEscapedQuotesInLiteralStrings = true,
        allowEmptyToml = true,
    ),
    outputConfig = TomlOutputConfig(
        indentation = TomlIndentation.FOUR_SPACES,
    )
)