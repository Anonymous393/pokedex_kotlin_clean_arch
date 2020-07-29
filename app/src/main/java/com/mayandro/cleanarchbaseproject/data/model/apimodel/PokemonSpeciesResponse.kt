import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse (

	@SerializedName("base_happiness") val base_happiness : Int,
	@SerializedName("capture_rate") val capture_rate : Int,
	@SerializedName("egg_groups") val egg_groups : List<EggGroups>,
	@SerializedName("evolution_chain") val evolution_chain : EvolutionChain,
	@SerializedName("evolves_from_species") val evolves_from_species : String,
	@SerializedName("flavor_text_entries") val flavor_text_entries : List<FlavorTextEntries>,
	@SerializedName("form_descriptions") val form_descriptions : List<String>,
	@SerializedName("forms_switchable") val forms_switchable : Boolean,
	@SerializedName("gender_rate") val gender_rate : Int,
	@SerializedName("genera") val genera : List<Genera>,
	@SerializedName("habitat") val habitat : Habitat,
	@SerializedName("has_gender_differences") val has_gender_differences : Boolean,
	@SerializedName("id") val id : Int,
	@SerializedName("is_baby") val is_baby : Boolean,
	@SerializedName("name") val name : String,
	@SerializedName("order") val order : Int
)

data class EvolutionChain (
	@SerializedName("url") val url : String
)

data class EggGroups (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)

data class Genera (
	@SerializedName("genus") val genus : String,
	@SerializedName("language") val language : Language
)

data class Language (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)

data class Habitat (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)

data class FlavorTextEntries (
	@SerializedName("flavor_text") val flavor_text : String,
	@SerializedName("language") val language : Language,
	@SerializedName("version") val version : Version
)

data class Version (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)