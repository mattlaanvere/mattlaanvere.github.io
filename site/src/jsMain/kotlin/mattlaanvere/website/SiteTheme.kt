package mattlaanvere.website

import androidx.compose.runtime.compositionLocalOf
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color

// Which section of the site we're in — drives color theming in layouts and widgets
sealed interface SiteSection {
    data object Software : SiteSection
    data object Music : SiteSection
}

val LocalSiteSection = compositionLocalOf<SiteSection> { SiteSection.Software }

// Software palette — cool blue/teal
object SoftwareColors {
    val accent = Color.rgb(0x4A9EFF)
    val accentDark = Color.rgb(0x1A6ECC)
    val accentSubtle = Color.rgb(0xE8F2FF)
}

// Music palette — warm purple/amber
object MusicColors {
    val accent = Color.rgb(0xAB5CF7)
    val accentDark = Color.rgb(0x7B2CC4)
    val accentSubtle = Color.rgb(0xF3E8FF)
}

/**
 * The general site palette (near-background, brand colors) used by shared components
 * like the nav, footer, and cards.
 */
class SitePalette(
    val nearBackground: Color,
    val cobweb: Color,
    val brand: Brand,
) {
    class Brand(
        val primary: Color = Color.rgb(0x3C83EF),
        val accent: Color = Color.rgb(0xF3DB5B),
    )
}

object SitePalettes {
    val light = SitePalette(
        nearBackground = Color.rgb(0xF4F6FA),
        cobweb = Colors.LightGray,
        brand = SitePalette.Brand(
            primary = Color.rgb(0x3C83EF),
            accent = Color.rgb(0xFCBA03),
        )
    )
    val dark = SitePalette(
        nearBackground = Color.rgb(0x13171F),
        cobweb = Colors.LightGray.inverted(),
        brand = SitePalette.Brand(
            primary = Color.rgb(0x3C83EF),
            accent = Color.rgb(0xF3DB5B),
        )
    )
}

fun ColorMode.toSitePalette(): SitePalette = when (this) {
    ColorMode.LIGHT -> SitePalettes.light
    ColorMode.DARK -> SitePalettes.dark
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = Color.rgb(0xFAFAFA)
    ctx.theme.palettes.light.color = Colors.Black
    ctx.theme.palettes.dark.background = Color.rgb(0x06080B)
    ctx.theme.palettes.dark.color = Colors.White
}
