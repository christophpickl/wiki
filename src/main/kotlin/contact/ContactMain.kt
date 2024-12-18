package contact

import Wiki
import common.Ref

object ContactTabs // via extensions

object ContactWiki : Wiki() {
    override val titleText = "Contact Improvisation Wiki"
    override val tabs = listOf(
        ContactTabs.general,
        ContactTabs.animalCrossings,
    )
    override val refIds = ContactRef.entries.map { it.id }
}

enum class ContactRef(
    override val label: String,
    override val id: String,
) : Ref {
    AnimalHopOn("Hop On", "animal_crossings/hop_on"),
    AnimalHopOff("Hop Off", "animal_crossings/hop_off"),
}
