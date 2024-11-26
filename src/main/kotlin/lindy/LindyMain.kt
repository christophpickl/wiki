package lindy

import Section
import tabs.SectionedTab
import SubSection
import tabs.Tab
import Wiki
import kotlinx.html.*
import tabs.sectionedTab
import youtube

object LindyWiki : Wiki() {
    override val titleText = "Lindy Hop Wiki"
    override val tabs = listOf(
        sectionedTab("Introduction") {
            section("Resources") {
                subSection("Music") {
                    render {
                        p { +"Some music list" }
                    }
                }
            }
        },
        sectionedTab("Beginner") {
            section("General Advice") {
                subSection("Butterfly") {
                    render {
                        p { +"Some description" }
                        youtube(id = "7e2SjRIfD4U?si=R4WWwPD0DGrYyjdX", caption = "some caption")
                    }
                }
            }
            section("Techniques") {
                render {
                    p { +"Some notes" }
                }
            }
        }
    )
}
