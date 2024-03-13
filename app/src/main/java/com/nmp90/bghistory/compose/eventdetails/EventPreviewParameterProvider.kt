package com.nmp90.bghistory.compose.eventdetails

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nmp90.bghistory.events.Event

class EventPreviewParameterProvider : PreviewParameterProvider<Event> {
    override val values: Sequence<Event>
        get() = sequenceOf(
            Event(
                id = "",
                title = "Второ въстание в Северозападна България",
                place = "Нишко",
                leader = "Власич",
                year = "1835/01/01",
                result = "Потушено",
                description = "Избухва въстание и в Нишко. Разбунтувалите се села искат покровителство от сръбския княз, но той предпочел ролята на посредник.",
                topic = 1,
            )
        )

}