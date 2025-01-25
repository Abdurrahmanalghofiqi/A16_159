package com.umy.pam_api.ui.navigasi

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHome: DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home awal"
}

object DestinasiEntry: DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Aset"
}

object DestinasiMain: DestinasiNavigasi{
    override val route  = "homeapp"
    override val titleRes = "Home"
}

object DestinasiHomeAset: DestinasiNavigasi {
    override val route ="home aset"
    override val titleRes = "Home aset"
}


