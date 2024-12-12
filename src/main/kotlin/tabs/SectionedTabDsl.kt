package tabs

import Section
import SubSection
import SubSubSection
import common.toAnchorId
import kotlinx.html.FlowContent
import kotlinx.html.HtmlBlockTag
import kotlinx.html.div

@DslMarker
annotation class TabTagMarker

fun tab(title: String, renderer: FlowContent.() -> Unit): Tab {
    return object : Tab(title.toAnchorId(), title) {
        override fun renderContentIn(root: HtmlBlockTag) {
            root.div {
                renderer()
            }
        }
    }
}

fun sectionedTab(tabTitle: String, code: SectionedTabDsl.() -> Unit): SectionedTab {
    val dsl = SectionedTabDsl()
    dsl.code()
    return object : SectionedTab(
        tabId = tabTitle.toAnchorId(),
        title = tabTitle,
    ) {
        override val sections: List<Section> = dsl.sections
    }
}

@TabTagMarker
class SectionedTabDsl {
    val sections = mutableListOf<Section>()

    fun section(sectionTitle: String, code: SectionDsl.() -> Unit) {
        val dsl = SectionDsl()
        dsl.code()
        sections += Section(
            id = sectionTitle.toAnchorId(),
            title = sectionTitle,
            renderer = dsl.content,
            subSections = dsl.subSections,
        )
    }
}

@TabTagMarker
class SectionDsl {
    var content: FlowContent.() -> Unit = {}
    val subSections = mutableListOf<SubSection>()

    fun content(code: FlowContent.() -> Unit) {
        content = code
    }

    fun subSection(subSectionTitle: String, showInToc: Boolean = true, code: SubSectionDsl.() -> Unit) {
        val dsl = SubSectionDsl()
        dsl.code()
        subSections += SubSection(
            id = subSectionTitle.toAnchorId(),
            title = subSectionTitle,
            subSubSections = dsl.subSubSections,
            showInToc = showInToc,
            renderer = dsl.content,
        )
    }
}

@TabTagMarker
class SubSectionDsl {
    var content: FlowContent.() -> Unit = {}
    val subSubSections = mutableListOf<SubSubSection>()

    fun content(code: FlowContent.() -> Unit) {
        content = code
    }
    fun subSubSection(subSubSectionTitle: String, code: SubSubSectionDsl.() -> Unit) {
        val dsl = SubSubSectionDsl()
        dsl.code()
        subSubSections += SubSubSection(
            id = subSubSectionTitle.toAnchorId(),
            title = subSubSectionTitle,
            renderer = dsl.content,
        )
    }
}

@TabTagMarker
class SubSubSectionDsl {
    var content: FlowContent.() -> Unit = {}

    fun content(code: FlowContent.() -> Unit) {
        content = code
    }
}
